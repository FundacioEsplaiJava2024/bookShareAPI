package com.bookShare;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DbConnect {

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String PROTOCOL = "jdbc:mysql:";
    public static String HOST;
    public static String IP = "127.0.0.1:";
    public static final String BD_NAME = "bookShare";
    public static String USER;
    public static String PASSWORD;
    public static String BD_URL;
    
    public static void loadCredentials(){
        Properties properties = new Properties();
        FileInputStream input;
        try {
            input = new FileInputStream("src/main/resources/application.properties");
            System.out.println(input);
            properties.load(input);
            HOST = IP + properties.getProperty("port");
            System.out.println(HOST);
            USER = properties.getProperty("user");
            PASSWORD = properties.getProperty("password");
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void loadDriver() {
        try {
            //getConnectionProperties(); añadir esta linea en caso de que leamos más adelante los parametros desde fichero.
            Class.forName(DRIVER);
            BD_URL = String.format("%s//%s/%s", PROTOCOL, HOST, BD_NAME);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
            //throw new PersistException("Driver not found", OpResult.DB_DRIVER.getCode());
        }
    }
    
    /**
     * gets and returns a connection to database
     * @return connection
     * @throws IOException 
     * @throws PersistException in case of connetion error
     */
    public Connection getConnection(){
        loadCredentials();
        BD_URL = String.format("%s//%s/%s", PROTOCOL, HOST, BD_NAME);
        Connection conn=null;
        try {
            conn = DriverManager.getConnection(BD_URL, USER, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
            //throw new PersistException("Error connecting to database", OpResult.DB_NOCONN.getCode());
        }
        return conn;
    }
}