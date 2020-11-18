package com.joshi234.Randores;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;

import java.util.function.Supplier;

public class OreSaveData extends WorldSavedData implements Supplier {
    public CompoundNBT data = new CompoundNBT();

    public OreSaveData()
    {
        super(Randores.MODID);
    }

    public OreSaveData(String name)
    {
        super(name);
    }

    @Override
    public void read(CompoundNBT nbt)
    {
        data = nbt.getCompound("color");
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt)
    {
        nbt.put("color", data);
        return nbt;
    }

    public static OreSaveData forWorld(ServerWorld world)
    {
        DimensionSavedDataManager storage = world.getSavedData();
        Supplier<OreSaveData> sup = new OreSaveData();
        OreSaveData saver = (OreSaveData) storage.getOrCreate(sup, Randores.MODID);

        if (saver == null)
        {
            saver = new OreSaveData();
            storage.set(saver);
        }
        return saver;
    }

    @Override
    public Object get()
    {
        return this;
    }
}
