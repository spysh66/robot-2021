/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;


import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Robot;

import static frc.robot.Mapping.Constants.*;

public class Drive extends SubsystemBase {

    private static Drive mInstance;

    // -------------------------------
    // Encoder constants
    // -------------------------------
    private double limitSpeed = 0;
    private double limitRotate = 0;
    private double scaleSpeed;
    private double scaleRotate;
    private final double moveAccelerationLimit = 0.07;
    private final double rotateAccelerationLimit = 0.08; // Velocity - Tune for different drivetrain, if it's too
                                                         // low/sluggish

    // Encoder Scale Factor (Meter)/(Pulse)
    private final double encoderScale = (1 / ENCODER_EDGES_PER_REV) * WHEEL_DIAMETER * Math.PI;

    // -------------------------------
    // Speed Controllers
    // -------------------------------
    // Define left Speed Controller
    private final SpeedControllerGroup left = new SpeedControllerGroup(frontLeft, backLeft);

    // Define right Speed Controller
    private final SpeedControllerGroup right = new SpeedControllerGroup(frontRight, backRight);

    // -------------------------------
    // Differential Drive
    // -------------------------------
    // Define Differential Drive
    private final DifferentialDrive m_drive = new DifferentialDrive(left, right);

    // -------------------------------
    // Encoder, Gyro, and Odometer
    // -------------------------------

    /**
     * Gets the average distance of the two encoders.
     *
     * @return the average of the two encoder readings
     */
    public double getAverageEncoderDistance() {
        return (getLeftDistance() + getRightDistance()) / 2.0;
    }

    public double getLeftDistance() {
        return (frontLeft.getSelectedSensorPosition(0) * encoderScale);
    }

    public double getRightDistance() {
        return (frontRight.getSelectedSensorPosition(0) * -1 * encoderScale);// * 0.001;
    }

    public static void resetEncoders() {
        frontLeft.setSelectedSensorPosition(0);
        frontRight.setSelectedSensorPosition(0);
    }

    // Odometery class for tracking robot pose
    private final DifferentialDriveOdometry m_odometry;

    public Drive() {

        zeroHeading();
        resetEncoders();
        m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));

    }

    /**
     * Returns the currently-estimated pose of the robot.
     *
     * @return The pose.
     */
    public Pose2d getPose() {
        return m_odometry.getPoseMeters();
    }

    /**
     * Returns the current wheel speeds of the robot.
     *
     * @return The current wheel speeds.
     */
    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(frontLeft.getSelectedSensorVelocity(0),
                frontRight.getSelectedSensorVelocity(0));
    }

    /**
     * Resets the odometry to the specified pose.
     *
     * @param pose The pose to which to set the odometry.
     */
    public void resetOdometry(Pose2d pose) {
        resetEncoders();
        m_odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
    }

    // Arcade Drive, one Joystick controls forwards/backwards, the other controls

    public void arcadeDrive(double moveSpeed, double rotateSpeed) {

        // Limits for speed, using quadratics and max/min
        moveSpeed = deadband(moveSpeed);
        rotateSpeed = deadband(rotateSpeed);

        // Normal is 0.8 move, 0.75 rotate
        scaleSpeed = moveSpeed < 0 ? -0.9 : 0.9;
        scaleRotate = rotateSpeed < 0 ? -0.65 : 0.65;

        moveSpeed *= scaleSpeed * moveSpeed;
        rotateSpeed *= scaleRotate * rotateSpeed;

        // Acceleration Curve, takes the difference of the input value and a limited
        // value
        // Checks to see if it's greater than the limit for acceleration

        // M O V E
        double bSpeed = moveSpeed - limitSpeed;
        if (bSpeed > moveAccelerationLimit) {
            limitSpeed += moveAccelerationLimit;

        } else if (bSpeed < -moveAccelerationLimit) {
            limitSpeed -= moveAccelerationLimit;

        } else if (bSpeed <= moveAccelerationLimit) {
            limitSpeed = moveSpeed;

        }

        // R O T A T E
        double bRotate = rotateSpeed - limitRotate;
        if (bRotate > rotateAccelerationLimit) {
            limitRotate += rotateAccelerationLimit;

        } else if (bRotate < -rotateAccelerationLimit) {
            limitRotate -= rotateAccelerationLimit;

        } else if (bRotate <= rotateAccelerationLimit) {

            limitRotate = rotateSpeed;
        }

        // frontLeft.set(ControlMode.PercentOutput, -limitRotate,
        // DemandType.ArbitraryFeedForward, limitSpeed);
        // frontRight.set(ControlMode.PercentOutput, +limitRotate * 1.028,
        // DemandType.ArbitraryFeedForward, limitSpeed);

        // Constants.frontLeft.set(ControlMode.Velocity, targetVelocity_Units);
        // Constants.frontRight.set(ControlMode.Velocity, targetVelocity_Units);
        m_drive.arcadeDrive(moveSpeed, rotateSpeed);

    }


    // Tank Drive, one Joystick controls the left, one controls the right.
    public void tankDrive(double leftSpeed, double rightSpeed) {

        // Limits for speed, using quadratics and max/min
        leftSpeed = deadband(leftSpeed);
        rightSpeed = deadband(rightSpeed);

        limitSpeed = leftSpeed < 0 ? -0.8 : 0.8;
        limitSpeed = rightSpeed < 0 ? -0.8 : 0.8;

        leftSpeed *= limitSpeed * leftSpeed;
        rightSpeed *= limitSpeed * rightSpeed;

        // Tells the program to run the driveTank
        // differentialDrive.tankDrive(leftSpeed, rightSpeed);
        frontRight.set(ControlMode.PercentOutput, rightSpeed);
        frontLeft.set(ControlMode.PercentOutput, leftSpeed);
    }

    public void tankDriveVolts(double leftVolts, double rightVolts) {
        left.setVoltage(leftVolts);
        right.setVoltage(-rightVolts);
        m_drive.feed();
    }

    /**
     * Sets the max output of the drive. Useful for scaling the drive to drive more
     * slowly.
     *
     * @param maxOutput the maximum output to which the drive will be constrained
     */
    public void setMaxOutput(final double maxOutput) {
        setMaxOutput(maxOutput);
    }

    /**
     * Zeroes the heading of the robot.
     */
    public void zeroHeading() {
        Robot.m_gyro.reset();
    }

    /**
     * Returns the heading of the robot.
     *
     * @return the robot's heading in degrees, from 180 to 180
     */
    public double getHeading() {
        return Math.IEEEremainder(Robot.m_gyro.getAngle(), 360) * (kGyroReversed ? -1.0 : 1.0);
    }

    /**
     * Returns the turn rate of the robot.
     *
     * @return The turn rate of the robot, in degrees per second
     */
    public double getTurnRate() {
        return Robot.m_gyro.getRate() * (kGyroReversed ? -1.0 : 1.0);
    }

    /*
     * 
     * Prevents the robot from moving with a small amount of input
     * 
     */
    private static double deadband(final double input) {
        if (Math.abs(input) < 0.2) {
            return 0;
        } else {
            return input;
        }
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

        // System.out.println(m_gyro.isRotating());
        // System.out.println(Robot.m_gyro.getAngle());
        // System.out.println(getHeading());
        // System.out.println("Left");
        // System.out.println(getLeftDistance());
        // System.out.println("Right");
        // System.out.println(getRightDistance());

        // System.out.println("Sensor Vel:" + frontLeft.getSelectedSensorVelocity());
        // System.out.println("Sensor Pos:" + frontLeft.getSelectedSensorPosition());
        // System.out.println("Out %" + frontLeft.getMotorOutputPercent());
        // System.out.println("Out Of Phase:" + faults.SensorOutOfPhase);
    }

}
