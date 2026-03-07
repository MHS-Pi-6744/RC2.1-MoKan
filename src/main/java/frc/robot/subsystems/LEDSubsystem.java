package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDSubsystem extends SubsystemBase{
    public enum FixedPalettePattern {
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
        Twinkles_Rainbow_Palette(-.55),
        Twinkles_Party_Palette(-.53),
        Twinkles_Ocean_Palette(-.51),
        Twinkles_Lava_Palette(-.49),
        Twinkles_Forest_Palette(-.47),
        Color_Waves_Rainow_Palette(-.45),
        Color_Waves_Party_Palette(-.43),
        Color_Wavs_Ocean_Palette(-.41),
        Color_Waves_Lava_Palette(-.39),
        Color_Waves_Forest_Palette(-.37),
        Larson_Scanner_Red(-.35),
        Larson_Scanner_Gray(-.33),
        Light_Chase_Red(-.31),
        Light_Chase_Blue(-.29),
        Light_Chase_Gray(-.27),
        Heartbeat_Red(-.25),
        Heartbeat_Blue(-.23),
        Heartbeat_White(-.21),
        Heartbeat_Gray(-.19),
        Breath_Red(-.17),
        Breath_Blue(-.15),
        Breach_Gray(-.13),
        Strobe_Red(-.11),
        Strobe_Blue(-.09),
        Strobe_Gold(-.07),
        Strobe_White(-.05);
        

        private double value;

        FixedPalettePattern(double value) {
            this.value = value;
        }

    }
    
    public Spark m_led = new Spark(0);

    public Command setLED(FixedPalettePattern p) {
        return run(
            () -> m_led.set(p.value)
        );
    }
    
}
