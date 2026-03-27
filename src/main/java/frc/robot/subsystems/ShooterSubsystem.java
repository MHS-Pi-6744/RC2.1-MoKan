// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.ShooterSubsystemConstants.kCenterCanId;
import static frc.robot.Constants.ShooterSubsystemConstants.kLeftCanId;
import static frc.robot.Constants.ShooterSubsystemConstants.kRightCanId;

import java.util.function.Function;
import java.util.function.Supplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Configs.Default;
import frc.robot.motor_ctl.Flywheel;

public class ShooterSubsystem extends SubsystemBase {
  private Flywheel m_left, m_center, m_right;
  Supplier<Double> distance;
  Function<Double, Double> func =
      (x) -> {
        var max = 4000;
        var val = 366 * x + 2000;
        return val > max ? max : val;
      };

  public ShooterSubsystem(Supplier<Double> distance) {
    m_left = new Flywheel(kLeftCanId, Default.Config);
    m_center = new Flywheel(kCenterCanId, Default.Config);
    m_right = new Flywheel(kRightCanId, Default.Config.inverted(true));
    this.distance = distance;
  }

  public Command runRPM(double rpm) {
    return new ParallelCommandGroup(m_left.runRPM(rpm), m_center.runRPM(rpm), m_right.runRPM(rpm), new WaitCommand(0.1));
  }

  public Command stopFlywheel() {
    return new ParallelCommandGroup(m_left.stopMotor(), m_center.stopMotor(), m_right.stopMotor(), new WaitCommand(0.1));
  }

  public Command runAtSet() {
    return new ParallelCommandGroup(m_left.runAtSet(), m_center.runAtSet(), m_right.runAtSet(), new WaitCommand(0.1));
  }

  public Command incrementSetpoint(int increment) {
    return new ParallelCommandGroup(
        m_left.incrSet(increment), m_center.incrSet(increment), m_right.incrSet(increment), new WaitCommand(0.1));
  }

  public void rpmCtl(double rpm) {
    m_left.rpmCtl(rpm);
    m_center.rpmCtl(rpm);
    m_right.rpmCtl(rpm);
  }

  public void smartShoot(double distance) {
    var rpm = func.apply(distance);
    rpmCtl(rpm);
  }

  public void smartShoot(Supplier<Double> distance) {
    smartShoot(distance.get());
  }

  public void smartShoot() {
    smartShoot(this.distance);
  }

  public Command smartShootCommand() {
    return new RunCommand(() -> smartShoot(this.distance));
  }

  public Command clearFaults() {
    return new ParallelCommandGroup(
        m_left.clearFaults(), m_center.clearFaults(), m_right.clearFaults());
  }

  public boolean atSetpoint() {
    return m_left.atSetpoint() || m_center.atSetpoint() || m_right.atSetpoint();
  }

  public Trigger setpointAchieved() {
    return new Trigger(this::atSetpoint);
  }

  @Override
  public void periodic() {
    var rpm = func.apply(distance.get());
    SmartDashboard.putNumber("Shooter/RPM Out", rpm);
  }
}
