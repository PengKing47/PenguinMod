package com.penguinmod.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer PENGUIN = 
        new EntityModelLayer(Identifier.of("penguinmod", "penguin"), "main");
}
