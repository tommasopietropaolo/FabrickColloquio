package com.fabrick.bankapp.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class FabrickResponse<T> implements Serializable {
    private String status;
    private List<String> errors;
    private T payload;

    @JsonCreator
    public FabrickResponse(@JsonProperty("status") String status,
                           @JsonProperty("errors") List<String> errors,
                           @JsonProperty("payload") T payload) {
        this.status = status;
        this.errors = errors;
        this.payload = payload;
    }

    public FabrickResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
