package com.eriksandsten.chromedtp;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.StreamReadConstraints;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.eriksandsten.chromedtp.request.BaseRequest;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

public class ChromeDTPWebSocketClient {
    public static final BlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
    private Map<String, Consumer<Object>> callbacks = new HashMap<>();
    private static final JsonFactory objectMapper = new ObjectMapper().getFactory().setStreamReadConstraints(StreamReadConstraints.builder().maxStringLength(80_000_000).build());

    private static WebSocketClient socketClient = null;

    public ChromeDTPWebSocketClient(String serverUri) {
        if (socketClient == null) {
            socketClient = new WebSocketClient(URI.create(serverUri)) {
                @Override
                public void onOpen(ServerHandshake handshake) {
                    System.out.println("Connected to DevTools WebSocket");
                }

                @Override
                public void onMessage(String message) {
                    System.out.println("\n==========================================================================================================\n");
                    System.out.println("Received: " + message);
                    System.out.println("\n==========================================================================================================\n\n");

                    try {
                        if (!message.startsWith("{\"method\"")) {
                            queue.put(message); // queue.add("DEJSAN");
                        } else {
                            MethodObj methodObj = objectMapper.createParser(message).readValueAs(MethodObj.class);
                        }
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e.getCause());
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    System.out.println("Disconnected from WebSocket");
                }

                @Override
                public void onError(Exception ex) {
                    ex.printStackTrace();
                    throw new RuntimeException(ex.getCause());
                }
            };
        }
    }

    public void addCallback(String eventName, Consumer<Object> callback) {
        callbacks.put(eventName, callback);
    }

    public static class MethodObj {
        public String method;
        public Object params;
        public String sessionId;
    }

    public static WebSocketClient getSocketClient() {
        return socketClient;
    }

    public static <T> T executeCommand(String command, Class<T> clazz) {
        try {
            socketClient.send(command);
            String response = queue.take();
            return objectMapper.createParser(response).readValueAs(clazz);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }

    public static <T> T executeCommand(BaseRequest command, Class<T> clazz) {
        try {
            String json = command.getJSON();
            socketClient.send(json);
            String response = queue.take();
            return objectMapper.createParser(response).readValueAs(clazz);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }
}
