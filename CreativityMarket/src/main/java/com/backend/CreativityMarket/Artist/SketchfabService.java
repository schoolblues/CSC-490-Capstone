package com.backend.CreativityMarket.Artist;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class SketchfabService {

    private static final String UPLOAD_URL = "https://api.sketchfab.com/v3/models";

    @Value("${sketchfab.api.token}")
    private String apiToken;

    private final RestTemplate restTemplate = new RestTemplate();

    public String uploadModel(MultipartFile file, String name, String description, String tags) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Token " + apiToken);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("name", name);

        if (description != null && !description.isBlank()) {
            body.add("description", description);
        }
        if (tags != null && !tags.isBlank()) {
            body.add("tags", tags.replace(",", " ").trim());
        }

        ByteArrayResource fileResource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };
        body.add("modelFile", fileResource);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                UPLOAD_URL, HttpMethod.POST, request, new ParameterizedTypeReference<>() {});

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            Object uid = response.getBody().get("uid");
            if (uid != null) {
                return uid.toString();
            }
        }

        throw new RuntimeException("Sketchfab upload failed: " + response.getStatusCode());
    }
}
