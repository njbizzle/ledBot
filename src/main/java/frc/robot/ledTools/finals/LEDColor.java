package frc.robot.ledTools.finals;

import edu.wpi.first.wpilibj.util.Color;

public class LEDColor extends Color {

  public final int r;
  public final int g;
  public final int b;
  public final int a;

  public LEDColor(){
    this(0, 0, 0, 0);
  }

  public LEDColor(int r, int g, int b){
    this(r, g, b, 255);
  }

  public LEDColor(Color color){
    this(
      (int) (color.red * 255),
      (int) (color.green * 255),
      (int) (color.blue * 255)
    );
  }

  public LEDColor(int r, int g, int b, int a){
    this.r = clamp(r);
    this.g = clamp(g);
    this.b = clamp(b);
    this.a = clamp(a);
  }

  private int clamp(int num){
    return Math.min(Math.max(num, 0), 255);
  }

  public LEDColor merge(LEDColor other) { // overlay other if needed
    double aThis = (double) a / 255d;
    double aOther = (double) other.a / 255d;
    if (aThis + aOther > 255) aOther = aThis - 255;
    
    return new LEDColor(
      (int) (r * aThis) + (int) (other.r * aOther),
      (int) (g * aThis) + (int) (other.g * aOther),
      (int) (b * aThis) + (int) (other.b * aOther),
      a + other.a
    );
  }
}
