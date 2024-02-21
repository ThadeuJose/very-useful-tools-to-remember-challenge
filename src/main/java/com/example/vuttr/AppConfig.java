package com.example.vuttr;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.vuttr.database.FakeDataSource;
import com.example.vuttr.tool.ToolDataSource;
import com.example.vuttr.tool.ToolUseCase;

@Configuration
public class AppConfig {
    @Bean
    public ToolUseCase toolUseCase(ToolDataSource dataSource) {
        return new ToolUseCase(dataSource);
    }

    @Bean
    public ToolDataSource dataSource() {
        return new FakeDataSource();
    }

}
