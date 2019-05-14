package by.hohma13.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class InputOutputController {

    static final String ROOM_PROPERTIES = "bulb.status.room";
    static final String BULB_STATUS_PATH = "src/main/resources/config.properties";

    private FileInputStream in;
    private FileOutputStream out;

    private Properties bulbsProperties;

    private String roomMsg;
    private String roomNum;
    private String roomStatus;

    public InputOutputController (String num){
        this.roomNum = num;
    }

    public InputOutputController (String num, String status){
        this.roomNum = num;
        this.roomMsg = status;
    }


    public String getBulbStatus() throws IOException {
        in = new FileInputStream(BULB_STATUS_PATH);
        bulbsProperties = new Properties();
        bulbsProperties.load(in);
        roomStatus = bulbsProperties.getProperty(ROOM_PROPERTIES+roomNum);
        in.close();
        return roomStatus;
    }

    public String setBulbStatus() throws IOException {
        out = new FileOutputStream(BULB_STATUS_PATH);
        bulbsProperties = new Properties();
        bulbsProperties.setProperty(ROOM_PROPERTIES+roomNum, roomMsg);
        bulbsProperties.store(out, null);
        out.close();
        return roomMsg;
    }

}