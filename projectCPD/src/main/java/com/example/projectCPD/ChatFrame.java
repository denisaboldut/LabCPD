package com.example.projectCPD;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChatFrame extends JFrame{
    private  JTextArea textArea;
    private JTextArea inputText;
    private ArrayList<String> messageSendList = new ArrayList<>();
    private String topic;
    private JButton sendButton;

    public ChatFrame(String topic){
        this.topic= topic;
        sendButton=new JButton("SEND");
        sendButton.setBounds(130,100,100, 40);
        sendButton.setEnabled(false);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!inputText.getText().isEmpty()) {
                    textArea.append(topic + ": " + inputText.getText() + "\n");
                    messageSendList.add(inputText.getText());
                    inputText.setText("");
                }
            }
        });

        textArea = new JTextArea(20,27);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);

        inputText =  new JTextArea(2,10);
        JScrollPane scrollPaneInput = new JScrollPane(inputText);
        inputText.setEditable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(325,425);
        setTitle("App2: "+topic);
        getContentPane().add(scrollPane);
        getContentPane().add(scrollPaneInput);
        getContentPane().add(sendButton);
        setLayout(new FlowLayout());
        setLocation(500,10);
        setVisible(true);
    }

    public void printMessage(String message){
        textArea.append(topic+": "+message+"\n");
    }

    public void setEditableInputText(boolean status){
        inputText.setEditable(status);
        sendButton.setEnabled(status);
    }

    public void resetListOfMessages(){
        messageSendList.clear();
    }

    public String getTopic() {
        return topic;
    }

    public ArrayList<String> getMessageSendList() {
        return messageSendList;
    }
}
