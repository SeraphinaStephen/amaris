package com.example.amaris.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class DeathsEmbraceEffect extends MobEffect {

    public DeathsEmbraceEffect() {
        // Mark as harmful (red category) with a pure red color (0xFF0000)
        super(MobEffectCategory.HARMFUL, 0xFF0000);
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