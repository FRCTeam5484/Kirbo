package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.subDriveTrain;

public class Autonomous_MoveToEncoderValue extends CommandBase {
  subDriveTrain drive;
  int rotations;
  public Autonomous_MoveToEncoderValue(subDriveTrain _drive, int _rotations) {
    drive = _drive;
    rotations = _rotations;
    addRequirements(drive);
  }

  @Override
  public void initialize() {
    drive.ResetEncoders();
  }

  @Override
  public void execute() {
    drive.DriveStraightForRotations(rotations);
  }

  @Override
  public void end(boolean interrupted) {
    drive.stopDrive();
  }

  @Override
  public boolean isFinished() {
    return rotations < drive.getAverageEncoderDistance() ? true : false;
  }
}
