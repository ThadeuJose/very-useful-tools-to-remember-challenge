package com.example.vuttr.tool;

import java.util.List;

public interface ToolDataSource {
    public ToolDataModel save(ToolDataModel model);

    public boolean hasTool(String title);

    public List<ToolDataModel> getAllTool();
}
