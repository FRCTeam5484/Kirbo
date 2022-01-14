package frc.robot;

public final class Constants {
    private final static class HardwareId{
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
        public final static boolean LeftDriveInverted = false;
        public final static boolean RightDriveInverted = true;
        // Speed
        public final static double ManualMaxSpeed = 0.7;
        public final static double AutoMaxSpeed = 0.3;
    }
}
