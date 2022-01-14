package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Autonomous_MoveForSeconds;
import frc.robot.commands.Autonomous_MoveToEncoderValue;
import frc.robot.commands.cmdDriveTrain_HoldGround;
import frc.robot.subsystems.subDriveTrain;
import edu.wpi.first.wpilibj2.command.Command;
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

  private void AddAutoCommands(){
    autoChooser.setDefaultOption("Move For Seconds", new Autonomous_MoveForSeconds(drive, 4, 0.4));  
    autoChooser.addOption("Move To Encoder Value", new Autonomous_MoveToEncoderValue(drive, 10)); 
    SmartDashboard.putData("Autonomous", autoChooser);
  }

  private void DriverOneFunctions() {    
    // Default Commands
    drive.setDefaultCommand(new RunCommand(() -> drive.tankDrive(driverOne), drive));

    new JoystickButton(driverOne, Button.kB.value).whileHeld(new cmdDriveTrain_HoldGround(drive));
  } 

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}
