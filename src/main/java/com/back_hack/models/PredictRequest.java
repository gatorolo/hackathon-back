package com.back_hack.models;

public record PredictRequest(
        String nombre,
        int tenure,
        double monthly,
        int calls
) {
}
