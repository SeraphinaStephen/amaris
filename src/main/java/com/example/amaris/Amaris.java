package com.example.amaris;

import com.example.amaris.entity.ModEntities;
import com.example.amaris.client.renderer.ModEntityRenderers;
import com.example.amaris.item.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.ThrownTridentRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import com.example.amaris.effect.ModEffects;

@Mod(Amaris.MODID)
public class Amaris {
    public static final String MODID = "amaris";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Amaris() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register items and entities
        ModItems.register(modEventBus);
        ModEntities.register(modEventBus);
        ModEffects.register(modEventBus);

        // Register to Forge's Event Bus
        MinecraftForge.EVENT_BUS.register(this);

        // Add items to creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            // Ensure AMARAS_TRIDENT is registered before adding it to the creative tab
            ModItems.AMARAS_TRIDENT.ifPresent(event::accept);
            event.accept(ModItems.SERENS_SCYTHE.get());
            event.accept(ModItems.LUCKY_SEVEN.get());
            event.accept(ModItems.BLADES_MERCY.get());
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Server is starting!");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        private static final Logger LOGGER = LogUtils.getLogger();

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("Client setup initiated.");
            EntityRenderers.register(ModEntities.AMARAS_TRIDENT_ENTITY.get(), ThrownTridentRenderer::new);
        }
    }
}
