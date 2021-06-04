package com.example.projectCPD;

import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.logging.Logger;

@Component
public class Application2 {
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static PrintWriter out;
    private static BufferedReader in;
    private static Socket clientSocket;
    private static final Logger LOGGER = Logger.getLogger(Application2.class.getName());
    private static ChatFrameTopic chatFrameTopic = new ChatFrameTopic();
    private static List<String> topicsToPublish = new ArrayList<>();
    private static GCPConfiguration.PubsubOutboundGateway gateway;

    private static String messageSent = "";

    public Application2(GCPConfiguration.PubsubOutboundGateway gateway) {
        Application2.gateway = gateway;
    }

    @Bean
    @ServiceActivator(inputChannel = "pubsubInputChannel")
    public MessageHandler messageReceiver() {
        return message -> {
            String messageReceived = new String((byte[]) message.getPayload());
            String topic = message.getHeaders().get(GcpPubSubHeaders.TOPIC, String.class);
            if ((chatFrameTopic.getLastMessageSentPerTopic(topic) != null)&&(!chatFrameTopic.getLastMessageSentPerTopic(topic).equals(messageReceived))) {
                chatFrameTopic.printMessage(topic, messageReceived);
            }
            LOGGER.info(message.getHeaders().get(GcpPubSubHeaders.TOPIC, String.class) + " " + "Message arrived! Payload: " + new String((byte[]) message.getPayload()));
            BasicAcknowledgeablePubsubMessage originalMessage =
                    message.getHeaders().get(GcpPubSubHeaders.ORIGINAL_MESSAGE, BasicAcknowledgeablePubsubMessage.class);
            originalMessage.ack();
        };
    }


    public static void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();
        startConnection("127.0.0.1", 1003);

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String token;
        while ((token = in.readLine()) != null) {
            System.out.println("Am primit: " + token);
            LOGGER.info(token);
            for (String topic : topicsToPublish) {
                chatFrameTopic.printMessage(topic, "=== You can talk ===");
                chatFrameTopic.setEditableInputTextPerTopic(topic, true);
            }

            TimerTask task = new TimerTask() {
                public void run() {
                    System.out.println("Task performed on: " + new Date() + "n" +
                            "Thread's name: " + Thread.currentThread().getName());
                    sendMessage("token2");
                    for (String topic : topicsToPublish) {
                        if ((chatFrameTopic.getMessagesToPublishPerTopic(topic).length()) > 2) {
                            System.out.println(topic + ": " + chatFrameTopic.getMessagesToPublishPerTopic(topic));
                            gateway.sendToPubsub(topic, chatFrameTopic.getMessagesToPublishPerTopic(topic));
                            messageSent = chatFrameTopic.getMessagesToPublishPerTopic(topic);
                            chatFrameTopic.saveLastMessagePerTopic(topic, messageSent);
                        }
                        chatFrameTopic.printMessage(topic, "--- time's up ---");
                        chatFrameTopic.setEditableInputTextPerTopic(topic, false);
                        chatFrameTopic.resetListOfMessagesPerTopic(topic);
                    }
                }
            };

            Timer timer = new Timer("Timer");
            long delay = 10000L;
            timer.schedule(task, delay);
        }
    }

    public static void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public static void sendMessage(String msg){
        out.println(msg);
        System.out.println("am trimis " + msg);
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }


    public void stop() throws IOException {
        in.close();
        out.close();
        socket.close();
        serverSocket.close();
    }

    public static void startApp2() throws IOException {
        topicsToPublish.add("Architecture");
        topicsToPublish.add("Space");
        topicsToPublish.add("Birds");
        chatFrameTopic.createChats(topicsToPublish);

        start(1002);
    }

}
