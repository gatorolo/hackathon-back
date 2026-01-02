package com.back_hack.models;

public record PredictResponse(
        double probability,
        String title,
        String reco,
        String risk,
        String color
) {
}
