package com.tenpo.calculator;

import com.tenpo.calculator.calculator.application.CalculatorRequestDto;
import com.tenpo.calculator.calculator.application.CalculatorResponseDto;
import com.tenpo.calculator.security.user.application.UserDto;
import com.tenpo.calculator.security.user.application.UserTokenDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CheckHTTPResponse {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeTestClass
    public void setup() {
        registerAdminUser();
    }

    @Test
    public void unauthorizedMessageWhenTokenIsAbsent() {
        assertThat(testRestTemplate.getForObject("http://localhost:" + port + "/sum-numbers", String.class ), containsString("Unauthorized"));
    }

    @Test
    public void sumNumbersSuccess() {
        CalculatorRequestDto request = new CalculatorRequestDto();
        List<Double> numbers = new ArrayList<>();
        numbers.add(1.1);
        numbers.add(2.2);
        numbers.add(3.3);
        request.setNumbers(numbers);

        HttpEntity<CalculatorRequestDto> httpRequest = new HttpEntity<>(request, getAutheticatedHeaders());

        ResponseEntity<CalculatorResponseDto> response = testRestTemplate.postForEntity("http://localhost:" + port + "/sum-numbers", httpRequest, CalculatorResponseDto.class);
        assertEquals(6.6, response.getBody().getNumbersSum());
    }

    @Test
    public void sumNumbersBadRequest() {
        CalculatorRequestDto request = new CalculatorRequestDto();

        HttpEntity<CalculatorRequestDto> httpRequest = new HttpEntity<>(request, getAutheticatedHeaders());

        ResponseEntity<CalculatorResponseDto> response = testRestTemplate.postForEntity("http://localhost:" + port + "/sum-numbers", httpRequest, CalculatorResponseDto.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    private HttpHeaders getAutheticatedHeaders() {
        UserDto userDto = new UserDto("admin", "admin@admin.com", "admin", "admin", "admin");

        HttpEntity<UserDto> httpRequest = new HttpEntity<>(userDto, new HttpHeaders());
        ResponseEntity<UserTokenDto> response = testRestTemplate.postForEntity("http://localhost:" + port + "/api/auth/login", httpRequest, UserTokenDto.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + response.getBody().getJwt());
        return headers;
    }

    private void registerAdminUser() {
        UserDto userDto = new UserDto("admin", "admin@admin.com", "admin", "admin", "admin");

        HttpEntity<UserDto> httpRequest = new HttpEntity<>(userDto, new HttpHeaders());
        ResponseEntity<UserTokenDto> response = testRestTemplate.postForEntity("http://localhost:" + port + "/api/auth/signup", httpRequest, UserTokenDto.class);
    }

}
