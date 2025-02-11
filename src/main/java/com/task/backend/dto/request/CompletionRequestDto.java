package com.task.backend.dto.request;



public record CompletionRequestDto(
    String model,
    String prompt,
    float temperature
) {

    public static CompletionRequestDto of(
        String model, String prompt, float temperature
    ) {
        return new CompletionRequestDto(model, prompt, temperature);
    }


}
