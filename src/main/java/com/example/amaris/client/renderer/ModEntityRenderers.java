package com.example.amaris.client.renderer;

import com.example.amaris.entity.ModEntities;
import com.example.amaris.entity.projectile.AmarasTridentEntity;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.ThrownTridentRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// Make this a client-specific event handler
@Mod.EventBusSubscriber(modid = "amaris", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEntityRenderers {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // Register the default trident renderer for AmarasTridentEntity
        event.registerEntityRenderer(ModEntities.AMARAS_TRIDENT_ENTITY.get(), ThrownTridentRenderer::new);

        // Or, if you want to test using a simpler renderer like the default item renderer:
        // event.registerEntityRenderer(ModEntities.AMARAS_TRIDENT_ENTITY.get(), ThrownItemRenderer::new);
    }
}