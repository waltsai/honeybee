package com.waltsai.beegirl.client.render.feature;

import com.waltsai.beegirl.ModUtils;
import com.waltsai.beegirl.client.HoneyBeeGirlEntityModel;
import com.waltsai.beegirl.entity.HoneyBeeGirlEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class HairFeatureRenderer extends FeatureRenderer<HoneyBeeGirlEntity, HoneyBeeGirlEntityModel<HoneyBeeGirlEntity>> {
    public HairFeatureRenderer(FeatureRendererContext<HoneyBeeGirlEntity, HoneyBeeGirlEntityModel<HoneyBeeGirlEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, HoneyBeeGirlEntity beeEntity, float f, float g, float h, float j, float k, float l) {
        if (!beeEntity.isInvisible()) {
            matrixStack.push();
            matrixStack.translate(0.0D, 0.0D, 0.325F);
            double d = MathHelper.lerp((double) h, beeEntity.prevHairX, beeEntity.hairX) - MathHelper.lerp((double) h, beeEntity.prevX, beeEntity.getX());
            double e = MathHelper.lerp((double) h, beeEntity.prevHairY, beeEntity.hairY) - MathHelper.lerp((double) h, beeEntity.prevY, beeEntity.getY());
            double m = MathHelper.lerp((double) h, beeEntity.prevHairZ, beeEntity.hairZ) - MathHelper.lerp((double) h, beeEntity.prevZ, beeEntity.getZ());
            float n = beeEntity.prevBodyYaw + (beeEntity.bodyYaw - beeEntity.prevBodyYaw);
            double o = (double) MathHelper.sin(n * 0.017453292F);
            double p = (double) (-MathHelper.cos(n * 0.017453292F));
            float q = (float) e * 10.0F;
            q = MathHelper.clamp(q, -6.0F, 32.0F);
            float r = (float) (d * o + m * p) * 100.0F;
            r = MathHelper.clamp(r, 0.0F, 150.0F);
            float s = (float) (d * p - m * o) * 100.0F;
            s = MathHelper.clamp(s, -20.0F, 20.0F);
            if (r < 0.0F) {
                r = 0.0F;
            }

            float t = MathHelper.lerp(h, beeEntity.prevStrideDistance, beeEntity.strideDistance);
            q += MathHelper.sin(MathHelper.lerp(h, beeEntity.prevHorizontalSpeed, beeEntity.horizontalSpeed) * 6.0F) * 32.0F * t;

            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityTranslucent(new Identifier(ModUtils.MOD_ID, "textures/entity/honeybeegirl.png")));
            this.getContextModel().renderBackHair(matrixStack, vertexConsumer, i, LivingEntityRenderer.getOverlay(beeEntity, 0.0F), q, r, s);
            matrixStack.pop();
        }
    }
}