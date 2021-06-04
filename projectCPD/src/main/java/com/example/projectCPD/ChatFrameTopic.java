package com.example.projectCPD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatFrameTopic {
    private List<ChatFrame> chatFrameList = new ArrayList<>();
    private Map<String,String> topicAndLastMessage = new HashMap<>();

    public ChatFrameTopic(){
        topicAndLastMessage.put("Architecture","");
        topicAndLastMessage.put("Space","");
        topicAndLastMessage.put("Birds","");
    }

    public void createChats(List<String> topics) {
        for (String topic : topics) {
            ChatFrame chatFrame = new ChatFrame(topic);
            chatFrameList.add(chatFrame);
        }
    }

    public void printMessage(String topic, String message) {
        for (ChatFrame chatFrame : chatFrameList) {
            if (chatFrame.getTopic().equals(topic)) {
                chatFrame.printMessage(message);
            }
        }
    }

    public void resetListOfMessagesPerTopic(String topic) {
        for (ChatFrame chatFrame : chatFrameList) {
            if (chatFrame.getTopic().equals(topic)) {
                chatFrame.resetListOfMessages();
            }
        }
    }

    public void setEditableInputTextPerTopic(String topic, boolean status) {
        for (ChatFrame chatFrame : chatFrameList) {
            if (chatFrame.getTopic().equals(topic)) {
                chatFrame.setEditableInputText(status);
            }
        }
    }

    public String getMessagesToPublishPerTopic(String topic) {
        for (ChatFrame chatFrame : chatFrameList) {
            if (chatFrame.getTopic().equals(topic)) {
                return chatFrame.getMessageSendList().toString();
            }
        }
        return "";
    }

    public void saveLastMessagePerTopic(String topic, String message){
        topicAndLastMessage.put(topic, message);
    }

    public String getLastMessageSentPerTopic(String topic){
        return topicAndLastMessage.get(topic);
    }

}
