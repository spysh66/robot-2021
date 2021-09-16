package frc.robot.Autonomous.Routes;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Drivetrain.Drive;
import frc.robot.Limelight.LimeTurn;
import frc.robot.Mapping.Constants;
import frc.robot.Mapping.RobotCommands;
import frc.robot.Autonomous.*;

public class FiveBallAngled extends SequentialCommandGroup {

    public FiveBallAngled(Drive drive) {

        
        super(
            // new RobotCommands().shooterFar,
            // new RobotCommands().retractCollector2,
            // new AutoRev(4, 0.25, 0.6),
            // new AutoShoot(3, 0.25, 0.6),
            new TurnToAngle2(35, 0.8, drive), //Facing balls
            // new AutonDrive(2.5 * Constants.moveConstant, 0.8, drive).alongWith(new RobotCommands().groundCollect),
            // new AutonDrive(3 * Constants.moveConstant, -0.8, drive),
            new TurnToAngle2(75, -0.8, drive),
            new AutonAlign(drive)//,
            // new AutoRev(4, 0.25, 0.6),
            // new AutoShoot(3, 0.25, 0.6)
            
            
            

        );

    }

}