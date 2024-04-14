package frc.robot.ledTools.animation;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.ledTools.Strip;
import frc.robot.ledTools.finals.LEDColor;
import frc.robot.ledTools.finals.StripState;

public abstract class Animation {
  
  public static final double kDefaultSpeed = 10;

  private double m_secPerCycle;
  private int m_targetRes;

  public abstract boolean isActive();
  public abstract LEDColor getLEDColor(double timeProgress, double ledProgress, int targetRes);

  public Animation(double secPerCycle, int targetRes) {
    m_secPerCycle = secPerCycle;
    m_targetRes = targetRes;
  }

  public void setSpeed(double secPerCycle) {
    m_secPerCycle = secPerCycle;
  }

  public void setTargetRes(int targetRes) {
    m_targetRes = targetRes;
  }

  public StripState getStrip() {
    LEDColor[] colors = new LEDColor[m_targetRes];

    for (int ledIndex = 0; ledIndex < m_targetRes; ledIndex++){
      colors[ledIndex] = getLEDColor(
          (Timer.getFPGATimestamp() % m_secPerCycle) / m_secPerCycle,
          (double) ledIndex / (double) m_targetRes,
          m_targetRes
      );
    }
    return new StripState(colors);
  }
}
