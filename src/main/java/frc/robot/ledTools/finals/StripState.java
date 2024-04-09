package frc.robot.ledTools.finals;

import java.util.ArrayList;

public final class StripState {

  public final LEDColor[] colors;

  public StripState() {
    this(new LEDColor[]{new LEDColor()});
  }


  public StripState(int resolution) {
    this(new LEDColor[resolution]);
  }


  public StripState(LEDColor[] colors) {
    if (colors.length != 0){
      for (LEDColor color : colors) {
        if (color == null) color = new LEDColor();
      }
      
      this.colors = colors;
    } 
    else {
      this.colors = new LEDColor[]{new LEDColor()};
    }
  }

  public static StripState merge(ArrayList<StripState> states) {
    StripState returnState = new StripState();

    for (StripState state : states) {
      returnState.merge(state);
    }

    return returnState;
  }

  public StripState scale(int resolution) {
    LEDColor[] colorsOut = new LEDColor[resolution];

    double step = (double) resolution / (double) colors.length;
    double indexDouble = 0;
    int indexInt = 0;

    for (int rescaledIndex = 0; rescaledIndex < colorsOut.length; rescaledIndex++) {
      indexInt = (int) Math.round(indexDouble);

      indexInt = Math.max(indexInt, 0);
      indexInt = Math.min(indexInt, colors.length);

      colorsOut[rescaledIndex] = colors[indexInt];

      indexDouble += step;
    }

    return new StripState(colorsOut);
  }

  public StripState merge(StripState other) {

    int resolution = colors.length;

    other = other.scale(resolution);

    LEDColor[] colorsOut = new LEDColor[resolution];

    for (int ledIndex = 0; ledIndex < resolution; ledIndex++) {
      LEDColor base = colors[ledIndex];
      LEDColor overlay = other.colors[ledIndex];

      if ((base == null) && (overlay == null)) colorsOut[ledIndex] = new LEDColor();
      if (base == null) colorsOut[ledIndex] = overlay;
      if (overlay == null) colorsOut[ledIndex] = base;
      else colorsOut[ledIndex] = base.merge(overlay);
    }

    return new StripState(colorsOut);
  }
}
