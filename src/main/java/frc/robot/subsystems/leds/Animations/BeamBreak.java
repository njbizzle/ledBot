package frc.robot.subsystems.leds.Animations;

import frc.robot.ledTools.Strip;
import frc.robot.ledTools.animation.Animation;
import frc.robot.ledTools.finals.LEDColor;

import java.util.function.DoubleSupplier;

public class BeamBreak extends Animation {

  private DoubleSupplier m_beamBreakSupplier;
  private double m_maxVaule;

  public BeamBreak(DoubleSupplier beamBreakSupplier, double maxValue) {
    super(31);
    m_beamBreakSupplier = beamBreakSupplier;
    m_maxVaule = maxValue;
  }

  @Override
  public LEDColor getLEDColor(double ledProgress, Strip stripOut) {
    double num = m_beamBreakSupplier.getAsDouble();
    double denom = m_maxVaule;

    double beamBreakProgress = num / denom;
    
    return ledProgress <= beamBreakProgress ? new LEDColor(255, 0, 0) : new LEDColor();
  }

}
