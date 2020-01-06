/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorSubsystem extends SubsystemBase {
	/**
	 * Creates a new ExampleSubsystem.
	 */

	private SpeedController motor;

	public MotorSubsystem(SpeedController motor) {
		// Reference motor
		this.motor = motor;
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	/**
	 * Sets the speed of the motor controller
	 * @param speed double between [-1, 1]
	 */
	public void setSpeed(double speed) {
		this.motor.set(speed);
	}

	public double getMotorSpeed() {
		return this.motor.get();
	}
}