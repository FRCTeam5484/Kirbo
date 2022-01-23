package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveSystem;
import frc.robot.subsystems.subDriveTrain;

public class Autonomous_MoveForSeconds extends CommandBase {
  subDriveTrain drive;
  int Seconds;
  double Speed;
  Timer time;
  public Autonomous_MoveForSeconds(subDriveTrain _drive, int _seconds, double _speed) {
    drive = _drive;
    Seconds = _seconds;
    Speed = _speed;
    addRequirements(drive);
  }

  @Override
  public void initialize() {
    time = new Timer();
    time.start();
  }

  @Override
  public void execute() {
    drive.autoDrive(MathUtil.clamp(Speed, DriveSystem.AutoMinSpeed, DriveSystem.AutoMaxSpeed));
  }

  @Override
  public void end(boolean interrupted) {
    drive.stopDrive();
  }

  @Override
  public boolean isFinished() {
    return time.get() > Seconds ? true : false;
  }
}
