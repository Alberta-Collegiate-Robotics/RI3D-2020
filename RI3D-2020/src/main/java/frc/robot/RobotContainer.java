/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.ExampleCommand;
import frc.robot.commands.ActivateMotor;
//import frc.robot.commands.TogglePiston;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.DifferentialDriveTrain;
import frc.robot.subsystems.MotorSubsystem;
//import frc.robot.subsystems.PistonSubsystem;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

//import edu.wpi.first.wpilibj.Solenoid;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
	// The robot's subsystems and commands are defined here...
	private final ExampleSubsystem exampleSubsystem;
	private final ExampleCommand autoCommand;

	private final SpeedController lbMotor;
	private final SpeedController lfMotor;
	private final SpeedController rbMotor;
	private final SpeedController rfMotor;

	private final SpeedController shooterMotor;
	private final SpeedController intakeMotor;

	private final SpeedController hopperMotor;

	// private final SpeedController elevatorUp;
	// private final SpeedController elevatorDown;

	//private final SpeedController controlPanelMotor;

	private final DifferentialDriveTrain driveTrainSubsystem;
	private final MotorSubsystem shooterSubsystem;
	private final MotorSubsystem intakeSubsystem;
	private final MotorSubsystem hopperSubsystem;
	//private final MotorSubsystem controlPanelSubsystem;
	// private final MotorSubsystem elevatorUpSubsystem;
	// private final MotorSubsystem elevatorDownSubsystem;

	private final GenericHID mainController;
	private final Button shooterButton;
	//private final Button controlPanelButton;

	/**
	 * The container for the robot.  Contains subsystems, OI devices, and commands.
	 */
	public RobotContainer() {
		// Create motor objects
		this.lbMotor = new WPI_TalonSRX(Constants.lbMotorPort);
		this.lfMotor = new WPI_TalonSRX(Constants.lfMotorPort);
		this.rbMotor = new WPI_TalonSRX(Constants.rbMotorPort);
		this.rfMotor = new WPI_TalonSRX(Constants.rfMotorPort);

		this.shooterMotor = new VictorSP(Constants.shooterMotorPort);
		this.intakeMotor = new VictorSP(Constants.intakeMotorPort);

		this.hopperMotor = new VictorSP(Constants.hopperMotorPort);

		// this.elevatorUp = new WPI_TalonSRX(Constants.shooterMotorPort);
		// this.elevatorDown = new WPI_TalonSRX(Constants.intakeMotorPort);

		//this.controlPanelMotor = new Spark(Constants.controlPanelPort);

		// Initalize subsystems and commands
		this.exampleSubsystem = new ExampleSubsystem();
		
		// this.elevatorUpSubsystem = new MotorSubsystem(elevatorUp);
		// this.elevatorDownSubsystem = new MotorSubsystem(elevatorDown);
		
		this.driveTrainSubsystem = new DifferentialDriveTrain(this.lbMotor, this.lfMotor, this.rbMotor, this.rfMotor);

		this.shooterSubsystem = new MotorSubsystem(this.shooterMotor);
		this.intakeSubsystem = new MotorSubsystem(this.intakeMotor);

		this.hopperSubsystem = new MotorSubsystem(this.hopperMotor);

		//this.controlPanelSubsystem = new MotorSubsystem(this.controlPanelMotor);

		this.autoCommand = new ExampleCommand(exampleSubsystem);

		// Define IO devices
		this.mainController = new XboxController(Constants.mainControllerPort);
		// Define button objects
		this.shooterButton = new JoystickButton(this.mainController, Constants.shooterButtonPort);
		//this.controlPanelButton = new JoystickButton(this.mainController, Constants.controlPanelButtonPort);

		// Create/define default drive command
		this.driveTrainSubsystem.setDefaultCommand(new RunCommand(() -> this.driveTrainSubsystem.arcadeDrive(this.mainController.getX(), this.mainController.getY()), this.driveTrainSubsystem));
		// Set the intake and hopper to be constantly running
		this.intakeSubsystem.setDefaultCommand(new ActivateMotor(this.intakeSubsystem, Constants.intakeMotorSpeed));
		this.hopperSubsystem.setDefaultCommand(new ActivateMotor(this.hopperSubsystem, Constants.hopperMotorSpeed));

		// TODO attach command to height subsystem

		// Configure the button bindings
		this.configureButtonBindings();
	}

	/**
	 * Use this method to define your button->command mappings.  Buttons can be created by
	 * instantiating a {@link GenericHID} or one of its subclasses ({@link
	 * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
	 * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
	 */
	private void configureButtonBindings() {
		this.shooterButton.whenHeld(new ActivateMotor(shooterSubsystem, Constants.shooterMotorSpeed));
		//this.controlPanelButton.whenPressed(new ActivateMotor(controlPanelSubsystem, Constants.controlPanelSpeed));
	}


	/**
	 * Use this to pass he autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	public Command getAutonomousCommand() {
		// An ExampleCommand will run in autonomous
		return this.autoCommand;
	}
}