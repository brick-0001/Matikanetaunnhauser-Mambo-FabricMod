package brick_001.mambomodel.client.model;

import brick_001.mambomodel.client.render.entity.state.MamboCharacterEntityRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.model.EntityModel;

public class mambo_character extends EntityModel<MamboCharacterEntityRenderState> {
    // head is here for animation overrides
	private final ModelPart Head;

    private final Animation idleAnimation;
	private final Animation walkAnimation;
	private final Animation eatAnimation;
	private final Animation hurtAnimation;
	private final Animation sit_1Animation;
	private final Animation sit_2Animation;

	public mambo_character(ModelPart root) {
        super(root);
        ModelPart fullBody = root.getChild("FullBody");
		this.Head = fullBody.getChild("Head");
        ModelPart hair = this.Head.getChild("Hair");
        ModelPart hat = this.Head.getChild("Hat");
        ModelPart rightEar = this.Head.getChild("RightEar");
        ModelPart leftEar = this.Head.getChild("LeftEar");
        ModelPart waist = fullBody.getChild("Waist");
        ModelPart body = waist.getChild("Body");
        ModelPart skirt = waist.getChild("Skirt");
        ModelPart innerSkirt = skirt.getChild("innerSkirt");
        ModelPart outerSkirt = skirt.getChild("outerSkirt");
        ModelPart tail = waist.getChild("Tail");
        ModelPart fluff = tail.getChild("Fluff");
        ModelPart rightArm = fullBody.getChild("RightArm");
        ModelPart leftArm = fullBody.getChild("LeftArm");
        ModelPart rightLeg = fullBody.getChild("RightLeg");
        ModelPart leftLeg = fullBody.getChild("LeftLeg");

		this.idleAnimation = mambo_characterAnimation.Idle.createAnimation(root);
		this.walkAnimation = mambo_characterAnimation.Walk.createAnimation(root);
		this.eatAnimation = mambo_characterAnimation.Eat.createAnimation(root);
		this.hurtAnimation = mambo_characterAnimation.Hurt.createAnimation(root);
		this.sit_1Animation = mambo_characterAnimation.Sit_1.createAnimation(root);
		this.sit_2Animation = mambo_characterAnimation.Sit_2.createAnimation(root);
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData FullBody = modelPartData.addChild("FullBody", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0f, 0.0f, 0.0f));

		ModelPartData Head = FullBody.addChild("Head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
		.uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)), ModelTransform.of(0.0F, -24.0F, 0.0F, -0.1047F, 0.0873F, 0.0F));

		ModelPartData Hair = Head.addChild("Hair", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0f, 0.0f, 0.0f));

		ModelPartData HairRight_r1 = Hair.addChild("HairRight_r1", ModelPartBuilder.create().uv(97, 6).mirrored().cuboid(-0.7052F, -3.9627F, -0.9587F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-2.55F, 3.25F, -3.75F, -0.3491F, 0.0F, -0.1745F));

		ModelPartData HairLeft_r1 = Hair.addChild("HairLeft_r1", ModelPartBuilder.create().uv(97, 6).cuboid(-1.1469F, -3.9699F, -1.0029F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.45F, 3.25F, -3.75F, -0.3491F, 0.0F, 0.1745F));

		ModelPartData Hat = Head.addChild("Hat", ModelPartBuilder.create(), ModelTransform.of(-1.75F, -7.25F, -1.75F, 0.1309F, 0.48F, 0.0F));

		ModelPartData Brim_r1 = Hat.addChild("Brim_r1", ModelPartBuilder.create().uv(96, 0).cuboid(-5.0F, -1.0F, -7.0F, 8.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(64, 0).cuboid(-5.0F, -3.0F, -6.0F, 8.0F, 3.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 2.0F, 0.3054F, -0.0873F, -0.3491F));

		ModelPartData RightEar = Head.addChild("RightEar", ModelPartBuilder.create(), ModelTransform.of(-3.0F, -8.0F, -3.0F, 0.0f, 0.0f, 0.0f));

		ModelPartData EarRightLower_r1 = RightEar.addChild("EarRightLower_r1", ModelPartBuilder.create().uv(118, 2).mirrored().cuboid(-0.7847F, -1.2719F, -0.8454F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.199F, -0.2236F, 0.503F, -0.0873F, -0.0436F, -0.5672F));

		ModelPartData EarRightLow_r1 = RightEar.addChild("EarRightLow_r1", ModelPartBuilder.create().uv(118, 2).mirrored().cuboid(-7.6197F, -3.2776F, 0.4996F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(6.199F, 1.2764F, -0.247F, 0.0873F, -0.0436F, 0.1309F));

		ModelPartData EarRightHigh_r1 = RightEar.addChild("EarRightHigh_r1", ModelPartBuilder.create().uv(114, 0).mirrored().cuboid(-5.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(4.949F, -1.7236F, 0.753F, 0.0F, -0.0436F, -0.3927F));

		ModelPartData beadRed_r1 = RightEar.addChild("beadRed_r1", ModelPartBuilder.create().uv(122, 0).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, -0.75F, 0.25F, -0.1953F, -0.426F, 1.2041F));

		ModelPartData beadBlue_r1 = RightEar.addChild("beadBlue_r1", ModelPartBuilder.create().uv(118, 0).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, -1.25F, -0.25F, -0.9807F, -0.426F, 1.2041F));

		ModelPartData LeftEar = Head.addChild("LeftEar", ModelPartBuilder.create(), ModelTransform.of(3.551F, -8.2236F, -2.747F, 0.0F, -0.5672F, 0.1309F));

		ModelPartData EarLeftLower_r1 = LeftEar.addChild("EarLeftLower_r1", ModelPartBuilder.create().uv(118, 2).cuboid(-0.2153F, -1.2719F, -0.8454F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 0.25F, -0.0873F, 0.0436F, 0.5672F));

		ModelPartData EarLeftLow_r1 = LeftEar.addChild("EarLeftLow_r1", ModelPartBuilder.create().uv(118, 2).cuboid(6.6197F, -3.2776F, 0.4996F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, 2.0F, -0.5F, 0.0873F, 0.0436F, -0.1309F));

		ModelPartData EarLeftHigh_r1 = LeftEar.addChild("EarLeftHigh_r1", ModelPartBuilder.create().uv(114, 0).cuboid(4.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-4.75F, -1.0F, 0.5F, 0.0F, 0.0436F, 0.3927F));

		ModelPartData Waist = FullBody.addChild("Waist", ModelPartBuilder.create(), ModelTransform.of(0.0F, -12.0F, 0.0F, 0.0f, 0.0f, 0.0f));

		ModelPartData Body = Waist.addChild("Body", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(16, 32).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(0.0F, -12.0F, 0.0F, 0.0F, 0.0F, 0.0F));

		ModelPartData Bag_r1 = Body.addChild("Bag_r1", ModelPartBuilder.create().uv(89, 0).cuboid(-5.25F, 0.0F, -1.25F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 12.0F, 0.0F, 0.1309F, 0.0F, 0.4363F));

		ModelPartData tits_r1 = Body.addChild("tits_r1", ModelPartBuilder.create().uv(19, 19).cuboid(-2.0F, -3.0F, -1.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.1F, 3.75F, -2.75F, -0.6109F, -0.0436F, 0.0F));

		ModelPartData tits_r2 = Body.addChild("tits_r2", ModelPartBuilder.create().uv(19, 19).cuboid(-2.0F, -3.0F, -1.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.1F, 3.75F, -2.75F, -0.6109F, 0.0F, 0.0F));

		ModelPartData Skirt = Waist.addChild("Skirt", ModelPartBuilder.create(), ModelTransform.of(0.0F, -0.2F, 0.0F, 0.0f, 0.0f, 0.0f));

		ModelPartData topperBack_r1 = Skirt.addChild("topperBack_r1", ModelPartBuilder.create().uv(80, 11).cuboid(-5.0F, 0.0F, -1.0F, 6.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, -0.55F, 2.15F, -2.7489F, 0.0F, 3.1416F));

		ModelPartData topperFront_r1 = Skirt.addChild("topperFront_r1", ModelPartBuilder.create().uv(64, 11).cuboid(-5.0F, -1.0F, -1.0F, 6.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 0.5F, -1.5F, 0.3927F, 0.0F, 0.0F));

		ModelPartData innerSkirt = Skirt.addChild("innerSkirt", ModelPartBuilder.create().uv(90, 13).cuboid(-4.75F, 0.0F, -3.0F, 9.0F, 1.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 3.0F, 0.0F, 0.0f, 0.0f, 0.0f));

		ModelPartData back_r1 = innerSkirt.addChild("back_r1", ModelPartBuilder.create().uv(60, 14).cuboid(-5.0F, -1.0F, -1.0F, 6.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(1.8F, -0.25F, 2.15F, -0.7854F, 0.0F, 0.0F));

		ModelPartData left_r1 = innerSkirt.addChild("left_r1", ModelPartBuilder.create().uv(62, 15).mirrored().cuboid(-1.0F, -1.0F, -1.0F, 5.0F, 1.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-5.25F, 0.45F, 1.3F, -2.2689F, 1.6144F, -3.1416F));

		ModelPartData left_r2 = innerSkirt.addChild("left_r2", ModelPartBuilder.create().uv(62, 15).cuboid(-4.0F, -1.0F, -1.0F, 5.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(4.75F, 0.75F, 1.6F, -2.2689F, -1.6144F, 3.1416F));

		ModelPartData front_r1 = innerSkirt.addChild("front_r1", ModelPartBuilder.create().uv(60, 14).cuboid(-5.0F, -1.0F, -1.0F, 6.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(1.8F, 1.25F, -3.0F, 0.8727F, -0.0436F, 0.0F));

		ModelPartData outerSkirt = Skirt.addChild("outerSkirt", ModelPartBuilder.create(), ModelTransform.of(-3.5F, 3.5F, 4.5F, 0.0f, 0.0f, 0.0f));

		ModelPartData back3_r1 = outerSkirt.addChild("back3_r1", ModelPartBuilder.create().uv(107, 5).cuboid(-2.5F, -4.0F, -1.0F, 4.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.5236F, -0.1745F, 0.0873F));

		ModelPartData back2_r1 = outerSkirt.addChild("back2_r1", ModelPartBuilder.create().uv(107, 5).cuboid(-2.75F, -4.0F, -1.0F, 4.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(6.25F, 0.5F, -0.75F, -0.5236F, -2.7925F, -0.0436F));

		ModelPartData back1_r1 = outerSkirt.addChild("back1_r1", ModelPartBuilder.create().uv(118, 5).cuboid(-2.0F, -4.0F, -1.0F, 4.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.5F, 0.5F, 0.0F, -0.5672F, 3.1416F, 0.0F));

		ModelPartData side2_r1 = outerSkirt.addChild("side2_r1", ModelPartBuilder.create().uv(118, 5).cuboid(-0.0681F, -2.0121F, -0.3981F, 4.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, -2.0F, -2.75F, -0.5672F, 1.5272F, 0.0F));

		ModelPartData side1_r1 = outerSkirt.addChild("side1_r1", ModelPartBuilder.create().uv(118, 5).cuboid(-0.0681F, -2.0121F, -0.3981F, 4.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(8.4F, -1.75F, -6.5F, -0.5672F, -1.4835F, 0.0F));

		ModelPartData front3_r1 = outerSkirt.addChild("front3_r1", ModelPartBuilder.create().uv(118, 5).cuboid(-3.0F, -4.0F, -1.0F, 4.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(4.5F, 0.5F, -8.5F, -0.5672F, -0.0873F, 0.0F));

		ModelPartData front2_r1 = outerSkirt.addChild("front2_r1", ModelPartBuilder.create().uv(107, 5).cuboid(-3.0F, -4.0F, -1.0F, 4.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.75F, 0.5F, -8.25F, -0.5236F, 0.2618F, 0.1309F));

		ModelPartData front1_r1 = outerSkirt.addChild("front1_r1", ModelPartBuilder.create().uv(107, 5).cuboid(-3.0F, -4.0F, -1.0F, 4.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(6.0F, 0.0F, -8.75F, 0.5236F, 2.8798F, -0.1309F));

		ModelPartData Tail = Waist.addChild("Tail", ModelPartBuilder.create(), ModelTransform.of(0.0F, -0.5F, 1.75F, 0.5423F, -0.9561F, -0.6401F));

		ModelPartData OuterSegment_r1 = Tail.addChild("OuterSegment", ModelPartBuilder.create().uv(110, 23).cuboid(-1.1F, -0.9F, -0.7F, 2.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.567F, 4.2141F, -0.9712F, -0.1245F, 0.1796F));

		ModelPartData InnerSegment_r1 = Tail.addChild("InnerSegment", ModelPartBuilder.create().uv(98, 20).cuboid(-1.5F, -1.5F, -7.0F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, 3.5F, 4.8301F, -0.5236F, 0.0F, 0.0F));

		ModelPartData Fluff = Tail.addChild("Fluff", ModelPartBuilder.create(), ModelTransform.of(-0.9076F, 5.6867F, 6.3661F, -0.22F, -0.1278F, 0.0285F));

		ModelPartData FluffSegment4_r1 = Fluff.addChild("FluffSegment4_r1", ModelPartBuilder.create().uv(120, 17).cuboid(0.85F, -0.9F, 3.8F, 0.0F, 2.0F, 3.0F, new Dilation(-0.25F))
		.uv(120, 19).cuboid(-0.65F, -0.9F, 3.8F, 0.0F, 2.0F, 3.0F, new Dilation(-0.25F)), ModelTransform.of(0.9076F, -3.1197F, -2.152F, -0.2182F, 0.9599F, 1.5708F));

		ModelPartData FluffSegment2_r1 = Fluff.addChild("FluffSegment2_r1", ModelPartBuilder.create().uv(120, 21).cuboid(-0.85F, -0.9F, 3.8F, 0.0F, 2.0F, 3.0F, new Dilation(-0.25F))
		.uv(120, 23).cuboid(0.65F, -0.9F, 3.8F, 0.0F, 2.0F, 3.0F, new Dilation(-0.25F)), ModelTransform.of(0.9076F, -3.1197F, -2.152F, -0.9712F, -0.1245F, 0.1796F));

		ModelPartData RightArm = FullBody.addChild("RightArm", ModelPartBuilder.create().uv(42, 16).cuboid(-1.0F, -2.0F, -2.0F, 2.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(42, 32).cuboid(-1.0F, -2.0F, -2.0F, 2.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(-5.0F, -22.0F, 0.0F, -0.1745F, 0.0F, 0.0873F));

		ModelPartData LeftArm = FullBody.addChild("LeftArm", ModelPartBuilder.create().uv(34, 48).cuboid(-1.0F, -2.0F, -2.0F, 2.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(50, 48).cuboid(-1.0F, -2.0F, -2.0F, 2.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(5.0F, -22.0F, 0.0F, 0.2094F, 0.0F, -0.0873F));

		ModelPartData RightLeg = FullBody.addChild("RightLeg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 32).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(-1.9F, -12.0F, 0.0F, 0.192F, 0.0F, 0.0349F));

		ModelPartData LeftLeg = FullBody.addChild("LeftLeg", ModelPartBuilder.create().uv(16, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(1.9F, -12.0F, 0.0F, -0.1745F, 0.0F, -0.0349F));
		return TexturedModelData.of(modelData, 128, 64);
	}

	@Override
	public void setAngles(MamboCharacterEntityRenderState state) {
		super.setAngles(state);

		this.resetTransforms();

		this.Head.yaw = state.relativeHeadYaw * ((float)Math.PI / 180F);
		this.Head.pitch = state.pitch * ((float)Math.PI / 180F);

		this.hurtAnimation.apply(state.hurtAnimationState, state.age, 1.0F);
		this.eatAnimation.apply(state.eatAnimationState, state.age, 1.0F);
		this.idleAnimation.apply(state.idleAnimationState, state.age, 1.0F);
		this.walkAnimation.applyWalking(state.limbSwingAnimationProgress, state.limbSwingAmplitude, 4.0F, 1.0F);
		this.sit_1Animation.apply(state.sit_1AnimationState, state.age, 1.0F);
		this.sit_2Animation.apply(state.sit_2AnimationState, state.age, 1.0F);
	}
}