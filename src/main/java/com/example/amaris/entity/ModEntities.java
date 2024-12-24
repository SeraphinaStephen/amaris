package com.example.amaris.entity;

import com.example.amaris.Amaris;
import com.example.amaris.entity.projectile.AmarasTridentEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Amaris.MODID);

    // Register the entity type
    public static final RegistryObject<EntityType<AmarasTridentEntity>> AMARAS_TRIDENT_ENTITY = ENTITY_TYPES.register("amaras_trident",
            () -> EntityType.Builder.<AmarasTridentEntity>of(AmarasTridentEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f) // Size of the trident entity
                    .build(Amaris.MODID + ":amaras_trident")); // Use string format for the entity name

    // Register the entity types to the event bus
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);  // Register this Deferred Register to the mod's event bus
    }
}
