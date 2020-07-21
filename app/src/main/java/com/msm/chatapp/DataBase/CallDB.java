package com.msm.chatapp.DataBase;

import com.msm.chatapp.DataModels.CallModel;
import com.msm.chatapp.DataModels.ChatModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CallDB {

    public static List<CallModel> callModelList;

    public static void InitiateDB(){
        callModelList=new ArrayList<>();
        CallModel callModel;
        Date date;
        for (int i=0;i<20;i++) {
            callModel = new CallModel();
            callModel.setName("Manas"+i);
            callModel.setType("0");
            date=new Date();
            date=new Date(date.getTime()-(10)*60000);
            callModel.setDate(date);
            callModelList.add(callModel);
            callModel = new CallModel();
            callModel.setName("Rohith"+i);
            callModel.setType("1");
            date=new Date();
            date=new Date(date.getTime()-(9)*60000);
            callModel.setDate(date);
            callModelList.add(callModel);
            callModel = new CallModel();
            callModel.setName("Meghana"+i);
            callModel.setType("0");
            date=new Date();
            date=new Date(date.getTime()-(8)*60000);
            callModel.setDate(date);
            callModelList.add(callModel);
            callModel = new CallModel();
            callModel.setName("Rakshith"+i);
            callModel.setType("2");
            date=new Date();
            date=new Date(date.getTime()-(7)*60000);
            callModel.setDate(date);
            callModelList.add(callModel);
            callModel = new CallModel();
            callModel.setName("Ragini"+i);
            callModel.setType("1");
            date=new Date();
            date=new Date(date.getTime()-(6)*60000);
            callModel.setDate(date);
            callModelList.add(callModel);
        }
    }

    public static List<CallModel> getPage(int pos){
        return callModelList.subList(pos,pos+8);
    }

}
