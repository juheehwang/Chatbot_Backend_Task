package com.task.backend.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.backend.config.ChatGPTConfig;
import com.task.backend.dto.request.CompletionRequestDto;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final ChatGPTConfig chatGPTConfig;

    public Map<String, Object> prompt(String question) throws IOException, InterruptedException {
        log.debug("[+] 프롬프트를 수행합니다.");

        ObjectMapper mapper = new ObjectMapper();
        String input = mapper.writeValueAsString(CompletionRequestDto.of("gpt-3.5-turbo",question,1L));

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.openai.com/v1/completions"))
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer <API Key>")
            .POST(HttpRequest.BodyPublishers.ofString(input))
            .build();

        HttpClient client = HttpClient.newHttpClient();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            log.debug("result ======= {}",response.body());

            }
        return Map.of();
    }


}
