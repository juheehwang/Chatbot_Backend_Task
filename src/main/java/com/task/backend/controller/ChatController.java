package com.task.backend.controller;

import com.task.backend.service.ChatService;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/prompt")
    public ResponseEntity<Map<String, Object>> selectPrompt(@RequestBody String question)
        throws IOException, InterruptedException {
        Map<String, Object> result = chatService.prompt(question);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
