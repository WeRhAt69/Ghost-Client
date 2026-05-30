package com.example.module.combat;

import com.example.module.Category;
import com.example.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class AutoClicker extends Module {
    private int tickCounter = 0;
    private int nextClickDelay = 8;

    public AutoClicker() {
        super("AutoClicker", Category.COMBAT);
    }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        
        // Sicherheitssperre: Nur klicken, wenn der Spieler auf der Welt ist und kein Menü offen hat
        if (mc.player == null || mc.currentScreen != null) {
            return;
        }

        // Führt die Logik aus, sobald die Standard-Angriffstaste (Linksklick) gehalten wird
        if (mc.options.attackKey.isPressed()) {
            this.tickCounter++;

            if (this.tickCounter >= this.nextClickDelay) {
                // Simuliert das Herunterdrücken der Taste im Minecraft-Input-System
                KeyBinding.onKeyPressed(mc.options.attackKey.getDefaultKey());
                
                // Generiert eine neue, menschliche Verzögerung (Mittelwert 8 Ticks, Abweichung 2 Ticks)
                this.nextClickDelay = calculateGaussianDelay(8, 2);
                this.tickCounter = 0;
            }
        } else {
            // Zurücksetzen, wenn die Taste losgelassen wird
            this.tickCounter = 0;
        }
    }

    /**
     * Mathematische Box-Muller-Transformation.
     * Erzeugt eine Glockenkurve (Normalverteilung) um den Mittelwert,
     * damit die Klick-Geschwindigkeit organisch schwankt.
     */
    private int calculateGaussianDelay(double average, double deviation) {
        double u1 = Math.random();
        double u2 = Math.random();
        
        // Standard-Gauß-Rauschen
        double noise = Math.sqrt(-2.0 * Math.log(u1)) * Math.cos(2.0 * Math.PI * u2);
        
        // Wert skalieren und runden
        int finalDelay = (int) Math.round(average + (noise * deviation));
        
        // Schutzgrenze: Mindestens 1 Tick Verzögerung, um Crashes zu vermeiden
        return Math.max(1, finalDelay);
    }
}

