/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.ActivateMotor;
import frc.robot.commands.ActivateMotorLambda;
import frc.robot.commands.ActivatePiston;
import frc.robot.commands.ActivateArcadeDrive;

import frc.robot.subsystems.DifferentialDriveTrain;
import frc.robot.subsystems.MotorSubsystem;
import frc.robot.subsystems.PistonSubsystem;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Preferences;

//import edu.wpi.first.wpilibj.Solenoid;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
	// The robot's subsystems and commands are defined here...

	private final SpeedController lbMotor;
	private final SpeedController lfMotor;
	private final SpeedController rbMotor;
	private final SpeedController rfMotor;

	private final SpeedController leftDriveMotors;
	private final SpeedController rightDriveMotors;

	private final SpeedController shooterMotorA;
	private final SpeedController shooterMotorB;
	private final SpeedController shooterMotors;

	private final SpeedController intakeMotor;
	private final SpeedController hopperMotor;

	private final DoubleSolenoid piston;
	//private final SpeedController controlPanelMotor;

	private final SpeedController upperElevatorLeftMotor;
	private final SpeedController upperElevatorRightMotor;
	private final SpeedController lowerElevatorLeftMotor;
	private final SpeedController lowerElevatorRightMotor;

	private final SpeedController upperElevatorMotors;
	private final SpeedController lowerElevatorMotors;

	private final DifferentialDriveTrain driveTrainSubsystem;
	private final MotorSubsystem shooterSubsystem;
	private final MotorSubsystem intakeSubsystem;
	private final MotorSubsystem hopperSubsystem;

	//private final ElevatorSubsystem elevatorSubsystem;
	private final MotorSubsystem upperElevatorSubsystem;
	private final MotorSubsystem lowerElevatorSubsystem;

	private final PistonSubsystem pistonSubsystem;
	//private final MotorSubsystem controlPanelSubsystem;

	private final GenericHID mainController;
	private final GenericHID secondaryController;

	private final Button shooterButton;
	private final Button intakeButton;
	private final Button hopperButton;
	private final Button hopperBurstButton;
	
	private final Button pistonButton;

	private final Button upperElevatorUpButton;
	private final Button upperElevatorDownButton;
	private final Button lowerElevatorUpButton;
	private final Button lowerElevatorDownButton;

	//private final Button controlPanelButton;

	private final Command autoCommand;

	private final Preferences preferences;

	/**
	 * The container for the robot.  Contains subsystems, OI devices, and commands.
	 */
	public RobotContainer() {

		// Start camera server
		CameraServer.getInstance().startAutomaticCapture();

		// Retrieve Preferences instance
		this.preferences = Preferences.getInstance();

		// Create motor objects
		this.lbMotor = new WPI_TalonSRX(Constants.lbMotorPort);
		this.lfMotor = new WPI_TalonSRX(Constants.lfMotorPort);
		this.rbMotor = new WPI_TalonSRX(Constants.rbMotorPort);
		this.rfMotor = new WPI_TalonSRX(Constants.rfMotorPort);

		this.leftDriveMotors = new SpeedControllerGroup(this.lbMotor, this.lfMotor);
		this.rightDriveMotors = new SpeedControllerGroup(this.rbMotor, this.rfMotor);

		this.shooterMotorA = new VictorSP(Constants.shooterMotorPortA);
		this.shooterMotorB = new VictorSP(Constants.shooterMotorPortB);

		this.shooterMotors = new SpeedControllerGroup(shooterMotorA, shooterMotorB);

		this.intakeMotor = new VictorSP(Constants.intakeMotorPort);
		this.hopperMotor = new VictorSP(Constants.hopperMotorPort);

		this.upperElevatorLeftMotor = new VictorSP(Constants.upperElevatorLeftMotorPort);
		this.upperElevatorRightMotor = new VictorSP(Constants.upperElevatorRightMotorPort);
		this.lowerElevatorLeftMotor = new VictorSP(Constants.lowerElevatorLeftMotorPort);
		this.lowerElevatorRightMotor = new VictorSP(Constants.lowerElevatorRightMotorPort);

		this.upperElevatorMotors = new SpeedControllerGroup(upperElevatorLeftMotor, upperElevatorRightMotor);
		this.lowerElevatorMotors = new SpeedControllerGroup(lowerElevatorLeftMotor, lowerElevatorRightMotor);

		// Create solenoid object
		this.piston = new DoubleSolenoid(Constants.pistonSolenoidPortA, Constants.pistonSolenoidPortB);

		// Create testing speedcontroller
		// everythingTest = new SpeedControllerGroup(
		// 	lbMotor, lfMotor, rbMotor, rfMotor,
		// 	shooterMotors, intakeMotor, hopperMotor,
		// 	upperElevatorLeftMotor, upperElevatorRightMotor,
		// 	lowerElevatorLeftMotor, lowerElevatorRightMotor
		// );

		//this.controlPanelMotor = new Spark(Constants.controlPanelPort);

		// Initalize subsystems and commands
		this.driveTrainSubsystem = new DifferentialDriveTrain(this.leftDriveMotors, this.rightDriveMotors);

		this.shooterSubsystem = new MotorSubsystem(this.shooterMotors);
		this.intakeSubsystem = new MotorSubsystem(this.intakeMotor);

		this.hopperSubsystem = new MotorSubsystem(this.hopperMotor);

		this.pistonSubsystem = new PistonSubsystem(this.piston);

		//this.elevatorSubsystem = new ElevatorSubsystem(this.upperElevatorMotors, this.lowerElevatorMotors);

		this.upperElevatorSubsystem = new MotorSubsystem(this.upperElevatorMotors);
		this.lowerElevatorSubsystem = new MotorSubsystem(this.lowerElevatorMotors);

		//this.controlPanelSubsystem = new MotorSubsystem(this.controlPanelMotor);

		//this.motorTestSubsystem = new MotorSubsystem(everythingTest);

		// Setup autocommand
		Command forward = new ActivateArcadeDrive(this.driveTrainSubsystem, 1.0, 0).withTimeout(Constants.autonomousForwardTime);
		Command turn = new ActivateArcadeDrive(this.driveTrainSubsystem, 0, 1.0).withTimeout(Constants.autonomousTurnTime);
		Command shoot = new ActivateMotor(this.shooterSubsystem, 1.0).withTimeout(Constants.autonomousShootTime);

		this.autoCommand = new SequentialCommandGroup(forward, turn, shoot);

		// Define IO devices
		this.mainController = new Joystick(Constants.mainControllerPort);
		this.secondaryController = new XboxController(Constants.secondaryControllerPort);

		// Define button objects
		this.shooterButton = new JoystickButton(this.mainController, Constants.shooterButtonPort);
		this.intakeButton = new JoystickButton(this.mainController, Constants.intakeButtonPort);
		this.hopperButton = new JoystickButton(this.mainController, Constants.hopperButtonPort);
		this.hopperBurstButton = new JoystickButton(this.mainController, Constants.hopperBurstButtonPort);

		this.pistonButton = new JoystickButton(this.mainController, Constants.pistonButtonPort);

		this.upperElevatorUpButton = new JoystickButton(this.mainController, Constants.upperElevatorUpButtonPort);
		this.upperElevatorDownButton = new JoystickButton(this.mainController, Constants.upperElevatorDownButtonPort);
		this.lowerElevatorUpButton = new JoystickButton(this.mainController, Constants.lowerElevatorUpButtonPort);
		this.lowerElevatorDownButton = new JoystickButton(this.mainController, Constants.lowerElevatorDownButtonPort);

		

		//this.controlPanelButton = new JoystickButton(this.mainController, Constants.controlPanelButtonPort);

		// Create/define default drive command
		this.driveTrainSubsystem.setDefaultCommand(new RunCommand(() -> this.driveTrainSubsystem.arcadeDrive(this.mainController.getY(), this.mainController.getX()), this.driveTrainSubsystem));
		// Set the intake and hopper to be constantly running
		//this.intakeSubsystem.setDefaultCommand(new ActivateMotor(this.intakeSubsystem, Constants.intakeMotorSpeed));

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

		// Link all motors to shooter button for testing
		// Create activatemotor command, manually link duplicate subsystems
		// ActivateMotor motorTest = new ActivateMotor(this.motorTestSubsystem, preferences.getDouble("motorTestSpeed", Constants.motorTestSpeed));
		// motorTest.addRequirements(
		// 	this.driveTrainSubsystem,
		// 	this.shooterSubsystem, this.intakeSubsystem, this.hopperSubsystem, this.upperElevatorSubsystem, this.lowerElevatorSubsystem
		// );
		// // Create InstantCommand that schedules the motorTest command, but changes the speed first
		// this.shooterButton.whenHeld(
		// 	new InstantCommand(() -> {
		// 		motorTest.setSpeed(preferences.getDouble("motorTestSpeed", Constants.motorTestSpeed));
		// 		motorTest.schedule();
		// 	}
		// 	)
		// );

		this.shooterButton.toggleWhenPressed(new ActivateMotorLambda(shooterSubsystem, () -> this.mainController.getRawAxis(3)));
		this.intakeButton.toggleWhenPressed(new ActivateMotor(intakeSubsystem, Constants.intakeMotorSpeed));
		this.hopperButton.whenHeld(new ActivateMotor(hopperSubsystem, Constants.hopperMotorSpeed));
		this.hopperBurstButton.whenPressed(new ActivateMotor(hopperSubsystem, Constants.hopperMotorSpeed).withTimeout(Constants.hopperBurstTime));

		this.upperElevatorUpButton.whenHeld(new ActivateMotor(upperElevatorSubsystem, Constants.upperElevatorSpeed));
		this.upperElevatorDownButton.whenHeld(new ActivateMotor(upperElevatorSubsystem, -Constants.upperElevatorSpeed));
		this.lowerElevatorUpButton.whenHeld(new ActivateMotor(lowerElevatorSubsystem, Constants.upperElevatorSpeed));
		this.lowerElevatorDownButton.whenHeld(new ActivateMotor(lowerElevatorSubsystem, -Constants.upperElevatorSpeed));

		this.pistonButton.whenPressed(new ActivatePiston(pistonSubsystem, 1)).whenReleased(new ActivatePiston(pistonSubsystem, -1));
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