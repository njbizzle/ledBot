package frc.robot.ledTools;

import java.util.ArrayList;
import java.util.List;

import frc.robot.ledTools.animation.Animation;
import frc.robot.ledTools.finals.StripState;

public class LEDManager {
  private ArrayList<Strip> m_outputStrips;
  private ArrayList<Animation> m_states;

  public LEDManager(Strip outputStrip) {
    this(new ArrayList<Strip>(List.of(outputStrip)), new ArrayList<Animation>());
  }

  public LEDManager(Strip outputStrip, ArrayList<Animation> states) {
    this(new ArrayList<Strip>(List.of(outputStrip)), states);
  }

  public LEDManager(ArrayList<Strip> outputStrips, ArrayList<Animation> states) {
    m_outputStrips = outputStrips;
    m_states = states;
  }

  public void addState(Animation state){
    addState(new ArrayList<>(List.of(state)));
  }

  public void addState(ArrayList<Animation> states) {
    states.forEach(state -> m_states.add(state));
  }

  public void checkStates() {
    for (Animation state : m_states){
      if (!state.isActive()) continue;
        m_outputStrips.forEach(
          strip -> strip.setStrip(state.getStrip(strip))
        );
      
    }
  }

}
