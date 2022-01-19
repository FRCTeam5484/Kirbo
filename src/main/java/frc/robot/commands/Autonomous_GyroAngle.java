package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.helpers.Gyro;
import frc.robot.subsystems.subDriveTrain;

public class Autonomous_GyroAngle extends CommandBase {
  subDriveTrain drive;
  double angle;
  Gyro gyro;

  public Autonomous_GyroAngle(subDriveTrain _drive, double _angle) {
    drive = _drive;
    angle = _angle;
    addRequirements(drive);
  }

  @Override
  public void initialize() {
    gyro = new Gyro();
    gyro.reset();
  }

  @Override
  public void execute() {
    drive.GyroTurnToAngle(angle);
  }

  @Override
  public void end(boolean interrupted) {
    drive.stopDrive();
  }

  @Override
  public boolean isFinished() {
    return angle > gyro.getRawAngle() ? true: false;
  }
}
