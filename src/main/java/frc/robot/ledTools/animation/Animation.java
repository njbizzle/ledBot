package frc.robot.ledTools.animation;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.ledTools.Strip;
import frc.robot.ledTools.finals.LEDColor;
import frc.robot.ledTools.finals.StripState;

public abstract class Animation {
  
  public static final double kDefaultSpeed = 10;

  private double m_secPerCycle;
  private int m_targetRes;
  private BooleanSupplier m_isActive;

  public abstract LEDColor getLEDColor(double timeProgress, double ledProgress, Strip stripOut);

  public Animation(double secPerCycle, int targetRes, BooleanSupplier isActive) {
    m_secPerCycle = secPerCycle;
    m_targetRes = targetRes;
    m_isActive = isActive;
  }

  public void setSpeed(double secPerCycle) {
    m_secPerCycle = secPerCycle;
  }

  public void setTargetRes(int targetRes) {
    m_targetRes = targetRes;
  }

  public boolean isActive() {
    return m_isActive.getAsBoolean();
  }

  public StripState getStrip(Strip stripOut) {
    LEDColor[] colors = new LEDColor[stripOut.resolution];

    for (int ledIndex = 0; ledIndex < m_targetRes; ledIndex++){
      colors[ledIndex] = getLEDColor(
          (Timer.getFPGATimestamp() % m_secPerCycle) / m_secPerCycle,
          (double) ledIndex / (double) stripOut.resolution,
          stripOut
      );
    }
    return new StripState(colors);
  }
}
