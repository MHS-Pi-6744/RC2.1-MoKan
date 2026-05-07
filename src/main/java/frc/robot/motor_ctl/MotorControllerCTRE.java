package frc.robot.motor_ctl;

import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.hardware.TalonFXS;

public class MotorControllerCTRE {
    private String s_motorName;
    private TalonFXS m_otor;

    public MotorControllerCTRE(int canID){
        s_motorName = "Motor #" + canID + "/";
        m_otor = new TalonFXS(canID);

        var position_request = new PositionDutyCycle(pos);

        m_otor.setControl(position_request);
    }
}
