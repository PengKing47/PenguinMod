package com.penguinmod.entity.client;

import com.penguinmod.entity.custom.PenguinEntity;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class PenguinRenderer extends MobEntityRenderer<PenguinEntity, PenguinModel<PenguinEntity>> {
    private static final Identifier TEXTURE = Identifier.of("penguinmod", "textures/entity/penguin.png");

    public PenguinRenderer(EntityRendererFactory.Context context) {
        super(context, new PenguinModel<>(context.getPart(ModModelLayers.PENGUIN)), 0.6f);
    }

    @Override
    public Identifier getTexture(PenguinEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(PenguinEntity livingEntity, float f, float g, MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider, int i) {
        
        if(livingEntity.isBaby()){
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        }else{
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }


    
}
