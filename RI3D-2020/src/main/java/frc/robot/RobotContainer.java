/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

<<<<<<< Updated upstream
=======
import frc.robot.subsystems.DifferentialDriveTrain;
import frc.robot.commands.*;
import frc.robot.subsystems.ExampleSubsystem;

>>>>>>> Stashed changes
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand autoCommand = new ExampleCommand(exampleSubsystem);



  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
<<<<<<< Updated upstream
=======

    // Create motor objects
    this.lbMotor = new Spark(Constants.lbMotorPort);    
    this.lfMotor = new Spark(Constants.lfMotorPort);
    this.rbMotor = new Spark(Constants.rbMotorPort);
    this.rfMotor = new Spark(Constants.rfMotorPort);
    
    // Initalize subsystems and commands
    this.exampleSubsystem = new ExampleSubsystem();
    this.autoCommand = new ExampleCommand(exampleSubsystem);
    this.driveTrainSubsystem = new DifferentialDriveTrain(this.lbMotor, this.lfMotor, this.rbMotor, this.rfMotor);

    this.shooterSubsystem = new MotorSubsystem(shooterMotor);
    this.intakeSubsystem = new MotorSubsystem(intakeMotor);

    // Define IO devices
    this.mainController = new XboxController(Constants.mainControllerPort);

    // Define button objects
    this.launchButton = new JoystickButton(this.mainController, Constants.launchButtonPort);

    // Create/define default drive command
    this.driveTrainSubsystem.setDefaultCommand(new RunCommand(() -> this.driveTrainSubsystem.arcadeDrive(this.mainController.getX(), this.mainController.getY()), this.driveTrainSubsystem));
    
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======
    this.launchButton.whenHeld(new CommandUpperSystem(subsystem));
>>>>>>> Stashed changes
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
