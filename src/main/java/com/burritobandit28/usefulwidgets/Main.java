package com.burritobandit28.usefulwidgets;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class Main implements ModInitializer {

    public static final String MOD_ID = "usefulwidgets";

    //urls
    public static String URL_ONE;
    public static String URL_TWO;
    public static String URL_THREE;
    public static String URL_FOUR;

    //textures
    public static Identifier TEXTURE_ONE;
    public static Identifier TEXTURE_TWO;
    public static Identifier TEXTURE_THREE;
    public static Identifier TEXTURE_FOUR;

    //close or not
    public static boolean CLOSE_ON_USE_ONE;
    public static boolean CLOSE_ON_USE_TWO;
    public static boolean CLOSE_ON_USE_THREE;
    public static boolean CLOSE_ON_USE_FOUR;

    //load or not
    public static boolean LOAD_ONE;
    public static boolean LOAD_TWO;
    public static boolean LOAD_THREE;
    public static boolean LOAD_FOUR;

    //config
    public static File CONFIG_DIR = new File("./config/usefulwidgets/");
    public static File f = new File("./config/usefulwidgets/config.properties");
    private static final Logger LOGGER = LogManager.getLogger("Useful Widgets");
    public static String line;

    public static void readConfig() throws IOException {

        //set up file reader
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        //Button 1
        line = br.readLine();
        line = line.substring(line.lastIndexOf("=") + 1).replaceAll(" ", "");
        LOAD_ONE = Boolean.parseBoolean(line);
        if (LOAD_ONE == true) {
            line = br.readLine();
            line = line.substring(line.indexOf("="));
            line = line.substring(0, 0) + line.substring(0 + 1);
            URL_ONE = line;
            LOGGER.info("URL 1 set to " + line);
            line = br.readLine();
            line = line.substring(line.lastIndexOf("=") + 1).replaceAll(" ", "");
            LOGGER.info("Texture 1 set to " + line);
            TEXTURE_ONE = new Identifier(MOD_ID, "textures/gui/" + line);
            line = br.readLine();
            line = line.substring(line.lastIndexOf("=") + 1).replaceAll(" ", "");
            CLOSE_ON_USE_ONE = Boolean.parseBoolean(line);
            LOGGER.info("Close link 1 on use set to " + CLOSE_ON_USE_ONE);
        }
        else{
            LOGGER.info("Button 1 set not to load");
            TEXTURE_ONE = new Identifier(MOD_ID, "textures/gui/.png");
            br.readLine();
            br.readLine();
            br.readLine();
        }

        //Button 2
        line = br.readLine();
        line = line.substring(line.lastIndexOf("=") + 1).replaceAll(" ", "");
        LOAD_TWO = Boolean.parseBoolean(line);
        if (LOAD_TWO == true) {
            line = br.readLine();
            line = line.substring(line.indexOf("="));
            line = line.substring(0, 0) + line.substring(0 + 1);
            URL_TWO = line;
            LOGGER.info("URL 2 set to " + line);
            line = br.readLine();
            line = line.substring(line.lastIndexOf("=") + 1).replaceAll(" ", "");
            LOGGER.info("Texture 2 set to " + line);
            TEXTURE_TWO = new Identifier(MOD_ID, "textures/gui/" + line);
            line = br.readLine();
            line = line.substring(line.lastIndexOf("=") + 1).replaceAll(" ", "");
            CLOSE_ON_USE_TWO = Boolean.parseBoolean(line);
            LOGGER.info("Close link 2 on use set to " + CLOSE_ON_USE_TWO);
        }
        else {
            LOGGER.info("Button 2 set not to load");
            TEXTURE_TWO = new Identifier(MOD_ID, "textures/gui/.png");
            br.readLine();
            br.readLine();
            br.readLine();
        }


        //Button 3
        line = br.readLine();
        line = line.substring(line.lastIndexOf("=") + 1).replaceAll(" ", "");
        LOAD_THREE = Boolean.parseBoolean(line);
        if (LOAD_THREE == true) {
            line = br.readLine();
            line = line.substring(line.indexOf("="));
            line = line.substring(0, 0) + line.substring(0 + 1);
            URL_THREE = line;
            LOGGER.info("URL 3 set to " + line);
            line = br.readLine();
            line = line.substring(line.lastIndexOf("=") + 1).replaceAll(" ", "");
            LOGGER.info("Texture 3 set to " + line);
            TEXTURE_THREE = new Identifier(MOD_ID, "textures/gui/" + line);
            line = br.readLine();
            line = line.substring(line.lastIndexOf("=") + 1).replaceAll(" ", "");
            CLOSE_ON_USE_THREE = Boolean.parseBoolean(line);
            LOGGER.info("Close link 3 on use set to " + CLOSE_ON_USE_THREE);
        }
        else {
            LOGGER.info("Button 3 set not to load");
            TEXTURE_THREE = new Identifier(MOD_ID, "textures/gui/.png");
            br.readLine();
            br.readLine();
            br.readLine();
        }

        //Button 4
        line = br.readLine();
        line = line.substring(line.lastIndexOf("=") + 1).replaceAll(" ", "");
        LOAD_FOUR = Boolean.parseBoolean(line);
        if (LOAD_FOUR == true) {
            line = br.readLine();
            line = line.substring(line.indexOf("="));
            line = line.substring(0, 0) + line.substring(0 + 1);
            URL_FOUR = line;
            LOGGER.info("URL 4 set to " + line);
            line = br.readLine();
            line = line.substring(line.lastIndexOf("=") + 1).replaceAll(" ", "");
            LOGGER.info("Texture 4 set to " + line);
            TEXTURE_FOUR = new Identifier(MOD_ID, "textures/gui/" + line);
            line = br.readLine();
            line = line.substring(line.lastIndexOf("=") + 1).replaceAll(" ", "");
            CLOSE_ON_USE_FOUR = Boolean.parseBoolean(line);
            LOGGER.info("Close link 4 on use set to " + CLOSE_ON_USE_FOUR);
        }
        else {
            LOGGER.info("Button 4 set not to load");
            TEXTURE_FOUR = new Identifier(MOD_ID, "textures/gui/.png");
            br.readLine();
            br.readLine();
            br.readLine();
        }

        // close
        br.close();
        fr.close();
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Useful Widgets loading...");

        if (!CONFIG_DIR.exists()) {
            LOGGER.info("Config directory and file don't exist!");
            CONFIG_DIR.mkdir();
            LOGGER.info("Config directory made!");
            try {
                f.createNewFile();
                FileWriter CONFIG_WRITER = new FileWriter(f);
                CONFIG_WRITER.write("load_1=true\nlink_1=https://www.curseforge.com/minecraft/mc-mods\ntexture_1=curseforge.png\nclose_on_use_1=false\nload_2=true\nlink_2=https://www.planetminecraft.com/\ntexture_2=planetmc.png\nclose_on_use_2=false\nload_3=true\nlink_3=https://modrinth.com/\ntexture_3=modrinth.png\nclose_on_use_3=false\nload_4=true\nlink_4=https://www.youtube.com/\ntexture_4=youtube.png\nclose_on_use_4=false");
                CONFIG_WRITER.close();
                LOGGER.info("Config file made!");
            }
            catch (IOException e) {e.printStackTrace();}
        }
        else {
            LOGGER.info("Config folder exists, checking for config file...");

        }
        if (CONFIG_DIR.exists() && !f.exists()) {
            try {
                LOGGER.info("Config file doesn't exist!");
                f.createNewFile();
                FileWriter CONFIG_WRITER = new FileWriter(f);
                CONFIG_WRITER.write("load_1=true\nlink_1=https://www.curseforge.com/minecraft/mc-mods\ntexture_1=curseforge.png\nclose_on_use_1=false\nload_2=true\nlink_2=https://www.planetminecraft.com/\ntexture_2=planetmc.png\nclose_on_use_2=false\nload_3=true\nlink_3=https://modrinth.com/\ntexture_3=modrinth.png\nclose_on_use_3=false\nload_4=true\nlink_4=https://www.youtube.com/\ntexture_4=youtube.png\nclose_on_use_4=false");
                CONFIG_WRITER.close();
                LOGGER.info("Config file made! Now reading...");
            }
            catch (IOException e) {e.printStackTrace();}
        }
        else {
            LOGGER.info("Config file exists, reading...");

        }

        try{readConfig();}
        catch (IOException e) {e.printStackTrace();}
        System.setProperty("java.awt.headless", "false");

        LOGGER.info("Useful Widgets loaded!");


    }
}
