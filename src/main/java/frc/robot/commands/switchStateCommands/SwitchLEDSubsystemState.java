package frc.robot.commands.switchStateCommands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.leds.LEDSubsystem;
import frc.robot.subsystems.leds.LEDSubsystem.LEDSubsystemState;

public class SwitchLEDSubsystemState extends Command {

  frc.robot.subsystems.leds.LEDSubsystem m_ledSubSystem = LEDSubsystem.getInstance();
  LEDSubsystemState m_ledSubsystemState;

  public SwitchLEDSubsystemState(LEDSubsystemState state) {
    m_ledSubsystemState = state;
  }

  @Override
  public void initialize() {
    m_ledSubSystem.setLEDSubsystemState(m_ledSubsystemState);
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}