package com.example.amaris.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.example.amaris.Amaris;

public class ModEffects{
    // Create a deferred register for mob effects (potions)
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Amaris.MODID);

    // Register "Death's Embrace" effect
    public static final RegistryObject<MobEffect> DEATHS_EMBRACE =
            MOB_EFFECTS.register("deaths_embrace", DeathsEmbraceEffect::new);

    public static final RegistryObject<MobEffect> SOULMATES_EFFECT =
            MOB_EFFECTS.register("soulmates", SoulmatesEffect::new);

    public static final RegistryObject<MobEffect> SPICE_OF_LIFE_EFFECT =
            MOB_EFFECTS.register("spice_of_life", SpiceOfLifeEffect::new);

    // Called inside the main mod class during initialization
    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}