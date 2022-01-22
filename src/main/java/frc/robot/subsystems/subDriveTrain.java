package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants.DriveSystem;
import frc.robot.helpers.Gyro;
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

  private RelativeEncoder left1Encoder = leftDriveMaster.getEncoder();
  private RelativeEncoder left2Encoder = leftDriveSlave.getEncoder();
  private RelativeEncoder right1Encoder = rightDriveMaster.getEncoder();
  private RelativeEncoder right2Encoder = rightDriveSlave.getEncoder();
  public String driveMode = "TeleOp";

  public Gyro gyro = new Gyro();
  
  public subDriveTrain() {
    SetMotorSettings();
    SetPIDSettings();
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

  private void SetPIDSettings() {
    leftPIDController = leftDriveMaster.getPIDController();
    rightPIDController = rightDriveMaster.getPIDController();

    leftPIDController.setP(DriveSystem.PValue);
    leftPIDController.setI(DriveSystem.IValue);
    leftPIDController.setD(DriveSystem.DValue);
    leftPIDController.setIZone(DriveSystem.IZValue);
    leftPIDController.setFF(DriveSystem.FFalue);
    leftPIDController.setOutputRange(DriveSystem.AutoMinSpeed, DriveSystem.AutoMaxSpeed);

    rightPIDController.setP(DriveSystem.PValue);
    rightPIDController.setI(DriveSystem.IValue);
    rightPIDController.setD(DriveSystem.DValue);
    rightPIDController.setIZone(DriveSystem.IZValue);
    rightPIDController.setFF(DriveSystem.FFalue);
    rightPIDController.setOutputRange(DriveSystem.AutoMinSpeed, DriveSystem.AutoMaxSpeed);
  }
  public void DriveStraightUsingSparkMaxPID(final double rotations) {
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
    driveTrain.tankDrive(driver.getLeftY() * DriveSystem.ManualMaxSpeed, driver.getRightY() * DriveSystem.ManualMaxSpeed);
    driveTrain.setDeadband(0.08);
  }

  public void autoTurn(double rot) {
    driveMode = "Auto";
    driveTrain.arcadeDrive(0, -rot);
  }

  public void arcadeDrive(double fwd, double rot){
    driveMode = "TeleOp";
    driveTrain.arcadeDrive(fwd * DriveSystem.ManualMaxSpeed, -rot * DriveSystem.ManualMaxSpeed);
  }

  public void ResetEncoders(){
    left1Encoder.setPosition(0);
    left2Encoder.setPosition(0);
    right1Encoder.setPosition(0);
    right2Encoder.setPosition(0);
  }
  
  public void ResetGyro(){
    gyro.zeroHeading();
  }

  public double GetHeading(){
    return gyro.getHeading();
  }

  public void DriveStraightUsingEncoders(double speed){
    driveMode = "Autonomous";
    double error = getLeftSideEncoderValue() - getRightSideEncoderValue();
    double turnPower = DriveSystem.AutoTurnMaxSpeed * -error;
    driveTrain.arcadeDrive(speed, turnPower, false);
  }

  public void SimpleMove(double speed){
    driveMode = "Autonomous";
    driveTrain.tankDrive(speed, -speed);
  }

  public void DriveStraightUsingGyro(double speed, int heading){
    driveMode = "Autonomous";
    double error = gyro.getHeading() - heading;
    double turnPower = DriveSystem.AutoTurnMaxSpeed * -error;
    driveTrain.arcadeDrive(speed, turnPower);
  }

  public void GyroTurnTowardsAngle(double angle){
    driveMode = "Autonomous";
    if(angle > 0) {
      double error = angle - gyro.getHeading();
      driveTrain.tankDrive(1 * error, 1 * error);
    }
    else {
      double error = Math.abs(angle) - gyro.getHeading();
      driveTrain.tankDrive(-1 * error, -1 * error);
    }
  }

  public double getLeftSideEncoderValue(){
    return Math.abs(left1Encoder.getPosition());
  }

  public double getRightSideEncoderValue(){
    return Math.abs(right1Encoder.getPosition());
  }

  public double getAverageEncoderDistance(){
    return (getLeftSideEncoderValue() + getRightSideEncoderValue()) / 2.0;
  }
}
