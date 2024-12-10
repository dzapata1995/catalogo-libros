package com.dzapata.literatura.config;

import com.dzapata.literatura.service.ConsoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final ConsoleService consoleService;

    public ConsoleRunner(ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    @Override
    public void run(String... args) throws Exception {
        consoleService.start();
    }
}
