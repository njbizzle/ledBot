package frc.robot.ledTools.animation;

import frc.robot.ledTools.finals.LEDColor;
import frc.robot.ledTools.finals.LEDInput;
import frc.robot.ledTools.finals.StripState;

import java.util.HashMap;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.IntSupplier;

public class AnimationState {
  
  private BooleanSupplier m_isActive;

  private Function<LEDInput, LEDColor> m_function;
  private HashMap<String, IntSupplier> m_inputs;

  private int m_resolution;

  public AnimationState(
    Function<LEDInput, LEDColor> function, 
    BooleanSupplier isActive , int resolution
  ) {
    this(function, null, isActive, resolution);
  }

  public AnimationState(
    Function<LEDInput, LEDColor> function,
    HashMap<String, IntSupplier> inputs, 
    BooleanSupplier isActive, int resolution
  ) {
    m_function = function;
    m_inputs = inputs;

    m_isActive = isActive;
    m_resolution = resolution;
  }

  public boolean isActive() {
    return m_isActive.getAsBoolean();
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
