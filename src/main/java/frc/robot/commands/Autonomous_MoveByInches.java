package frc.robot.commands;

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
        // Close loop on heading
        drive::getAverageEncoderDistance,
        // Set reference to target
        targetDistanceInInches * DriveSystem.EncoderTickToInch,
        // Pipe output to turn robot
        output -> drive.autoDrive(output),
        // Require the drive
        drive);

    // Set the controller to be continuous (because it is an angle controller)
    getController().enableContinuousInput(-180, 180);
    // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
    // setpoint before it is considered as having reached the reference
    getController()
       .setTolerance(DriveSystem.DriveToleranceDis);
  }

  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
