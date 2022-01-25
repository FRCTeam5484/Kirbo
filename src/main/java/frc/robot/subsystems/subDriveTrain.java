package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants.DriveSystem;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.SPI;

public class subDriveTrain extends SubsystemBase implements Loggable {
  @Log.MotorController(name = "Left Master Motor")
  public CANSparkMax leftDriveMaster = new CANSparkMax(DriveSystem.LeftMasterMotorId, MotorType.kBrushless);
  @Log.MotorController(name = "Left Follower Motor")
  public CANSparkMax leftDriveSlave = new CANSparkMax(DriveSystem.LeftSlaveMotorId, MotorType.kBrushless);
  @Log.MotorController(name = "Right Master Motor")
  public CANSparkMax rightDriveMaster = new CANSparkMax(DriveSystem.RightMasterMotorId, MotorType.kBrushless);
  @Log.MotorController(name = "Right Follower Motor")
  public CANSparkMax rightDriveSlave = new CANSparkMax(DriveSystem.RightSlaveMotorId, MotorType.kBrushless);
  @Log.DifferentialDrive(name = "Drive")
  public DifferentialDrive driveTrain = new DifferentialDrive(leftDriveMaster, rightDriveMaster);

  @Log.Encoder(name = "Left Master Encoder")
  private RelativeEncoder leftEncoder = leftDriveMaster.getEncoder();
  @Log.Encoder(name = "Right Master Encoder")
  private RelativeEncoder rightEncoder = rightDriveMaster.getEncoder();
  public String driveMode = "TeleOp";

  public AHRS gyro = new AHRS(SPI.Port.kMXP);
  
  public subDriveTrain() {
    SetMotorSettings();
    gyro.reset();
    gyro.calibrate();
  }

  @Override
  public void periodic() {  }

  private void SetMotorSettings() {
    leftDriveMaster.restoreFactoryDefaults();
    leftDriveSlave.restoreFactoryDefaults();

    leftDriveMaster.setSmartCurrentLimit(30);
    leftDriveSlave.setSmartCurrentLimit(30);

    leftDriveMaster.setInverted(DriveSystem.LeftDriveInverted);

    leftDriveMaster.setIdleMode(IdleMode.kCoast);
    leftDriveSlave.setIdleMode(IdleMode.kCoast);

    leftDriveSlave.follow(leftDriveMaster, false);

    rightDriveMaster.restoreFactoryDefaults();
    rightDriveSlave.restoreFactoryDefaults();

    rightDriveMaster.setSmartCurrentLimit(30);
    rightDriveSlave.setSmartCurrentLimit(30);

    rightDriveMaster.setInverted(DriveSystem.RightDriveInverted);

    rightDriveMaster.setIdleMode(IdleMode.kCoast);
    rightDriveSlave.setIdleMode(IdleMode.kCoast);

    rightDriveSlave.follow(rightDriveMaster, false);
  }

  //#region Drive Methods
  public void tankDrive(final XboxController driver) {
    driveMode = "TeleOp";
    driveTrain.setDeadband(0.08);
    driveTrain.tankDrive(driver.getLeftY() * DriveSystem.ManualSpeedFactor, driver.getRightY() * DriveSystem.ManualSpeedFactor);
  }

  public void autoTurn(double rot) {
    driveMode = "Auto";
    driveTrain.arcadeDrive(0, MathUtil.clamp(-rot, DriveSystem.AutoMinSpeed, DriveSystem.AutoMaxSpeed));
    System.out.println(MathUtil.clamp(-rot, DriveSystem.AutoMinSpeed, DriveSystem.AutoMaxSpeed)  + " " + getGyroHeading());
  }

  public void autoDrive(double speed) {
    driveMode = "Auto";
    driveTrain.arcadeDrive(MathUtil.clamp(-speed, DriveSystem.AutoMinSpeed, DriveSystem.AutoMaxSpeed), 0);
  }

  public void stopDrive() {
    leftDriveMaster.stopMotor();
    rightDriveMaster.stopMotor();
  }

  public void DriveLock() {
    leftDriveMaster.setIdleMode(IdleMode.kBrake);
    leftDriveSlave.setIdleMode(IdleMode.kBrake);
    rightDriveMaster.setIdleMode(IdleMode.kBrake);
    rightDriveSlave.setIdleMode(IdleMode.kBrake);
  }

  public void DriveUnlock() {
    leftDriveMaster.setIdleMode(IdleMode.kCoast);
    leftDriveSlave.setIdleMode(IdleMode.kCoast);
    rightDriveMaster.setIdleMode(IdleMode.kCoast);
    rightDriveSlave.setIdleMode(IdleMode.kCoast);
  }
  //#endregion

  //#region Gryo Methods
  public void setGyroZeroYaw(){
    gyro.zeroYaw();
  }

  public double getGyroHeading(){
    return Math.IEEEremainder(gyro.getAngle(), 360) * (DriveSystem.GyroReversed ? -1.0 : 1.0);
  }
  //#endregion

  //#region Encoder Methods
  public void setEncoders(){
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);
  }

  public double getLeftSideEncoderValue(){
    return -leftEncoder.getPosition();
  }

  public double getRightSideEncoderValue(){
    return -rightEncoder.getPosition();
  }

  public double getAverageEncoderDistance(){
    return (getLeftSideEncoderValue() + getRightSideEncoderValue()) / 2.0;
  }
  //#endregion
}