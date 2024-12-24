package com.example.amaris;

import com.example.amaris.entity.ModEntities;
import com.example.amaris.client.renderer.entity.AmarasTridentRenderer;
import com.example.amaris.item.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
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

@Mod(Amaris.MODID)
public class Amaris {
    public static final String MODID = "amaris";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Amaris() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register items and entities
        ModItems.register(FMLJavaModLoadingContext.get().getModEventBus());

        // Register the entity renderer
        ModEntities.register(modEventBus);
        EntityRenderers.register(ModEntities.AMARAS_TRIDENT_ENTITY.get(), AmarasTridentRenderer::new);



        // Register the event handler for server starting
        MinecraftForge.EVENT_BUS.register(this);

        // Add items to creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            // Ensure AMARAS_TRIDENT is registered before adding it to the creative tab
            if (ModItems.AMARAS_TRIDENT.isPresent()) {
                event.accept(ModItems.AMARAS_TRIDENT.get());
            } else {
                LOGGER.warn("Amaras Trident item is not registered properly.");
            }
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
            LOGGER.info("Player Name: {}", Minecraft.getInstance().getUser().getName());
        }
    }
}

