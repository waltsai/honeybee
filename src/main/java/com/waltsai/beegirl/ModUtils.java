package com.waltsai.beegirl;

import com.waltsai.beegirl.entity.ModEntityInitializer;
import com.waltsai.beegirl.entity.ModEntityProvider;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class ModUtils implements ModInitializer {

    public static final String MOD_ID = "beegirl";

    @Override
    public void onInitialize() {
        ModEntityProvider.registerElements();
        ModEntityInitializer.initialize();
    }

    public static Identifier byId(String location) {
        return new Identifier(MOD_ID, location);
    }
}
