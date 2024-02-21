package com.example.vuttr.database;

import com.example.vuttr.tool.ToolDataModel;
import com.example.vuttr.tool.ToolDataSource;

public class FakeDataSource implements ToolDataSource {

    @Override
    public ToolDataModel save(ToolDataModel model) {
        return new ToolDataModel(3, model.title(), model.link(), model.description(), model.tags());
    }

}
