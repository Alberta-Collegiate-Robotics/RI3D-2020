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

  private SpeedController lmotor1;
  private SpeedController lmotor2;
  private SpeedController rmotor1;
  private SpeedController rmotor2;

  public ElevatorSubsystem(SpeedController lmotor1, SpeedController lmotor2, SpeedController rmotor1, SpeedController rmotor2) {
    // Reference motor
    this.lmotor1 = lmotor1;
    this.lmotor2 = lmotor2;
    this.rmotor1 = rmotor1;
    this.rmotor2 = rmotor2;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * Wraps the internal speed controller
   * @param speed double between [-1, 1]
   */
  public void setSpeed(double speed) {
    this.motor.set(speed);
  }
}
