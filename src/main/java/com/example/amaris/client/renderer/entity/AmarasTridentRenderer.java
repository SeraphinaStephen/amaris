package com.example.amaris.client.renderer.entity;

import com.example.amaris.entity.projectile.AmarasTridentEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.MultiBufferSource;

public class AmarasTridentRenderer extends EntityRenderer<AmarasTridentEntity> {

    public AmarasTridentRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(AmarasTridentEntity entity, float entityYaw, float partialTicks, PoseStack matrixStack,
                       MultiBufferSource buffer, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLightIn);
        // You can add custom rendering code here if necessary.
    }

    @Override
    public ResourceLocation getTextureLocation(AmarasTridentEntity entity) {
        // Return the texture for your custom trident here.
        return new ResourceLocation("amaris", "textures/entity/amaras_trident.png");
    }
}
