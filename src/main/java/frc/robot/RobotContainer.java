package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Autonomous_MoveForSeconds;
import frc.robot.commands.cmdDriveTrain_HoldGround;
import frc.robot.commands.Autonomous_TurnToAngle;
import frc.robot.commands.cmdDriveTrain_ResetEncoders;
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
    SmartDashboard.putData("Autonomous", autoChooser);
  }

  private void DriverOneFunctions() {
    // Default Commands
    drive.setDefaultCommand(new RunCommand(() -> drive.tankDrive(driverOne), drive));

    new JoystickButton(driverOne, Button.kX.value)
        .whenPressed(new Autonomous_TurnToAngle(drive, -90).withTimeout(5));

    // Turn to -90 degrees with a profile when the B button is pressed, with a 5 second timeout
    new JoystickButton(driverOne, Button.kB.value)
        .whenPressed(new Autonomous_TurnToAngle(drive, 90).withTimeout(5));

    // Reset Heading when 'Y' button is pressed, with a 1 second timeout
    new JoystickButton(driverOne, Button.kY.value)
        .whenPressed(new InstantCommand(() -> drive.ResetGyro()));
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}
