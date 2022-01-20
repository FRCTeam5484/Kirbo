package frc.robot.commands;


import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.DriveSystem;
import frc.robot.subsystems.subDriveTrain;

public class Autonomous_MoveToEncoderTargetPID extends PIDCommand {
  public Autonomous_MoveToEncoderTargetPID(subDriveTrain drive, double targetEncoderValue) {
    super(
      new PIDController(DriveSystem.PValue, DriveSystem.IValue, DriveSystem.DValue),
      drive::getAverageEncoderDistance,
      targetEncoderValue,
      output -> drive.arcadeDrive(output, 0),
      drive);
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
