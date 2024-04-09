package frc.robot.ledTools.animation;

import edu.wpi.first.wpilibj.Timer;

import java.util.HashMap;
import java.util.function.IntSupplier;

public final class LEDInput {
  public final double time;
  public final double ledProgress;
  public HashMap<String, IntSupplier> inputs;


  public LEDInput(int ledIndex) {
    this(ledIndex, null);
  }

  public LEDInput(double ledProgress, HashMap<String, IntSupplier> inputs) {
    time = Timer.getFPGATimestamp();
    this.ledProgress = ledProgress;
    this.inputs = inputs;
  }
}
