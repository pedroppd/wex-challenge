package com.testewex.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileNotFoundException;
import java.util.*;

@RestController
@RequestMapping("/clientes-wex")
public class WexController {

    @Autowired
    private LogFileProcessor logFileProcessor;

    @GetMapping
    public ResponseEntity<Set<String>> clientesFieis() {
        final List<String> fileNames = List.of("./log-dia1.txt", "./log-dia2.txt");
        final List<User> allUsers = new ArrayList<>();
        fileNames.forEach(fileName -> {
            try {
                var listUsuarios = logFileProcessor.getUserFromFile(fileName);
                allUsers.addAll(listUsuarios);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        final Set<String> users = new HashSet<>();
        final Set<String> userResponse = new HashSet<>();
        allUsers.forEach((user) -> {
            if (!users.add(user.getId())) {
                userResponse.add(user.getCustomerId());
            }
        });
        return ResponseEntity.ok().body(userResponse);
    }
}
