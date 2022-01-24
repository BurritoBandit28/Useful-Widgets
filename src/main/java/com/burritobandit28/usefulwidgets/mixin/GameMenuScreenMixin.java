package com.burritobandit28.usefulwidgets.mixin;


import com.burritobandit28.usefulwidgets.Main;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.io.IOException;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin extends Screen {
    protected GameMenuScreenMixin(Text title) {
        super(title);
    }

// time for some spaghetti code

    @Inject(at = @At("RETURN"), method = "initWidgets")
    private void addButtonOne(CallbackInfo ci) {
        if (Main.LOAD_ONE) {
            this.addDrawableChild(new TexturedButtonWidget(this.width / 2 - 124, this.height / 4 + 48 + -16, 20, 20, 0, 0, 20, Main.TEXTURE_ONE, 32, 64, (button) -> {
                try {
                    Desktop.getDesktop().browse(java.net.URI.create(Main.URL_ONE));
                } catch (Exception e) {e.printStackTrace();}

                if (Main.CLOSE_ON_USE_ONE) {
                    this.client.scheduleStop();
                }

            }, new TranslatableText("widgetsmod.button_one")));
        }
    }

    @Inject(at = @At("RETURN"), method = "initWidgets")
    private void addButtonTwo(CallbackInfo ci) {
        if (Main.LOAD_TWO) {
            this.addDrawableChild(new TexturedButtonWidget(this.width / 2 - 124, this.height / 4 + 72 + -16, 20, 20, 0, 0, 20, Main.TEXTURE_TWO, 32, 64, (button) -> {
                try {
                    Desktop.getDesktop().browse(java.net.URI.create(Main.URL_TWO));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (Main.CLOSE_ON_USE_TWO) {
                    this.client.scheduleStop();
                }

            }, new TranslatableText("widgetsmod.button_two")));
        }
    }

    @Inject(at = @At("RETURN"), method = "initWidgets")
    private void addButtonThree(CallbackInfo ci) {
        if (Main.LOAD_THREE) {
            this.addDrawableChild(new TexturedButtonWidget(this.width / 2 + 104, this.height / 4 + 48 + -16, 20, 20, 0, 0, 20, Main.TEXTURE_THREE, 32, 64, (button) -> {
                try {
                    Desktop.getDesktop().browse(java.net.URI.create(Main.URL_THREE));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (Main.CLOSE_ON_USE_THREE) {
                    this.client.scheduleStop();
                }

            }, new TranslatableText("widgetsmod.button_three")));
        }
    }
    @Inject(at = @At("RETURN"), method = "initWidgets")
    private void addButtonFour(CallbackInfo ci) {
        if (Main.LOAD_FOUR) {
            this.addDrawableChild(new TexturedButtonWidget(this.width / 2 + 104, this.height / 4 + 72 + -16, 20, 20, 0, 0, 20, Main.TEXTURE_FOUR, 32, 64, (button) -> {
                try {
                    Desktop.getDesktop().browse(java.net.URI.create(Main.URL_FOUR));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (Main.CLOSE_ON_USE_FOUR) {
                    this.client.scheduleStop();
                }

            }, new TranslatableText("widgetsmod.button_four")));
        }
    }

    @Inject(at = @At("RETURN"), method = "initWidgets")
    private void addReloadButton(CallbackInfo ci) {
        this.addDrawableChild(new TexturedButtonWidget(this.width / 2 - 124, this.height / 4 + 96 + -16, 20, 20, 0, 0, 20, new Identifier(Main.MOD_ID, "textures/gui/reload.png"), 32, 64, (button) -> {
            try {
                Main.readConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.client.reloadResources();
        }, new TranslatableText("widgetsmod.reload_button")));
    }





}
