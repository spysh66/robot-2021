package frc.robot.Commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Limelight.Camera;
import frc.robot.Mapping.Constants;
import frc.robot.Mapping.RobotContainer;

public class Test extends CommandBase {

    public Test() {

    }

    public void initialize() {

        // System.out.println("High");
        // System.out.println(Constants.lightSensorHigh.getValue());
        // System.out.println("Low");
        // System.out.println(Constants.lightSensorLow.getValue());

        // System.out.println(SmartDashboard.getNumber("Time", 0.0));

        System.out.println("Delta");
        System.out.println(Camera.getInstance().getDeltaXAngle());
        
     
        System.out.println("Distance");
        System.out.println(Camera.getInstance().getDistance());
        


    }

    public void execute() {

    }
    public boolean isFinished(){
        return true;

    }

}