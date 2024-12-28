package com.example.amaris.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class SoulmatesEffect extends MobEffect {

    public SoulmatesEffect() {
        // Mark as harmful (red category) with a sky blue color (0x87CEEB)
        super(MobEffectCategory.NEUTRAL, 0x87CEEB);
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