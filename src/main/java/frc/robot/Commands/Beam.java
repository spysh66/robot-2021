package frc.robot.Commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Mapping.Constants;

public class Beam extends CommandBase {

    public double lightHigh;
    public double lightLow;

    public void execute() {

        lightHigh = Constants.lightSensorHigh.getValue();
        lightLow = Constants.lightSensorHigh.getValue();

        while (lightHigh < 20 || lightLow < 20) {

            if (lightHigh < 20) {

                // Run motor in reverse
                Constants.conveyor.set(ControlMode.PercentOutput, -0.3);

            }
            if (lightLow < 20) {

                // Run motor forward
                Constants.conveyor.set(ControlMode.PercentOutput, 0.3);

            }
        }

    }

}