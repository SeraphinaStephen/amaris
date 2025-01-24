package com.example.amaris.item.custom;

import com.example.amaris.entity.projectile.AmarasTridentEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class AmarasTridentItem extends TridentItem {

    public AmarasTridentItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        // Enable the ability to "charge" the throw by holding right-click
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(itemStack); // The item is used, but not yet thrown
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        if (entity instanceof Player player) {
            throwTrident(level, player, stack);
            System.out.println("Trident entity added!");
        }
        else {
            System.out.println("Client side: Entity is not added.");
        }

    }

    private void throwTrident(Level level, Player player, ItemStack stack) {
        AmarasTridentEntity tridentEntity = new AmarasTridentEntity(level, player, stack);

        // Set the initial velocity/direction of the trident
        tridentEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);

        if (!level.isClientSide()) {
            level.addFreshEntity(tridentEntity);
        }

        // Damage the item
        stack.hurtAndBreak(1, player, (entity) -> entity.broadcastBreakEvent(player.getUsedItemHand()));

        // Play the trident throw sound
        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.TRIDENT_THROW, player.getSoundSource(), 1.0F, 1.0F);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000; // How long the player can hold right-click (max charge time)
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.SPEAR; // Mimics the spear-charging animation (like the vanilla trident)
    }
}