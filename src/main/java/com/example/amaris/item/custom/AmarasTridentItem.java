package com.example.amaris.item.custom;

import com.example.amaris.entity.projectile.AmarasTridentEntity;
import com.example.amaris.entity.ModEntities;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.level.Level;

public class AmarasTridentItem extends TridentItem {

    private static final int COOLDOWN_TICKS = 20; // Delay before the trident can be thrown again
    private int cooldownTicks = 0;

    public AmarasTridentItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        // The normal right-click behavior (only triggers once, not while holding)
        if (!level.isClientSide) {
            throwTrident(player, level, hand);
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
    }

    public void inventoryTickCustom(ItemStack stack, Level level, Player player, int slot, boolean isSelected) {
        if (!level.isClientSide && isSelected && player.isUsingItem()) {
            if (cooldownTicks <= 0) {
                // Only throw trident if cooldown is finished
                throwTrident(player, level, player.getUsedItemHand());
                cooldownTicks = COOLDOWN_TICKS;  // Reset the cooldown
            } else {
                cooldownTicks--; // Reduce cooldown
            }
        }
    }


    private void throwTrident(Player player, Level level, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        // Create and shoot the trident entity
        AmarasTridentEntity tridentEntity = new AmarasTridentEntity(ModEntities.AMARAS_TRIDENT_ENTITY.get(), level);
        tridentEntity.setOwner(player); // Set player as the owner
        tridentEntity.setItem(itemStack.copy()); // Set the item stack for the entity

        // Shoot the trident from the player's position
        tridentEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);

        // Add the entity to the world
        level.addFreshEntity(tridentEntity);

        // Damage the item (the trident)
        itemStack.hurtAndBreak(1, player, (entity) -> entity.broadcastBreakEvent(hand));

        // Play the throw sound
        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.TRIDENT_THROW, player.getSoundSource(), 1.0F, 1.0F);
    }
}
