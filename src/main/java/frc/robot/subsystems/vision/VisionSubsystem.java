package frc.robot.subsystems.vision;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.ArrayList;
import java.util.List;

import frc.robot.Constants.VisionConstants;

public class VisionSubsystem extends SubsystemBase{

  private Camera m_mainCamera = new Camera(VisionConstants.kMainCameraName);

  private static VisionSubsystem m_instance;

  public static VisionSubsystem getInstance() {
    if (m_instance == null)
      m_instance = new VisionSubsystem();
    return m_instance;
  }

  private VisionSubsystem() {}

  private ArrayList<Camera> m_cameras = new ArrayList<Camera>(List.of(
      m_mainCamera
  ));
  

  public ArrayList<Camera> getCameras() {
    return m_cameras;
  }
}
