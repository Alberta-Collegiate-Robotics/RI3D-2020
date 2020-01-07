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
import edu.wpi.first.wpilibj2.command.button.POVButton;

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

	private final SpeedController elevatorMotorA;
	private final SpeedController elevatorMotorB;

	private final SpeedController elevatorMotors;

	private final DifferentialDriveTrain driveTrainSubsystem;
	private final MotorSubsystem shooterSubsystem;
	private final MotorSubsystem intakeSubsystem;
	private final MotorSubsystem hopperSubsystem;

	//private final ElevatorSubsystem elevatorSubsystem;
	private final MotorSubsystem elevatorSubsystem;

	private final PistonSubsystem pistonSubsystem;
	//private final MotorSubsystem controlPanelSubsystem;

	private final GenericHID mainController;
	private final GenericHID secondaryController;

	private final Button shooterButton;
	private final Button intakeButton;
	private final Button hopperButton;
	private final Button hopperBurstButton;
	
	private final Button pistonButton;
	//private final Button pistonForwardButton;
	//private final Button pistonReverseButton;

	private final Button elevatorUpButton;
	private final Button elevatorDownButton;

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
		// Drive motors and groups
		this.lbMotor = new WPI_TalonSRX(Constants.lbMotorPort);
		this.lfMotor = new WPI_TalonSRX(Constants.lfMotorPort);
		this.rbMotor = new WPI_TalonSRX(Constants.rbMotorPort);
		this.rfMotor = new WPI_TalonSRX(Constants.rfMotorPort);
		this.leftDriveMotors = new SpeedControllerGroup(this.lbMotor, this.lfMotor);
		this.rightDriveMotors = new SpeedControllerGroup(this.rbMotor, this.rfMotor);
		// Invert some motors
		//this.rightDriveMotors.setInverted(true);
		//this.leftDriveMotors.setInverted(true);

		// Shooter motors and group
		this.shooterMotorA = new WPI_TalonSRX(Constants.shooterMotorAPort);
		this.shooterMotorB = new WPI_TalonSRX(Constants.shooterMotorBPort);
		this.shooterMotors = new SpeedControllerGroup(shooterMotorA, shooterMotorB);

		// Intake and hopper motor
		this.intakeMotor = new VictorSP(Constants.intakeMotorPort);
		this.hopperMotor = new VictorSP(Constants.hopperMotorPort);

		// Elevator motors and group
		this.elevatorMotorA = new VictorSP(Constants.elevatorMotorAPort);
		this.elevatorMotorB = new VictorSP(Constants.elevatorMotorBPort);
		this.elevatorMotors = new SpeedControllerGroup(elevatorMotorA, elevatorMotorB);

		// Solenoid object
		this.piston = new DoubleSolenoid(Constants.pistonSolenoidPortA, Constants.pistonSolenoidPortB);

		// Initalize subsystems and commands
		// Drive subsystem
		this.driveTrainSubsystem = new DifferentialDriveTrain(this.leftDriveMotors, this.rightDriveMotors);

		// Shooter, intake, hopper motor subsystems
		this.shooterSubsystem = new MotorSubsystem(this.shooterMotors);
		this.intakeSubsystem = new MotorSubsystem(this.intakeMotor);
		this.hopperSubsystem = new MotorSubsystem(this.hopperMotor);
		// Elevator motor subsystem
		this.elevatorSubsystem = new MotorSubsystem(this.elevatorMotors);

		// Solenoid piston subsystem
		this.pistonSubsystem = new PistonSubsystem(this.piston);

		// Setup autocommand
		// Action commands
		Command forward = new ActivateArcadeDrive(this.driveTrainSubsystem, 1.0, 0).withTimeout(Constants.autonomousForwardTime);
		Command turn = new ActivateArcadeDrive(this.driveTrainSubsystem, 0, 1.0).withTimeout(Constants.autonomousTurnTime);
		Command shoot = new ActivateMotor(this.shooterSubsystem, 1.0).withTimeout(Constants.autonomousShootTime);
		// Group autocommand
		this.autoCommand = new SequentialCommandGroup(forward, turn, shoot);

		// Define IO devices
		// Main joystick
		this.mainController = new Joystick(Constants.mainControllerPort);
		// Secondary 'Xbox' controller, emulated from panel
		this.secondaryController = new XboxController(Constants.secondaryControllerPort);

		// Define button objects
		// Shooter, intake, hopper regular buttons
		this.shooterButton = new JoystickButton(this.secondaryController, Constants.shooterButtonPort);
		this.intakeButton = new JoystickButton(this.secondaryController, Constants.intakeButtonPort);
		this.hopperButton = new JoystickButton(this.secondaryController, Constants.hopperButtonPort);
		// Different button for bursting the hopper
		this.hopperBurstButton = new JoystickButton(this.mainController, Constants.hopperBurstButtonPort);

		// Elevator buttons
		this.elevatorUpButton = new POVButton(this.mainController, Constants.elevatorUpButtonPort);
		this.elevatorDownButton = new POVButton(this.mainController, Constants.elevatorDownButtonPort);

		// Piston button
		this.pistonButton = new JoystickButton(this.secondaryController, Constants.pistonButtonPort);
		// POV buttons
		//this.pistonForwardButton = new POVButton(this.mainController, Constants.pistonForwardButtonPOVAngle);
		//this.pistonReverseButton = new POVButton(this.mainController, Constants.pistonReverseButtonPOVAngle);

		// Create default drive command
		this.driveTrainSubsystem.setDefaultCommand(new RunCommand(() -> this.driveTrainSubsystem.arcadeDrive(this.mainController.getY(), this.mainController.getX()), this.driveTrainSubsystem));

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

		// Bind shooter, intake, hopper buttons using Constants speeds
		// Shooter rawAxis is the joystick slider
		this.shooterButton.toggleWhenPressed(new ActivateMotorLambda(shooterSubsystem, () -> this.mainController.getRawAxis(3)));
		this.intakeButton.whenHeld(new ActivateMotor(intakeSubsystem, Constants.intakeMotorSpeed));
		this.hopperButton.whenHeld(new ActivateMotor(hopperSubsystem, Constants.hopperMotorSpeed));
		
		this.hopperButton.whenHeld(new ActivateMotor(intakeSubsystem, Constants.intakeMotorSpeed));
		
		this.hopperBurstButton.whenPressed(new ActivateMotor(hopperSubsystem, Constants.hopperMotorSpeed).withTimeout(Constants.hopperBurstTime));

		// Bind elevator buttons
		this.elevatorUpButton.whenHeld(new ActivateMotor(elevatorSubsystem, Constants.elevatorSpeed));
		this.elevatorDownButton.whenHeld(new ActivateMotor(elevatorSubsystem, -Constants.elevatorSpeed));

		// Bind piston buttons
		//this.pistonButton.whenPressed(new ActivatePiston(pistonSubsystem, 1)).whenReleased(new ActivatePiston(pistonSubsystem, -1));
		//this.pistonForwardButton.whenHeld(new ActivatePiston(pistonSubsystem, 1));
		//this.pistonReverseButton.whenHeld(new ActivatePiston(pistonSubsystem, -1));
		this.pistonButton.whenPressed(new ActivatePiston(pistonSubsystem, 1).withTimeout(Constants.pistonTime));
		this.pistonButton.whenReleased(new ActivatePiston(pistonSubsystem, -1).withTimeout(Constants.pistonTime));

		//this.pistonButton.whenHeld(new ActivateMotor(intakeSubsystem, Constants.intakeMotorSpeed));

	}


	/**
	 * Use this to pass he autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	public Command getAutonomousCommand() {
		// Returns the auto command constructed in the constructor
		return this.autoCommand;
	}
}