package hu.webuni.hr.jozsi.controller;

import hu.webuni.hr.jozsi.dto.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class EmployeeRestControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCreateEmployee_ValidInput_ReturnsCreated() {
        EmployeeDto dto = new EmployeeDto();
        dto.setName("John");
        dto.setPosition("Engineer");
        dto.setSalary(3000);
        dto.setStartDate(LocalDateTime.now().minusDays(1));

        ResponseEntity<EmployeeDto> response = restTemplate.postForEntity("/api/employees", dto, EmployeeDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("John");
    }

    @Test
    void testCreateEmployee_InvalidInput_ReturnsBadRequest() {
        EmployeeDto dto = new EmployeeDto();
        dto.setName("");
        dto.setPosition("Engineer");
        dto.setSalary(-100);
        dto.setStartDate(LocalDateTime.now().plusDays(1));

        ResponseEntity<String> response = restTemplate.postForEntity("/api/employees", dto, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testUpdateEmployee_ValidInput_ReturnsOk() throws Exception {
        EmployeeDto createDto = new EmployeeDto();
        createDto.setName("Jane");
        createDto.setPosition("Manager");
        createDto.setSalary(4000);
        createDto.setStartDate(LocalDateTime.now().minusDays(1));
        ResponseEntity<EmployeeDto> createResponse = restTemplate.postForEntity("/api/employees", createDto, EmployeeDto.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Long id = createResponse.getBody().getId();

        EmployeeDto updateDto = createResponse.getBody();
        updateDto.setSalary(4500);
        restTemplate.put("/api/employees/" + id, updateDto);

        ResponseEntity<EmployeeDto> getResponse = restTemplate.getForEntity("/api/employees/" + id, EmployeeDto.class);
        assertThat(getResponse.getBody().getSalary()).isEqualTo(4500);
    }

    @Test
    void testUpdateEmployee_InvalidInput_ReturnsBadRequest() {
        EmployeeDto dto = new EmployeeDto();
        dto.setName("Bob");
        dto.setPosition("Intern");
        dto.setSalary(1000);
        dto.setStartDate(LocalDateTime.now().minusDays(1));
        ResponseEntity<EmployeeDto> createResponse = restTemplate.postForEntity("/api/employees", dto, EmployeeDto.class);
        Long id = createResponse.getBody().getId();

        dto.setName("");
        dto.setSalary(-500);
        restTemplate.put("/api/employees/" + id, dto, String.class);
        ResponseEntity<String> putResponse = restTemplate.exchange(
                "/api/employees/" + id,
                org.springframework.http.HttpMethod.PUT,
                new org.springframework.http.HttpEntity<>(dto),
                String.class
        );
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}