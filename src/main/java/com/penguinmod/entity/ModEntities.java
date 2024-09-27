package com.penguinmod.entity;


import com.penguinmod.entity.custom.PenguinEntity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<PenguinEntity> PENGUIN = Registry.register(Registries.ENTITY_TYPE,
        Identifier.of("penguinmod", "penguin"), 
        EntityType.Builder.create(PenguinEntity::new, SpawnGroup.CREATURE)
            .dimensions(1f, 1f).build());


    public static void registerEntities(){
        FabricDefaultAttributeRegistry.register(ModEntities.PENGUIN, PenguinEntity.createPenguinAttributes());
    }
    
}
