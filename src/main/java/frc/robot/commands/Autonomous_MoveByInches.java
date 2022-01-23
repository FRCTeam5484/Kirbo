package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.DriveSystem;
import frc.robot.subsystems.subDriveTrain;

public class Autonomous_MoveByInches extends PIDCommand {
  public Autonomous_MoveByInches(subDriveTrain drive, double targetDistanceInInches) {
    super(
        new PIDController(
          DriveSystem.DrivePValue, 
          DriveSystem.DriveIValue, 
          DriveSystem.DriveDValue),
        drive::getAverageEncoderDistance,
        targetDistanceInInches * DriveSystem.EncoderTickToInch,
        output -> drive.autoDrive(MathUtil.clamp(output, -0.5, 0.5)),
        drive);
    getController().setTolerance(DriveSystem.DriveToleranceDis);
  }

  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
