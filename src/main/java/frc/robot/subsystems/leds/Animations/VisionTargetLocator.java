package frc.robot.subsystems.leds.Animations;

import java.lang.annotation.Target;
import java.util.ArrayList;

import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.geometry.Transform3d;
import frc.robot.ledTools.Strip;
import frc.robot.ledTools.animation.Animation;
import frc.robot.ledTools.finals.LEDColor;

import frc.robot.subsystems.vision.VisionSubsystem;
import frc.robot.subsystems.vision.Camera;

public class VisionTargetLocator extends Animation{
  VisionSubsystem m_visionSubsystem = VisionSubsystem.getInstance();

  Camera m_camera = m_visionSubsystem.getCameras().get(0);

  private double m_cameraAngle = 0;
  private double m_cameraX = 0;
  private double m_cameraY = 0;

  private double m_yaw = 0;
  private double m_pitch = 0;
  private double m_skew = 0;

  boolean m_hasTarget = false;

  public VisionTargetLocator() {
    super(31);
  }

  @Override
  public LEDColor getLEDColor(double ledProgress, Strip stripOut) {
    if (ledProgress == 0){
      updateCameraInfo();
      // System.out.println("angles updated to : " + m_cameraAngle);
    }

    if (!m_hasTarget)
      return new LEDColor();

    // System.out.println(angle + " " + ledProgress);
    if (Math.abs(m_cameraAngle - ledProgress) < (0.05/m_cameraX)) {
      // return new LEDColor(255, 255, 255);
      return new LEDColor(
        (int) (200 - m_yaw * 255),
        (int) (200 - m_pitch * 255),
        (int) (200 - m_skew * 255)
      );
    }
    return new LEDColor();
  }

  public void updateCameraInfo() {

    ArrayList<PhotonTrackedTarget> targets = m_camera.getTargets();
    m_hasTarget = targets.size() != 0;
    if (!m_hasTarget)
      return;
    
    PhotonTrackedTarget target = targets.get(0);
    System.out.println(target.getYaw());

    Transform3d transform = target.getBestCameraToTarget();
    double x = transform.getX();
    double y = transform.getY();

    m_cameraX = x;
    m_cameraY = y;

    int range = 1;
    m_yaw = (target.getYaw() / range);
    m_pitch = (target.getPitch() / range);
    m_skew = (target.getSkew() / range);

    m_cameraAngle = (Math.atan(y/x) / 4 * Math.PI) + 0.5;
  }
}
