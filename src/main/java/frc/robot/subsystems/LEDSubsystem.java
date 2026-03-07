package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDSubsystem extends SubsystemBase{
    public enum Pattern {
        Rainbow_Rainbow(-.99),
        Rainbow_Party(-.97),
        Rainbow_Ocean(-.95),
        Rainbow_Lava(-.93),
        Rainbow_Forest(-.91),
        Rainbow_Glitter(-0.89),
        Confetti(0.87),
        Shot_Red(-0.85),
        Shot_Blue(-0.83),
        Shot_White(-0.81),
        Sinelon_Rainbow(-0.79),
        Sinelon_Party(-0.77),
        Sinelon_Ocean(-0.75),
        Sinelon_Lava(-0.73),
        Sinelon_Forest(-0.71),
        BPM_Rainbow(-0.69),
        BPM_Party(-0.67),
        BPM_Ocean(-0.65),
        BPM_Lava(-0.63),
        BPM_Forest(-0.61),
        Fire_Medium(-0.59),
        Fire_Large(-0.57),
        

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
