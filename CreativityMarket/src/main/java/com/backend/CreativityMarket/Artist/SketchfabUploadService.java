package com.backend.CreativityMarket.Artist;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SketchfabUploadService {

    private static final String SKETCHFAB_MODELS_URL = "https://api.sketchfab.com/v3/models";
    private static final Pattern UID_PATTERN = Pattern.compile("([a-zA-Z0-9]{32})");

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${sketchfab.api.token:}")
    private String sketchfabApiToken;

    public Optional<String> uploadModel(MultipartFile modelFile, ModelUploadForm form) {
        if (modelFile == null || modelFile.isEmpty() || !isUploadEnabled()) {
            return Optional.empty();
        }

        try {
            MultiValueMap<String, Object> payload = new LinkedMultiValueMap<>();
            payload.add("name", safe(form.getTitle(), "Untitled Model"));
            payload.add("description", safe(form.getDescription(), "Uploaded from Creativity Market"));
            payload.add("isPublished", "true");
            payload.add("source", "Creativity Market");
            payload.add("modelFile", toResource(modelFile));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.setBearerAuth(sketchfabApiToken.trim());

            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(payload, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity(SKETCHFAB_MODELS_URL, request, Map.class);

            if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
                return Optional.empty();
            }

            Object uid = response.getBody().get("uid");
            if (uid == null) {
                return Optional.empty();
            }

            return Optional.of(uid.toString());
        } catch (RestClientException | IOException ex) {
            return Optional.empty();
        }
    }

    public Optional<String> parseUid(String sketchfabUrlOrUid) {
        if (sketchfabUrlOrUid == null || sketchfabUrlOrUid.isBlank()) {
            return Optional.empty();
        }

        Matcher matcher = UID_PATTERN.matcher(sketchfabUrlOrUid.trim());
        if (matcher.find()) {
            return Optional.of(matcher.group(1));
        }

        return Optional.empty();
    }

    public boolean isUploadEnabled() {
        return sketchfabApiToken != null && !sketchfabApiToken.isBlank();
    }

    private ByteArrayResource toResource(MultipartFile modelFile) throws IOException {
        return new ByteArrayResource(modelFile.getBytes()) {
            @Override
            public String getFilename() {
                return modelFile.getOriginalFilename();
            }
        };
    }

    private String safe(String value, String fallback) {
        return value == null || value.isBlank() ? fallback : value;
    }
}
