package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.MotorConstants;
import frc.robot.subsystems.vision.VisionSubsystem;

import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class MotorSubsystem extends SubsystemBase {

  private CANSparkMax m_motor;

  private static MotorSubsystem m_instance;

  public static MotorSubsystem getInstance() {
    if (m_instance == null)
      m_instance = new MotorSubsystem();
    return m_instance;
  }

  private MotorSubsystem() {
    m_motor = new CANSparkMax(MotorConstants.kMotorCanID, MotorType.kBrushless);
  }

  public void setMotorOut(double speed) {
    m_motor.set(speed);
  }

  @Override
  public void periodic() {}
}
