package com.example.vuttr.tool;

import java.util.List;

public record CreatedRequest(String title, String link, String description, List<String> tags) {
    public CreatedRequest(String title, String link, String description, List<String> tags) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.tags = List.copyOf(tags);
    }
}
