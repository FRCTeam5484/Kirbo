package frc.robot.helpers;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.Constants.DriveSystem;

public class Gyro 
{
    public AHRS ahrs;
    
    public Gyro(){
        ahrs = new AHRS(SPI.Port.kMXP);
        ahrs.calibrate();
	    ahrs.reset();
    }

    public boolean isConnected(){
        return ahrs.isConnected();
    }
      
    public void zeroHeading() {
        ahrs.zeroYaw();
    }
    
    public double getHeading() {
        return Math.IEEEremainder(ahrs.getAngle(), 360) * (DriveSystem.GyroReversed ? -1.0 : 1.0);
    }
    
    public double getAngle() {
    return ahrs.getAngle();
    }
    
    public double getRate() {
        return ahrs.getRate();
    }

    public double getTurnRate() {
        return ahrs.getRate() * (DriveSystem.GyroReversed ? -1.0 : 1.0);
    }

    public double getCompassHeading(){
        return ahrs.getCompassHeading();
    }
}
