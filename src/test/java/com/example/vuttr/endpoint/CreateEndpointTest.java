package com.example.vuttr.endpoint;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.vuttr.tool.CreatedResponse;
import com.example.vuttr.tool.ToolDataModel;
import com.example.vuttr.tool.ToolDataSource;
import com.example.vuttr.tool.ToolForm;
import com.google.gson.Gson;

@SpringBootTest
@AutoConfigureMockMvc
class CreateEndpointTest {

    private static final String APPLICATION_JSON = "application/json;charset=UTF-8";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ToolDataSource toolDataSource;

    @Test
    void shouldCreatePlanetAndReturnCorrectUrl() throws Exception {
        when(toolDataSource.save(any()))
                .thenReturn(createToolModel(3));

        Gson gson = new Gson();
        String json = gson.toJson(createToolRequest());

        String responseJson = this.mockMvc
                .perform(post("/tools").contentType(APPLICATION_JSON).content(json).accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        CreatedResponse expected = gson.fromJson(responseJson, CreatedResponse.class);
        assertThat(expected.id()).isEqualTo(3);
        assertThat(expected.title()).isEqualTo("ToolTest");
    }

    @Test
    void shouldCreateToolAndReturnCorrectUrl() throws Exception {
        when(toolDataSource.save(any()))
                .thenReturn(createToolModel(4));

        Gson gson = new Gson();
        String json = gson.toJson(createToolRequest());

        this.mockMvc.perform(post("/tools").contentType(APPLICATION_JSON).content(json).accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/4")));
    }

    private ToolDataModel createToolModel(int id) {
        String title = "ToolTest";
        String link = "http://tooltest.com";
        String description = "Test Tool long description";
        List<String> tags = new ArrayList<>(Arrays.asList(
                "organization",
                "planning",
                "collaboration",
                "writing"));
        return new ToolDataModel(id, title, link, description, tags);
    }

    private ToolForm createToolRequest() {
        String title = "ToolTest";
        String link = "http://tooltest.com";
        String description = "Test Tool long description";
        List<String> tags = new ArrayList<>(Arrays.asList(
                "organization",
                "planning",
                "collaboration",
                "writing"));

        return new ToolForm(title, link, description, tags);
    }
}