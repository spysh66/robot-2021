package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Robot;
import frc.robot.Drivetrain.Drive;

public class Reset extends CommandBase {

    public Reset() {

    }

    public void initialize() {

        System.out.println("Reset");
       // Robot.m_gyro.zeroYaw();
        Drive.resetEncoders();

    }

    public boolean isFinished() {
        return true;
    }

}