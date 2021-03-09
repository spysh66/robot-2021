package frc.robot.Limelight;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Robot;
import frc.robot.Drivetrain.Drive;
import frc.robot.Limelight.LimelightInterface.LimelightLEDMode;

public class LimelightOn extends CommandBase {

    LimelightInterface limelight = new LimelightInterface();
    boolean on = false;

    public LimelightOn() {

    }

    public void initialize() {

        if(on == false){
        limelight.setLEDMode(LimelightLEDMode.ON);
        on = true;
        }else if (on == true){
            limelight.setLEDMode(LimelightLEDMode.OFF);
        on = false;
        }

    }

    public boolean isFinished() {
        return true;
    }

}