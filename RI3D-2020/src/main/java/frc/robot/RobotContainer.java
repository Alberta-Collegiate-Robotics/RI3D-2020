/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.ActivateMotor;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.ToggleMotor;
import frc.robot.commands.TogglePiston;
import frc.robot.commands.ActivateArcadeDrive;

import frc.robot.subsystems.DifferentialDriveTrain;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.MotorSubsystem;
import frc.robot.subsystems.PistonSubsystem;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.cameraserver.CameraServer;

//import edu.wpi.first.wpilibj.Solenoid;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
	// The robot's subsystems and commands are defined here...
	private final ExampleCommand autoCommand;

	private final SpeedController lbMotor;
	private final SpeedController lfMotor;
	private final SpeedController rbMotor;
	private final SpeedController rfMotor;

	private final SpeedController leftDriveMotors;
	private final SpeedController rightDriveMotors;

	private final SpeedController shooterMotor;
	private final SpeedController intakeMotor;
	private final SpeedController hopperMotor;

	private final SpeedController can4, can5;
	private final SpeedController pwm3, pwm4;

	private final DoubleSolenoid piston;
	//private final SpeedController controlPanelMotor;

	private final SpeedController upperElevatorLeft;
	private final SpeedController upperElevatorRight;
	private final SpeedController lowerElevatorLeft;
	private final SpeedController lowerElevatorRight;

	private final SpeedControllerGroup upperElevator, lowerElevator;

	private final DifferentialDriveTrain driveTrainSubsystem;
	private final MotorSubsystem shooterSubsystem;
	private final MotorSubsystem intakeSubsystem;
	private final MotorSubsystem hopperSubsystem;

	private final ElevatorSubsystem elevatorSubsystem;

	private final PistonSubsystem pistonSubsystem;
	//private final MotorSubsystem controlPanelSubsystem;

	private final ExampleSubsystem exampleSubsystem;

	private final GenericHID mainController;
	private final Button shooterButton;
	private final Button pistonButton;

	private final Button upperElevatorUpButton;
	private final Button upperElevatorDownButton;
	private final Button lowerElevatorUpButton;
	private final Button lowerElevatorDownButton;

	private final Button toggleHopperButton;
	//private final Button controlPanelButton;

	private final SpeedControllerGroup everythingTest;

	/**
	 * The container for the robot.  Contains subsystems, OI devices, and commands.
	 */
	public RobotContainer() {
		// Create motor objects
		this.lbMotor = new WPI_TalonSRX(Constants.lbMotorPort);
		this.lfMotor = new WPI_TalonSRX(Constants.lfMotorPort);
		this.rbMotor = new WPI_TalonSRX(Constants.rbMotorPort);
		this.rfMotor = new WPI_TalonSRX(Constants.rfMotorPort);

		this.leftDriveMotors = new SpeedControllerGroup(this.lbMotor, this.lfMotor);
		this.rightDriveMotors = new SpeedControllerGroup(this.rbMotor, this.rfMotor);

		this.shooterMotor = new VictorSP(Constants.shooterMotorPort);
		this.intakeMotor = new VictorSP(Constants.intakeMotorPort);

		this.hopperMotor = new VictorSP(Constants.hopperMotorPort);

		this.upperElevatorLeft = new VictorSP(Constants.elevatorUpLeftPort);
		this.upperElevatorRight = new VictorSP(Constants.elevatorUpRightPort);
		this.lowerElevatorLeft = new VictorSP(Constants.elevatorDownLeftPort);
		this.lowerElevatorRight = new VictorSP(Constants.elevatorDownRightPort);

		this.upperElevator = new SpeedControllerGroup(upperElevatorLeft, upperElevatorRight);
		this.lowerElevator = new SpeedControllerGroup(lowerElevatorLeft, lowerElevatorRight);

		can4 = new WPI_TalonSRX(4);
		can5 = new WPI_TalonSRX(5);

		pwm3 = new VictorSP(3);
		pwm4 = new VictorSP(4);

		// Create solenoid object
		this.piston = new DoubleSolenoid(Constants.pistonSolenoidPortA, Constants.pistonSolenoidPortB);

		everythingTest = new SpeedControllerGroup(shooterMotor, intakeMotor, hopperMotor, can4, can5, pwm3, pwm4);

		//this.controlPanelMotor = new Spark(Constants.controlPanelPort);

		// Initalize subsystems and commands
		this.driveTrainSubsystem = new DifferentialDriveTrain(this.leftDriveMotors, this.rightDriveMotors);

		this.shooterSubsystem = new MotorSubsystem(this.shooterMotor);
		this.intakeSubsystem = new MotorSubsystem(this.intakeMotor);

		this.hopperSubsystem = new MotorSubsystem(this.hopperMotor);

		this.pistonSubsystem = new PistonSubsystem(this.piston);

		this.elevatorSubsystem = new ElevatorSubsystem(upperElevator, lowerElevator);

		//this.controlPanelSubsystem = new MotorSubsystem(this.controlPanelMotor);

		this.exampleSubsystem = new ExampleSubsystem();

		// Start camera server
		CameraServer.getInstance().startAutomaticCapture();

		// Setup autocommand
		//Command driveForward = new ActivateArcadeDrive(this.driveTrainSubsystem, 1.0, 0);
		//Command forward3s = driveForward.withTimeout(3);

		this.autoCommand = new ExampleCommand(exampleSubsystem);

		// Define IO devices
		this.mainController = new Joystick(Constants.mainControllerPort);
		// Define button objects
		this.shooterButton = new JoystickButton(this.mainController, Constants.shooterButtonPort);
		this.pistonButton = new JoystickButton(this.mainController, Constants.pistonButtonPort);

		this.upperElevatorUpButton = new JoystickButton(this.mainController, Constants.upperElevatorUpButtonPort);
		this.upperElevatorDownButton = new JoystickButton(this.mainController, Constants.upperElevatorDownButtonPort);
		this.lowerElevatorUpButton = new JoystickButton(this.mainController, Constants.lowerElevatorUpButtonPort);
		this.lowerElevatorDownButton = new JoystickButton(this.mainController, Constants.lowerElevatorDownButtonPort);

		this.toggleHopperButton = new JoystickButton(this.mainController, Constants.toggleHopperButtonPort);

		//this.controlPanelButton = new JoystickButton(this.mainController, Constants.controlPanelButtonPort);

		// Create/define default drive command
		this.driveTrainSubsystem.setDefaultCommand(new RunCommand(() -> this.driveTrainSubsystem.arcadeDrive(this.mainController.getY(), this.mainController.getX()), this.driveTrainSubsystem));
		// Set the intake and hopper to be constantly running
		this.intakeSubsystem.setDefaultCommand(new ActivateMotor(this.intakeSubsystem, Constants.intakeMotorSpeed));

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
		//this.shooterButton.whenHeld(new ActivateMotor(shooterSubsystem, Constants.shooterMotorSpeed));
		this.pistonButton.whenPressed(new TogglePiston(pistonSubsystem));

		this.shooterButton.whenHeld(new ActivateMotor(new MotorSubsystem(everythingTest), 0.5));

		//Yes, this is ugly. (Creating motor subsystem from elevator subsystem).
		MotorSubsystem upper = new MotorSubsystem(elevatorSubsystem.getUpperMotors()); 
		MotorSubsystem lower = new MotorSubsystem(elevatorSubsystem.getLowerMotors()); 

		this.upperElevatorUpButton.whenHeld(new ActivateMotor(upper, Constants.upperElevatorSpeed));
		this.upperElevatorDownButton.whenHeld(new ActivateMotor(upper, -Constants.upperElevatorSpeed));
		this.lowerElevatorUpButton.whenHeld(new ActivateMotor(lower, Constants.upperElevatorSpeed));
		this.lowerElevatorDownButton.whenHeld(new ActivateMotor(lower, -Constants.upperElevatorSpeed));

		this.toggleHopperButton.whenPressed(new ToggleMotor(hopperSubsystem, Constants.hopperMotorSpeed));

		//this.controlPanelButton.whenPressed(new ActivateMotor(controlPanelSubsystem, Constants.controlPanelSpeed));
	}


	/**
	 * Use this to pass he autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	public Command getAutonomousCommand() {
		// An ExampleCommand will run in autonomous
		//return this.autoCommand;
		return null;
	}
}