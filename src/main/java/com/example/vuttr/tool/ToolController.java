package com.example.vuttr.tool;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "tools")
public class ToolController {

    private ToolUseCase useCase;

    public ToolController(ToolUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping(consumes = "application/json;charset=UTF-8")
    public ResponseEntity<CreatedResponse> createTool(@RequestBody ToolForm request) {
        CreatedToolModel model = useCase.createTool(Mapper.fromFormToRequest(request));
        CreatedResponse response = Mapper.fromModelToResponse(model);
        return ResponseEntity.created(getUri(response.id())).body(response);
    }

    private URI getUri(int id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
