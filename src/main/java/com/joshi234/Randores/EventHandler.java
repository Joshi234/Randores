package com.joshi234.Randores;

import com.joshi234.Randores.RegistryHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus= Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {

    private static final Logger LOGGER = LogManager.getLogger();
    PacketHandler handler = new PacketHandler();
    public static int oreCount=2;
    public static String[] ore_name=new String[oreCount];
    @SubscribeEvent
    public void registerOres(BiomeLoadingEvent event) {
        RegistryHandler.addCopperOre(event.getGeneration());
    }
    @SubscribeEvent
    public void LoadWorld(PlayerEvent.PlayerLoggedInEvent event){
        handler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()), new OreData());
    }
    @SubscribeEvent
    public void WorldLoad(WorldEvent.Load event){
        if(event.getWorld().isRemote()==false) {

            if (event.getWorld() instanceof ServerWorld) {
                OreSaveData saver = OreSaveData.forWorld((ServerWorld) event.getWorld());
                CompoundNBT myData;
                myData = saver.data;
                if (myData.getInt("OreColor0") != 0) {
                    ore_name=new String[oreCount];
                    Randores.randomColor=new int[oreCount];
                    for(int i=0;i<oreCount;i++) {

                        Randores.randomColor[i] = myData.getInt("OreColor"+String.valueOf(i));
                        ore_name[i]=myData.getString("OreName"+String.valueOf(i));
                    }
                } else {

                    ore_name=new String[oreCount];
                    Randores.randomColor=new int[oreCount];
                    for(int i=0;i<oreCount;i++) {

                        ore_name[i]=RegistryHandler.oreNames1.get(RegistryHandler.random.nextInt(RegistryHandler.oreNames1.size()))+RegistryHandler.oreNames2.get(RegistryHandler.random.nextInt(RegistryHandler.oreNames2.size()));
                        Randores.randomColor[i]=new Color(Randores.random.nextInt(255),Randores.random.nextInt(255),Randores.random.nextInt(255)).getRGB();
                        myData.putInt("OreColor"+String.valueOf(i), Randores.randomColor[i]);
                        myData.putString("OreName"+String.valueOf(i),ore_name[i]);
                    }

                    saver.data = myData;
                    saver.markDirty();
                    LOGGER.debug(myData.getInt("OreColor0")+" orecolor");
                    LOGGER.debug("Put my data in!");
                }
            }
        }
    }
}
