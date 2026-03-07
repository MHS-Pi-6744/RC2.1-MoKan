package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDSubsystem extends SubsystemBase{
    public enum Pattern {
        RRP(-.99),
        RPP(-.97),
        ROP(-.95),
        RLP(-.93),
        RFP(-.91)

        private double value;

        Pattern(double value) {
            this.value = value;
        }

    }
    
    public Spark m_led = new Spark(0);

    public Command setLED(Pattern p) {
        return run(
            () -> m_led.set(p.value)
        );
    }
    
}
