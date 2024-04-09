package frc.robot.ledTools.finals;

import java.util.ArrayList;

public final class StripState {

  public final StripConstraints m_constraints;
  public final LEDColor[] m_colors;

  public StripState() {
    this(new LEDColor[m_constraints.getCount()]);
  }


  public StripState(LEDColor[] colors) {
    this(colors, new StripConstraints(0, 1));
  }

  public StripState(LEDColor[] colors, StripConstraints constraints) {
    m_constraints = constraints;
    m_colors = colors;
  }

  public static StripState merge(ArrayList<StripState> states) {
    if (states == null || states.size() <= 0) return null;

    StripState returnState = new StripState();

    for (StripState state : states) {
      returnState = merge(returnState, state);
    }

    return returnState;
  }

  public LEDColor[] getColors() {
    return m_colors;
  }
  public StripConstraints getContraints() {
    return new StripConstraints(m_constraints);
  }

  public StripState rescale(StripConstraints constraints) {
    LEDColor[] colorsOut = new LEDColor[constraints.getCount()];

    double step = (double) constraints.getCount() / (double) m_colors.length;
    double indexDouble = 0;
    int indexInt = 0;

    for (int rescaledIndex = 0; rescaledIndex < colorsOut.length; rescaledIndex++) {
      indexInt = (int) Math.round(indexDouble);

      indexInt = Math.max(indexInt, 0);
      indexInt = Math.min(indexInt, m_colors.length);

      colorsOut[rescaledIndex] = m_colors[indexInt];

      indexDouble += step;
    }

    return new StripState(colorsOut, new StripConstraints(constraints.getStartIndex(), constraints.getCount()));
  }

  public static StripState merge(StripState state1, StripState state2) {

    StripConstraints constraints = 
      (state1.getContraints().getCount() > state2.getContraints().getCount()) ? 
      state1.getContraints() : state2.getContraints();

    state1 = state1.rescale(constraints); // might be totatlly not needed
    state2 = state2.rescale(constraints);

    LEDColor[] colorsOut = new LEDColor[constraints.getCount()];

    for (int ledIndex = constraints.getStartIndex(); ledIndex < constraints.getCount(); ledIndex++) {
      LEDColor color1 = state1.getColors()[ledIndex];
      LEDColor color2 = state2.getColors()[ledIndex];

      if ((color1 == null) && (color2 == null)) colorsOut[ledIndex] = new LEDColor();
      if (color1 == null) colorsOut[ledIndex] = color2;
      if (color2 == null) colorsOut[ledIndex] = color1;
      else colorsOut[ledIndex] = color1.merge(color2);
    }

    return new StripState(colorsOut, constraints);
  }

  public static StripState createSolid(LEDColor color) {
    return new StripState(new LEDColor[] {color});
  }
}
