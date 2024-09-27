package com.penguinmod;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.penguinmod.block.ModBlocks;
import com.penguinmod.effect.ModEffects;
import com.penguinmod.entity.ModEntities;
import com.penguinmod.item.ModItems;
import com.penguinmod.sound.ModSounds;
import com.penguinmod.world.gen.ModEntityGeneration;

public class PenguinMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("penguinmod");

	@Override
	public void onInitialize() {		

		ModEntities.registerEntities();
		ModSounds.registerSounds();
		ModEffects.registerEffects();
		ModEntityGeneration.addSpawns();
		ModBlocks.registerBlocks();
		ModItems.registerItems();

		LOGGER.info("Hello Fabric world!");
	}
}