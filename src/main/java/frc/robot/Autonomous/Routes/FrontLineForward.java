package frc.robot.Autonomous.Routes;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Drivetrain.Drive;
import frc.robot.Mapping.Constants;
import frc.robot.Mapping.RobotCommands;
import frc.robot.Autonomous.*;

public class FrontLineForward extends SequentialCommandGroup {

    public FrontLineForward(Drive drive) {

        
        super(
            new RobotCommands().shooterFar,
            new RobotCommands().retractCollector2,
            new AutoRev(4, 0.25, 0.6),
            new AutoShoot(3, 0.25, 0.6),
            new AutonDrive(1 * Constants.moveConstant, 0.8, drive),
            new TurnToAngle2(75, 0.8, drive)
            
            
            

        );

    }

}