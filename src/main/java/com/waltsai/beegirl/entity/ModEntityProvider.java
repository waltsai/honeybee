package com.waltsai.beegirl.entity;

import com.waltsai.beegirl.ModUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.Registry;

public class ModEntityProvider {

    public static final EntityType<HoneyBeeGirlEntity> HONEY_BEE_GIRL = EntityType.Builder.create(HoneyBeeGirlEntity::new, SpawnGroup.MISC).setDimensions(0.4F, 1.6F).maxTrackingRange(12).build("honey_bee_girl");

    public static void registerElements() {
        Registry.register(Registry.ENTITY_TYPE, ModUtils.byId("honey_bee_girl"), HONEY_BEE_GIRL);
    }
}
