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
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SketchfabService {

    private static final String UPLOAD_URL = "https://api.sketchfab.com/v3/models";
    private static final Pattern UID_PATTERN = Pattern.compile("[a-f0-9]{32}");

    @Value("${sketchfab.api.token:}")
    private String apiToken;

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean isUploadEnabled() {
        return apiToken != null && !apiToken.isBlank();
    }

    public Optional<String> parseUid(String urlOrUid) {
        if (urlOrUid == null || urlOrUid.isBlank()) return Optional.empty();
        Matcher m = UID_PATTERN.matcher(urlOrUid.trim());
        return m.find() ? Optional.of(m.group()) : Optional.empty();
    }

    public Optional<String> uploadModel(MultipartFile file, String name, String description, String tags) {
        if (file == null || file.isEmpty() || !isUploadEnabled()) return Optional.empty();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Token " + apiToken);
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("name", name);
            if (description != null && !description.isBlank()) body.add("description", description);
            if (tags != null && !tags.isBlank()) body.add("tags", tags.replace(",", " ").trim());

            ByteArrayResource fileResource = new ByteArrayResource(file.getBytes()) {
                @Override public String getFilename() { return file.getOriginalFilename(); }
            };
            body.add("modelFile", fileResource);

            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                    UPLOAD_URL, HttpMethod.POST, request, new ParameterizedTypeReference<>() {});

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Object uid = response.getBody().get("uid");
                if (uid != null) return Optional.of(uid.toString());
            }
        } catch (Exception ex) {
            System.err.println("Sketchfab upload failed: " + ex.getMessage());
        }

        return Optional.empty();
    }
}
