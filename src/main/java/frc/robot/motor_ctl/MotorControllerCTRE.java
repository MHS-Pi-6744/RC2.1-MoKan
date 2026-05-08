package frc.robot.motor_ctl;

import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.hardware.TalonFXS;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorControllerCTRE extends SubsystemBase {
    private String s_motorName;
    private TalonFXS m_otor;

    public MotorControllerCTRE(int canID){
        s_motorName = "Motor #" + canID + "/";
        m_otor = new TalonFXS(canID);
    }
    /**
     * @return a {@link Command} that sets the motor speed to nothing
     */
    public Command stopMotor(double speed) {
        return runOnce(() -> m_otor.set(0));
    }

      /**
   * @param speed the speed the motor should go
   * @return a {@link Command} that sets the motor speed to the input speed
   */
    public Command runMotor(double speed) {
        return runOnce(() -> m_otor.set(speed));
    }

}
