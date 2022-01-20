package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    SetupSmartDashboard();
  }

  private void SetupSmartDashboard(){
    SmartDashboard.putString("Drive Mode", m_robotContainer.drive.driveMode);
    SmartDashboard.putNumber("Drive Left Power", m_robotContainer.drive.leftDriveMaster.get());
    SmartDashboard.putNumber("Drive Right Power", m_robotContainer.drive.rightDriveMaster.get());
    SmartDashboard.putNumber("Drive Left Encoder", m_robotContainer.drive.getLeftSideEncoderValue());
    SmartDashboard.putNumber("Drive Right Encoder", m_robotContainer.drive.getRightSideEncoderValue());   
    SmartDashboard.putNumber("Gyro Heading", m_robotContainer.drive.gyro.getHeading());
    SmartDashboard.putNumber("Gyro Angle", m_robotContainer.drive.gyro.getAngle());
    SmartDashboard.putNumber("Gyro Rate", m_robotContainer.drive.gyro.getRate());
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}
}
