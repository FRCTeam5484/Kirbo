package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.subDriveTrain;

public class Autonomous_MakeASquare extends SequentialCommandGroup {
  public Autonomous_MakeASquare(subDriveTrain drive) {
    addCommands(
      new InstantCommand(() -> drive.getGyroHeading()),
      new Autonomous_MoveForSeconds(drive, 2, .4),
      new Autonomous_TurnToAngle(drive, 90),
      new InstantCommand(() -> drive.getGyroHeading()),
      new Autonomous_MoveForSeconds(drive, 2, .4),
      new Autonomous_TurnToAngle(drive, 90),
      new InstantCommand(() -> drive.getGyroHeading()),
      new Autonomous_MoveForSeconds(drive, 2, .4),
      new Autonomous_TurnToAngle(drive, 90),
      new Autonomous_MoveForSeconds(drive, 2, .4)
    );
  }
}
