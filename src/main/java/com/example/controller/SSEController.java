package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.example.service.SSEservice;
import org.springframework.http.HttpStatus;
@CrossOrigin
@RestController
public class SSEController {


    // public static final List<SseEmitter> emitters = Collections.synchronizedList( new ArrayList<>());

    // // @RequestMapping(path = "/stream", method = RequestMethod.GET)
    // @GetMapping("/stream")
    // public SseEmitter stream() throws IOException {

    //     SseEmitter emitter = new SseEmitter();

    //     emitters.add(emitter);
    //     emitter.onCompletion(() -> emitters.remove(emitter));

    //     return emitter;
    // }
    @Autowired
    SSEservice service;
    
	final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

	@GetMapping("/notification")
	public ResponseEntity<SseEmitter> doNotify() throws InterruptedException, IOException {
		final SseEmitter emitter = new SseEmitter();
		service.addEmitter(emitter);
		service.doNotify();
		emitter.onCompletion(() -> service.removeEmitter(emitter));
		emitter.onTimeout(() -> service.removeEmitter(emitter));
		return new ResponseEntity<>(emitter, HttpStatus.OK);
	}


    
}