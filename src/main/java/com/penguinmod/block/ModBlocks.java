package com.penguinmod.block;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    
    public static final PenguinEggBlock PENGUIN_EGG = new PenguinEggBlock(Block.Settings.create().strength(1.0f));

    public static void registerBlocks(){
        Registry.register(Registries.BLOCK, Identifier.of("penguinmod", "penguin_egg"), PENGUIN_EGG);
        Registry.register(Registries.ITEM, Identifier.of("penguinmod", "penguin_egg"), new BlockItem(PENGUIN_EGG, new Item.Settings()));

    }
}
