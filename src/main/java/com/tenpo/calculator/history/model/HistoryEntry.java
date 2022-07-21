package com.tenpo.calculator.history.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="history")
public class HistoryEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String request;
    private String response;
    private String endpoint;

    public HistoryEntry() {
    }

    public HistoryEntry(String request, String response, String endpoint) {
        this.request = request;
        this.response = response;
        this.endpoint = endpoint;
    }
}
