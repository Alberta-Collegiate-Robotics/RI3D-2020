/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.MotorSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class ActivateMotor extends CommandBase {

	private final MotorSubsystem subsystem;
	private double speed;

	/**
	 * Creates a new ExampleCommand.
	 *
	 * @param subsystem The subsystem used by this command.
	 */
	public ActivateMotor(MotorSubsystem subsystem, double speed) {
		// Reference subsystem
		this.subsystem = subsystem;
		// Reference speed
		this.speed = speed;
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(subsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		this.subsystem.setSpeed(this.speed);
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		this.subsystem.setSpeed(0);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}

	/**
	 * Returns the speed that the motor will be set to
	 * @return double [-1, 1]
	 */
	public double getSpeed() {
		return this.speed;
	}

	/**
	 * Sets the speed the motor will bet set to upon initialize
	 * @param speed double [-1, 1]
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

}