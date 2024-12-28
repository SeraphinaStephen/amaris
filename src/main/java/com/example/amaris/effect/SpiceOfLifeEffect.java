package com.example.amaris.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class SpiceOfLifeEffect extends MobEffect {

    public SpiceOfLifeEffect() {
        // Mark as harmful (red category) with a forest green color (0x228B22)
        super(MobEffectCategory.NEUTRAL, 0x228B22);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // Leave empty - no gameplay effect
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false; // No ticking effects either
    }
}