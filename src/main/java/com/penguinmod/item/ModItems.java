package com.penguinmod.item;


import com.penguinmod.block.ModBlocks;
import com.penguinmod.entity.ModEntities;

import net.minecraft.item.Item.Settings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;

public class ModItems {
    
    private static final SpawnEggItem PENGUIN_SPAWN_EGG = new SpawnEggItem(ModEntities.PENGUIN, 15658718, 10592673, new Settings());

    public static void registerItems(){
        
        Registry.register(Registries.ITEM, Identifier.of("penguinmod", "penguin_spawn_egg"), PENGUIN_SPAWN_EGG);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> {
            content.add(PENGUIN_SPAWN_EGG);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> {
            content.add(ModBlocks.PENGUIN_EGG);
        });
        
    }

}
