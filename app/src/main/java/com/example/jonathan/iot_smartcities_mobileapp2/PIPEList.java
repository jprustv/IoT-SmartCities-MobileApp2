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

            // Pra Testar Offline
/*            List<PIPEInstance> tempList = new LinkedList<>();
            tempList.add(new PIPEInstance("110820161131", 27, 0.004, 8, 7, 62, 6, 12, 18));
            tempList.add(new PIPEInstance("110820161145", 15, 0.002, 8, 1, 89, 2, 29, 4));
            PIPEList = tempList;
*/

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
