package com.waltsai.beegirl.client;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.waltsai.beegirl.ModUtils;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;

public class HoneyBeeGirlEntityModel<T extends LivingEntity> extends BipedEntityModel<T> {
    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(ModUtils.MOD_ID, "honey_bee_girl"), "main");
    private final ModelPart chest;
    private final ModelPart bodyLayer;
    private final ModelPart backHair1;
    private final ModelPart backHair2;
    private final ModelPart backHair3;
    private final ModelPart backHair4;
    private final ModelPart leftFrontHair;
    private final ModelPart rightFrontHair;
    private final ModelPart leftSleeve;
    private final ModelPart rightSleeve;
    private final ModelPart leftPants;
    private final ModelPart rightPants;
    private final ModelPart leftWing;
    private final ModelPart rightWing;
    private int landingTick;
    private boolean prevOnGround;

    public HoneyBeeGirlEntityModel(ModelPart root) {
        super(root, RenderLayer::getEntityTranslucent);
        this.bodyLayer = root.getChild("body_layer");
        this.chest = root.getChild("chest");
        this.backHair1 = root.getChild("back_hair1");
        this.backHair2 = root.getChild("back_hair2");
        this.backHair3 = root.getChild("back_hair3");
        this.backHair4 = root.getChild("back_hair4");
        this.leftFrontHair = root.getChild("left_front_hair");
        this.rightFrontHair = root.getChild("right_front_hair");
        this.leftSleeve = root.getChild("left_sleeve");
        this.rightSleeve = root.getChild("right_sleeve");
        this.leftPants = root.getChild("left_pants");
        this.rightPants = root.getChild("right_pants");
        this.leftWing = root.getChild("left_wing");
        this.rightWing = root.getChild("right_wing");
    }

    public static TexturedModelData getTexturedModelData(Dilation dilation) {
        ModelData modelData = BipedEntityModel.getModelData(dilation, 0.0F);

        ModelPartData modelPartData = modelData.getRoot();

        modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        modelPartData.addChild("hat", ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        modelPartData.addChild("body", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(3, 93).cuboid(-4.0F, 2.62F, -4.06F, 8.0F, 1.0F, 1.0F, new Dilation(-0.101F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData chest = modelPartData.addChild("chest", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        chest.addChild("body_r1", ModelPartBuilder.create().uv(0, 85).cuboid(-4.0F, 11.5821F, 13.3821F, 8.0F, 4.0F, 4.0F, new Dilation(-0.105F)), ModelTransform.of(0.0F, 24.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

        chest.addChild("body_r2", ModelPartBuilder.create().uv(0, 77).cuboid(-4.0F, -2.0F, -0.2F, 8.0F, 4.0F, 4.0F, new Dilation(-0.1F)), ModelTransform.of(0.0F, 4.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

        modelPartData.addChild("body_layer", ModelPartBuilder.create().uv(16, 33).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.245F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData left_front_hair = modelPartData.addChild("left_front_hair", ModelPartBuilder.create().uv(24, 84).cuboid(-4.0F, 2.54F, -4.35F, 3.0F, 1.0F, 1.0F, new Dilation(-0.106F))
                .uv(24, 86).cuboid(-4.0F, 3.328F, -4.35F, 3.0F, 2.0F, 1.0F, new Dilation(-0.106F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        left_front_hair.addChild("body_r3", ModelPartBuilder.create().uv(24, 80).cuboid(-4.0F, -6.0609F, -1.852F, 3.0F, 3.0F, 1.0F, new Dilation(-0.105F)), ModelTransform.of(0.0F, 6.1209F, -5.248F, -0.7854F, 0.0F, 0.0F));

        ModelPartData right_front_hair = modelPartData.addChild("right_front_hair", ModelPartBuilder.create().uv(32, 84).cuboid(1.0F, 2.54F, -4.35F, 2.0F, 1.0F, 1.0F, new Dilation(-0.106F))
                .uv(32, 86).cuboid(1.894F, 3.328F, -4.35F, 2.0F, 2.0F, 1.0F, new Dilation(-0.106F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        right_front_hair.addChild("body_r4", ModelPartBuilder.create().uv(32, 80).cuboid(1.0F, -6.0609F, -1.852F, 3.0F, 3.0F, 1.0F, new Dilation(-0.105F)), ModelTransform.of(0.0F, 6.1209F, -5.248F, -0.7854F, 0.0F, 0.0F));

        modelPartData.addChild("back_hair1", ModelPartBuilder.create().uv(0, 67).cuboid(2.6F, -1.0F, 0.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F))
                .uv(4, 67).cuboid(1.9F, -1.0F, 0.0F, 1.0F, 8.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 67).mirrored().cuboid(-3.6F, -1.0F, 0.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 0.0F, 2.5F));

        modelPartData.addChild("back_hair4", ModelPartBuilder.create().uv(4, 67).mirrored().cuboid(-2.9F, -1.0F, 0.0F, 1.0F, 8.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 0.0F, 2.5F));

        modelPartData.addChild("back_hair2", ModelPartBuilder.create().uv(8, 67).cuboid(1.1F, -1.0F, 0.0F, 1.0F, 10.0F, 1.0F, new Dilation(0.0F))
                .uv(12, 67).cuboid(0.1F, -1.0F, 0.0F, 1.0F, 10.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 2.5F));

        modelPartData.addChild("back_hair3", ModelPartBuilder.create().uv(8, 67).mirrored().cuboid(-2.1F, -1.0F, 0.0F, 1.0F, 10.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(12, 67).mirrored().cuboid(-1.1F, -1.0F, 0.0F, 1.0F, 10.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 0.0F, 2.5F));

        modelPartData.addChild("left_arm", ModelPartBuilder.create().uv(32, 49).cuboid(-1.5F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, 1.0F, 0.0F));

        modelPartData.addChild("left_sleeve", ModelPartBuilder.create().uv(48, 49).cuboid(-1.5F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.2475F)), ModelTransform.pivot(5.0F, 1.0F, 0.0F));

        modelPartData.addChild("right_sleeve", ModelPartBuilder.create().uv(40, 33).cuboid(-2.5F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.2475F)), ModelTransform.pivot(-5.0F, 1.0F, 0.0F));

        modelPartData.addChild("right_arm", ModelPartBuilder.create().uv(40, 17).cuboid(-2.5F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 1.0F, 0.0F));

        modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(16, 49).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 12.0F, 0.0F));

        modelPartData.addChild("left_pants", ModelPartBuilder.create().uv(0, 49).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(2.0F, 12.0F, 0.0F));

        modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));

        modelPartData.addChild("right_pants", ModelPartBuilder.create().uv(0, 32).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));

        modelPartData.addChild("left_wing", ModelPartBuilder.create().uv(20, 46).mirrored().cuboid(0.0F, -7.0F, -2.5F, 0.0F, 9.0F, 22.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 4.0F, 2.0F));

        modelPartData.addChild("right_wing", ModelPartBuilder.create().uv(20, 46).cuboid(0.0F, -7.0F, -2.5F, 0.0F, 9.0F, 22.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 4.0F, 2.0F));


        return TexturedModelData.of(modelData, 64, 96);
    }

    @Override
    public void setAngles(T livingEntity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        boolean bl = livingEntity.getRoll() > 4;
        boolean bl2 = livingEntity.isInSwimmingPose();
        this.head.yaw = headYaw * 0.017453292F;
        if (bl) {
            this.head.pitch = -0.7853982F;
        } else if (this.leaningPitch > 0.0F) {
            if (bl2) {
                this.head.pitch = this.lerpAngle(this.leaningPitch, this.head.pitch, -0.7853982F);
            } else {
                this.head.pitch = this.lerpAngle(this.leaningPitch, this.head.pitch, headYaw * 0.017453292F);
            }
        } else {
            this.head.pitch = headPitch * 0.017453292F;
        }

        this.body.yaw = 0.0F;
        this.rightArm.pivotZ = 0.0F;
        this.rightArm.pivotX = -5.0F;
        this.leftArm.pivotZ = 0.0F;
        this.leftArm.pivotX = 5.0F;
        float k = 1.0F;
        if (bl) {
            k = (float)livingEntity.getVelocity().lengthSquared();
            k /= 0.2F;
            k *= k * k;
        }

        if (k < 1.0F) {
            k = 1.0F;
        }

        this.rightArm.roll = 0.0F;
        this.leftArm.roll = 0.0F;

        if (livingEntity.isOnGround() ^ prevOnGround) {
            this.landingTick++;
            if (this.landingTick > 25) {
                this.prevOnGround = livingEntity.isOnGround();
                this.landingTick = 0;
            }
        } else if (this.landingTick > 0) {
            this.landingTick--;
        }

        System.out.println(limbAngle);

        if(livingEntity.isOnGround()) {
            if(this.landingTick > 0) {
                this.leftWing.pitch = MathHelper.lerp(0.125F, this.leftWing.pitch, -0.1745F);
                this.leftWing.yaw = MathHelper.lerp(0.125F, this.leftWing.yaw, 0.8727F);
                this.leftWing.roll = MathHelper.lerp(0.125F, this.leftWing.roll, 1.309F);

                this.rightArm.pitch = MathHelper.lerp(0.35F, this.rightArm.pitch, MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 2.0F * limbDistance * 0.5F / k);
                this.leftArm.pitch = MathHelper.lerp(0.35F, this.leftArm.pitch, MathHelper.cos(limbAngle * 0.6662F) * 2.0F * limbDistance * 0.5F / k);
                this.rightLeg.pitch = MathHelper.lerp(0.35F, this.rightLeg.pitch, MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance / k);
                this.leftLeg.pitch = MathHelper.lerp(0.35F, this.leftLeg.pitch, MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance / k);
            } else {
                this.leftWing.pitch = -0.1745F;
                this.leftWing.yaw = 0.8727F;
                this.leftWing.roll = 1.309F;

                this.rightArm.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 2.0F * limbDistance * 0.5F / k;
                this.leftArm.pitch = MathHelper.cos(limbAngle * 0.6662F) * 2.0F * limbDistance * 0.5F / k;
                this.rightLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance / k;
                this.leftLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance / k;
            }
        } else {
            if(this.landingTick > 0) {
                this.leftWing.pitch = MathHelper.lerp(0.125F, this.leftWing.pitch, 0);
                this.leftWing.yaw = MathHelper.lerp(0.125F, this.leftWing.yaw, -1.5707965F * Math.abs(MathHelper.cos(limbAngle * 4.7123889F) * 0.35F / k));
                this.leftWing.roll = MathHelper.lerp(0.125F, this.leftWing.roll, 0);

                this.rightArm.pitch = MathHelper.lerp(0.35F, this.rightArm.pitch, MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 0.25F * limbDistance * 0.5F / k + 0.35F);
                this.leftArm.pitch = MathHelper.lerp(0.35F, this.leftArm.pitch, MathHelper.cos(limbAngle * 0.6662F) * 0.25F * limbDistance * 0.5F / k + 0.35F);
                this.rightLeg.pitch = MathHelper.lerp(0.35F, this.rightLeg.pitch, 0.51F + 0.18F * limbDistance);
                this.leftLeg.pitch = MathHelper.lerp(0.35F, this.leftLeg.pitch, 0.35F + 0.18F * limbDistance);
            } else {
                this.leftWing.pitch = 0;
                this.leftWing.yaw = -1.5707965F * Math.abs(MathHelper.cos(limbAngle * 4.7123889F) * 0.35F / k);
                this.leftWing.roll = 0;

                this.rightArm.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 0.25F * limbDistance * 0.5F / k + 0.35F;
                this.leftArm.pitch = MathHelper.cos(limbAngle * 0.6662F) * 0.25F * limbDistance * 0.5F / k + 0.35F;
                this.rightLeg.pitch = 0.51F + 0.18F * limbDistance;
                this.leftLeg.pitch = 0.35F + 0.18F * limbDistance;
            }
        }

        this.rightWing.pitch = this.leftWing.pitch;
        this.rightWing.yaw = -this.leftWing.yaw;
        this.rightWing.roll = -this.leftWing.roll;

        this.rightLeg.yaw = 0.0F;
        this.leftLeg.yaw = 0.0F;
        this.rightLeg.roll = 0.0F;
        this.leftLeg.roll = 0.0F;



        ModelPart var10000;
        if (this.riding) {
            var10000 = this.rightArm;
            var10000.pitch += -0.62831855F;
            var10000 = this.leftArm;
            var10000.pitch += -0.62831855F;
            this.rightLeg.pitch = -1.4137167F;
            this.rightLeg.yaw = 0.31415927F;
            this.rightLeg.roll = 0.07853982F;
            this.leftLeg.pitch = -1.4137167F;
            this.leftLeg.yaw = -0.31415927F;
            this.leftLeg.roll = -0.07853982F;
        }

        this.rightArm.yaw = 0.0F;
        this.leftArm.yaw = 0.0F;
        boolean bl3 = livingEntity.getMainArm() == Arm.RIGHT;
        boolean bl4;
        if (livingEntity.isUsingItem()) {
            bl4 = livingEntity.getActiveHand() == Hand.MAIN_HAND;
            if (bl4 == bl3) {
                this.positionRightArm(livingEntity);
            } else {
                this.positionLeftArm(livingEntity);
            }
        } else {
            bl4 = bl3 ? this.leftArmPose.isTwoHanded() : this.rightArmPose.isTwoHanded();
            if (bl3 != bl4) {
                this.positionLeftArm(livingEntity);
                this.positionRightArm(livingEntity);
            } else {
                this.positionRightArm(livingEntity);
                this.positionLeftArm(livingEntity);
            }
        }
        this.animateArms(livingEntity, animationProgress);

        if (this.sneaking) {
            this.body.pitch = 0.5F;
            var10000 = this.rightArm;
            var10000.pitch += 0.4F;
            var10000 = this.leftArm;
            var10000.pitch += 0.4F;
            this.rightLeg.pivotZ = 4.0F;
            this.leftLeg.pivotZ = 4.0F;
            this.rightLeg.pivotY = 12.2F;
            this.leftLeg.pivotY = 12.2F;
            this.head.pivotY = 4.2F;
            this.body.pivotY = 3.2F;
            this.leftArm.pivotY = 5.2F;
            this.rightArm.pivotY = 5.2F;
        } else {
            this.body.pitch = 0.0F;
            this.rightLeg.pivotZ = 0.1F;
            this.leftLeg.pivotZ = 0.1F;
            this.rightLeg.pivotY = 12.0F;
            this.leftLeg.pivotY = 12.0F;
            this.head.pivotY = 0.0F;
            this.body.pivotY = 0.0F;
            this.leftArm.pivotY = 2.0F;
            this.rightArm.pivotY = 2.0F;
        }

        HoneyBeeGirlEntityModel.swingArm(this.rightArm, animationProgress, 1.0F);
        HoneyBeeGirlEntityModel.swingArm(this.leftArm, animationProgress, -1.0F);

        if (this.leaningPitch > 0.0F) {
            float l = limbAngle % 26.0F;
            Arm arm = this.getPreferredArm(livingEntity);
            float m = arm == Arm.RIGHT && this.handSwingProgress > 0.0F ? 0.0F : this.leaningPitch;
            float n = arm == Arm.LEFT && this.handSwingProgress > 0.0F ? 0.0F : this.leaningPitch;
            float p;
            if (!livingEntity.isUsingItem()) {
                if (l < 14.0F) {
                    this.leftArm.pitch = this.lerpAngle(n, this.leftArm.pitch, 0.0F);
                    this.rightArm.pitch = MathHelper.lerp(m, this.rightArm.pitch, 0.0F);
                    this.leftArm.yaw = this.lerpAngle(n, this.leftArm.yaw, 3.1415927F);
                    this.rightArm.yaw = MathHelper.lerp(m, this.rightArm.yaw, 3.1415927F);
                    this.leftArm.roll = this.lerpAngle(n, this.leftArm.roll, 3.1415927F + 1.8707964F * this.method_2807(l) / this.method_2807(14.0F));
                    this.rightArm.roll = MathHelper.lerp(m, this.rightArm.roll, 3.1415927F - 1.8707964F * this.method_2807(l) / this.method_2807(14.0F));
                } else if (l >= 14.0F && l < 22.0F) {
                    p = (l - 14.0F) / 8.0F;
                    this.leftArm.pitch = this.lerpAngle(n, this.leftArm.pitch, 1.5707964F * p);
                    this.rightArm.pitch = MathHelper.lerp(m, this.rightArm.pitch, 1.5707964F * p);
                    this.leftArm.yaw = this.lerpAngle(n, this.leftArm.yaw, 3.1415927F);
                    this.rightArm.yaw = MathHelper.lerp(m, this.rightArm.yaw, 3.1415927F);
                    this.leftArm.roll = this.lerpAngle(n, this.leftArm.roll, 5.012389F - 1.8707964F * p);
                    this.rightArm.roll = MathHelper.lerp(m, this.rightArm.roll, 1.2707963F + 1.8707964F * p);
                } else if (l >= 22.0F && l < 26.0F) {
                    p = (l - 22.0F) / 4.0F;
                    this.leftArm.pitch = this.lerpAngle(n, this.leftArm.pitch, 1.5707964F - 1.5707964F * p);
                    this.rightArm.pitch = MathHelper.lerp(m, this.rightArm.pitch, 1.5707964F - 1.5707964F * p);
                    this.leftArm.yaw = this.lerpAngle(n, this.leftArm.yaw, 3.1415927F);
                    this.rightArm.yaw = MathHelper.lerp(m, this.rightArm.yaw, 3.1415927F);
                    this.leftArm.roll = this.lerpAngle(n, this.leftArm.roll, 3.1415927F);
                    this.rightArm.roll = MathHelper.lerp(m, this.rightArm.roll, 3.1415927F);
                }
            }

            this.leftLeg.pitch = MathHelper.lerp(this.leaningPitch, this.leftLeg.pitch, 0.3F * MathHelper.cos(limbAngle * 0.33333334F + 3.1415927F));
            this.rightLeg.pitch = MathHelper.lerp(this.leaningPitch, this.rightLeg.pitch, 0.3F * MathHelper.cos(limbAngle * 0.33333334F));
        }

        this.hat.copyTransform(this.head);
        this.leftPants.copyTransform(this.leftLeg);
        this.rightPants.copyTransform(this.rightLeg);
        this.leftSleeve.copyTransform(this.leftArm);
        this.rightSleeve.copyTransform(this.rightArm);
        this.bodyLayer.copyTransform(this.body);
        this.chest.copyTransform(this.body);
        this.leftFrontHair.copyTransform(this.body);
        this.rightFrontHair.copyTransform(this.body);
    }

    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return Iterables.concat(super.getBodyParts(), ImmutableList.of(this.leftSleeve, this.rightSleeve, this.leftPants, this.rightPants, this.bodyLayer, this.chest, this.leftFrontHair, this.rightFrontHair, this.leftWing, this.rightWing));
    }

    public void renderBackHair(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float q, float r, float s) {
        matrices.push();
        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(6.0F + r / 2.5F + q));
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F - s / 2.0F));
        matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(s / 2.0F));
        this.backHair1.render(matrices, vertices, light, overlay);
        matrices.pop();

        matrices.push();
        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(6.0F + r / 2.0F + q));
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F - s / 2.0F));
        matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(s / 2.0F));
        this.backHair2.render(matrices, vertices, light, overlay);
        matrices.pop();

        matrices.push();
        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(6.0F + r / 2.0F + q));
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F - s / 2.0F));
        matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(s / 2.0F));
        this.backHair3.render(matrices, vertices, light, overlay);
        matrices.pop();

        matrices.push();
        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(6.0F + r / 2.5F + q));
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F - s / 2.0F));
        matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(s / 2.0F));
        this.backHair4.render(matrices, vertices, light, overlay);
        matrices.pop();
    }

    protected void animateArms(T entity, float animationProgress) {
        if (!(this.handSwingProgress <= 0.0F)) {
            Arm arm = this.getPreferredArm(entity);
            ModelPart modelPart = this.getArm(arm);
            float f = this.handSwingProgress;
            this.body.yaw = MathHelper.sin(MathHelper.sqrt(f) * 6.2831855F) * 0.2F;
            ModelPart var10000;
            if (arm == Arm.LEFT) {
                var10000 = this.body;
                var10000.yaw *= -1.0F;
            }

            this.rightArm.pivotZ = MathHelper.sin(this.body.yaw) * 5.0F;
            this.rightArm.pivotX = -MathHelper.cos(this.body.yaw) * 5.0F;
            this.leftArm.pivotZ = -MathHelper.sin(this.body.yaw) * 5.0F;
            this.leftArm.pivotX = MathHelper.cos(this.body.yaw) * 5.0F;
            var10000 = this.rightArm;
            var10000.yaw += this.body.yaw;
            var10000 = this.leftArm;
            var10000.yaw += this.body.yaw;
            var10000 = this.leftArm;
            var10000.pitch += this.body.yaw;
            f = 1.0F - this.handSwingProgress;
            f *= f;
            f *= f;
            f = 1.0F - f;
            float g = MathHelper.sin(f * 3.1415927F);
            float h = MathHelper.sin(this.handSwingProgress * 3.1415927F) * -(this.head.pitch - 0.7F) * 0.75F;
            modelPart.pitch = (float)((double)modelPart.pitch - ((double)g * 1.2D + (double)h));
            modelPart.yaw += this.body.yaw * 2.0F;
            modelPart.roll += MathHelper.sin(this.handSwingProgress * 3.1415927F) * -0.4F;
        }
    }

    private void positionRightArm(T entity) {
        switch(this.rightArmPose) {
            case EMPTY:
                this.rightArm.yaw = 0.0F;
                break;
            case BLOCK:
                this.rightArm.pitch = this.rightArm.pitch * 0.5F - 0.9424779F;
                this.rightArm.yaw = -0.5235988F;
                break;
            case ITEM:
                this.rightArm.pitch = this.rightArm.pitch * 0.5F - 0.31415927F;
                this.rightArm.yaw = 0.0F;
                break;
            case BOW_AND_ARROW:
                this.rightArm.yaw = -0.1F + this.head.yaw;
                this.leftArm.yaw = 0.1F + this.head.yaw + 0.4F;
                this.rightArm.pitch = -1.5707964F + this.head.pitch;
                this.leftArm.pitch = -1.5707964F + this.head.pitch;
                break;
        }

    }

    private void positionLeftArm(T entity) {
        switch(this.leftArmPose) {
            case EMPTY:
                this.leftArm.yaw = 0.0F;
                break;
            case BLOCK:
                this.leftArm.pitch = this.leftArm.pitch * 0.5F - 0.9424779F;
                this.leftArm.yaw = 0.5235988F;
                break;
            case ITEM:
                this.leftArm.pitch = this.leftArm.pitch * 0.5F - 0.31415927F;
                this.leftArm.yaw = 0.0F;
                break;
            case BOW_AND_ARROW:
                this.rightArm.yaw = -0.1F + this.head.yaw - 0.4F;
                this.leftArm.yaw = 0.1F + this.head.yaw;
                this.rightArm.pitch = -1.5707964F + this.head.pitch;
                this.leftArm.pitch = -1.5707964F + this.head.pitch;
                break;
        }

    }

    private Arm getPreferredArm(T entity) {
        Arm arm = entity.getMainArm();
        return entity.preferredHand == Hand.MAIN_HAND ? arm : arm.getOpposite();
    }

    private float method_2807(float f) {
        return -65.0F * f + f * f;
    }

    public static void swingArm(ModelPart arm, float animationProgress, float sigma) {
        arm.roll += 2.35 * sigma * (MathHelper.cos(animationProgress * 0.045F)) * 0.05F + 0.05F;
        arm.pitch += sigma * MathHelper.sin(animationProgress * 0.067F) * 0.05F;
    }
}