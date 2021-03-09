package frc.robot.Spline;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;

import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.util.Units;

public class ExampleTrajectory {

    public ExampleTrajectory() {

        // S-Spline Test
        var TestStart = new Pose2d(Units.feetToMeters(0.0), Units.feetToMeters(0.0), Rotation2d.fromDegrees(0));
        var TestEnd = new Pose2d(Units.feetToMeters(3.0), Units.feetToMeters(5.0), Rotation2d.fromDegrees(0));

        var interiorWaypoints = new ArrayList<Translation2d>();
        interiorWaypoints.add(new Translation2d(Units.feetToMeters(1.5), Units.feetToMeters(3.0)));
        interiorWaypoints.add(new Translation2d(Units.feetToMeters(3.0), Units.feetToMeters(0)));

        TrajectoryConfig config = new TrajectoryConfig(Units.feetToMeters(6), Units.feetToMeters(6));
        // config.setReversed(false);

        final var trajectory = TrajectoryGenerator.generateTrajectory(TestStart, interiorWaypoints, TestEnd, config);
    }
}