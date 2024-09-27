package com.penguinmod;

import com.penguinmod.entity.ModEntities;
import com.penguinmod.entity.client.ModModelLayers;
import com.penguinmod.entity.client.PenguinModel;
import com.penguinmod.entity.client.PenguinRenderer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class PenguinModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PENGUIN, PenguinModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.PENGUIN, PenguinRenderer::new);

    }
    
}
