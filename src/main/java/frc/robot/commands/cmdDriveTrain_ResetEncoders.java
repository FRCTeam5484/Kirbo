package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.subDriveTrain;

public class cmdDriveTrain_ResetEncoders extends CommandBase {
  subDriveTrain drive;
  public cmdDriveTrain_ResetEncoders(subDriveTrain _drive){
    drive = _drive;
  }

  @Override
  public void initialize() {
    drive.ResetEncoders();
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
