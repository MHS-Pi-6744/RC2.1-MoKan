// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.ShooterSubsystemConstants.kCenterCanId;
import static frc.robot.Constants.ShooterSubsystemConstants.kLeftCanId;
import static frc.robot.Constants.ShooterSubsystemConstants.kRightCanId;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs.Default;
import frc.robot.motor_ctl.Flywheel;
import java.util.function.Function;
import java.util.function.Supplier;

public class ShooterSubsystem extends SubsystemBase {
  private Flywheel m_left, m_center, m_right;
  Supplier<Double> distance;
  Function<Double, Double> func =
      (x) -> {
        var val = 365 * x + 2236.23475;
        return val > 5000 ? 5000 : val;
      };

  public ShooterSubsystem(Supplier<Double> distance) {
    m_left = new Flywheel(kLeftCanId, Default.Config);
    m_center = new Flywheel(kCenterCanId, Default.Config);
    m_right = new Flywheel(kRightCanId, Default.Config.inverted(true));
    this.distance = distance;
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

  public void rpmCtl(double rpm) {
    m_left.rpmCtl(rpm);
    m_center.rpmCtl(rpm);
    m_right.rpmCtl(rpm);
  }

  public void smartShoot(double distance) {
    var rpm = func.apply(distance);
    SmartDashboard.putNumber("Shooter/Testing/Distance From Hub", distance);
    SmartDashboard.putNumber("Shooter/Testing/RPM Out", rpm);
    rpmCtl(rpm);
  }

  public void smartShoot(Supplier<Double> distance) {
    smartShoot(distance.get());
  }

  public void smartShoot() {
    smartShoot(this.distance);
  }

  @Override
  public void periodic() {
    var rpm = func.apply(distance.get());
    SmartDashboard.putNumber("Shooter/Distance From Hub", distance.get());
    SmartDashboard.putNumber("Shooter/RPM Out", rpm);
  }
}
