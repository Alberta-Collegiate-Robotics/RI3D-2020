/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.subsystems.DifferentialDriveTrain;
import frc.robot.commands.*;
import frc.robot.subsystems.ExampleSubsystem;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.subsystems.*; //We're going to use all the subsystems anyways

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
  //private final SpeedController intakeMotor;

  private final DifferentialDriveTrain driveTrainSubsystem;
  private final MotorSubsystem shooterSubsystem;
  //private final MotorSubsystem intakeSubsystem;

  private final GenericHID mainController;
  private final Button shooterButton;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    // Create motor objects
    this.lbMotor = new Spark(Constants.lbMotorPort);    
    this.lfMotor = new Spark(Constants.lfMotorPort);
    this.rbMotor = new Spark(Constants.rbMotorPort);
    this.rfMotor = new Spark(Constants.rfMotorPort);

    this.shooterMotor = new Spark(Constants.shooterMotorPort);
    //this.intakeMotor = new Spark(Constants.intakeMotorPort);
    
    // Initalize subsystems and commands
    this.exampleSubsystem = new ExampleSubsystem();
    this.autoCommand = new ExampleCommand(exampleSubsystem);
    this.driveTrainSubsystem = new DifferentialDriveTrain(this.lbMotor, this.lfMotor, this.rbMotor, this.rfMotor);

    this.shooterSubsystem = new MotorSubsystem(shooterMotor);
    //this.intakeSubsystem = new MotorSubsystem(intakeMotor);

    // Define IO devices
    this.mainController = new XboxController(Constants.mainControllerPort);

    // Define button objects
    this.shooterButton = new JoystickButton(this.mainController, Constants.launchButtonPort);

    // Create/define default drive command
    this.driveTrainSubsystem.setDefaultCommand(new RunCommand(() -> this.driveTrainSubsystem.arcadeDrive(this.mainController.getX(), this.mainController.getY()), this.driveTrainSubsystem));
    
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    this.shooterButton.whenHeld(new ActivateMotor(shooterSubsystem, Constants.shooterMotorSpeed));
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
