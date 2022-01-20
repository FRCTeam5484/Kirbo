package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.DriveSystem;
import frc.robot.subsystems.subDriveTrain;

public class Autonomous_TurnToAnglePID extends PIDCommand {
  public Autonomous_TurnToAnglePID(subDriveTrain drive, double targetAngleDegrees) {
    super(
      new PIDController(DriveSystem.PValue, DriveSystem.IValue, DriveSystem.DValue),
      drive.gyro::getHeading,
      targetAngleDegrees,
      output -> drive.arcadeDrive(0, output),
      drive);
    drive.gyro.zeroHeading();
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
