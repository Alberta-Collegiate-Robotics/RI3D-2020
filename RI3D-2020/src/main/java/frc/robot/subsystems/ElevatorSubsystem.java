/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */

  private SpeedController upperMotors;
  private SpeedController lowerMotors;

  public ElevatorSubsystem(SpeedController upperMotors, SpeedController lowerMotors) {
    // Reference motor
    this.upperMotors = upperMotors;
    this.lowerMotors = lowerMotors;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * Sets the speed of the upper motors
   * @param speed double between [-1, 1]
   */
  public void setUpperSpeed(double speed) {
    this.upperMotors.set(speed);
  }
  /**
   * Sets the speed of the lower motors
   * @param speed double between [-1, 1]
   */
  public void setLowerSpeed(double speed) {
    this.lowerMotors.set(speed);
  }
}
