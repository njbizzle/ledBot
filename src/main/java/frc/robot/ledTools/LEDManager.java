package frc.robot.ledTools;

import java.util.ArrayList;
import java.util.List;

import frc.robot.ledTools.animation.AnimationState;
import frc.robot.ledTools.finals.StripState;

public class LEDManager {
  private ArrayList<Strip> m_outputStrips;
  private ArrayList<AnimationState> m_states;

  public LEDManager(Strip outputStrip) {
    this(new ArrayList<Strip>(List.of(outputStrip)), new ArrayList<AnimationState>());
  }

  public LEDManager(Strip outputStrip, ArrayList<AnimationState> states) {
    this(new ArrayList<Strip>(List.of(outputStrip)), states);
  }

  public LEDManager(ArrayList<Strip> outputStrips, ArrayList<AnimationState> states) {
    m_outputStrips = outputStrips;
    m_states = states;
  }

  public void addState(AnimationState state){
    addState(new ArrayList<>(List.of(state)));
  }

  public void addState(ArrayList<AnimationState> states) {
    states.forEach(state -> m_states.add(state));
  }

  public void checkStates() {
    for (AnimationState state : m_states){
      if (!state.isActive()) continue;
        StripState stripOut = state.getStrip();

        m_outputStrips.forEach(
          strip -> strip.setStrip(stripOut.scale(strip.resolution))
        );
      
    }
  }

}
