/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.MotorSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class ActivateMotorLambda extends CommandBase {

	private final MotorSubsystem subsystem;
	private DoubleSupplier speedCalculator;

	/**
	 * Creates a new ExampleCommand, uses a 
	 *
	 * @param subsystem The subsystem used by this command.
	 * @param speedCalculator a DoubleSupplier used to dynamically change motor speed
	 */
	public ActivateMotorLambda(MotorSubsystem subsystem, DoubleSupplier speedCalculator) {
		// Reference subsystem
		this.subsystem = subsystem;
		// Reference speed
		this.speedCalculator = speedCalculator;
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(subsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		this.subsystem.setSpeed(this.speedCalculator.getAsDouble());
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		this.subsystem.setSpeed(this.speedCalculator.getAsDouble());
	}

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

}