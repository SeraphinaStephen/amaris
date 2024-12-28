package com.example.amaris.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class LuckySevenItem extends SwordItem {

    public LuckySevenItem(Tier tier, int baseDamage, float attackSpeed, Item.Properties properties) {
        super(tier, baseDamage, attackSpeed, properties);
    }

    // Override how the sword deals damage to add random damage functionality
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Random random = new Random();
        int randomDamage = random.nextInt(20) + 1; // Random damage between 1 and 20
        target.hurt(attacker.damageSources().playerAttack((Player) attacker), randomDamage); // Apply damage to the target

        // Play a sound effect for the random damage hit
        target.level().playSound(null, target.blockPosition(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 1.0F, 1.0F);

        // Damage the sword slightly
        stack.hurtAndBreak(1, attacker, (entity) -> entity.broadcastBreakEvent(attacker.getUsedItemHand()));

        return true; // Indicate the action was successful
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (!player.level().isClientSide) { // Ensure this only runs on the server
            Random random = new Random();

            // Define the random potion effects
            MobEffect[] effects = new MobEffect[]{
                    MobEffects.REGENERATION,
                    MobEffects.DAMAGE_BOOST,
                    MobEffects.MOVEMENT_SPEED,
                    MobEffects.INVISIBILITY,
                    MobEffects.NIGHT_VISION,
                    MobEffects.JUMP,
                    MobEffects.FIRE_RESISTANCE,
                    MobEffects.WATER_BREATHING,
                    MobEffects.ABSORPTION,
                    MobEffects.POISON,
                    MobEffects.WITHER,
                    MobEffects.BLINDNESS,
                    MobEffects.HEALTH_BOOST,
                    MobEffects.SATURATION,
                    MobEffects.GLOWING,
                    MobEffects.LEVITATION,
                    MobEffects.UNLUCK,
                    MobEffects.SLOW_FALLING,
                    MobEffects.CONDUIT_POWER,
                    MobEffects.DOLPHINS_GRACE,
                    MobEffects.BAD_OMEN,
                    MobEffects.HERO_OF_THE_VILLAGE,
                    MobEffects.HUNGER,
                    MobEffects.DIG_SLOWDOWN,
                    MobEffects.BAD_OMEN
            };

            // Choose a random effect from the array
            MobEffect randomEffect = effects[random.nextInt(effects.length)];
            int duration = random.nextInt(200) + 100; // Random duration: 5 to 15 seconds in ticks

            // Apply the effect to the target entity
            target.addEffect(new MobEffectInstance(randomEffect, duration, 1));

            // Play a sound to provide feedback to the player
            player.level().playSound(null, target.blockPosition(), SoundEvents.BREWING_STAND_BREW, SoundSource.PLAYERS, 1.0F, 1.0F);

            // Damage the item slightly
            stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));

            return InteractionResult.SUCCESS; // Indicate that the interaction was successful
        }

        return InteractionResult.PASS; // Fall back to default behavior
    }
}