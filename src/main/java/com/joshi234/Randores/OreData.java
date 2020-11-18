package com.joshi234.Randores;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public class OreData   {
    private static final Logger LOGGER = LogManager.getLogger();
    public OreData(){

    }
    public OreData(PacketBuffer buffer){
        Randores.randomColor= buffer.readVarIntArray();
    }
    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        LOGGER.debug("got package! gg(:");
        return true;
    }
    public void toBytes(PacketBuffer bufffer){

        bufffer.writeVarIntArray(Randores.randomColor);
    }

}
