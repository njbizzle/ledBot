package frc.robot.subsystems.vision;

import java.util.ArrayList;
import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

public class Camera {
  
  PhotonCamera m_camera;

  public Camera(String name) {
    m_camera = new PhotonCamera(name);
  }

  public ArrayList<PhotonTrackedTarget> getTargets() {
    PhotonPipelineResult result = m_camera.getLatestResult();

    return new ArrayList<>(result.getTargets());
  }


}
