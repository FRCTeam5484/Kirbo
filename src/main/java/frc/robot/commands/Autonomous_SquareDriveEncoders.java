// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.subDriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Autonomous_SquareDriveEncoders extends SequentialCommandGroup {
  /** Creates a new Autonomous_SquareDriveEncoders. */
  public Autonomous_SquareDriveEncoders(subDriveTrain drive) {
    addCommands(
      new InstantCommand(() -> drive.ResetEncoders()),
      new InstantCommand(() -> drive.ResetGyro()),
      new Autonomous_MoveByInches(drive, 36).withTimeout(2),
      new Autonomous_TurnToAngle(drive, 90),

      new InstantCommand(() -> drive.ResetEncoders()),
      new InstantCommand(() -> drive.ResetGyro()),
      new Autonomous_MoveByInches(drive, 36).withTimeout(2),
      new Autonomous_TurnToAngle(drive, 90),

      new InstantCommand(() -> drive.ResetEncoders()),
      new InstantCommand(() -> drive.ResetGyro()),
      new Autonomous_MoveByInches(drive, 36).withTimeout(2),
      new Autonomous_TurnToAngle(drive, 90),

      new InstantCommand(() -> drive.ResetEncoders()),
      new InstantCommand(() -> drive.ResetGyro()),
      new Autonomous_MoveByInches(drive, 24).withTimeout(2),
      new Autonomous_TurnToAngle(drive, 90)
    );
  }
}
