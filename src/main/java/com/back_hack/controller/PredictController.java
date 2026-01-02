package com.back_hack.controller;

import com.back_hack.models.PredictRequest;
import com.back_hack.models.PredictResponse;
import com.back_hack.services.PredictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analyze")
@CrossOrigin(origins = "http://localhost:4200")
public class PredictController {

    @Autowired
    private PredictService predictService;

    @PostMapping("/predict")
    public ResponseEntity<PredictResponse> analyze(@RequestBody PredictRequest request) {
        PredictResponse result = predictService.obtenerPrediccion(request);
        return ResponseEntity.ok(result);
    }
}
