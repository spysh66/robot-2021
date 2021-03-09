package frc.robot.Commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Drivetrain.Drive;

public class HalfSpeed extends CommandBase {

  private final Drive m_drive;
  private final DoubleSupplier m_forward;
  private final DoubleSupplier m_rotation;
  // private final DoubleSupplier m_scale;

  private DoubleSupplier m_scale;

  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param forward   The control input for driving forwards/backwards
   * @param rotation  The control input for turning
   */
  public HalfSpeed(Drive subsystem, DoubleSupplier forward, DoubleSupplier rotation, DoubleSupplier scale) {
    m_drive = subsystem;
    m_forward = forward;
    m_rotation = rotation;
    m_scale = scale;

    addRequirements(m_drive);
  }

  @Override
  public void execute() {

    m_drive.arcadeDrive(
        m_forward.getAsDouble() - (Math.signum(m_forward.getAsDouble()) * (m_scale.getAsDouble() * 0.2)),
        m_rotation.getAsDouble() - (Math.signum(m_rotation.getAsDouble()) * (m_scale.getAsDouble() * 0.2)));

  }

}