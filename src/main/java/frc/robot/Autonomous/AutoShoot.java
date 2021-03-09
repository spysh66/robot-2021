package frc.robot.Autonomous;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Mapping.Constants;

public class AutoShoot extends CommandBase {

    public double time;
    public double time2;
    public double top;
    public double bottom;
    public static double shooterSpeedFar = 800;


    public AutoShoot(double timeRun, double topSpeed, double bottomSpeed) {
    
     

     top = topSpeed;
     bottom = bottomSpeed;
     time2 = timeRun;
    }

  

    

    public void initialize(){
        time = Timer.getFPGATimestamp();
    }

    public void execute(){

        Constants.shooterTop.set(ControlMode.PercentOutput, top);// 0.35
        Constants.shooterBottom.set(ControlMode.PercentOutput, bottom); //0.30
        Constants.conveyor.set(ControlMode.PercentOutput, 0.9);
        Constants.collector.set(ControlMode.PercentOutput, -0.9);
        Constants.injector.set(ControlMode.PercentOutput, -0.9);


    }

    public boolean isFinished() {

        if(Timer.getFPGATimestamp() >= time + time2){
            Constants.shooterTop.set(ControlMode.PercentOutput, 0.0);
            Constants.shooterBottom.set(ControlMode.PercentOutput, 0.0);
            Constants.conveyor.set(ControlMode.PercentOutput, 0.0);
            Constants.collector.set(ControlMode.PercentOutput, 0.0);
            Constants.injector.set(ControlMode.PercentOutput, 0.0);
            return true;
            
        }
        return false;
    }

 

    

}