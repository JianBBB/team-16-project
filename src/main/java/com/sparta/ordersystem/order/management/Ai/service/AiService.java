package com.sparta.ordersystem.order.management.ai.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.ordersystem.order.management.ai.dto.AiRequestDto;
import com.sparta.ordersystem.order.management.ai.dto.AiResponseDto;
import com.sparta.ordersystem.order.management.ai.entity.Ai;
import com.sparta.ordersystem.order.management.ai.repository.AiRepository;
import com.sparta.ordersystem.order.management.user.entity.User;
import com.sparta.ordersystem.order.management.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class AiService {

    private final RestTemplate restTemplate;
    private final AiRepository aiRepository;
    private final UserRepository userRepository;

    @Value("${google.api.key}")
    private String apiKey;

    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent";

    public AiService(RestTemplate restTemplate, AiRepository aiRepository, UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.aiRepository = aiRepository;
        this.userRepository = userRepository;
    }

    public AiResponseDto getRecommendations(AiRequestDto requestDto, Long user_id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "{ \"contents\": [{ \"parts\": [{ \"text\": \"" + requestDto.getRequest() +" 답변은 50자 이내로 해줘\" }] }] }";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // API 호출 및 응답 처리
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                API_URL + "?key=" + apiKey,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        // 응답 본문에서 직접 필요한 데이터 추출
        String responseBody = responseEntity.getBody();
        String responseText = extractTextFromResponse(responseBody);

        // 사용자 조회
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("user not found with id: " + user_id));


        // 데이터 저장 및 DTO 반환
        Ai ai = new Ai(responseText, user);

        // 로그 추가
        System.out.println("Saving ai entity: " + ai.toString());

        try {
            aiRepository.save(ai);
            System.out.println("Successfully saved ai entity.");
        } catch (Exception e) {
            System.err.println("Error saving ai entity: " + e.getMessage());
            throw e;
        }

        AiResponseDto responseDto = new AiResponseDto();
        responseDto.setAi_id(ai.getAi_id()); // UUID 설정
        responseDto.setText(responseText);

        return responseDto;
    }

    // JSON 응답에서 text를 추출하는 메서드
    private String extractTextFromResponse(String responseBody) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(responseBody);
            JsonNode textNode = rootNode.path("candidates").get(0)
                    .path("content").path("parts").get(0)
                    .path("text");
            return textNode.asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse response", e);
        }
    }
}