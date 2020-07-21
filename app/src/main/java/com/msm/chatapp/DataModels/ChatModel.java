package com.msm.chatapp.DataModels;

import java.util.ArrayList;
import java.util.List;

public class ChatModel {

    private String name;
    private String id;
    private List<MessageModel> messages;

    public ChatModel(){
        messages=new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MessageModel> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageModel> messages) {
        this.messages = messages;
    }

    public void addMessage(MessageModel msg){
        this.messages.add(msg);
    }
}
