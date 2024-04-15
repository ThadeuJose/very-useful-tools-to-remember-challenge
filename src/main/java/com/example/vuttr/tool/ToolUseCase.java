package com.example.vuttr.tool;

import java.util.List;
import java.util.stream.Collectors;

public class ToolUseCase {

    private ToolDataSource dataSource;

    public ToolUseCase(ToolDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Result createTool(CreatedRequest request) {
        if (this.dataSource.hasTool(request.title())) {
            return new ErrorRecord(String.format("Resource %s already exists", request.title()));
        }
        ToolDataModel model = this.dataSource.save(fromRequestToDataModel(request));
        return new CreatedToolModel(model.id(), model.title(), model.link(), model.description(), model.tags());
    }

    public List<CreatedToolModel> getAllTools() {
        return dataSource.getAllTool().stream().map(
                elem -> new CreatedToolModel(elem.id(), elem.title(), elem.link(), elem.description(), elem.tags()))
                .collect(Collectors.toList());
    }

    public static ToolDataModel fromRequestToDataModel(CreatedRequest model) {
        return new ToolDataModel(0, model.title(), model.link(), model.description(),
                model.tags());
    }
}
