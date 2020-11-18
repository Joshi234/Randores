package com.joshi234.Randores;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class IngotItem extends Item {
    public int id=0;
    public IngotItem(Properties properties) {
        super(properties);
    }
    @Override
    public ITextComponent getDisplayName(ItemStack stack) {

            return new TranslationTextComponent(EventHandler.ore_name[id] + " Ingot");

    }
    public Item setId(int Id){
        id=Id;
        return this;
    }
}
