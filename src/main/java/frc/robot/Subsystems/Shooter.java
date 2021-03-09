package frc.robot.Subsystems;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Mapping.Constants;

public class Shooter extends SubsystemBase {

    public static boolean isOn = false;
    public static boolean isOnShoot = false;
    public static boolean isDeployed = false;

    public final static BooleanSupplier isDeployedSupplier = () -> isDeployed;
    public final static BooleanSupplier isOnSupplier = () -> isOn;
    public final static BooleanSupplier isOnShootSupplier = () -> isOnShoot;

    public static double shooterSpeed = 1100;
    public static double offsetClose = 0;
    public static double shooterSpeedFar = 1500;
    public static double offsetFar = 200;

    public final static DoubleSupplier shooterSpeedSupplier = () -> shooterSpeed;

    public void shooterFar() {

        Constants.angleSolenoid.set(true);
        Constants.angleSolenoidExhaust.set(false);
        isDeployed = true;

    }

    public void shooterNear() {

        Constants.angleSolenoid.set(false);
        Constants.angleSolenoidExhaust.set(true);
        isDeployed = false;

    }

    public void shooterRev() {

        isOn = true;
        if (isDeployed == false) {
            //CLOSE

            Constants.shooterTop.set(ControlMode.Velocity, shooterSpeed * 1.5 + offsetClose);
            Constants.shooterBottom.set(ControlMode.Velocity, shooterSpeed);

        }
        else if (isDeployed == true) {
            //FAR

            Constants.shooterTop.set(ControlMode.Velocity, shooterSpeedFar * 1.5);
            Constants.shooterBottom.set(ControlMode.Velocity, shooterSpeedFar + offsetFar);

        } 

    }

    public void shooterStop() {

        Constants.shooterTop.set(ControlMode.PercentOutput, 0);
        Constants.shooterBottom.set(ControlMode.PercentOutput, 0);
        Constants.conveyor.set(ControlMode.PercentOutput, 0.0);
        Constants.collector.set(ControlMode.PercentOutput, 0.0);
        Constants.injector.set(ControlMode.PercentOutput, 0.0);
        isOn = false;
        isOnShoot = false;

    }

    public void shoot() {

        isOn = true;
        if (isDeployed == false) {
            //CLOSE

            Constants.shooterTop.set(ControlMode.Velocity, shooterSpeed * 1.5 + offsetClose);
            Constants.shooterBottom.set(ControlMode.Velocity, shooterSpeed);
            Constants.conveyor.set(ControlMode.PercentOutput, 0.9);
            Constants.collector.set(ControlMode.PercentOutput, -0.9);
            Constants.injector.set(ControlMode.PercentOutput, -0.9);

        } else if (isDeployed == true) {

            //FAR
            Constants.shooterTop.set(ControlMode.Velocity, shooterSpeedFar * 1.5);
            Constants.shooterBottom.set(ControlMode.Velocity, shooterSpeedFar + offsetFar);
            Constants.conveyor.set(ControlMode.PercentOutput, 0.9);
            Constants.collector.set(ControlMode.PercentOutput, -0.9);
            Constants.injector.set(ControlMode.PercentOutput, -0.9);

        }

    }

    public void periodic() {

        SmartDashboard.putBoolean("SHOOTER IS REVED", isOn);
        SmartDashboard.putBoolean("SHOOTER IS HIGH", isDeployed);
        // SmartDashboard.putNumber("CloseShooterSpeed", shooterSpeed);
        // SmartDashboard.putNumber("CloseOffset", offsetClose);
        // SmartDashboard.putNumber("FarShooterSpeed", shooterSpeedFar);
        // SmartDashboard.putNumber("FarOffset", offsetFar);
        // SmartDashboard.getNumber("CloseOffset", offsetClose);
        //System.out.println(offsetClose);


    }

}