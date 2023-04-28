package org.example;

import java.io.*;
import java.util.Properties;

public class Main {

    private static Properties prop;

    private static String path = "./src/main/resources/test_cred.properties";

    public static void main(String[] args) {

        prop = new Properties();
        try {
            InputStream inputStream = new FileInputStream(path);
            prop.load(inputStream);
            System.out.println(prop.get("username"));
        } catch (IOException e) {
            System.out.println(e);
        }
        setProperties();
        System.out.println(prop.get("username"));
    }

    public static void setProperties() {
        OutputStream os;
        try {
            os = new FileOutputStream(path);
            prop.setProperty("username", "Anatolii");
            prop.store(os, null);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
    }
}