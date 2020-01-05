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

    public static final int xboxAButtonPort = 0;
    public static final int joystickTriggerButtonPort = 0;

    public static final int mainControllerPort = 0;
    public static final int shooterButtonPort = joystickTriggerButtonPort;
    public static final int controlPanelButtonPort = -1;

    public static final int lbMotorPort = 0;
    public static final int lfMotorPort = 1;
    public static final int rbMotorPort = 2;
    public static final int rfMotorPort = 3;

    public static final int shooterMotorPort = -1;
    public static final int intakeMotorPort = -1;

    public static final int hopperMotorPort = -1;

    //public static final int controlPanelPort = 0;

    public static final double shooterMotorSpeed = 1.0;
    public static final double intakeMotorSpeed = 1.0;

    public static final double hopperMotorSpeed = 0.5;

    //public static final double controlPanelSpeed = 1.0;
}