package com.fabrick.bankapp.service;

import com.fabrick.bankapp.client.FabrickResponse;
import com.fabrick.bankapp.dto.balanceDto.Balance;
import com.fabrick.bankapp.dto.transactionDto.Transaction;
import com.fabrick.bankapp.dto.transactionDto.TransactionType;
import com.fabrick.bankapp.dto.transactionDto.Transactions;
import com.fabrick.bankapp.dto.transferDto.Transfer;
import com.fabrick.bankapp.dto.transferDto.TransferRequest;
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
import java.util.List;

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

    @Test
    void testGetTransactionsReturningCorrectValues() throws InterruptedException {
        //given
        List<Transaction> transactionList = new ArrayList<>();
        Transaction transaction1 = new Transaction("1", "OP1", "2023-01-01", "2023-01-01",
                new TransactionType("dummy", "123"), 100.0, "EUR", "Deposit transaction");
        transactionList.add(transaction1);
        Transactions transactions = new Transactions(transactionList);
        FabrickResponse<Transactions> expectedResponse = new FabrickResponse<>("OK", new ArrayList<>(), transactions);
        givenExpectedResponse(expectedResponse);
        String expectedUrl = "/api/gbs/banking/v4.0/accounts/123456/transactions?fromAccountingDate=2019-01-01&toAccountingDate=2019-12-01";
        //when
        List<Transaction> response = victim.getTransactions("2019-01-01", "2019-12-01");
        //then
        assertThat(response).usingRecursiveComparison().isEqualTo(expectedResponse.getPayload().getList());

        RecordedRequest mockRequest = mockFabrickServer.takeRequest();
        //then
        assertThat(mockRequest.getPath()).isEqualTo(expectedUrl);
    }

    @Test
    void testGetTransactionsShouldThrowIllegalArgumentExceptionsWhenDatesAreNotValid() throws InterruptedException {
        assertThatThrownBy(() -> victim.getTransactions("2022-01-01", "2021-01-01"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("La data di fine non può essere prima della data di inizio.");
    }

    @Test
    void testGetTransactionsShouldThrowRunTimeExceptionAndLogErrorIfApiIsThrowing() throws InterruptedException {
        //given
        final String expectedError = "Fabrick getTransactions service api error, check logs for detail: ";
        mockFabrickServer.enqueue(
                new MockResponse().setResponseCode(403)
                        .setHeader("content-type", "application/json")
                        .setBody("{message: 'hello world'}"));

        assertThatThrownBy(() -> victim.getTransactions("2021-01-01", "2021-12-01"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage(expectedError);

        String expectedUrl = "/api/gbs/banking/v4.0/accounts/123456/transactions?fromAccountingDate=2021-01-01&toAccountingDate=2021-12-01";
        RecordedRequest mockRequest = mockFabrickServer.takeRequest();
        //then
        assertThat(mockRequest.getPath()).isEqualTo(expectedUrl);
    }

    @Test
    void testMakeTransferShouldThrowAnErrorIFServiceIsFailing() throws InterruptedException {
        final String expectedError = "{\"status\": \"KO\",\"errors\":[{\"code\": \"API000\",\"description\": " +
                "\"it.sella.pagamenti.servizibonifico.exception.ServiziInvioBonificoSubsystemException: " +
                "it.sella.pagamenti.sottosistemi.SottosistemiException: Errore tecnico CONTO 45685475:Conto 45685475 non esiste\",\"params\": \"\"}],\"payload\":{}}";
        mockFabrickServer.enqueue(
                new MockResponse().setResponseCode(400)
                        .setHeader("content-type", "application/json")
                        .setBody(expectedError));

        TransferRequest request = new TransferRequest();


        assertThatThrownBy(() -> victim.makeTransfer(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessage(expectedError);

        RecordedRequest mockRequest = mockFabrickServer.takeRequest();
        assertThat(mockRequest.getPath()).isEqualTo(String.format("/api/gbs/banking/v4.0/accounts/%s/payments/money-transfers", DUMMY_ACCOUNT_ID));
        assertThat(mockRequest.getMethod()).isEqualTo("POST");
        assertThat(mockRequest.getHeader("X-Time-Zone")).isEqualTo("Europe/Rome");
        assertThat(mockRequest.getHeader("Api-Key")).isEqualTo(DUMMY_API_KEY);
        assertThat(mockRequest.getHeader("Auth-Schema")).isEqualTo(DUMMY_SCHEMA);
        assertThat(mockRequest.getHeader("Content-Type")).isEqualTo("application/json");

    }

    @Test
    void testMakeTransferShouldReturnCorrectValues() throws InterruptedException {
        //given
        FabrickResponse<Transfer> expectedResponse = new FabrickResponse<>("OK", new ArrayList<>(), new Transfer("dummy"));
        givenExpectedResponse(expectedResponse);
        String expectedUrl = String.format("/api/gbs/banking/v4.0/accounts/%s/payments/money-transfers", DUMMY_ACCOUNT_ID);
        TransferRequest request = new TransferRequest();
        //when
        String response = victim.makeTransfer(request);
        RecordedRequest mockRequest = mockFabrickServer.takeRequest();
        assertThat(mockRequest.getPath()).isEqualTo(String.format("/api/gbs/banking/v4.0/accounts/%s/payments/money-transfers", DUMMY_ACCOUNT_ID));
        assertThat(mockRequest.getMethod()).isEqualTo("POST");
        assertThat(mockRequest.getHeader("X-Time-Zone")).isEqualTo("Europe/Rome");
        assertThat(mockRequest.getHeader("Api-Key")).isEqualTo(DUMMY_API_KEY);
        assertThat(mockRequest.getHeader("Auth-Schema")).isEqualTo(DUMMY_SCHEMA);
        assertThat(mockRequest.getHeader("Content-Type")).isEqualTo("application/json");
        //then
        assertThat(response).isEqualTo(expectedResponse.getPayload().getMoneyTransferId());

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
