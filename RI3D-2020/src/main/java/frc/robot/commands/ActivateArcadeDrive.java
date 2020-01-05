/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.DifferentialDriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class ActivateArcadeDrive extends CommandBase {

	private final DifferentialDriveTrain subsystem;
	private final double xSpeed;
	private final double zRotation;

	/**
	 * Activates the given DriveTrain using arcade drive parameters
	 * should usually be used with a timeout
	 * @param subsystem The drivetrain used by this command.
	 * @param xSpeed the xSpeed passed to the driveTrain, forward speed
	 * @param zRotation the zRotation passed to the driveTrain, rotational speed
	 */
	public ActivateArcadeDrive(DifferentialDriveTrain subsystem, double xSpeed, double zRotation) {
		// Reference subsystem
		this.subsystem = subsystem;
		// Reference speed
		this.xSpeed = xSpeed;
		this.zRotation = zRotation;
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(subsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		this.subsystem.arcadeDrive(this.xSpeed, this.zRotation);
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		this.subsystem.arcadeDrive(0, 0);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}