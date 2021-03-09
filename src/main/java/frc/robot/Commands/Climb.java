package frc.robot.Commands;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Drivetrain.Drive;
import frc.robot.Mapping.Constants;
import frc.robot.Subsystems.Climber;

/**
 * Code should inline a command this simple with
 * {@link edu.wpi.first.wpilibj2.command.RunCommand}.
 */
public class Climb extends CommandBase {
  private final Climber m_climber;
  private final DoubleSupplier m_teleoscope;
  private final DoubleSupplier m_winch;

  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param forward   The control input for driving forwards/backwards
   * @param rotation  The control input for turning
   */
  public Climb(Climber subsystem, DoubleSupplier winch, DoubleSupplier teleScope) {
    m_climber = subsystem;
    m_teleoscope = teleScope;
    m_winch = winch;

    addRequirements(m_climber);
  }

  public void execute() {

    Constants.teleoscope.set(ControlMode.PercentOutput, m_teleoscope.getAsDouble() * 0.4);
    Constants.winch.set(ControlMode.PercentOutput, m_winch.getAsDouble() * 0.5);

  }
}