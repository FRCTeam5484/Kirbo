package frc.robot;

public final class Constants {
    public final static class HardwareId{
        // Controllers
        private final static int DriverOneId = 0;
        private final static int DriverTwoId = 1;
        private final static int ButtonBoxId = 2;
        
        // SparkMax
        private final static int DriveRightMaster = 1;
        private final static int DriveRightSlave = 2;
        private final static int DriveLeftMaster = 3;
        private final static int DriveLeftSlave = 4;

    }

    public final static class DriveControllers {
        public final static int DriverOne = HardwareId.DriverOneId;
        public final static int DriverTwo = HardwareId.DriverTwoId;
        public final static int ButtonBox = HardwareId.ButtonBoxId;
    }

    public final static class DriveSystem {
        // Ids
        public final static int RightMasterMotorId = HardwareId.DriveRightMaster;
        public final static int RightSlaveMotorId = HardwareId.DriveRightSlave;
        public final static int LeftMasterMotorId = HardwareId.DriveLeftMaster;
        public final static int LeftSlaveMotorId = HardwareId.DriveLeftSlave;
        // Inversion
        public final static boolean LeftDriveInverted = true;
        public final static boolean RightDriveInverted = false;
        // Speed
        public final static double ManualSpeedFactor = 0.7;
        public final static double AutoMaxSpeed = 0.70;
        public final static double AutoMinSpeed = -0.40;
        public final static double AutoTurnMaxSpeed = 0.70;
        // PID Turn Values
        public static final double TurnPValue = 0.06;
        public static final double TurnIValue = 0;
        public static final double TurnDValue = 0.0033;
        public static final double TurnToleranceDeg = 3.5;
        public static final double MaxTurnRateDegPerSec = 100;
        public static final double TurnRateToleranceDegPerSec = 10;
        // PID Drive Values
        public static final double DrivePValue = 0.20;
        public static final double DriveIValue = 0;
        public static final double DriveDValue = 0;
        public static final double DriveToleranceDis = 1;
        
        public static final double IZValue = 0;
        public static final double FFalue = 0.1;
        
        public static final double MaxTurnAccelerationDegPerSecSquared = 300;
        public static final boolean GyroReversed = false;
        public static final double EncoderTickToInch = 0.48;
    }
}