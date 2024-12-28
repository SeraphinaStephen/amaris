package com.example.amaris.item;

import com.example.amaris.Amaris;
import com.example.amaris.effect.ModEffects;
import com.example.amaris.item.custom.AmarasTridentItem;
import com.example.amaris.item.custom.BladesMercyItem;
import com.example.amaris.item.custom.LuckySevenItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Amaris.MODID);

    public static final RegistryObject<Item> SERENS_SCYTHE = ITEMS.register("serens_scythe",
            () -> new SwordItem(Tiers.NETHERITE, 9994, -3, new Item.Properties().fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> LUCKY_SEVEN = ITEMS.register("lucky_seven",
            () -> new LuckySevenItem(Tiers.NETHERITE, 0, -2, new Item.Properties().fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> BLADES_MERCY = ITEMS.register("blades_mercy",
            () -> new BladesMercyItem(new Item.Properties()
                    .stacksTo(1)
                    .durability(99999999)
                    .rarity(Rarity.EPIC)
                    .fireResistant()));

    public static final RegistryObject<Item> AMARAS_TRIDENT = ITEMS.register("amaras_trident",
            () -> new AmarasTridentItem(new Item.Properties().stacksTo(1).durability(250))); // Use your custom item class here

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
