package frc.robot.commands;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.Constants.DriveSystem;
import frc.robot.subsystems.subDriveTrain;

public class Autonomous_TurnToAngleProfiled extends ProfiledPIDCommand {
  public Autonomous_TurnToAngleProfiled(subDriveTrain drive, double targetAngleDegrees) {
    super(
        new ProfiledPIDController(
            DriveSystem.TurnP,
            DriveSystem.TurnI,
            DriveSystem.TurnD,
            new TrapezoidProfile.Constraints(
                DriveSystem.MaxTurnRateDegPerS,
                DriveSystem.MaxTurnAccelerationDegPerSSquared)),
        drive.gyro::getHeading,
        targetAngleDegrees,
        (output, setpoint) -> drive.arcadeDrive(0, output),
        drive);

    getController().enableContinuousInput(-180, 180);
    getController()
        .setTolerance(DriveSystem.TurnToleranceDeg, DriveSystem.TurnRateToleranceDegPerS);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return getController().atGoal();
  }
}
