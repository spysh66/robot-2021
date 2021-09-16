package frc.robot.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Drivetrain.Drive;

public class ResetEncoder extends CommandBase {

    public void initialize() {
        Drive.resetEncoders();
     
    }

   

    public boolean isFinished() {
        return true;
    }

  

}