package com.joshi234.Randores;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class OreItem extends BlockItem {
    int id=0;
    private String DisplayName="?Not set";
    public OreItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack){

            return new TranslationTextComponent(EventHandler.ore_name[id] + " Ore");

    }
    public BlockItem setId(int Id){
        id=Id;
        return this;
    }
}
