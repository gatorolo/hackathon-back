package com.back_hack.services;

import com.back_hack.models.PredictRequest;
import com.back_hack.models.PredictResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PredictService {

    @Autowired
    private RestTemplate restTemplate;


    private final String api_python = "http://localhost:5000/predict";

    public PredictResponse obtenerPrediccion(PredictRequest request) {
        try {
            return restTemplate.postForObject(api_python, request, PredictResponse.class);
        } catch (Exception e) {
            return new PredictResponse(0.0, "Error", "Servidor no disponible", "N/A", "gray");
        }
    }
}
