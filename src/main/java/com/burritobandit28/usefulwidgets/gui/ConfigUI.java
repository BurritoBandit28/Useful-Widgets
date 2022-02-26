package com.burritobandit28.usefulwidgets.gui;

import com.burritobandit28.usefulwidgets.Main;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.TranslatableText;

import java.io.*;

@Environment(EnvType.CLIENT)
public class ConfigUI implements ModMenuApi {

    private static ConfigBuilder builder;

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {

            builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(new TranslatableText("title."+Main.MOD_ID+".config"));

            ConfigCategory button_1 = builder.getOrCreateCategory(new TranslatableText(Main.MOD_ID+".button_1.settings"));
            ConfigCategory button_2 = builder.getOrCreateCategory(new TranslatableText(Main.MOD_ID+".button_2.settings"));
            ConfigCategory button_3 = builder.getOrCreateCategory(new TranslatableText(Main.MOD_ID+".button_3.settings"));
            ConfigCategory button_4 = builder.getOrCreateCategory(new TranslatableText(Main.MOD_ID+".button_4.settings"));

            addOptions(button_1, 1);
            addOptions(button_2, 2);
            addOptions(button_3, 3);
            addOptions(button_4, 4);

            return builder.build();
        };
    }

    public void addOptions(ConfigCategory cc, Integer number){

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        cc.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option."+Main.MOD_ID+".load"), Boolean.parseBoolean(Main.cfg.getProperty("load_"+number)))
                .setDefaultValue(true)
                .setTooltip(new TranslatableText("option."+Main.MOD_ID+".load.tooltip"))
                .setSaveConsumer(b -> setLoadValue(b, number))
                .build());

        cc.addEntry(entryBuilder.startStrField(new TranslatableText("option."+Main.MOD_ID+".url"), Main.cfg.getProperty("link_" + number))
                .setDefaultValue("https://curseforge.com/minecraft/mc-mods")
                .setTooltip(new TranslatableText("option."+Main.MOD_ID+".url.tooltip"))
                .setSaveConsumer(s -> setLinkValue(s, number))
                .build());

        cc.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option."+Main.MOD_ID+".close_on_use"), Boolean.parseBoolean(Main.cfg.getProperty("close_on_use_"+number)))
                .setDefaultValue(false)
                .setTooltip(new TranslatableText("option."+Main.MOD_ID+".close_on_use.tooltip"))
                .setSaveConsumer(b -> setCloseValue(b, number))
                .build());


    }


    public void setLoadValue(Boolean b, Integer i) {
        try (InputStream input = new FileInputStream(Main.FILE)) {
            try (OutputStream output = new FileOutputStream(Main.FILE)){
                Main.cfg.load(input);
                Main.cfg.setProperty("load_"+i,String.valueOf(b));
                Main.cfg.store(output, "Made using Mod Menu GUI, that is why it's now all jumbled up.");
                Main.readConfig();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void setLinkValue(String s, Integer i) {
        try (InputStream input = new FileInputStream(Main.FILE)) {
            try (OutputStream output = new FileOutputStream(Main.FILE)){
                Main.cfg.load(input);
                Main.cfg.setProperty("link_"+i,s);
                Main.cfg.store(output, null);
                Main.readConfig();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCloseValue(Boolean b, Integer i) {
        try (InputStream input = new FileInputStream(Main.FILE)) {
            try (OutputStream output = new FileOutputStream(Main.FILE)){
                Main.cfg.load(input);
                Main.cfg.setProperty("close_on_use_"+i,String.valueOf(b));
                Main.cfg.store(output, null);
                Main.readConfig();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
