package com.joshi234.Randores;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredRandomFeatureList;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RegistryHandler {
    // create DeferredRegister object
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, com.joshi234.Randores.Randores.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, com.joshi234.Randores.Randores.MODID);
    public static ArrayList<String> oreNames1=new ArrayList<String >();
    public static ArrayList<String> oreNames2=new ArrayList<String >();
    public static ArrayList<RegistryObject<Block>> ores=new ArrayList<RegistryObject<Block>> ();
    public   static Random random=new Random();
    // register item
    public static final RegistryObject<Item> COPPER = ITEMS.register("ore_ingot", () ->
            new IngotItem(
                    new Item.Properties().group(Randores.ITEM_GROUP)
            ).setId(0)
    );
    public static final RegistryObject<Item> ORE1 = ITEMS.register("ore1_ingot", () ->
            new IngotItem(
                    new Item.Properties().group(Randores.ITEM_GROUP)
            ).setId(1)
    );
    public static final RegistryObject<Block> COPPER_ORE = BLOCKS.register("ore", () ->
            new Block(
                    Block.Properties
                            .create(Material.IRON)
                            .hardnessAndResistance(3.0f, 3.0f)
                            .sound(SoundType.STONE)
                            .harvestLevel(1)
                            .harvestTool(ToolType.PICKAXE)
            )
    );
    public static final RegistryObject<Block> ORE1_ORE = BLOCKS.register("ore1", () ->
            new Block(
                    Block.Properties
                            .create(Material.IRON)
                            .hardnessAndResistance(3.0f, 3.0f)
                            .sound(SoundType.STONE)
                            .harvestLevel(1)
                            .harvestTool(ToolType.PICKAXE)
            )
    );

    public static final RegistryObject<Item> COPPER_ORE_ITEM = ITEMS.register("ore", () ->
            new OreItem(
                    COPPER_ORE.get(),
                    new Item.Properties().group(Randores.ITEM_GROUP).addToolType(ToolType.PICKAXE,2)
            ).setId(0)
    );
    public static final RegistryObject<Item> ORE1_ITEM = ITEMS.register("ore1", () ->
            new OreItem(
                    ORE1_ORE.get(),
                    new Item.Properties().group(Randores.ITEM_GROUP).addToolType(ToolType.PICKAXE,2)
            ).setId(1)
    );
    public static void init() {
        // attach DeferredRegister to the event bus
        ores.add(ORE1_ORE);
        ores.add(COPPER_ORE);
        oreNames1.add("Cop");
        oreNames2.add("per");
        oreNames2.add("thium");
        oreNames2.add("on");
        oreNames1.add("Ir");
        oreNames1.add("Li");
        oreNames2.add("sium");
        oreNames1.add("Angle");
        oreNames2.add("site");

        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }



    public static void addCopperOre(BiomeGenerationSettings.Builder biome) {
        for(int i=0;i<ores.size();i++) {
            biome.withFeature(
                    GenerationStage.Decoration.UNDERGROUND_ORES,
                    Feature.ORE.withConfiguration(
                            new OreFeatureConfig(
                                    OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                                    ores.get(i).get().getDefaultState(),
                                    12
                            )
                    ).range(80).square().func_242731_b(20)

            );
        }
    }

}