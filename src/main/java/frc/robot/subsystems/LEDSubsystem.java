// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.AbstractMap.SimpleEntry;
import java.util.function.IntSupplier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ctre.phoenix.led.CANdle;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.LEDConstants;
import frc.robot.ledTools.LEDManager;
import frc.robot.ledTools.Strip;
import frc.robot.ledTools.animation.AnimationState;
import frc.robot.ledTools.finals.LEDColor;

public class LEDSubsystem extends SubsystemBase {

  // --- FIELDS AND CONSTRUCTORS ---

  private CANdle m_candle;
  private Strip m_strip;
  private LEDManager m_leds;

  private final int resolution = 10;

  private static LEDSubsystem m_instance;

  public static LEDSubsystem getInstance() {
    if (m_instance == null) m_instance = new LEDSubsystem();
    return m_instance;
  }

  public LEDSubsystem() {
    m_candle = new CANdle(LEDConstants.candleCanID);
    m_strip = new Strip(m_candle, 0, resolution);

    m_leds = new LEDManager(m_strip);

    m_leds.addState(
      new AnimationState(
        input -> {
          return new LEDColor();
        },

        new HashMap<String, IntSupplier>(Map.ofEntries(
          new SimpleEntry<String, IntSupplier>("test", () -> 0)
        )),
        
        () -> true,
        resolution
      )
    );
  }

  // --- GENERIC FUNCTIONS ---

  @Override
  public void periodic() {
    
  }

  // --- PRIVATE FUNCTIONS ---

  // --- PUBLIC FUNCTIONS ---
}
