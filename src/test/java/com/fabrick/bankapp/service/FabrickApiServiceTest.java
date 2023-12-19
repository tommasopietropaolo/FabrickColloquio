package com.fabrick.bankapp.service;

import com.fabrick.bankapp.client.FabrickResponse;
import com.fabrick.bankapp.dto.balanceDto.Balance;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class FabrickApiServiceTest {
    private final String DUMMY_API_KEY = "q412";
    private final Long DUMMY_ACCOUNT_ID = 123456L;
    private final String DUMMY_SCHEMA = "demo_schema";

    //@InjectMocks
    private FabrickApiService victim;

    public static MockWebServer mockFabrickServer;

    @BeforeAll
    static void setUp() throws IOException {
        mockFabrickServer = new MockWebServer();
        mockFabrickServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockFabrickServer.shutdown();
    }

    @BeforeEach
    void initialize() {
        String baseUrl = String.format("http://localhost:%s",
                mockFabrickServer.getPort());
        victim = new FabrickApiService(WebClient.builder(), baseUrl, DUMMY_API_KEY, DUMMY_ACCOUNT_ID, DUMMY_SCHEMA);
    }

    @Test
    void testGetBalanceReturningCorrectValue() throws InterruptedException {
        //given
        FabrickResponse<Balance> expectedResponse = new FabrickResponse<>("OK", new ArrayList<>(), new Balance(94.32));
        givenExpectedResponse(expectedResponse);
        String expectedUrl = String.format("/api/gbs/banking/v4.0/accounts/%s/balance", DUMMY_ACCOUNT_ID);
        //when
        Balance response = victim.getBalance();
        RecordedRequest mockRequest = mockFabrickServer.takeRequest();
        //then
        assertThat(response.getBalance()).isEqualTo(expectedResponse.getPayload().getBalance());
        assertThat(mockRequest.getPath()).isEqualTo(expectedUrl);
        assertThat(mockRequest.getHeader("Auth-Schema")).isEqualTo(DUMMY_SCHEMA);
        assertThat(mockRequest.getHeader("Api-Key")).isEqualTo(DUMMY_API_KEY);
        assertThat(mockRequest.getMethod()).isEqualTo("GET");

    }

    @Test
    void testGetBalanceShouldThrowExceptionAndLogErrorIfApiIsThrowing() throws InterruptedException {
        //given
        final String expectedError = "Fabrick getBalance service api error, check logs for detail:";
        mockFabrickServer.enqueue(
                new MockResponse().setResponseCode(403)
                        .setHeader("content-type", "application/json")
                        .setBody("{message: 'hello world'}"));

        //when
        assertThatThrownBy(() -> victim.getBalance()).isInstanceOf(RuntimeException.class)
                .hasMessage(expectedError);
        //then
        RecordedRequest mockRequest = mockFabrickServer.takeRequest();
        assertThat(mockRequest.getHeader("Auth-Schema")).isEqualTo(DUMMY_SCHEMA);
        assertThat(mockRequest.getHeader("Api-Key")).isEqualTo(DUMMY_API_KEY);
        assertThat(mockRequest.getMethod()).isEqualTo("GET");

    }


    //configuration  server mock
    void givenExpectedResponse(Object expectedResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            mockFabrickServer.enqueue(new MockResponse()
                    .setBody(objectMapper.writeValueAsString(expectedResponse))
                    .addHeader("Content-Type", "application/json"));
        } catch (Exception e) {
            System.out.println("Error while calling API ");
        }

    }

}
