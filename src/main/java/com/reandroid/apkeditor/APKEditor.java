package com.reandroid.apkeditor;

import com.reandroid.arsc.ARSCLib;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class APKEditor {
    private static Properties sProperties;
    public static String getARSCLibInfo(){
        return ARSCLib.getName() + "-" + ARSCLib.getVersion();
    }
    public static String getDescription(){
        return getProperties().getProperty("app.description", "---");
    }
    public static String getRepo(){
        return getProperties().getProperty("app.repo", "");
    }
    public static String getName(){
        return getProperties().getProperty("app.name", "---");
    }
    public static String getVersion(){
        return getProperties().getProperty("app.version", "---");
    }
    private static Properties getProperties(){
        if(sProperties!=null){
            return sProperties;
        }
        sProperties=new Properties();
        try {
            sProperties.load(APKEditor.class.getResourceAsStream(PATH_properties));
        } catch (IOException ex) {
            sProperties.put("app.description", ex.getMessage());
        }
        return sProperties;
    }
    public static String getJarName(){
        File file = new File(APKEditor.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation().getPath());
        if(file.isFile()){
            return file.getName();
        }
        return getName()+".jar";
    }
    public static boolean isExperimental() {
        return APKEditor.getVersion().contains("beta");
    }

    public static final String PATH_properties = "/apkeditor.properties";

}
