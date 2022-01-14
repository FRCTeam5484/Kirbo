package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.subDriveTrain;

public class cmdDriveTrain_HoldGround extends CommandBase {
  subDriveTrain drive;
  public cmdDriveTrain_HoldGround(subDriveTrain _drive) {
    drive = _drive;
    addRequirements(drive);
  }

  @Override
  public void initialize() {
    drive.DriveLock();
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    drive.DriveUnlock();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
