package com.waltsai.beegirl.entity;

import com.waltsai.beegirl.client.HoneyBeeGirlEntityModel;
import com.waltsai.beegirl.client.HoneyBeeGirlEntityRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.client.model.Dilation;

public class ModEntityInitializer {

    public static void initialize() {
        FabricDefaultAttributeRegistry.register(ModEntityProvider.HONEY_BEE_GIRL, HoneyBeeGirlEntity.createBeeGirlAttributes());
    }

    public static void initializeClient() {
        EntityRendererRegistry.register(ModEntityProvider.HONEY_BEE_GIRL, HoneyBeeGirlEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(HoneyBeeGirlEntityModel.LAYER_LOCATION, () -> HoneyBeeGirlEntityModel.getTexturedModelData(Dilation.NONE));
    }
}
