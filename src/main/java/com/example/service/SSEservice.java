package com.example.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.controller.SSEController;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class SSEservice {
    public void sendSseEventsToUI() { //your model class
        List<SseEmitter> sseEmitterListToRemove = new ArrayList<>();
        SSEController.emitters.forEach((SseEmitter emitter) -> {
            try {
                emitter.send("update", MediaType.APPLICATION_JSON);
            } catch (IOException e) {
                emitter.complete();
                sseEmitterListToRemove.add(emitter);
                e.printStackTrace();
            }
        });
        SSEController.emitters.removeAll(sseEmitterListToRemove);
    }
}
