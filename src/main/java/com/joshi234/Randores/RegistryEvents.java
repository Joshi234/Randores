package com.joshi234.Randores;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT,modid = "randores")
public  class RegistryEvents {


    @SubscribeEvent
    public static void onBlockColorRegistry(ColorHandlerEvent.Block colorHandlerEvent) {
        RenderTypeLookup.setRenderLayer(RegistryHandler.COPPER_ORE.get().getBlock(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(RegistryHandler.ORE1_ORE.get().getBlock(), RenderType.getCutout());
        BlockColors colors = colorHandlerEvent.getBlockColors();
        colors.register((state, reader, pos, color) -> {
            return Randores.randomColor[0];
        },RegistryHandler.COPPER_ORE.get());
        colors.register((state, reader, pos, color) -> {
            return Randores.randomColor[1];
        },RegistryHandler.ORE1_ORE.get());

    }

    @SubscribeEvent
    public static void onBlockColorRegistry(ColorHandlerEvent.Item colorHandlerEvent) {
        ItemColors colors = colorHandlerEvent.getItemColors();
        colors.register((stack,color) ->{
            return Randores.randomColor[0];
        },RegistryHandler.COPPER_ORE_ITEM.get().getItem(),RegistryHandler.COPPER.get().getItem());
        colors.register((stack,color) ->{
            return Randores.randomColor[1];
        },RegistryHandler.ORE1_ITEM.get().getItem(),RegistryHandler.ORE1.get().getItem());
    }


}