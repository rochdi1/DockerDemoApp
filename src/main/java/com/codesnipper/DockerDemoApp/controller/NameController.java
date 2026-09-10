package com.codesnipper.DockerDemoApp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/names")
public class NameController {

    @Value("${app.file.path}")
    private String filePath;

    // POST /names
    @PostMapping
    public String addName(@RequestBody String name) throws IOException {

        File file = new File(filePath);

        // create directory if not exists
        file.getParentFile().mkdirs();

        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(name + System.lineSeparator());
        }

        return "Name saved successfully";
    }

    // GET /names
    @GetMapping
    public List<String> getNames() throws IOException {
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            return List.of();
        }

        return Files.readAllLines(path);
    }
    // DELETE /names/{name}
    @DeleteMapping("/{name}")
    public String deleteName(@PathVariable String name) throws IOException {

        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            return "File does not exist";
        }

        List<String> updatedNames =
                Files.readAllLines(path)
                        .stream()
                        .map(String::trim)                 // 🔥 very important
                        .filter(n -> !n.equalsIgnoreCase(name.trim()))
                        .toList();

        Files.write(path, updatedNames,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.CREATE);

        return "Name removed successfully";
    }

}
