/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DifferentialDriveTrain extends SubsystemBase {

    private SpeedController left;
    private SpeedController right;

    private DifferentialDrive drive;

    /**
     * Creates a new differential DriveTrain subsystem linking to the given SpeedControllers
     * Built off of DifferentialDrive
     * https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/drive/DifferentialDrive.html
     * 
     * @param left SpeedController for left motors
     * @param right SpeedController for right motors
     */
    public DifferentialDriveTrain(SpeedController left, SpeedController right) {
        
        // Reference each motor section
        this.left = left;
        this.right = right;

        // Combine motor groups into differential drive
        this.drive = new DifferentialDrive(this.left, this.right);

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    /**
     * Method used for accsessing the internal differential drive object
     * Should only be needed for advanced usage, use with caution
     * It is better to extend the functionality of this class then call this method
     * @return Returns the internal differential drive of the subsystem
     */
    public DifferentialDrive drive() {
        return this.drive;
    }

    /**
     * Convience wrapper for the internal differential drive
     * @param xSpeed Forward linear axis speed, [-1, 1]
     * @param zRotation Clockwise rotational axis speed, [-1, 1]
     */
    public void arcadeDrive(double xSpeed, double zRotation) {
        this.drive.arcadeDrive(xSpeed, zRotation);
    }

    /**
     * Automatically retrieves the controller's getX()/getY() and passes it to arcadeDrive
     * @param controller A controller to provide x/y inputs
     */
    public void arcadeDrive(GenericHID controller) {
        this.arcadeDrive(controller.getX(), controller.getY());
    }

    /**
     * Convience wrapper for the internal differential drive
     * @param leftSpeed Forward linear axis speed of left side, [-1, 1]
     * @param rightSpeed Forward linear axis speed of right side, [-1, 1]
     */
    public void tankDrive(double leftSpeed, double rightSpeed) {
        this.drive.tankDrive(leftSpeed, rightSpeed);
    }

    /**
     * Sets internal motor (lb, lf, rb, rf) speeds to 0
     */
    public void stop() {
        // TODO DifferentialDrive has a .stopMotor method but there is little documentation, needs testing
        this.left.set(0);
        this.right.set(0);
        //this.drive.stopMotor();
    }

}
