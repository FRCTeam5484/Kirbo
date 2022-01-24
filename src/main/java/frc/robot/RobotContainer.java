package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Autonomous_AutoTest;
import frc.robot.commands.Autonomous_MakeASquare;
import frc.robot.commands.Autonomous_MoveForSeconds;
import frc.robot.commands.Autonomous_SquareDriveEncoders;
import frc.robot.commands.Autonomous_TriangleDriveEncoders;
import frc.robot.commands.Autonomous_TurnToAngle;
import frc.robot.subsystems.subDriveTrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


public class RobotContainer {
  SendableChooser<Command> autoChooser = new SendableChooser<>();

  // Controllers
  public final XboxController driverOne = new XboxController(Constants.DriveControllers.DriverOne);
  public final XboxController driverTwo = new XboxController(Constants.DriveControllers.DriverTwo);
  public final Joystick buttonBox = new Joystick(Constants.DriveControllers.ButtonBox);

  // SubSystems
  public final subDriveTrain drive = new subDriveTrain();

  public RobotContainer() {
    AddAutoCommands();
    DriverOneFunctions();
  }

  private void AddAutoCommands() {
    autoChooser.setDefaultOption("Move For Seconds", new Autonomous_MoveForSeconds(drive, 4, 0.4));
    autoChooser.addOption("Turn to Angle", new Autonomous_TurnToAngle(drive, 90));

    autoChooser.addOption("Make a Square", new Autonomous_MakeASquare(drive));
    autoChooser.addOption("Make Encoder Square", new Autonomous_SquareDriveEncoders(drive));

    autoChooser.addOption("Auto grab ball Move", new Autonomous_AutoTest(drive));
    SmartDashboard.putData("Autonomous", autoChooser);
  }

  private void DriverOneFunctions() {
    // Default Commands
    drive.setDefaultCommand(new RunCommand(() -> drive.tankDrive(driverOne), drive));
    
    new JoystickButton(driverOne, Button.kX.value)
      .whenPressed(new Autonomous_TurnToAngle(drive, -90).withTimeout(5));

    new JoystickButton(driverOne, Button.kB.value)
      .whenPressed(new Autonomous_TurnToAngle(drive, 90).withTimeout(5));

    new JoystickButton(driverOne, Button.kY.value)
      .whenPressed(new InstantCommand(() -> drive.setGyroZeroYaw()));

    new JoystickButton(driverOne, Button.kA.value)
      .whenPressed(new InstantCommand(() -> drive.setEncoders()));
        
    new JoystickButton(driverOne, Button.kRightBumper.value)
      .whenPressed(new Autonomous_SquareDriveEncoders(drive));

    new JoystickButton(driverOne, Button.kLeftBumper.value)
      .whenPressed(new Autonomous_TriangleDriveEncoders(drive));
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}
