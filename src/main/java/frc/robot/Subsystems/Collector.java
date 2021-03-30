package frc.robot.Subsystems;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Mapping.Constants;

public class Collector extends SubsystemBase {

    public static boolean isDeployed = false;
    public static boolean lightIsBlocked = false;
    public static boolean isCollecting = false;

    public final static BooleanSupplier isDeployedSupplier = () -> isDeployed;
    public final static BooleanSupplier isLightSupplier = () -> lightIsBlocked;
    public final static BooleanSupplier isCollectingSupplier = () -> isCollecting;

    public int lightHigh;
    public int lightLow;


    public void lightCheck() {

        System.out.println("High");
        System.out.println(Constants.lightSensorHigh.getValue());
        System.out.println("Low");
        System.out.println(Constants.lightSensorLow.getValue());
        // TODO: IF NEEDED ADD BOOLEAN INTERRUPTABLE
        

        while (lightHigh < 20 || lightLow < 20) {
            lightIsBlocked = true;
            lightHigh = Constants.lightSensorLow.getValue();
            lightLow = Constants.lightSensorLow.getValue();
            if (lightHigh < 20) {

                lightHigh = Constants.lightSensorLow.getValue();
                lightLow = Constants.lightSensorLow.getValue();

                // Run motor in reverse
                Constants.conveyor.set(ControlMode.PercentOutput, -0.3);

            }else if (lightLow < 20) {

                lightHigh = Constants.lightSensorLow.getValue();
                lightLow = Constants.lightSensorLow.getValue();
                
                // Run motor forward
                Constants.conveyor.set(ControlMode.PercentOutput, 0.3);

            }
        }
            lightIsBlocked = false;
        
    }

    public void deployCollector() {

        // Set Piston to extend/retract
        // Constants.intakeSolenoid.set(false);
        // Constants.intakeSolenoid2.set(true);

        isDeployed = true;

    }
    

    public void retractCollector() {

        // Constants.intakeSolenoid.set(true);
        // Constants.intakeSolenoid2.set(false);

        isDeployed = false;

    }

    public void groundEject(final double speed) {

        Constants.collector.set(ControlMode.PercentOutput, speed);

    }

    public void wheelSpeed(final double collectspeed, final double conveyorspeed) {

        // Reverse Conveyor and collector
        Constants.collector.set(ControlMode.PercentOutput, collectspeed);
        Constants.conveyor.set(ControlMode.PercentOutput, conveyorspeed);

    }

    public void collectorReverse(final double collectspeed, final double conveyorspeed, final double shooterspeed) {

        // Reverse Conveyor and collector
        Constants.collector.set(ControlMode.PercentOutput, collectspeed);
        Constants.injector.set(ControlMode.PercentOutput, collectspeed * 2);
        Constants.conveyor.set(ControlMode.PercentOutput, -conveyorspeed);
        Constants.shooterTop.set(ControlMode.PercentOutput, -shooterspeed);
        Constants.shooterBottom.set(ControlMode.PercentOutput, -shooterspeed);

    }

    public void humanCollect(final double collectspeed, final double conveyorspeed) {

        Constants.conveyor.set(ControlMode.PercentOutput, conveyorspeed);
        // Constants.collector.set(ControlMode.PercentOutput, collectspeed);

    }

    public void groundCollect(final double collectspeed, final double conveyorspeed) {

        // Reverse Conveyor and collector
        Constants.collector.set(ControlMode.PercentOutput, -collectspeed);
        Constants.conveyor.set(ControlMode.PercentOutput, conveyorspeed);

    }

    public void groundCollectOn(final double collectspeed, final double conveyorspeed) {

        // Reverse Conveyor and collector
      
        Constants.collector.set(ControlMode.PercentOutput, -collectspeed);
        Constants.conveyor.set(ControlMode.PercentOutput, conveyorspeed);
        isCollecting = true;

    }
    public void groundCollectOff() {

        // Reverse Conveyor and collector
      
        Constants.collector.set(ControlMode.PercentOutput, 0.0);
        Constants.conveyor.set(ControlMode.PercentOutput, 0.0);
        isCollecting = false;

    }

    public void reverseInjector(final double speed) {

        Constants.injector.set(ControlMode.PercentOutput, speed);
        Constants.shooterTop.set(ControlMode.PercentOutput, speed * 0.5);
        Constants.shooterBottom.set(ControlMode.PercentOutput, -speed * 0.5);

    }

    // public void initialize() {

    //     lightHigh = Constants.lightSensorHigh.getValue();
    //     lightLow = Constants.lightSensorHigh.getValue();

    // }

    public void execute() {

        

    }

}