package frc.helpers;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Gyro
{
	private ADXRS450_Gyro g;
	
	public Gyro(){
		g = new ADXRS450_Gyro();
		g.calibrate();
	}

	/**
	 * 
	 * @return angle 0-359 degrees
	 */
	public double get(){
		return ((g.getAngle()%360+360)%360);
	}
	
	public double getRawAngle(){
		return g.getAngle();
	}

    public double getRawRate(){
        return g.getRate();
    }
	
	/**
	 * 
	 * @return angle 0-359 degrees as an integer
	 */
	public int getInt(){
		return (int)((g.getAngle()%360+360)%360);
	}
	
	/**
	 * Reset the gyro.
	 */
	public void reset(){
		g.reset();
	}	
}