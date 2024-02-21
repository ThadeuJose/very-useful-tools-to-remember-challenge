package com.example.vuttr.tool;

public class Mapper {

    private Mapper() {
        super();
    }

    public static CreatedRequest fromFormToRequest(ToolForm model) {
        return new CreatedRequest(model.title(), model.link(), model.description(),
                model.tags());
    }

    public static CreatedResponse fromModelToResponse(CreatedToolModel model) {
        return new CreatedResponse(model.id(), model.title(), model.link(), model.description(),
                model.tags());
    }
}
