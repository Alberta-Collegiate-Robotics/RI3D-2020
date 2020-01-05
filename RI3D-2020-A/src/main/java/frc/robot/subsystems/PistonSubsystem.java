/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PistonSubsystem extends SubsystemBase {
	/**
	 * Creates a new ExampleSubsystem.
	 */

	private Solenoid solenoid;

	public PistonSubsystem(Solenoid solenoid) {
		// Reference solenoid
		this.solenoid = solenoid;
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public boolean getState() {
		return solenoid.get();
	}

	/**
	 * Wraps the internal solenoid
	 * @param speed double between [-1, 1]
	 */
	public void setState(boolean open) {
		this.solenoid.set(open);
	}
}