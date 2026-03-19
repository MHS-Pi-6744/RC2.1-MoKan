// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.ShooterSubsystemConstants.kCenterCanId;
import static frc.robot.Constants.ShooterSubsystemConstants.kLeftCanId;
import static frc.robot.Constants.ShooterSubsystemConstants.kRightCanId;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs.Default;
import frc.robot.motor_ctl.Flywheel;

public class ShooterSubsystem extends SubsystemBase {
  private Flywheel m_left, m_center, m_right;

  public ShooterSubsystem() {
    m_left = new Flywheel(kLeftCanId, Default.Config);
    m_center = new Flywheel(kCenterCanId, Default.Config);
    m_right = new Flywheel(kRightCanId, Default.Config.inverted(true));
  }

  public Command runRPM(double rpm) {
    return new ParallelCommandGroup(m_left.runRPM(rpm), m_center.runRPM(rpm), m_right.runRPM(rpm));
  }

  public Command stopFlywheel() {
    return new ParallelCommandGroup(m_left.stopMotor(), m_center.stopMotor(), m_right.stopMotor());
  }

  public Command runAtSet() {
    return new ParallelCommandGroup(m_left.runAtSet(), m_center.runAtSet(), m_right.runAtSet());
  }

  public Command incrementSetpoint(int increment) {
    return new ParallelCommandGroup(
        m_left.incrSet(increment), m_center.incrSet(increment), m_right.incrSet(increment));
  }

  private double rpmFromDist(double x) {
    return 2236.23475 * Math.pow(1.1132, x);
  }

  public Command smartShoot(double distance) {
    var rpm = rpmFromDist(distance);
    return runRPM(rpm);
  }
}
