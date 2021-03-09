package frc.robot.Commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

import frc.robot.Drivetrain.Drive;
import frc.robot.Mapping.Constants;

/**
 * A command that will turn the robot to the specified angle.
 */
public class TurnToAngle extends PIDCommand {

  // TODO: Tune PID for TurnToAngle to work

  /**
   * // * Turns to robot to the specified angle. // * // * @param
   * targetAngleDegrees The angle to turn to // * @param drive The drive subsystem
   * to use //
   */
  public TurnToAngle(double targetAngleDegrees, Drive drive) { // Drive drive
    super(new PIDController(Constants.kTurnP, Constants.kTurnI, Constants.kTurnD),
        // Close loop on heading
        drive::getHeading,
        // Set reference to target
        targetAngleDegrees,
        // Pipe output to turn robot
        output -> drive.arcadeDrive(0, output),
        // Require the drive
        drive);

    // Set the controller to be continuous (because it is an angle controller)
    // getController().enableContinuousInput(-180, 180);
    // Set the controller tolerance - the delta tolerance ensures the robot is
    // stationary at the
    // setpoint before it is considered as having reached the reference
    getController().setTolerance(Constants.kTurnToleranceDeg, Constants.kTurnRateToleranceDegPerS);

  }

  @Override
  public boolean isFinished() {
    // End when the controller is at the reference.
    return getController().atSetpoint();
  }
}