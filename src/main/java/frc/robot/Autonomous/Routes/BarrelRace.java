package frc.robot.Autonomous.Routes;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Drivetrain.Drive;
import frc.robot.Mapping.Constants;
import frc.robot.Mapping.RobotCommands;
import frc.robot.Autonomous.*;

public class BarrelRace extends SequentialCommandGroup {

    public BarrelRace(Drive drive) {

        
        super(
             new ResetEncoder(),
            new AutonDrive(4 * Constants.moveConstant,0.8, 0.8, drive),//,
            new AutonDrive(20 * Constants.moveConstant,0.8, 0.0, drive),
            new AutonDrive(8.5 * Constants.moveConstant,0.7, 0.6, drive),
            new AutonDrive(21 * Constants.moveConstant,0.0, 0.8, drive),
            new AutonDrive(6 * Constants.moveConstant,0.8, 0.8, drive),
            new ResetEncoder()
            
            

        );

    }

}