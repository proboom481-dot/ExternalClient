package com.externalvisuals.render;

import com.externalvisuals.ExternalVisuals;
import com.externalvisuals.modules.visual.CustomCrosshair;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

public final class VisualHud {

    private VisualHud() {
    }

    public static void init() {

        HudRenderCallback.EVENT.register(
                VisualHud::render
        );
    }

    private static void render(
            MatrixStack matrices,
            float tickDelta
    ) {

        MinecraftClient mc =
                MinecraftClient.getInstance();

        if (mc.player == null
                || mc.world == null
                || mc.currentScreen != null) {
            return;
        }

        CustomCrosshair crosshair =
                ExternalVisuals.MODULE_MANAGER
                        .get(CustomCrosshair.class);

        if (crosshair == null
                || !crosshair.isEnabled()) {
            return;
        }

        int centerX =
                mc.getWindow().getScaledWidth() / 2;

        int centerY =
                mc.getWindow().getScaledHeight() / 2;

        int size = crosshair.getSize();
        int gap = crosshair.getGap();
        int thickness = crosshair.getThickness();
        int color = crosshair.getColor();

        // Left
        DrawableHelper.fill(
                matrices,
                centerX - gap - size,
                centerY - thickness / 2,
                centerX - gap,
                centerY + thickness / 2 + 1,
                color
        );

        // Right
        DrawableHelper.fill(
                matrices,
                centerX + gap,
                centerY - thickness / 2,
                centerX + gap + size,
                centerY + thickness / 2 + 1,
                color
        );

        // Top
        DrawableHelper.fill(
                matrices,
                centerX - thickness / 2,
                centerY - gap - size,
                centerX + thickness / 2 + 1,
                centerY - gap,
                color
        );

        // Bottom
        DrawableHelper.fill(
                matrices,
                centerX - thickness / 2,
                centerY + gap,
                centerX + thickness / 2 + 1,
                centerY + gap + size,
                color
        );
    }
}