package com.waltsai.beegirl;

import com.waltsai.beegirl.entity.ModEntityInitializer;
import net.fabricmc.api.ClientModInitializer;

public class ClientUtils implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModEntityInitializer.initializeClient();
    }
}
