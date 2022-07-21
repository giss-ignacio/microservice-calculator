package com.tenpo.calculator.history.model;

public interface HistoryService {
    void log(Object request, Object response, String endpoint);
}
