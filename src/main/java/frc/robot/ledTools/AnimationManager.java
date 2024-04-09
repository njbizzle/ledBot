package frc.robot.ledTools;

import java.util.ArrayList;
import java.util.List;

public class AnimationManager {
  private ArrayList<Strip> m_outputStrips;

  public AnimationManager(Strip outputStrip) {
    this(new ArrayList<Strip>(List.of(outputStrip)));
  }

  public AnimationManager(ArrayList<Strip> outputStrips) {
    m_outputStrips = outputStrips;
  }



}
