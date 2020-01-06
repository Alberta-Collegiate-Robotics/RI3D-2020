/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PistonSubsystem extends SubsystemBase {
	/**
	 * Creates a new ExampleSubsystem.
	 */

	private DoubleSolenoid solenoid;

	public PistonSubsystem(DoubleSolenoid solenoid) {
		// Reference solenoid
		this.solenoid = solenoid;
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	/**
	 * Wraps the internal solenoid .get method
	 * @return The state of the internal solenoid, boolean
	 */
	public DoubleSolenoid.Value getState() {
		return solenoid.get();
	}

	public boolean getBooleanState() {
		return this.solenoid.get() == Value.kForward;
	}

	/**
	 * Wraps the internal solenoid .set method
	 * @param state boolean passed to internal solenoid
	 */
	public void setState(boolean state) {
		if(state) {
			this.solenoid.set(Value.kForward);
		} else {
			this.solenoid.set(Value.kReverse);
		}
	}
	public void setState(Value state) {
		this.solenoid.set(state);
	}
}