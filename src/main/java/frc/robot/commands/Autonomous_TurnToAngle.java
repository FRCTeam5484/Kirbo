package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.DriveSystem;
import frc.robot.subsystems.subDriveTrain;

public class Autonomous_TurnToAngle extends PIDCommand {
  public Autonomous_TurnToAngle(subDriveTrain drive, double targetAngleDegrees) {
    super(
        new PIDController(
          DriveSystem.TurnPValue, 
          DriveSystem.TurnIValue, 
          DriveSystem.TurnDValue),
        drive::getGyroHeading,
        targetAngleDegrees,
        output -> drive.autoTurn(output),
        drive);

    getController().enableContinuousInput(-180, 180);
    getController().setTolerance(DriveSystem.TurnToleranceDeg, DriveSystem.TurnRateToleranceDegPerSec);
  }

  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
