package frc.robot.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Drivetrain.Drive;

/**
 * A command that will turn the robot to the specified angle.
 */
public class TurnToAngle2 extends CommandBase {
  private final Drive m_drive;
  private final double target, speed;

  /**
   * Creates a new TurnToAngle2.
   * 
   * @param targetDegrees the target degrees to turn to
   * @param moveSpeed     how fast you want it to turn
   * @param subsystem     what subsystem it should require
   */
  public TurnToAngle2(double targetDegrees, double moveSpeed, Drive subsystem) {
    m_drive = subsystem;
    target = targetDegrees;
    speed = moveSpeed;

    addRequirements(m_drive);
  }

  public void initialize() {
    m_drive.zeroHeading();
  }

  @Override
  public void execute() {


    System.out.println(m_drive.getHeading());
    m_drive.arcadeDrive(0, speed);

  }

  public boolean isFinished() {

  

    return(Math.abs(m_drive.getHeading()) >= target);

 
}
}