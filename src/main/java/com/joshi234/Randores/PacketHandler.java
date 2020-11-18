package com.joshi234.Randores;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Randores.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );
    static int id=0;


    public PacketHandler( ){

        INSTANCE.registerMessage(id++,OreData.class,OreData::toBytes,OreData::new,OreData::handle);

    }
}
