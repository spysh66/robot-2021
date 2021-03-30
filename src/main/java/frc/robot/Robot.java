package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Limelight.LimelightInterface;
import frc.robot.Limelight.LimelightInterface.LimelightLEDMode;
import frc.robot.Mapping.RobotContainer;
import frc.robot.Mapping.SpeedControllerSetUp;

/**
 * TODO: Reenable collector pneumatics and compressor
 * 
 * 
 */
public class Robot extends TimedRobot {

  // If mode = 1, Arcade
  // If mode = 2, Tank
  public static int mode = 1;

  // Defining Subsystems

  public static SpeedControllerSetUp speedcontrollersetup;
  //public static LimelightInterface limelight = new LimelightInterface();

  public static AHRS m_gyro;
  private RobotContainer m_robotContainer;

  // Commands to be used later
  private Command m_autonomousCommand;
  public static boolean autoVal;
  public static String gameData;
  // private Command autonomousCommand;
  // private SendableChooser<Character> autoSide;
  // private SendableChooser<Character> autoGroup;
  // private SendableChooser<Command> chooser = new SendableChooser<>();

  // public static double v;
  // public static double x;
  // double y;
  // double a;

  // Initalizing
  public void robotInit() {

    // Sets up the camera
   // CameraServer.getInstance().startAutomaticCapture();
    // For limelight, use 10.31.0.1:5801
  //  limelight.setLEDMode(LimelightLEDMode.OFF);

    // Gets what type of game is being played, not that important
    gameData = DriverStation.getInstance().getGameSpecificMessage();

    try {
      /***********************************************************************
       * navX-MXP: - Communication via RoboRIO MXP (SPI, I2C) and USB. - See
       * http://navx-mxp.kauailabs.com/guidance/selecting-an-interface.
       * 
       * navX-Micro: - Communication via I2C (RoboRIO MXP or Onboard) and USB. - See
       * http://navx-micro.kauailabs.com/guidance/selecting-an-interface.
       * 
       * VMX-pi: - Communication via USB. - See
       * https://vmx-pi.kauailabs.com/installation/roborio-installation/
       * 
       * Multiple navX-model devices on a single robot are supported.
       ************************************************************************/
      m_gyro = new AHRS(SPI.Port.kMXP);
    } catch (RuntimeException ex) {
      DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
    }

    m_robotContainer = new RobotContainer();
    new SpeedControllerSetUp().configure();

  }

  @Override
  public void robotPeriodic() {
    // Runs the Scheduler
    CommandScheduler.getInstance().run();

    // NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    // NetworkTableEntry tv = table.getEntry("tv");
    // NetworkTableEntry tx = table.getEntry("tx");
    // NetworkTableEntry ty = table.getEntry("ty");
    // NetworkTableEntry ta = table.getEntry("ta");

    

   

    // read values periodically
    // v = tv.getDouble(0.0);
    // x = tx.getDouble(0.0);
    // y = ty.getDouble(0.0);
    // a = ta.getDouble(0.0);

    // // post to smart dashboard periodically
    // SmartDashboard.putNumber("LimelightX", x);
    // SmartDashboard.putNumber("LimelightY", y);
    // SmartDashboard.putNumber("LimelightArea", a);
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {

    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.

    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    // v = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    // // x = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    // y = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
