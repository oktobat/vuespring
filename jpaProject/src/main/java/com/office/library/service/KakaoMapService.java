package com.office.library.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@Service
public class KakaoMapService {

    private final String KAKAO_API_KEY = "67a7b2b6fce96906b326c26a7f6a4f74";

    public KakaoMapService() {
    }

    public Map<String, Double> getAddressCoordinates(String address) {
        Map<String, Double> latlon = new HashMap<>();
        try {
            RestTemplate restTemplate = new RestTemplate();

            String apiUrl = UriComponentsBuilder.fromHttpUrl("https://dapi.kakao.com/v2/local/search/address.json")
                    .queryParam("query", address)
                    .build().toUriString();

            HttpHeaders headers = new HttpHeaders();

            headers.set("Authorization", "KakaoAK " + KAKAO_API_KEY);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            JsonNode documents = jsonNode.get("documents");

            if (documents.isArray() && documents.size() > 0) {
                double latitude = documents.get(0).get("y").asDouble();
                double longitude = documents.get(0).get("x").asDouble();

                System.out.println("위도 : " + latitude);
                System.out.println("경도 : " + longitude);

                latlon.put("lat", latitude);
                latlon.put("lon", longitude);

            } else {
                System.out.println("ㅇㅇ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return latlon;
    }
}