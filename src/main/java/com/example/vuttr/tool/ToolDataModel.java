package com.example.vuttr.tool;

import java.util.List;

public record ToolDataModel(int id, String title, String link, String description, List<String> tags) {
    public ToolDataModel(int id, String title, String link, String description, List<String> tags) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.description = description;
        this.tags = List.copyOf(tags);
    }
}
