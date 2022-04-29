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
            ConfigCategory command_1 = builder.getOrCreateCategory(new TranslatableText(Main.MOD_ID+".command_1.settings"));
            ConfigCategory command_2 = builder.getOrCreateCategory(new TranslatableText(Main.MOD_ID+".command_2.settings"));
            ConfigCategory command_3 = builder.getOrCreateCategory(new TranslatableText(Main.MOD_ID+".command_3.settings"));
            ConfigCategory command_4 = builder.getOrCreateCategory(new TranslatableText(Main.MOD_ID+".command_4.settings"));

                addOptions(button_1, 1);
                addOptions(button_2, 2);
                addOptions(button_3, 3);
                addOptions(button_4, 4);
                addCommandOptions(command_1, 1);
                addCommandOptions(command_2, 2);
                addCommandOptions(command_3, 3);
                addCommandOptions(command_4, 4);


            return builder.build();
        };
    }

    public void addOptions(ConfigCategory cc, Integer number){


            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            cc.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option." + Main.MOD_ID + ".load"), Boolean.parseBoolean(Main.cfg.getProperty("load_" + number)))
                    .setDefaultValue(true)
                    .setTooltip(new TranslatableText("option." + Main.MOD_ID + ".load.tooltip"))
                    .setSaveConsumer(b -> setLoadValue(b, number))
                    .build());

            cc.addEntry(entryBuilder.startStrField(new TranslatableText("option." + Main.MOD_ID + ".url"), Main.cfg.getProperty("link_" + number))
                    .setDefaultValue(defaultLink(number))
                    .setTooltip(new TranslatableText("option." + Main.MOD_ID + ".url.tooltip"))
                    .setSaveConsumer(s -> setLinkValue(s, number))
                    .build());

            cc.addEntry(entryBuilder.startStrField(new TranslatableText("option." + Main.MOD_ID + ".texture"), Main.cfg.getProperty("texture_" + number))
                    .setDefaultValue(defaultTexture(number))
                    .setTooltip(new TranslatableText("option." + Main.MOD_ID + ".texture.tooltip"))
                    .setSaveConsumer(s -> setTextureValue(s, number))
                    .build());

            cc.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option." + Main.MOD_ID + ".close_on_use"), Boolean.parseBoolean(Main.cfg.getProperty("close_on_use_" + number)))
                    .setDefaultValue(false)
                    .setTooltip(new TranslatableText("option." + Main.MOD_ID + ".close_on_use.tooltip"))
                    .setSaveConsumer(b -> setCloseValue(b, number))
                    .build());
    }

    public void addCommandOptions(ConfigCategory cc, Integer number){


        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        cc.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option." + Main.MOD_ID + ".load"), Boolean.parseBoolean(Main.cfg2.getProperty("load_" + number)))
                .setDefaultValue(true)
                .setTooltip(new TranslatableText("option." + Main.MOD_ID + ".load.tooltip"))
                .setSaveConsumer(b -> setLoadValueCommand(b, number))
                .build());

        cc.addEntry(entryBuilder.startStrField(new TranslatableText("option." + Main.MOD_ID + ".command"), Main.cfg2.getProperty("command_" + number))
                .setDefaultValue(defaultCommand(number))
                .setTooltip(new TranslatableText("option." + Main.MOD_ID + ".command.tooltip"))
                .setSaveConsumer(s -> setCommandValue(s, number))
                .build());

        cc.addEntry(entryBuilder.startStrField(new TranslatableText("option." + Main.MOD_ID + ".texture"), Main.cfg2.getProperty("texture_" + number))
                .setDefaultValue(defaultCommandTexture(number))
                .setTooltip(new TranslatableText("option." + Main.MOD_ID + ".texture.tooltip"))
                .setSaveConsumer(s -> setTextureValueCommand(s, number))
                .build());
    }


    public static String defaultLink(Integer n){
        if (n == 1) {
            return "https://curseforge.com/minecraft/mc-mods";
        }
        else if (n == 2) {
            return "https://www.planetminecraft.com";
        }
        else if (n== 3) {
            return "https://modrinth.com";
        }
        else if (n == 4) {
            return "https://youtube.com";
        }
        else {
            return "";
        }
    }

    public static String defaultCommand(Integer n){
        if (n == 1) {
            return "/kill @e[type=!player, type=!armor_stand, type=!item_frame, type=!marker]";
        }
        else if (n == 2) {
            return "/gamemode creative";
        }
        else if (n== 3) {
            return "/gamemode survival";
        }
        else if (n == 4) {
            return "example.mcfunction";
        }
        else {
            return "";
        }
    }

    public static String defaultTexture(Integer n){
        if (n == 1) {
            return "curseforge.png";
        }
        else if (n == 2) {
            return "planetmc.png";
        }
        else if (n== 3) {
            return "modrinth.png";
        }
        else if (n == 4) {
            return "youtube.png";
        }
        else {
            return "";
        }
    }

    public static String defaultCommandTexture(Integer n){
        if (n == 1) {
            return "commands/skull.png";
        }
        else if (n == 2) {
            return "commands/creative.png";
        }
        else if (n== 3) {
            return "commands/survival.png";
        }
        else if (n == 4) {
            return "commands/slash.png";
        }
        else {
            return "";
        }
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

    public void setLoadValueCommand(Boolean b, Integer i) {
        try (InputStream input = new FileInputStream(Main.COMMAND_FILE)) {
            try (OutputStream output = new FileOutputStream(Main.COMMAND_FILE)){
                Main.cfg2.load(input);
                Main.cfg2.setProperty("load_"+i,String.valueOf(b));
                Main.cfg2.store(output, "Made using Mod Menu GUI, that is why it's now all jumbled up.");
                Main.readCommandConfig();
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
                Main.cfg.store(output, "Made using Mod Menu GUI, that is why it's now all jumbled up.");
                Main.readConfig();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCommandValue(String s, Integer i) {
        try (InputStream input = new FileInputStream(Main.COMMAND_FILE)) {
            try (OutputStream output = new FileOutputStream(Main.COMMAND_FILE)){
                Main.cfg2.load(input);
                Main.cfg2.setProperty("command_"+i,s);
                Main.cfg2.store(output, "Made using Mod Menu GUI, that is why it's now all jumbled up.");
                Main.readCommandConfig();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTextureValue(String s, Integer i) {
        try (InputStream input = new FileInputStream(Main.FILE)) {
            try (OutputStream output = new FileOutputStream(Main.FILE)){
                Main.cfg.load(input);
                Main.cfg.setProperty("texture_"+i,s);
                Main.cfg.store(output, "Made using Mod Menu GUI, that is why it's now all jumbled up.");
                Main.readConfig();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTextureValueCommand(String s, Integer i) {
        try (InputStream input = new FileInputStream(Main.COMMAND_FILE)) {
            try (OutputStream output = new FileOutputStream(Main.COMMAND_FILE)){
                Main.cfg2.load(input);
                Main.cfg2.setProperty("texture_"+i,s);
                Main.cfg2.store(output, "Made using Mod Menu GUI, that is why it's now all jumbled up.");
                Main.readCommandConfig();
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
                Main.cfg.store(output, "Made using Mod Menu GUI, that is why it's now all jumbled up.");
                Main.readConfig();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
