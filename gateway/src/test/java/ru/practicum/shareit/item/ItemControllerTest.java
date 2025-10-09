package ru.practicum.shareit.item;

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
import ru.practicum.shareit.item.dto.ItemDto;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest

@AutoConfigureMockMvc
class ItemControllerTest {

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
    void createItemWithValidPayloadReturnsOk() throws Exception {
        ItemDto dto = ItemDto.builder()
                .name("Drill")
                .description("Cordless drill")
                .available(true)
                .requestId(5L)
                .build();

        mockMvc.perform(post("/items")
                        .header(USER_HEADER, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void createItemWithInvalidBodyReturnsBadRequest() throws Exception {
        ItemDto dto = ItemDto.builder()
                .name("")
                .description("")
                .available(true)
                .build();

        mockMvc.perform(post("/items")
                        .header(USER_HEADER, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void getItemByIdReturnsOk() throws Exception {
        mockMvc.perform(get("/items/8")
                        .header(USER_HEADER, "6"))
                .andExpect(status().isOk());
    }

    @Test
    void getOwnerItemsReturnsOk() throws Exception {
        mockMvc.perform(get("/items")
                        .header(USER_HEADER, "11"))
                .andExpect(status().isOk());
    }

    @Test
    void searchItemsReturnsOk() throws Exception {
        mockMvc.perform(get("/items/search")
                        .param("text", "drill"))
                .andExpect(status().isOk());
    }

    @Test
    void getByIdWithInvalidUserHeaderReturnsBadRequest() throws Exception {
        mockMvc.perform(get("/items/1")
                        .header(USER_HEADER, "0"))
                .andExpect(status().isBadRequest());
    }
}