package com.example.vuttr.tool;

public interface ToolDataSource {
    public ToolDataModel save(ToolDataModel model);

    public boolean hasTool(String title);
}
