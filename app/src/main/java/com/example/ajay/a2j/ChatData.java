package com.example.ajay.a2j;


public class ChatData {
    public String body;
    public boolean isMine;// Did I send the message.
    public ChatData(String Sender, String Receiver, String messageString, boolean isMINE) {
        body = messageString;
        isMine = isMINE;
    }
}