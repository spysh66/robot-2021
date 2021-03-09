package frc.robot.Autonomous;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Mapping.Constants;

public class AutoRev extends CommandBase {

    public double time;
    public double time2;
    public double top;
    public double bottom;
    public static double shooterSpeedFar = 800;


    public AutoRev(double timeRun, double topSpeed, double bottomSpeed) {
    


    
        top = topSpeed;
        bottom = bottomSpeed;
        time2 = timeRun;
    }

   




    public void initialize(){
     //   new WaitCommand(time)
     time = Timer.getFPGATimestamp();
    }

   

    public void execute() {
        Constants.shooterTop.set(ControlMode.PercentOutput, top); //0.35
        Constants.shooterBottom.set(ControlMode.PercentOutput, bottom); //0.30
    

    }
    // @Override
    public boolean isFinished() {

        if(Timer.getFPGATimestamp() >= time + time2){
            // Constants.shooterTop.set(ControlMode.PercentOutput, 0.0);
            // Constants.shooterBottom.set(ControlMode.PercentOutput, 0.0);
            return true;
            
        }
        return false;
    }


    

}