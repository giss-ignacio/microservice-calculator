package com.tenpo.calculator.history.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl implements HistoryService{

    private static final Logger logger = LoggerFactory.getLogger(HistoryServiceImpl.class);
    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public void log(Object request, Object response, String endpoint) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            String requestJson = ow.writeValueAsString(request);
            String responseJson = ow.writeValueAsString(response);
            historyRepository.save(new HistoryEntry(requestJson, responseJson, endpoint));
        } catch (Exception e) {
            logger.error("Can't save history entry: {}", e.getMessage());
        }
    }
}
