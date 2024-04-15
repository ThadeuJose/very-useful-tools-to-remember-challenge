package com.example.vuttr.database;

import java.util.Collections;
import java.util.List;

import com.example.vuttr.tool.ToolDataModel;
import com.example.vuttr.tool.ToolDataSource;

public class FakeDataSource implements ToolDataSource {

    @Override
    public ToolDataModel save(ToolDataModel model) {
        return new ToolDataModel(3, model.title(), model.link(), model.description(), model.tags());
    }

    @Override
    public boolean hasTool(String title) {
        return false;
    }

    @Override
    public List<ToolDataModel> getAllTool() {
        return Collections.emptyList();
    }

}
