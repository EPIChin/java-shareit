package ru.practicum.shareit.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.practicum.shareit.request.dto.ItemRequestCreateDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ItemRequestControllerTest {

    private static final String USER_HEADER = "X-Sharer-User-Id";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void createRequestWithValidPayloadReturnsOk() throws Exception {
        ItemRequestCreateDto dto = ItemRequestCreateDto.builder()
                .description("Need a drill")
                .build();

        mockMvc.perform(post("/requests")
                        .header(USER_HEADER, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void createRequestWithBlankDescriptionReturnsBadRequest() throws Exception {
        ItemRequestCreateDto dto = ItemRequestCreateDto.builder()
                .description("")
                .build();

        mockMvc.perform(post("/requests")
                        .header(USER_HEADER, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getOwnRequestsReturnsOk() throws Exception {
        mockMvc.perform(get("/requests")
                        .header(USER_HEADER, "3"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllRequestsReturnsOk() throws Exception {
        mockMvc.perform(get("/requests/all")
                        .header(USER_HEADER, "5"))
                .andExpect(status().isOk());
    }

    @Test
    void getRequestByIdReturnsOk() throws Exception {
        mockMvc.perform(get("/requests/9")
                        .header(USER_HEADER, "7"))
                .andExpect(status().isOk());
    }
}