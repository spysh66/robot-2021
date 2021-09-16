package frc.robot.Autonomous.Routes;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Drivetrain.Drive;
import frc.robot.Limelight.LimeTurn;
import frc.robot.Mapping.Constants;
import frc.robot.Mapping.RobotCommands;
import frc.robot.Autonomous.*;

public class EightBall extends SequentialCommandGroup {

    public EightBall(Drive drive) {

        
        super(
            // new RobotCommands().shooterFar,
            // new RobotCommands().retractCollector2,
            // new AutoRev(4, 0.25, 0.6),
            // new AutoShoot(3, 0.25, 0.6),
            new TurnToAngle2(130, -0.8, drive), //Turn to face trench
            // new AutonDrive(20 * Constants.moveConstant, 0.8, drive).alongWith(new RobotCommands().groundToggle), //run through and collect balls
            // new RobotCommands().groundToggle,
            // new AutonDrive(18 * Constants.moveConstant, -0.8, drive),
            // new TurnToAngle2(160, 0.8, drive), //Turn to face target
            new AutonAlign(drive)
            // new TurnToAngle2(-35, 0.8, drive), //Turn to face target
            // new AutoRev(4, 0.25, 0.6),
            // new AutoShoot(3, 0.25, 0.6)

            

        );

    }

}