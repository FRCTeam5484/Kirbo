package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.subDriveTrain;

public class Autonomous_Turn90Degrees extends CommandBase {
  subDriveTrain drive;
  Timer time;
  

  public Autonomous_Turn90Degrees(subDriveTrain _drive) {
    drive = _drive;
    addRequirements(drive);
  }

  @Override
  public void initialize() {
    time = new Timer();
    time.start();
  }

  @Override
  public void execute() {
    drive.arcadeDrive();
  }

  @Override
  public void end(boolean interrupted) {
    drive.stopDrive();
  }

  @Override
  public boolean isFinished() {
    return time.get()>1 ? true : false;
  }
}
