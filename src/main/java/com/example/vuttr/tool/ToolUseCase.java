package com.example.vuttr.tool;

public class ToolUseCase {

    private ToolDataSource dataSource;

    public ToolUseCase(ToolDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public CreatedToolModel createTool(CreatedRequest request) {
        ToolDataModel model = this.dataSource.save(fromRequestToDataModel(request));
        return new CreatedToolModel(model.id(), model.title(), model.link(), model.description(), model.tags());
    }

    public static ToolDataModel fromRequestToDataModel(CreatedRequest model) {
        return new ToolDataModel(0, model.title(), model.link(), model.description(),
                model.tags());
    }
}
