package com.marciobueno.aos.helloweb.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.RequestEntity;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/info")
public class InfoController {

    @GetMapping
    public ResponseEntity<Object> getCatImage() {
        RestTemplate restTemplate = new RestTemplate();
        
        try {
            String url = "https://api.thecatapi.com/v1/images/search";
            
            HttpHeaders headers = new HttpHeaders();
            headers.set("x-api-key", "live_eoN8i99g8r912L42iiIFAlTpPiuE9mL4rnKuJcUodRmG8cGIKKYiTL2y5fiBrf4D");

            RequestEntity<String> entity = new RequestEntity<>(headers, HttpMethod.GET, new java.net.URI(url));
            
            ResponseEntity<List> response = restTemplate.exchange(entity, List.class);
            
            if (response.getBody() != null && !response.getBody().isEmpty()) {
                String imageUrl = (String) ((Map) response.getBody().get(0)).get("url");
                return ResponseEntity.ok(Map.of("url", imageUrl));
            } else {
                return ResponseEntity.status(500).body("Erro ao obter a imagem de gato.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao fazer a requisição para a API de gatos: " + e.getMessage());
        }
    }
}
