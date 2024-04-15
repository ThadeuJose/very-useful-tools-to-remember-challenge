package com.example.vuttr.endpoint;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

import com.example.vuttr.tool.GetAllToolsResponse;
import com.example.vuttr.tool.ToolDataModel;
import com.example.vuttr.tool.ToolDataSource;
import com.google.gson.Gson;

@SpringBootTest
@AutoConfigureMockMvc
class GetAllEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ToolDataSource toolDataSource;

    @Test
    void shouldReturnAllItens() throws Exception {
        when(toolDataSource.getAllTool())
                .thenReturn(List.of(
                        createToolModel(1, "Notion"),
                        createToolModel(2, "Obsidian"),
                        createToolModel(3, "Tool 3")));

        String responseJson = this.mockMvc.perform(get("/tools"))
                .andDo(print()).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Gson gson = new Gson();
        GetAllToolsResponse expected = gson.fromJson(responseJson, GetAllToolsResponse.class);
        assertThat(expected.size()).isEqualTo(3);
        assertThat(expected.tools().get(0).title()).isEqualTo("Notion");
        assertThat(expected.tools().get(1).title()).isEqualTo("Obsidian");
        assertThat(expected.tools().get(2).title()).isEqualTo("Tool 3");
    }

    private ToolDataModel createToolModel(int id, String title) {
        String link = "http://tooltest.com";
        String description = "Test Tool long description";
        List<String> tags = new ArrayList<>(Arrays.asList(
                "organization",
                "planning",
                "collaboration",
                "writing"));
        return new ToolDataModel(id, title, link, description, tags);
    }
}
