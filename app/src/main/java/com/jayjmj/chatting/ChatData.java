package com.jayjmj.chatting;

/**
 * Created by jiseung on 16. 7. 1.
 */
public class ChatData {
    private String userName;
    private String message;

    public ChatData(){}
    public ChatData(String userName, String message){
        this.userName = userName;
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }
}
