package frc.robot.Mapping;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.util.Units;

public final class Constants {

  // === XBOX CHANNELS === //
  // AXES
  public static final int leftXAxisChannel = 0;
  public static final int leftYAxisChannel = 1;
  public static final int leftTriggerChannel = 2;
  public static final int rightTriggerChannel = 3;
  public static final int rightXAxisChannel = 4;
  public static final int rightYAxisChannel = 5;

  // BUTTONS
  public static final int aButtonChannel = 1;
  public static final int bButtonChannel = 2;
  public static final int xButtonChannel = 3;
  public static final int yButtonChannel = 4;

  public static final int leftBumperChannel = 5;
  public static final int rightBumperChannel = 6;

  public static final int backButtonChannel = 7;
  public static final int startButtonChannel = 8;

  public static final int POVU = 0;
  public static final int POVR = 90;
  public static final int POVD = 180;
  public static final int POVL = 270;

  // === MOTORS === //


  public static final int shooterBottomChannel = 5;
  public static final int shooterTopChannel = 6;

  public static final int collectorChannel = 7;
  public static final int injectorChannel = 8;
  public static final int conveyorChannel = 9;

  public static final int TeleoscopeChannel = 10;
  public static final int WinchChannel = 11;
  
  public static final int backLeftDriveMotor = 1;
  public static final int backRightDriveMotor = 2;
  public static final int frontLeftDriveMotor = 3;
  public static final int frontRightDriveMotor = 4;

  public static final WPI_TalonFX frontLeft = new WPI_TalonFX(frontLeftDriveMotor);
  public static final WPI_TalonFX backLeft = new WPI_TalonFX(backLeftDriveMotor);
  public static final WPI_TalonFX frontRight = new WPI_TalonFX(frontRightDriveMotor);
  public static final WPI_TalonFX backRight = new WPI_TalonFX(backRightDriveMotor);

  public static final WPI_VictorSPX collector = new WPI_VictorSPX(collectorChannel);
  public static final WPI_VictorSPX conveyor = new WPI_VictorSPX(conveyorChannel);
  public static final WPI_VictorSPX injector = new WPI_VictorSPX(injectorChannel);

  public static final WPI_TalonFX shooterTop = new WPI_TalonFX(shooterTopChannel);
  public static final WPI_TalonFX shooterBottom = new WPI_TalonFX(shooterBottomChannel);

  public static final WPI_TalonFX winch = new WPI_TalonFX(WinchChannel);
  public static final WPI_TalonSRX teleoscope = new WPI_TalonSRX(TeleoscopeChannel);

  // === SOLENOID === //
  // public static final Solenoid intakeSolenoid = new Solenoid(41, 1);
  // public static final Solenoid intakeSolenoid2 = new Solenoid(41, 2);
  public static final Solenoid angleSolenoid = new Solenoid(41, 3);
  public static final Solenoid angleSolenoidExhaust = new Solenoid(41, 0);

  // === SENSORS === //

  public static final AnalogInput lightSensorHigh = new AnalogInput(1);
  public static final AnalogInput lightSensorLow = new AnalogInput(2);

  public static final DigitalInput bumperLeft = new DigitalInput(1);
  public static final DigitalInput bumperRight = new DigitalInput(2);

  // === ENCODER === //
  public static final double MM_TO_IN = 0.0393701;
  public static final double WHEEL_TO_WHEEL_DIAMETER_INCHES = 320 * MM_TO_IN;
  public static final double WHEEL_DIAMETER_INCHES = 5.5;
  public static final double PULSES_PER_ROTATION = 256;
  public static final int leftEncoderPort = 3;
  public static final int rightEncoderPort = 4;
  public static final int kEncoderCPR = 1024;

  public static final double WHEEL_DIAMETER = 0.0254 * 6;
  public static final double gearRatio = 10.71;
  public static final double wheelCircumferenceInches = 18.85;
  public static final double ENCODER_EDGES_PER_REV = 2048;
  

  // === PROFILE === //

  // TODO: RUN CHARACTERIZATION AND CHANGE

  
  public static final double ksVolts = 0.682;
  //1.11
  public static final double kvVoltSecondsPerMeter = 2.23;
  //0.104
  public static final double kaVoltSecondsSquaredPerMeter = 0.249;
  //2.3
  public static final double kPDriveVel = 2.3;
  //0.6575104629481551
  public static final double kTrackwidthMeters = 0.83;
  public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(kTrackwidthMeters);

  //Good (for now)
  public static final double kMaxSpeedMetersPerSecond = 3;
  public static final double kMaxAccelerationMetersPerSecondSquared = 3;
  public static final double kRamseteB = 2;
  public static final double kRamseteZeta = 0.7;

  // leftEncoder.setDistancePerPulse((2 * Math.PI * WHEEL_DIAMETER_INCHES) /
  // PULSES_PER_ROTATION);

  // === GYRO === //
  public static final boolean kGyroReversed = true;

  // === TURN PID === //
  // USED IN TURNTOANGLE
  public static final double kTurnP = 0.4;
  public static final double kTurnI = 0;
  public static final double kTurnD = 0;

  public static final double kMaxTurnRateDegPerS = 100;
  public static final double kMaxTurnAccelerationDegPerSSquared = 300;

  public static final double kTurnToleranceDeg = 5;
  public static final double kTurnRateToleranceDegPerS = 10; // degrees per second

  // === JOYSTICK PORTS === //
  public static final int DriveControllerPort = 0;
  public static final int TechControllerPort = 1;

  // === SPEED CONSTANTS === //

  public static final double speedTop = 0.35;
  public static final double speedBottom = 0.35;
  public static final double moveConstant = 2.7;


  public static final double kMaxTrackerDistance = 9.0;
  public static final double kMaxGoalTrackAge = 2.5;
  public static final double kMaxGoalTrackSmoothingTime = 0.5;
  public static double kCameraFrameRate = 30;
  public static final double kPathFollowingMaxAccel = 40;

}

// RobotMap.driveControls.getRightTrigger() * 22000

// RobotMap.driveControls.getRightTrigger();
// RobotMap.rightDriveMotor.set(ControlMode.Velocity, 11000); //22000 is
// maximum, 100% power