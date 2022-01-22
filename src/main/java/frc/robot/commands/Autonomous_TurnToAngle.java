package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.DriveSystem;
import frc.robot.subsystems.subDriveTrain;

public class Autonomous_TurnToAngle extends PIDCommand {
  public Autonomous_TurnToAngle(subDriveTrain drive, double targetAngleDegrees) {
    super(
        new PIDController(
          DriveSystem.PValue, 
          DriveSystem.IValue, 
          DriveSystem.DValue),
        // Close loop on heading
        drive::GetHeading,
        // Set reference to target
        targetAngleDegrees,
        // Pipe output to turn robot
        output -> drive.autoTurn(output),
        // Require the drive
        drive);

    // Set the controller to be continuous (because it is an angle controller)
    getController().enableContinuousInput(-180, 180);
    // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
    // setpoint before it is considered as having reached the reference
    getController()
        .setTolerance(DriveSystem.TurnToleranceDeg, DriveSystem.TurnRateToleranceDegPerSec);
  }

  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
