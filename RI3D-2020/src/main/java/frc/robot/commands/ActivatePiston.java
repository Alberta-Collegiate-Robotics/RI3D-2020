/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.PistonSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class ActivatePiston extends CommandBase {

	private final PistonSubsystem subsystem;

	private final int state;

	/**
	 * Creates a new ActivatePiston Command
	 * Activates the given Piston subsystem until the command is interrupted
	 *
	 * @param subsystem The subsystem used by this command.
	 */
	public ActivatePiston(PistonSubsystem subsystem, int state) {
		// Reference subsystem
		this.subsystem = subsystem;
		// Reference state
		this.state = state;
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(subsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		// Activate the piston
		this.subsystem.setState(this.state);
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		// Deactivate the piston
		this.subsystem.setState(0);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}