package frc.robot.ledTools.Animation;

import frc.robot.ledTools.finals.LEDColor;
import frc.robot.ledTools.finals.StripState;

import java.util.HashMap;
import java.util.function.Function;
import java.util.function.IntSupplier;

public class AnimationState {
  
  private Function<LEDInput, LEDColor> m_function;
  private HashMap<String, IntSupplier> m_inputs;

  private int m_resolution;

  public AnimationState(Function<LEDInput, LEDColor> function) {
    this(function, null);
  }

  public AnimationState(
    Function<LEDInput, LEDColor> function,
    HashMap<String, IntSupplier> inputs
  ) {
    m_function = function;
    m_inputs = inputs;
  }

  public StripState getStrip() {
    LEDColor[] colors = new LEDColor[m_resolution];

    for (int ledIndex = 0; ledIndex < m_resolution; ledIndex++){
      colors[ledIndex] = m_function.apply(
        new LEDInput(
          (double) ledIndex / (double) m_resolution, m_inputs
        )
      );
    }

    return new StripState(colors);
  }

}
