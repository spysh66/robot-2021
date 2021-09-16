package frc.robot.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Drivetrain.Drive;

public class AutonDrive extends CommandBase {
    public final Drive drive;
    private final double goal, left, right;

    public AutonDrive(final double goalDistance, final double leftSpeed, final double rightSpeed, final Drive drive) {
        this.drive = drive;
        this.goal = goalDistance;
        this.left = leftSpeed;
        this.right = rightSpeed;

        addRequirements(drive);
    }

    public void initialize() {
        Drive.resetEncoders();
     
    }

    public void execute() {
        // double error = drive.getLeftDistance() - drive.getRightDistance();

        // System.out.println("Error");
        // System.out.println(error);
        

        drive.tankDrive(left, right);

    }

    public boolean isFinished() {
        System.out.println(drive.getRightDistance());
        return (Math.abs(drive.getAverageEncoderDistance()) >= goal);
    }

  

}