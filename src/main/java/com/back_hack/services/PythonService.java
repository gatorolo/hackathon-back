package com.back_hack.services;

import com.back_hack.models.PredictRequest;
import com.back_hack.models.PredictResponse;
import org.springframework.stereotype.Service;

@Service
public class PythonService {

    public PredictResponse obtenerPrediccion(PredictRequest request) {
        double probability;
        String risk;
        String title;
        String reco;
        String color;

        if (request.calls() > 5 || request.tenure() < 6) {
            probability = 0.88;
            risk = "ALTO";
            title = "Riesgo de Fuga";
            reco = "El cliente tiene muchas quejas. Aplicar plan de retención VIP.";
            color = "red";
        } else if (request.monthly() > 100) {
            probability = 0.45;
            risk = "MEDIO";
            title = "Cliente Inestable";
            reco = "Gasto elevado detectado. Ofrecer descuento por lealtad.";
            color = "yellow";
        } else {
            probability = 0.12;
            risk = "BAJO";
            title = "Cliente Fiel";
            reco = "Mantener servicio actual. Sin riesgo aparente.";
            color = "green";
        }

        return new PredictResponse(probability, title, reco, risk, color);
    }
}
