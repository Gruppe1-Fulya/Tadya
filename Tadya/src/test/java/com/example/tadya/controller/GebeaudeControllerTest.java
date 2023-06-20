package com.example.tadya.controller;

import com.example.tadya.model.Gebeaude;
import com.example.tadya.repository.GebeaudeRepository;
import com.example.tadya.service.GebeaudeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GebeaudeController.class)
class GebeaudeControllerTest {

    @Autowired
    private MockMvc mvc;
    @Mock
    private GebeaudeRepository repository;


    @Test
    void findAll() throws Exception {
        List<Gebeaude> buildings = Arrays.asList(
                new Gebeaude(475846957, null, LocalDateTime.of(
                        2023, 06, 20, 17, 39, 26), false, null, null)
        );
        when(repository.findAll()).thenReturn(buildings);

        RequestBuilder request = MockMvcRequestBuilders.get("/map");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals(new Gebeaude(539115442, "building", LocalDateTime.of(2023, 06, 20,17,39,26), false, null, null), result.getResponse());
    }

    @Test
    void getBuilding() {
        Gebeaude building = new Gebeaude(, "Building 1");
        when(repository.findById(1)).thenReturn(Optional.of(building));

        // Act
        Gebeaude result = controller.getBuilding(1);

        // Assert
        assertEquals("Building 1", result.getName());
    }

    @Test
    void sendHelpRequest() {
    }

    @Test
    void deleteBuilding() {
    }

    @Test
    void deleteAll() {
    }
}