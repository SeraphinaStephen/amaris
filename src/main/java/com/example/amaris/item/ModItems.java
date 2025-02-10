package com.example.amaris.item;

import com.example.amaris.Amaris;
import com.example.amaris.item.custom.AmarasTridentItem;
import com.example.amaris.item.custom.BladesMercyItem;
import com.example.amaris.item.custom.LuckySevenItem;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Amaris.MODID);

    public static final RegistryObject<Item> SERENS_SCYTHE = ITEMS.register("serens_scythe",
            () -> new SwordItem(Tiers.NETHERITE, 9994, -3, new Item.Properties().fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> LUCKY_SEVEN = ITEMS.register("lucky_seven_sword",
            () -> new LuckySevenItem(Tiers.NETHERITE, 0, -2, new Item.Properties().fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> BLADES_MERCY = ITEMS.register("blades_mercy",
            () -> new BladesMercyItem(new Item.Properties()
                    .stacksTo(1)
                    .durability(99999999)
                    .rarity(Rarity.EPIC)
                    .fireResistant()));

    public static final RegistryObject<Item> GIOS_CROWBAR = ITEMS.register("gios_crowbar_battle_staff",
            () -> new SwordItem(Tiers.NETHERITE, 8, -2, new Item.Properties().fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> AMARAS_TRIDENT = ITEMS.register("amaras_trident",
            () -> new AmarasTridentItem(SpellDataRegistryHolder.of(new SpellDataRegistryHolder(SpellRegistry.LIGHTNING_LANCE_SPELL, 10))));

    //public static final RegistryObject<Item> LIGHTNING_ROD_STAFF = ITEMS.register("lightning_rod", () ->
    // new StaffItem(ItemPropertiesHelper.equipment(1).rarity(Rarity.UNCOMMON).fireResistant(), 4, -3, Map.of(AttributeRegistry.COOLDOWN_REDUCTION.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", .15, AttributeModifier.Operation.MULTIPLY_BASE), AttributeRegistry.LIGHTNING_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", .15, AttributeModifier.Operation.MULTIPLY_BASE), AttributeRegistry.SPELL_POWER.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", .05, AttributeModifier.Operation.MULTIPLY_BASE))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
