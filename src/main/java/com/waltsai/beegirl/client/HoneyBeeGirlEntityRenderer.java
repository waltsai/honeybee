package com.waltsai.beegirl.client;

import com.waltsai.beegirl.ModUtils;
import com.waltsai.beegirl.client.render.feature.HairFeatureRenderer;
import com.waltsai.beegirl.entity.HoneyBeeGirlEntity;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class HoneyBeeGirlEntityRenderer extends BipedEntityRenderer<HoneyBeeGirlEntity, HoneyBeeGirlEntityModel<HoneyBeeGirlEntity>> {
    public HoneyBeeGirlEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new HoneyBeeGirlEntityModel<>(ctx.getPart(HoneyBeeGirlEntityModel.LAYER_LOCATION)), 0.5F);
        this.addFeature(new ArmorFeatureRenderer<>(this, new BipedEntityModel<>(ctx.getPart(EntityModelLayers.PLAYER_INNER_ARMOR)), new BipedEntityModel<>(ctx.getPart(EntityModelLayers.PLAYER_OUTER_ARMOR))));
        this.addFeature(new HairFeatureRenderer(this));
    }

    @Override
    protected void scale(HoneyBeeGirlEntity entity, MatrixStack matrices, float amount) {
        super.scale(entity, matrices, amount);
        matrices.scale(0.8F, 0.8F, 0.8F);
    }

    @Override
    public Identifier getTexture(HoneyBeeGirlEntity mobEntity) {
        return ModUtils.byId("textures/entity/honeybeegirl.png");
    }
}
