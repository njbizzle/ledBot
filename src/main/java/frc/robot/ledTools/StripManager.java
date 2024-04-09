package frc.robot.ledTools;

import java.util.ArrayList;
import java.util.List;

public class StripManager {
  private ArrayList<Strip> m_outputStrips;

  public StripManager(Strip outputStrip) {
    this(new ArrayList<Strip>(List.of(outputStrip)));
  }

  public StripManager(ArrayList<Strip> outputStrips) {
    m_outputStrips = outputStrips;
  }



}
