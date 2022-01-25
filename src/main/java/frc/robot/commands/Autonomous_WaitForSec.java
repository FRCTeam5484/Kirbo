// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.subDriveTrain;

public class Autonomous_WaitForSec extends CommandBase {

  subDriveTrain drive;
  int Seconds;
  Timer time;

  public Autonomous_WaitForSec(subDriveTrain _drive, int _Seconds) {
    drive = _drive;
    Seconds = _Seconds;
  }

  @Override
  public void initialize() {
    time = new Timer();
    time.start();
  }

  @Override
  public void execute() {
    drive.stopDrive();
  }

  @Override
  public void end(boolean interrupted) {
    drive.stopDrive();
  }

  @Override
  public boolean isFinished() {
    return time.get() > Seconds ? true : false;
  }
}
