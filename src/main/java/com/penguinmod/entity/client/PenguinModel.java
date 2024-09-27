package com.penguinmod.entity.client;

import com.penguinmod.entity.animation.ModAnimations;
import com.penguinmod.entity.custom.PenguinEntity;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
@SuppressWarnings("unused")
public class PenguinModel<T extends PenguinEntity> extends SinglePartEntityModel<T> {
	private final ModelPart penguin;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart left_feather;
	private final ModelPart right_feather;
	private final ModelPart beak;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart left_foot;
	private final ModelPart right_foot;
	public PenguinModel(ModelPart root) {
		this.penguin = root.getChild("penguin");
		this.body = penguin.getChild("body");
		this.head = body.getChild("head");
		this.left_feather = head.getChild("left_feather");
		this.right_feather = head.getChild("right_feather");
		this.beak = head.getChild("beak");
		this.left_arm = body.getChild("left_arm");
		this.right_arm = body.getChild("right_arm");
		this.left_foot = body.getChild("left_foot");
		this.right_foot = body.getChild("right_foot");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData penguin = modelPartData.addChild("penguin", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 23.0F, 0.0F));

		ModelPartData body = penguin.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-4.5F, -12.0F, -4.0F, 9.0F, 12.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 20).cuboid(-3.5F, -5.0F, -3.0F, 7.0F, 5.0F, 6.0F, new Dilation(-0.001F)), ModelTransform.pivot(0.0F, -12.0F, 0.0F));

		ModelPartData left_feather = head.addChild("left_feather", ModelPartBuilder.create().uv(0, 5).cuboid(0.75F, -17.5F, -3.5F, 3.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(2, 5).cuboid(3.75F, -18.5F, -3.5F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(6, 3).cuboid(3.75F, -17.5F, -1.5F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(6, 0).cuboid(3.75F, -17.5F, 0.5F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(26, 1).cuboid(3.75F, -18.5F, -2.5F, 0.0F, 1.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 13.25F, 0.0F));

		ModelPartData right_feather = head.addChild("right_feather", ModelPartBuilder.create().uv(0, 4).cuboid(-3.75F, -17.5F, -3.5F, 3.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 5).cuboid(-3.75F, -18.5F, -3.5F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 5).cuboid(-3.75F, -17.5F, -1.5F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-3.75F, -17.5F, 0.5F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(26, 0).cuboid(-3.75F, -18.5F, -2.5F, 0.0F, 1.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 13.25F, 0.0F));

		ModelPartData beak = head.addChild("beak", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -14.5F, -5.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 12.0F, 0.0F));

		ModelPartData left_arm = body.addChild("left_arm", ModelPartBuilder.create().uv(28, 14).cuboid(0.5F, 0.0F, -3.0F, 1.0F, 10.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, -11.0F, 0.0F));

		ModelPartData right_arm = body.addChild("right_arm", ModelPartBuilder.create().uv(20, 25).cuboid(-1.5F, 0.0F, -3.0F, 1.0F, 10.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, -11.0F, 0.0F));

		ModelPartData left_foot = body.addChild("left_foot", ModelPartBuilder.create().uv(0, 31).cuboid(1.0F, -4.0F, -5.0F, 3.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 4.0F, 0.0F));

		ModelPartData right_foot = body.addChild("right_foot", ModelPartBuilder.create().uv(26, 0).cuboid(-4.0F, -4.0F, -5.0F, 3.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 4.0F, 0.0F));
		return TexturedModelData.of(modelData, 48, 48);
	}
	
	@Override
	public void setAngles(PenguinEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(headYaw, headPitch);

		if(entity.isInFluid()){
			this.animateMovement(ModAnimations.swim, limbAngle, limbDistance, 3.5f, 5.0F);
			this.updateAnimation(entity.idleAnimationState, ModAnimations.idle, animationProgress);
		}else{
			this.animateMovement(ModAnimations.walk, limbAngle, limbDistance, 3.5f, 5.0F);
			this.updateAnimation(entity.idleAnimationState, ModAnimations.idle, animationProgress);
		}

		


	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0f, 30.0f);
		headPitch = MathHelper.clamp(headPitch, -25.0f, 45.0f);

		this.head.yaw = headYaw * 0.017453292f;
		this.head.pitch = headPitch * 0.017453292f;
	}


	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		penguin.render(matrices, vertices, light, overlay);
	}
	@Override
	public ModelPart getPart() {
		return this.penguin;
	}
}