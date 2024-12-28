package com.example.amaris.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BladesMercyItem extends SwordItem {
    public BladesMercyItem(Properties properties) {
        super(Tiers.NETHERITE, -5, -2f, properties);
    }

    // Called when left-clicking entities (damaging entities)
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof Player player) {
            // Check and heal the target (if it's a player)
            if (target instanceof Player) {
                target.heal(20.0f); // Heal the target
                player.getCooldowns().addCooldown(this, 20); // Add cooldown to prevent spam
            }
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos); // Get the state of the block being clicked
        Player player = context.getPlayer();

        // Check if the targeted block is bonemealable
        if (state.getBlock() instanceof BonemealableBlock bonemealable) {
            // If we're on the server side and the block is bonemealable, apply the effect
            if (!level.isClientSide) {
                if (bonemealable.isValidBonemealTarget(level, pos, state, level.isClientSide)) {
                    if (bonemealable.isBonemealSuccess(level, level.random, pos, state)) {
                        // Grow the block (simulate bonemeal usage)
                        bonemealable.performBonemeal((ServerLevel) level, level.random, pos, state);

                        // Show particles for fertilizer effect
                        level.levelEvent(1505, pos, 0); // 1505 = green bonemeal particles

                        // Damage the Blade's Mercy slightly
                        context.getItemInHand().hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(context.getHand()));
                    }
                }
            }
            return InteractionResult.SUCCESS;
        }

        return super.useOn(context); // Fallback to default behavior for non-bonemealable blocks
    }
}
