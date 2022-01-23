package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.subDriveTrain;

public class Autonomous_TriangleDriveEncoders extends SequentialCommandGroup {
  public Autonomous_TriangleDriveEncoders(subDriveTrain drive) {
    addCommands(
      new InstantCommand(() -> drive.setEncoders()),
      new InstantCommand(() -> drive.setGyroHeading()),
      new Autonomous_MoveByInches(drive, 36).withTimeout(2),
      new Autonomous_TurnToAngle(drive, 135),

      new InstantCommand(() -> drive.setEncoders()),
      new InstantCommand(() -> drive.setGyroHeading()),
      new Autonomous_MoveByInches(drive, 36).withTimeout(2),
      new Autonomous_TurnToAngle(drive, 135),

      new InstantCommand(() -> drive.setEncoders()),
      new InstantCommand(() -> drive.setGyroHeading()),
      new Autonomous_MoveByInches(drive, 36).withTimeout(2),
      new Autonomous_TurnToAngle(drive, 135),

      new InstantCommand(() -> drive.setEncoders()),
      new InstantCommand(() -> drive.setGyroHeading()),
      new Autonomous_TurnToAngle(drive, 135)

    );
  }
}
