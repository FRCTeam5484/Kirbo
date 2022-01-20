package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.subDriveTrain;

public class Autonomous_GyroAngle extends CommandBase {
  subDriveTrain drive;
  double angle;

  public Autonomous_GyroAngle(subDriveTrain _drive, double _angle) {
    drive = _drive;
    angle = _angle;
    addRequirements(drive);
  }

  @Override
  public void initialize() {
    drive.gyro.zeroHeading();
  }

  @Override
  public void execute() {
    drive.GyroTurnTowardsAngle(angle);
  }

  @Override
  public void end(boolean interrupted) {
    drive.stopDrive();
  }

  @Override
  public boolean isFinished() {
    if(angle > 0) {
      return angle < drive.gyro.getHeading() ? true: false;
    }
    else{
      return angle > drive.gyro.getHeading() ? true: false;
    }
  }
}
