package com.example.vuttr.tool;

import java.util.List;

public record GetAllToolsResponse(List<CreatedToolModel> tools) {

    public int size() {
        return tools.size();
    }

}
