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
public class Autonomous_AutoTest extends SequentialCommandGroup {
  /** Creates a new Autonomous_AutoTest. */
  public Autonomous_AutoTest(subDriveTrain drive) {
    addCommands(
      //To first ball
      new InstantCommand(() -> drive.setEncoders()),
      new InstantCommand(() -> drive.getGyroHeading()),
      new Autonomous_MoveByInches(drive, 100),
      new InstantCommand(() -> drive.getGyroHeading()),
      new Autonomous_TurnToAngle(drive, 180),
      //Go to shoot ball
      new InstantCommand(() -> drive.setEncoders()),
      new Autonomous_MoveByInches(drive, 105),
      //shoot bang bang
      //Go to third ball
      new InstantCommand(() -> drive.setEncoders()),
      new InstantCommand(() -> drive.getGyroHeading()),
      new Autonomous_TurnToAngle(drive, 106),
      new Autonomous_MoveByInches(drive, 150)
      //suck suck ball
    );
  }
}
