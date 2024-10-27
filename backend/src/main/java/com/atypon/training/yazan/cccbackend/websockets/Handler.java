package com.atypon.training.yazan.cccbackend.websockets;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;
import java.io.IOException;
import java.time.LocalTime;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
public class Handler extends TextWebSocketHandler implements SubProtocolCapable {

    private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Server Connection Opened");
        sessions.add(session);

        TextMessage message = new TextMessage("one-time message from the server.");
        log.info("Server sends {} message", message);
        session.sendMessage(message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("Server Connection Closed: {}", status);
        sessions.remove(session);
    }

    @Scheduled(fixedRate = 10000)
    void sendPeriodicMessages() throws IOException {
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                String broadcast = "server periodic message: " + LocalTime.now();
                log.info("Server sends {} message", broadcast);
                session.sendMessage(new TextMessage(broadcast));
            }
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String request = message.getPayload();
        log.info("Server received {}", request);

        String response = String.format("Response from server to %s", request);
        log.info("Server sends {}", response);
        session.sendMessage(new TextMessage(response));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("Server transport error: {}", exception.getMessage());
    }

    @Override
    public List<String> getSubProtocols() {
        return Collections.singletonList("subprotocol.demo.websocket");
    }
}
