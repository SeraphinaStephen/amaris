package com.example.amaris.entity.projectile;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class AmarasTridentEntity extends ThrownTrident {
    private ItemStack tridentItem = ItemStack.EMPTY;

    // This constructor is required for entity registration
    public AmarasTridentEntity(EntityType<? extends ThrownTrident> entityType, Level level) {
        super(entityType, level);
    }

    // This constructor is for when we spawn the trident with an owner and an item stack
    public AmarasTridentEntity(Level level, LivingEntity owner, ItemStack stack) {
        super(level, owner, stack);
        System.out.println("AmarasTridentEntity is being created and added to the level!");
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        if (this.getOwner() instanceof ServerPlayer player) {
            Vec3 hitLocation = this.position();

            // Teleport player to hit location
            teleportPlayer(player, hitLocation);

            // Play teleport sound
            this.level().playSound(null, hitLocation.x, hitLocation.y, hitLocation.z,
                    SoundEvents.ENDERMAN_TELEPORT, this.getSoundSource(), 1.0F, 1.0F);

            // Remove the trident entity
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);

        if (this.getOwner() instanceof ServerPlayer player) {
            Vec3 hitLocation = result.getLocation();

            // Teleport player to the block impact location
            teleportPlayer(player, hitLocation);

            // Play teleport sound
            this.level().playSound(null, hitLocation.x, hitLocation.y, hitLocation.z,
                    SoundEvents.ENDERMAN_TELEPORT, this.getSoundSource(), 1.0F, 1.0F);

            // Remove the trident entity
            this.discard();
        }
    }

    private void teleportPlayer(ServerPlayer player, Vec3 location) {
        // Teleport the player to the target location
        player.teleportTo(location.x, location.y, location.z);

        // Prevent fall damage
        player.fallDistance = 0.0F;

        // Generate portal particles
        for (int i = 0; i < 16; i++) {
            this.level().addParticle(ParticleTypes.PORTAL,
                    location.x + (this.random.nextDouble() - 0.5),
                    location.y + (this.random.nextDouble() - 0.5),
                    location.z + (this.random.nextDouble() - 0.5),
                    (this.random.nextDouble() - 0.5) * 2.0,
                    -this.random.nextDouble(),
                    (this.random.nextDouble() - 0.5) * 2.0);
        }
    }

    public void setItem(ItemStack itemStack) {
        this.tridentItem = itemStack.copy(); // Store a copy of the ItemStack
    }

    public ItemStack getItem() {
        return this.tridentItem;
    }
}
