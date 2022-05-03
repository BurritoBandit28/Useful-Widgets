package com.burritobandit28.usefulwidgets.mixin;

import com.burritobandit28.usefulwidgets.Main;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(InventoryScreen.class)
public abstract class SurvivalInventoryMixin extends AbstractInventoryScreen<PlayerScreenHandler> {


    public SurvivalInventoryMixin(PlayerScreenHandler screenHandler, PlayerInventory playerInventory, Text text) {
        super(screenHandler, playerInventory, text);
    }

    @Inject(at = @At("RETURN"), method = "init")
    private void loadCommandWidgetOne(CallbackInfo ci) {
        if (Main.LOAD_ONE_COMMAND) {
            this.addDrawableChild(new TexturedButtonWidget(this.x, (25 + (this.y - 52)), 20, 20, 0, 0, 20, Main.TEXTURE_ONE_COMMAND, 32, 64, (button) -> {

                System.out.println(this.y);

                if (Main.COMMAND_ONE.endsWith(".mcfunction")) {
                    try {
                        Main.runMcfunction(Main.COMMAND_ONE);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    MinecraftClient.getInstance().player.sendChatMessage(Main.COMMAND_ONE);
                    Main.LOGGER.info("Executed "+Main.COMMAND_ONE);
                }
            }, new TranslatableText(Main.MOD_ID+".command_button_one")));
        }
    }

    @Inject(at = @At("RETURN"), method = "init")
    private void loadCommandWidgetTwo(CallbackInfo ci) {
        if (Main.LOAD_TWO_COMMAND) {
            this.addDrawableChild(new TexturedButtonWidget(this.x + 25, (25 + (this.y - 52)), 20, 20, 0, 0, 20, Main.TEXTURE_TWO_COMMAND, 32, 64, (button) -> {

                if (Main.COMMAND_TWO.endsWith(".mcfunction")) {
                    try {
                        Main.runMcfunction(Main.COMMAND_TWO);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    MinecraftClient.getInstance().player.sendChatMessage(Main.COMMAND_TWO);
                    Main.LOGGER.info("Executed "+Main.COMMAND_TWO);
                }
            }, new TranslatableText(Main.MOD_ID+".command_button_two")));
        }

    }

    @Inject(at = @At("RETURN"), method = "init")
    private void loadCommandWidgetThree(CallbackInfo ci) {
        if (Main.LOAD_THREE_COMMAND) {
            this.addDrawableChild(new TexturedButtonWidget(this.x + 50, (25 + (this.y - 52)), 20, 20, 0, 0, 20, Main.TEXTURE_THREE_COMMAND, 32, 64, (button) -> {

                if (Main.COMMAND_THREE.endsWith(".mcfunction")) {
                    try {
                        Main.runMcfunction(Main.COMMAND_THREE);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    MinecraftClient.getInstance().player.sendChatMessage(Main.COMMAND_THREE);
                    Main.LOGGER.info("Executed "+Main.COMMAND_THREE);
                }
            }, new TranslatableText(Main.MOD_ID+".command_button_three")));
        }

    }

    @Inject(at = @At("RETURN"), method = "init")
    private void loadCommandWidgetFour(CallbackInfo ci) {
        if (Main.LOAD_FOUR_COMMAND) {
            this.addDrawableChild(new TexturedButtonWidget(this.x + 75, (25 + (this.y - 52)), 20, 20, 0, 0, 20, Main.TEXTURE_FOUR_COMMAND, 32, 64, (button) -> {

                if (Main.COMMAND_FOUR.endsWith(".mcfunction")) {
                    try {
                        Main.runMcfunction(Main.COMMAND_FOUR);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    MinecraftClient.getInstance().player.sendChatMessage(Main.COMMAND_FOUR);
                    Main.LOGGER.info("Executed "+Main.COMMAND_FOUR);
                }
            }, new TranslatableText(Main.MOD_ID+".command_button_FOUR")));
        }

    }

    @Inject(at = @At("RETURN"), method = "init")
    private void addReloadButton(CallbackInfo ci) {
        this.addDrawableChild(new TexturedButtonWidget(this.x + 100, (25 + (this.y - 52)), 20, 20, 0, 0, 20, new Identifier(Main.MOD_ID, "textures/gui/commands/reload.png"), 32, 64, (button) -> {

            try {
                Main.readCommandConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, new TranslatableText(Main.MOD_ID+".command_reload_button")));
    }

}
