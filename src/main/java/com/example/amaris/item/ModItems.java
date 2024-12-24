package com.example.amaris.item;

import com.example.amaris.Amaris;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Amaris.MODID);

    public static final RegistryObject<Item> SERENS_SCYTHE = ITEMS.register("serens_scythe",
            () -> new SwordItem(Tiers.NETHERITE, 5000, -2, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> LUCKY_SEVEN = ITEMS.register("lucky_seven",
            () -> new SwordItem(Tiers.NETHERITE, 5000, -2, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> AMARAS_TRIDENT = ITEMS.register("amaras_trident", () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
