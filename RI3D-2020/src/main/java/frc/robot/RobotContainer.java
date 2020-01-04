/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.subsystems.DifferentialDriveTrain;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.SpeedController;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;

import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  private final DifferentialDriveTrain driveTrainSubsystem;
  private final ExampleSubsystem exampleSubsystem;
  private final ExampleCommand autoCommand;

  private final GenericHID mainController;
  private final Button launchButton;

  // Define motors TODO determine actual classes
  private final SpeedController lbMotor;
  private final SpeedController lfMotor;
  private final SpeedController rbMotor;
  private final SpeedController rfMotor;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    // Create motor objects
    // TODO change to use real objects, dependent on robot probably
    this.lbMotor = new SpeedController(Constants.lbMotorPort);    
    this.lfMotor = new SpeedController(Constants.lfMotorPort);
    this.rbMotor = new SpeedController(Constants.rbMotorPort);
    this.rfMotor = new SpeedController(Constants.rfMotorPort);
    
    // Initalize subsystems and commands
    this.exampleSubsystem = new ExampleSubsystem();
    this.autoCommand = new ExampleCommand(exampleSubsystem);
    this.driveTrainSubsystem = new DifferentialDriveTrain(this.lbMotor, this.lfMotor, this.rbMotor, this.rfMotor);

    // Define IO devices
    this.mainController = new XboxController(Constants.mainControllerPort);

    // Define button objects
    this.launchButton = new JoystickButton(this.mainController, Constants.launchButtonPort);

    // Create/define default drive command
    this.driveTrainSubsystem.setDefaultCommand(new RunCommand(() -> this.driveTrainSubsystem.arcadeDrive(this.mainController), this.driveTrainSubsystem));

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

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return this.autoCommand;
  }
}
