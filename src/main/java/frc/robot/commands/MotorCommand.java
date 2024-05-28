package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MotorSubsystem;

public class MotorCommand extends Command {
  MotorSubsystem m_motorSubsystem = MotorSubsystem.getInstance();

  DoubleSupplier m_motorSupplier;

  public MotorCommand(DoubleSupplier motorSupplier) {
    m_motorSupplier = motorSupplier;

    addRequirements(m_motorSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double value = m_motorSupplier.getAsDouble();
    m_motorSubsystem.setMotorOut(value);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}