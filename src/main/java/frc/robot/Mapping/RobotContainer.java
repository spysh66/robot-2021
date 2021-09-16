
package frc.robot.Mapping;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Sendable;
//Egg
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.Limelight.LimeTurn;
import frc.robot.Limelight.LimelightOn;
import frc.robot.Drivetrain.DefaultDrive;
import frc.robot.Commands.*;
import static frc.robot.Mapping.RobotCommands.*;

import java.util.ArrayList;
import java.util.List;

public class RobotContainer {

  public Sendable time;

  private final RobotCommands robotCommands = new RobotCommands();

  // DRIVE CONTROLLER
  public final static XboxController m_driveController = new XboxController(Constants.DriveControllerPort);
  // TECH CONTROLLER
  //public final static XboxController m_techController = new XboxController(Constants.TechControllerPort);

  // === Drive === //
  private final AxisButton halfSpeed = new AxisButton(m_driveController, Constants.rightTriggerChannel);
  private final AxisButton limeTurn = new AxisButton(m_driveController, Constants.leftTriggerChannel);

  private final JoystickButton reset = new JoystickButton(m_driveController, Constants.aButtonChannel);
  private final JoystickButton test = new JoystickButton(m_driveController, Constants.bButtonChannel);
  private final JoystickButton limeLightOn = new JoystickButton(m_driveController, Constants.backButtonChannel);
  //private final JoystickButton beam = new JoystickButton(m_driveController, Constants.yButtonChannel);

  // === Collector === //
 // private final AxisButton groundEject = new AxisButton(m_techController, Constants.leftTriggerChannel);
  private final JoystickButton collectorReverse = new JoystickButton(m_driveController, Constants.yButtonChannel);
 // private final AxisButton groundCollect = new AxisButton(m_techController, Constants.rightTriggerChannel);
  private final JoystickButton humanCollect = new JoystickButton(m_driveController, Constants.xButtonChannel);
 // private final JoystickButton deployCollector = new JoystickButton(m_techController, Constants.aButtonChannel);

  // === Shooter === //
  private final JoystickButton shoot = new JoystickButton(m_driveController, Constants.rightBumperChannel);
  private final JoystickButton shooterRev = new JoystickButton(m_driveController, Constants.leftBumperChannel);
  private final POVButton shooterFar = new POVButton(m_driveController, Constants.POVU);
  private final POVButton shooterNear = new POVButton(m_driveController, Constants.POVD);

  private final SequentialCommandGroup BehindLineToTrench = new SequentialCommandGroup(
      new frc.robot.Autonomous.Routes.BehindLineToTrench(m_robotDrive));
  private final SequentialCommandGroup FrontLineForward = new SequentialCommandGroup(
      new frc.robot.Autonomous.Routes.FrontLineForward(m_robotDrive));
  private final SequentialCommandGroup FrontLineBackward = new SequentialCommandGroup(
      new frc.robot.Autonomous.Routes.FrontLineBackward(m_robotDrive));
  private final SequentialCommandGroup EightBall = new SequentialCommandGroup(
      new frc.robot.Autonomous.Routes.EightBall(m_robotDrive));
  private final SequentialCommandGroup FiveBallAligned = new SequentialCommandGroup(
      new frc.robot.Autonomous.Routes.FiveBallAligned(m_robotDrive));
  private final SequentialCommandGroup FiveBallAngled = new SequentialCommandGroup(
      new frc.robot.Autonomous.Routes.FiveBallAngled(m_robotDrive));
      private final SequentialCommandGroup BarrelRace = new SequentialCommandGroup(
        new frc.robot.Autonomous.Routes.BarrelRace(m_robotDrive));
  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  SendableChooser<Integer> m_time = new SendableChooser<>();

  /**
   * The container for the robot. Contains subsystems, RobotContainer devices, and
   * commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    // Set the default drive command to split-stick arcade drive
    m_robotDrive.setDefaultCommand(
        // // A split-stick arcade command, with forward/backward controlled by the left
        // // hand, and turning controlled by the right.
        new DefaultDrive(m_robotDrive, () -> -m_driveController.getY(GenericHID.Hand.kLeft),
            () -> m_driveController.getX(GenericHID.Hand.kRight)));
        // new DefaultDrive(m_robotDrive, () -> m_driveController.getY(GenericHID.Hand.kLeft),
        // () -> m_driveController.getY(GenericHID.Hand.kRight)));
    // m_climber.setDefaultCommand(

    //     new Climb(m_climber, () -> m_techController.getY(GenericHID.Hand.kLeft),
    //         () -> m_techController.getY(GenericHID.Hand.kRight)));

    m_chooser.addOption("BehindLineToTrench", BehindLineToTrench);
    m_chooser.addOption("FrontLineForward", FrontLineForward);
    m_chooser.addOption("FrontLineBackward", FrontLineBackward);
    m_chooser.addOption("FiveBallAligned", FiveBallAligned);
    m_chooser.addOption("FiveBallAngled", FiveBallAngled);
    m_chooser.addOption("Eight", EightBall);
    m_chooser.addOption("Barrel", BarrelRace);
    //m_chooser.addOption("In-Front", TestRoute);
    m_time.addOption("No Wait", 0);
    m_time.addOption("1 sec", 1);
    m_time.addOption("2 sec", 2);
    m_time.addOption("3 sec", 3);
    m_time.addOption("4 sec", 4);
    m_time.addOption("5 sec", 5);

    // Put the chooser on the dashboard
    Shuffleboard.getTab("Autonomous").add(m_chooser);
    Shuffleboard.getTab("Autonomous").add(m_time);
    // time = SmartDashboard.getData("Time");
  }

  private void configureButtonBindings() {

    reset.whenPressed(new Reset());
    test.whenPressed(new Test());
    limeLightOn.whenPressed(new LimelightOn());
    // beam.whenPressed(robotCommands.beam);

    halfSpeed.whileActiveContinuous(new HalfSpeed(m_robotDrive, () -> -m_driveController.getY(GenericHID.Hand.kLeft),
        () -> m_driveController.getX(GenericHID.Hand.kRight),
        () -> m_driveController.getRawAxis(Constants.rightTriggerChannel)));
    limeTurn.whileActiveContinuous(new LimeTurn(m_robotDrive, () -> -m_driveController.getY(GenericHID.Hand.kLeft)));

  //  groundEject.whileActiveContinuous(robotCommands.groundEject);
    collectorReverse.whileHeld(robotCommands.collectorReverse);
    // collectorReverse.whenReleased(robotCommands.beam.withTimeout(1));
  //  groundCollect.whileActiveContinuous(robotCommands.groundCollect);
    // groundCollect.whenInactive(robotCommands.beam.withTimeout(1));
    humanCollect.whileHeld(robotCommands.humanCollect);
    // humanCollect.whenReleased(robotCommands.beam.withTimeout(1));
  //  deployCollector.toggleWhenPressed(robotCommands.collector);

    shooterRev.toggleWhenPressed(robotCommands.shooterRev);
    shooterFar.toggleWhenPressed(robotCommands.shooterFar);
    shooterNear.toggleWhenPressed(robotCommands.shooterNear);
    shoot.whileHeld(robotCommands.shoot);

  }

  // public Command getAutonomousCommand() {
  //   return m_chooser.getSelected();
  // }

  public Command getAutonomousCommand() {

    // Create a voltage constraint to ensure we don't accelerate too fast
    var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(Constants.ksVolts,
            Constants.kvVoltSecondsPerMeter,
                                       Constants.kaVoltSecondsSquaredPerMeter),
                                       Constants.kDriveKinematics,
            10);

    // Create config for trajectory
    TrajectoryConfig config =
        new TrajectoryConfig(Constants.kMaxSpeedMetersPerSecond,
        Constants.kMaxAccelerationMetersPerSecondSquared)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(Constants.kDriveKinematics)
            // Apply the voltage constraint
            .addConstraint(autoVoltageConstraint);

    // An example trajectory to follow.  All units in meters.
    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(0, 0, new Rotation2d(0)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(
            new Translation2d(1, 1),
            new Translation2d(2, -1)
        ),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(3, 0, new Rotation2d(0)),
        // Pass config
        config
    );

    RamseteCommand ramseteCommand = new RamseteCommand(
        exampleTrajectory,
        m_robotDrive::getPose,
        new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta),
        new SimpleMotorFeedforward(Constants.ksVolts,
        Constants.kvVoltSecondsPerMeter,
        Constants.kaVoltSecondsSquaredPerMeter),
        Constants.kDriveKinematics,
        m_robotDrive::getWheelSpeeds,
        new PIDController(Constants.kPDriveVel, 0, 0),
        new PIDController(Constants.kPDriveVel, 0, 0),
        // RamseteCommand passes volts to the callback
        m_robotDrive::tankDriveVolts,
        m_robotDrive
    );

    // Reset odometry to the starting pose of the trajectory.
    m_robotDrive.resetOdometry(exampleTrajectory.getInitialPose());

    // Run path following command, then stop at the end.
    return ramseteCommand.andThen(() -> m_robotDrive.tankDriveVolts(0, 0));
  }
}