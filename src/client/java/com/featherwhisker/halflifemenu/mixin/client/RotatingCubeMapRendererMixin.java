package com.featherwhisker.halflifemenu.mixin.client;

import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.featherwhisker.halflifemenu.HalfLifeMenu;

@Mixin(RotatingCubeMapRenderer.class)
public class RotatingCubeMapRendererMixin
{
    @Unique
    private static final Identifier BACKGROUND_TEXTURE = Identifier.of("halflifemenu", "textures/bkg.png");

    /**
     * @author Featherwhisker
     * @reason Basically I need to be able to replace all backgrounds
     */
    @Overwrite
    public void render(DrawContext context, int width, int height, float alpha, float tickDelta)
    {
        context.drawTexture(BACKGROUND_TEXTURE, 0, 0, 0, 0, width, height);
        //context.drawGuiTexture(BACKGROUND_TEXTURE, 0, 0, width, height);
    }

    @Inject(at = @At("TAIL"), method = "<init>")
    public void init(CubeMapRenderer cubeMap, CallbackInfo ci)
    {
        MinecraftClient.getInstance().getTextureManager().bindTexture(BACKGROUND_TEXTURE);
    }
}
