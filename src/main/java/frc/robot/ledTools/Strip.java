package frc.robot.ledTools;

import com.ctre.phoenix.led.CANdle;

import frc.robot.ledTools.finals.LEDColor;
import frc.robot.ledTools.finals.StripConstraints;
import frc.robot.ledTools.finals.StripState;

public class Strip {
  
  private StripConstraints m_constraints = new StripConstraints(0, 10);

  private CANdle m_candle;

  public Strip(CANdle candle, int startIndex, int length) {
    this(candle, new StripConstraints(startIndex, length));
  }

  public Strip(CANdle m_candle, StripConstraints constraints) {
    m_constraints = constraints;
  }

  public void update(StripState raw) {
    int ledIndex = m_constraints.getStartIndex();

    for (LEDColor color : raw.getColors()){
      m_candle.setLEDs(
        color.r, color.g, color.b, 
        0, ledIndex, 1
      );
      ledIndex++;
    }
  }

}
