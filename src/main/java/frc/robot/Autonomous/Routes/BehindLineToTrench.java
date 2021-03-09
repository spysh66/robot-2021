package frc.robot.Autonomous.Routes;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Drivetrain.Drive;
import frc.robot.Mapping.Constants;
import frc.robot.Mapping.RobotCommands;
import frc.robot.Autonomous.*;

public class BehindLineToTrench extends SequentialCommandGroup {

    public BehindLineToTrench(Drive drive) {

        
        super(
            //
            new RobotCommands().shooterFar,
            new RobotCommands().retractCollector2,
            new AutoRev(4, 0.55, 0.5),
            new AutoShoot(3, 0.55, 0.5),
            new RobotCommands().deployCollector2,
            new RobotCommands().shooterNear,
            new AutonDrive(4 * Constants.moveConstant, -0.8, drive),
            new TurnToAngle2(75, 0.8, drive),
            new AutonDrive(4 * Constants.moveConstant, 0.8, drive),
            new TurnToAngle2(60, 0.8, drive),
            new AutonDrive(2 * Constants.moveConstant, 0.8, drive),
            new RobotCommands().retractCollector2
            
            
            

        );

    }

}