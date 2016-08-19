package com.example.jonathan.iot_smartcities_mobileapp2;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jonathan on 11/08/2016.
 */
public class PIPEList {

    List<PIPEInstance> PIPEList = new LinkedList<PIPEInstance>();
    Connection connection = new Connection();

    public void getAllData(){
        try {
            PIPEList = connection.sendGet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<PIPEInstance> getPIPEList(){
        return PIPEList;
    }

    public PIPEInstance getPipeAtTime(String timestamp){
        for(PIPEInstance pipeInstance : PIPEList) {
            if (pipeInstance.getTimestamp().equals(timestamp)) return pipeInstance;
        }
        return null;
    }

}
