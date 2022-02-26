package com.burritobandit28.usefulwidgets;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

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

    //public static ArrayList<Identifier> TEST = new ArrayList<Identifier>();


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
    public static File DIR = new File("./config/usefulwidgets/");
    public static File FILE = new File("./config/usefulwidgets/config.properties");
    private static final Logger LOGGER = LogManager.getLogger("Useful Widgets");
    public static final Properties cfg = new Properties();

    public static void readConfig() throws IOException {

        //Load config
        try (InputStream input = new FileInputStream(FILE)) {

            cfg.load(input);

            //Button 1
            LOAD_ONE = Boolean.parseBoolean(cfg.getProperty("load_1"));
            if (LOAD_ONE) {

                URL_ONE = cfg.getProperty("link_1");
                LOGGER.info("URL 1 set to " + URL_ONE);

                TEXTURE_ONE = new Identifier(MOD_ID, "textures/gui/" + cfg.getProperty("texture_1"));
                LOGGER.info("Texture 1 set to " + cfg.getProperty("texture_1"));

                CLOSE_ON_USE_ONE = Boolean.parseBoolean(cfg.getProperty("close_on_use_1"));
                LOGGER.info("Close link 1 on use set to " + CLOSE_ON_USE_ONE);

            } else {
                LOGGER.info("Button 1 set not to load");
                TEXTURE_ONE = new Identifier(MOD_ID, "textures/gui/.png");
            }

            //Button 2
            LOAD_TWO = Boolean.parseBoolean(cfg.getProperty("load_2"));
            if (LOAD_TWO) {

                URL_TWO = cfg.getProperty("link_2");
                LOGGER.info("URL 2 set to " + URL_TWO);

                TEXTURE_TWO = new Identifier(MOD_ID, "textures/gui/" + cfg.getProperty("texture_2"));
                LOGGER.info("Texture 2 set to " + cfg.getProperty("texture_2"));

                CLOSE_ON_USE_TWO = Boolean.parseBoolean(cfg.getProperty("close_on_use_2"));
                LOGGER.info("Close link 2 on use set to " + CLOSE_ON_USE_TWO);

            } else {
                LOGGER.info("Button 2 set not to load");
                TEXTURE_TWO = new Identifier(MOD_ID, "textures/gui/.png");
            }

            //Button 3
            LOAD_THREE = Boolean.parseBoolean(cfg.getProperty("load_3"));
            if (LOAD_THREE) {

                URL_THREE = cfg.getProperty("link_3");
                LOGGER.info("URL 3 set to " + URL_THREE);

                TEXTURE_THREE = new Identifier(MOD_ID, "textures/gui/" + cfg.getProperty("texture_3"));
                LOGGER.info("Texture 3 set to " + cfg.getProperty("texture_3"));

                CLOSE_ON_USE_THREE = Boolean.parseBoolean(cfg.getProperty("close_on_use_3"));
                LOGGER.info("Close link 3 on use set to " + CLOSE_ON_USE_THREE);

            } else {
                LOGGER.info("Button 3 set not to load");
                TEXTURE_THREE = new Identifier(MOD_ID, "textures/gui/.png");
            }

            //Button 4
            LOAD_FOUR = Boolean.parseBoolean(cfg.getProperty("load_4"));
            if (LOAD_FOUR) {

                URL_FOUR = cfg.getProperty("link_4");
                LOGGER.info("URL 4 set to " + URL_FOUR);

                TEXTURE_FOUR = new Identifier(MOD_ID, "textures/gui/" + cfg.getProperty("texture_4"));
                LOGGER.info("Texture 4 set to " + cfg.getProperty("texture_4"));

                CLOSE_ON_USE_FOUR = Boolean.parseBoolean(cfg.getProperty("close_on_use_4"));
                LOGGER.info("Close link 4 on use set to " + CLOSE_ON_USE_FOUR);

            } else {
                LOGGER.info("Button 4 set not to load");
                TEXTURE_FOUR = new Identifier(MOD_ID, "textures/gui/.png");
            }
        }
    }

    public static void writeConfig() throws IOException {

        FileWriter CONFIG_WRITER = new FileWriter(FILE);
        CONFIG_WRITER.write("""
                load_1=true
                link_1=https://curseforge.com/minecraft/mc-mods
                texture_1=curseforge.png
                close_on_use_1=false
                load_2=true
                link_2=https://planetminecraft.com/
                texture_2=planetmc.png
                close_on_use_2=false
                load_3=true
                link_3=https://modrinth.com/
                texture_3=modrinth.png
                close_on_use_3=false
                load_4=true
                link_4=https://youtube.com/
                texture_4=youtube.png
                close_on_use_4=false""");
        CONFIG_WRITER.close();

    }





    @Override
    public void onInitialize() {
        LOGGER.info("Useful Widgets loading...");

        if (!DIR.exists()) {
            LOGGER.info("Config directory and file don't exist!");
            DIR.mkdir();
            LOGGER.info("Config directory made!");
            try {
                FILE.createNewFile();
                writeConfig();
                LOGGER.info("Config file made!");
            }
            catch (IOException e) {e.printStackTrace();}
        }
        else {
            LOGGER.info("Config folder exists, checking for config file...");

        }
        if (DIR.exists() && !FILE.exists()) {
            try {
                LOGGER.info("Config file doesn't exist!");
                FILE.createNewFile();
                writeConfig();
                LOGGER.info("Config file made! Now reading...");
            }
            catch (IOException e) {e.printStackTrace();}
        }
        else {
            LOGGER.info("Config file exists, reading...");

        }

        try {
            readConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty("java.awt.headless", "false");

        LOGGER.info("Useful Widgets loaded!");


    }



    // OLD CODE, didn't have the heart to delete it


        /*public static void readConfig() throws IOException {

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
            line = line.substring(0, 0) + line.substring(1);
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
    }*/

    //Might actually need some of this tbh

    /*public static void writeConfig() throws IOException {
        try (OutputStream output = new FileOutputStream(FILE)) {

            //button 1
            cfg.setProperty("load_1","true");
            cfg.setProperty("link_1","https://curseforge.com/minecraft/mc-mods");
            cfg.setProperty("texture_1","curseforge.png");
            cfg.setProperty("close_on_use_1","false");

            //button 2
            cfg.setProperty("load_2","true");
            cfg.setProperty("link_2","https://planetminecraft.com/");
            cfg.setProperty("texture_2","planetmc.png");
            cfg.setProperty("close_on_use_2","false");

            //button 3
            cfg.setProperty("load_3","true");
            cfg.setProperty("link_3","https://modrinth.com/");
            cfg.setProperty("texture_3","modrinth.png");
            cfg.setProperty("close_on_use_3","false");

            //button 4
            cfg.setProperty("load_4","true");
            cfg.setProperty("link_4","https://youtube.com/");
            cfg.setProperty("texture_4","youtube.png");
            cfg.setProperty("close_on_use_4","false");

            //save
            cfg.store(output, null);

        }
    }*/






}
