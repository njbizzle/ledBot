// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.IOConstants;
import frc.robot.commands.MotorCommand;
import frc.robot.commands.switchStateCommands.SwitchLEDSubsystemState;
import frc.robot.subsystems.MotorSubsystem;
import frc.robot.subsystems.vision.VisionSubsystem;

import frc.robot.subsystems.leds.LEDSubsystem;
import frc.robot.subsystems.leds.LEDSubsystem.LEDSubsystemState;

public class RobotContainer {
  LEDSubsystem m_ledSubSystem = LEDSubsystem.getInstance();
  VisionSubsystem m_visionSubSystem = VisionSubsystem.getInstance();
  MotorSubsystem m_motorSubsystem = MotorSubsystem.getInstance();

  CommandXboxController m_motorController;
  
  public RobotContainer() {
    m_motorController = new CommandXboxController(IOConstants.kMotorControllerPort);
    configureBindings();
  }

  private void configureBindings() {
    m_motorSubsystem.setDefaultCommand(
      new MotorCommand(m_motorController::getRightY)
    );

    SmartDashboard.putData("SinWaves", new SwitchLEDSubsystemState(LEDSubsystemState.SinWaves));
    SmartDashboard.putData("VisionTarget", new SwitchLEDSubsystemState(LEDSubsystemState.VisionTarget));
    SmartDashboard.putData("BeamBreak", new SwitchLEDSubsystemState(LEDSubsystemState.BeamBreak));
  }

  public Command getAutonomousCommand() {
    return Commands.print("Autonomous Command");
  }
}
