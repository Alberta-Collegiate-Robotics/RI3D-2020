/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final int mainControllerPort = 0;
    public static final int secondaryControllerPort = 1;

    public static final int joystickTriggerButtonPort = 1;
    public static final int joystickSideButtonPort = 2;

    public static final int shooterButtonPort = 3;
    public static final int intakeButtonPort = 4;
    public static final int hopperButtonPort = 5;
    public static final int hopperBurstButtonPort = 6;
    
    public static final int elevatorUpButtonPort = 12;
    public static final int elevatorDownButtonPort = 11;

    public static final int pistonButtonPort = joystickSideButtonPort;
    public static final int pistonForwardButtonPOVAngle = 0;
    public static final int pistonReverseButtonPOVAngle = 180;

    public static final int lbMotorPort = 2;
    public static final int lfMotorPort = 3;
    public static final int rbMotorPort = 4;
    public static final int rfMotorPort = 5;

    public static final int shooterMotorAPort = 0;
    public static final int shooterMotorBPort = 1;

    public static final int intakeMotorPort = 3;
    public static final int hopperMotorPort = 2;

    public static final int elevatorMotorAPort = 0;
    public static final int elevatorMotorBPort = 1;

    public static final int pistonSolenoidPortA = 0;
    public static final int pistonSolenoidPortB = 1;

    public static final double shooterMotorSpeed = -1.0;
    public static final double intakeMotorSpeed = 0.8;
    public static final double hopperMotorSpeed = 0.3;

    public static final double elevatorSpeed = 0.25;

    public static final double motorTestSpeed = 0.5;

    //public static final double controlPanelSpeed = 1.0;

    public static final double autonomousForwardTime = 3.0;
    public static final double autonomousTurnTime = 3.0;
    public static final double autonomousShootTime = 3.0;

    public static final double hopperBurstTime = 0.5;

}
