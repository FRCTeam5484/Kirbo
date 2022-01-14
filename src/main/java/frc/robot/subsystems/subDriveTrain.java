package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants.DriveSystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class subDriveTrain extends SubsystemBase {
  public CANSparkMax leftDriveMaster = new CANSparkMax(DriveSystem.LeftMasterMotorId, MotorType.kBrushless);
  public CANSparkMax leftDriveSlave = new CANSparkMax(DriveSystem.LeftSlaveMotorId, MotorType.kBrushless);
  public CANSparkMax rightDriveMaster = new CANSparkMax(DriveSystem.RightMasterMotorId, MotorType.kBrushless);
  public CANSparkMax rightDriveSlave = new CANSparkMax(DriveSystem.RightSlaveMotorId, MotorType.kBrushless);
  public DifferentialDrive driveTrain = new DifferentialDrive(leftDriveMaster, rightDriveMaster);

  private SparkMaxPIDController leftPIDController;
  private SparkMaxPIDController rightPIDController;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

  public RelativeEncoder left1Encoder = leftDriveMaster.getEncoder();
  public RelativeEncoder left2Encoder = leftDriveSlave.getEncoder();
  public RelativeEncoder right1Encoder = rightDriveMaster.getEncoder();
  public RelativeEncoder right2Encoder = rightDriveSlave.getEncoder();
  public String driveMode = "TeleOp";
  
  public subDriveTrain() {
    SetMotorSettings();
    SetPIDSettings();
  }

  @Override
  public void periodic() {}

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

  private void SetPIDSettings() {
    leftPIDController = leftDriveMaster.getPIDController();
    rightPIDController = rightDriveMaster.getPIDController();

    kP = 0.1;
    kI = 1e-4;
    kD = 1;
    kIz = 0;
    kFF = .1;
    kMaxOutput = .5;
    kMinOutput = -.5;

    leftPIDController.setP(kP);
    leftPIDController.setI(kI);
    leftPIDController.setD(kD);
    leftPIDController.setIZone(kIz);
    leftPIDController.setFF(kFF);
    leftPIDController.setOutputRange(kMinOutput, kMaxOutput);

    rightPIDController.setP(kP);
    rightPIDController.setI(kI);
    rightPIDController.setD(kD);
    rightPIDController.setIZone(kIz);
    rightPIDController.setFF(kFF);
    rightPIDController.setOutputRange(kMinOutput, kMaxOutput);
  }
  public void DriveStraightForRotations(final double rotations) {
    driveMode = "Auto";
    leftPIDController.setReference(rotations, CANSparkMax.ControlType.kPosition);
    rightPIDController.setReference(-rotations, CANSparkMax.ControlType.kPosition);
    SmartDashboard.putNumber("Drive Set Point", rotations);
  }

  public void stopDrive() {
    driveMode = "TeleOp";
    leftDriveMaster.stopMotor();
    rightDriveMaster.stopMotor();
  }

  public void DriveLock() {
    leftDriveMaster.stopMotor();
    rightDriveMaster.stopMotor();
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

  public void tankDrive(final XboxController driver) {
    driveMode = "TeleOp";
    driveTrain.tankDrive(-driver.getLeftY() * DriveSystem.ManualMaxSpeed, driver.getRightY() * DriveSystem.ManualMaxSpeed);
    driveTrain.setDeadband(0.08);
  }

  public void ResetEncoders(){
    left1Encoder.setPosition(0);
    left2Encoder.setPosition(0);
    right1Encoder.setPosition(0);
    right2Encoder.setPosition(0);
  }

  public void DriveStraightUsingEncoders(){
    double error = left1Encoder.getPosition() - right1Encoder.getPosition();
    double turnPower = .04 * -error;
    driveTrain.arcadeDrive(DriveSystem.AutoMaxSpeed, turnPower, false);
  }

  public void SimpleMove(double speed){
    driveTrain.tankDrive(speed, -speed);
  }
}
