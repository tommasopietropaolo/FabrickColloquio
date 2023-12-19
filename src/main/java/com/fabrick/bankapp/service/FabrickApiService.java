package com.fabrick.bankapp.service;

import com.fabrick.bankapp.client.FabrickResponse;
import com.fabrick.bankapp.dto.balanceDto.Balance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class FabrickApiService {

    public static final String BASE_PATH = "/api/gbs/banking/v4.0/accounts";
    private final WebClient webClient;
    private final Long accountId;

    private static final Logger logger = LoggerFactory.getLogger(FabrickApiService.class);

    public FabrickApiService(
            WebClient.Builder webClientBuilder,
            @Value("${fabrick.api.base-url}") String baseUrl,
            @Value("${fabrick.api.api-key}") String apiKey,
            @Value("${fabrick.api.account-id}") Long accountId,
            @Value("${fabrick.api.auth-schema}") String authSchema){
        this.webClient = webClientBuilder.baseUrl(baseUrl)
                .defaultHeader("Auth-Schema", authSchema)
                .defaultHeader("Api-Key", apiKey)
                .build();


        this.accountId = accountId;

    }
    //Display Balance
    public Balance getBalance() throws RuntimeException {
        FabrickResponse<Balance> response;
        try {
            response = webClient.get()
                    .uri(BASE_PATH + "/{accountId}/balance", accountId)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<FabrickResponse<Balance>>() {
                    })
                    .block();

        } catch (Exception e) {
            logger.error("Error while calling API ", e);
            throw new RuntimeException("Fabrick getBalance service api error, check logs for detail:");
        }

        return response.getPayload();
    }

}
