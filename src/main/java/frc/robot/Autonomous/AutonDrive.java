package frc.robot.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Drivetrain.Drive;

public class AutonDrive extends CommandBase {
    public final Drive drive;
    private final double goal, speed;

    public AutonDrive(final double goalDistance, final double driveSpeed, final Drive drive) {
        this.drive = drive;
        this.goal = goalDistance;
        this.speed = driveSpeed;

        addRequirements(drive);
    }

    public void initialize() {
        Drive.resetEncoders();
     
    }

    public void execute() {
        // double error = drive.getLeftDistance() - drive.getRightDistance();

        // System.out.println("Error");
        // System.out.println(error);
        System.out.println(drive.getRightDistance());

        drive.arcadeDrive(speed, 0);

    }

    public boolean isFinished() {
        return (Math.abs(drive.getAverageEncoderDistance()) >= goal);
    }

  

}