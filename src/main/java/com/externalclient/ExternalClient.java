package com.externalclient;

import net.fabricmc.api.ClientModInitializer;

public class ExternalClient implements ClientModInitializer {

    public static final String NAME = "ExternalClient";
    public static final String VERSION = "1.0.0";

    @Override
    public void onInitializeClient() {
        System.out.println("=================================");
        System.out.println("       ExternalClient 1.0.0      ");
        System.out.println("       Minecraft 1.16.5          ");
        System.out.println("=================================");
    }
}