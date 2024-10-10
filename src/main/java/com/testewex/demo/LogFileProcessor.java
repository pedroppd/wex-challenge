package com.testewex.demo;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogFileProcessor {

    public List<User> getUserFromFile(String nomeArquivo) throws FileNotFoundException {
        List<User> users = new ArrayList<>();
        try {
            FileReader arq = new FileReader(nomeArquivo);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            int lineNumber = 0;
            while (linha != null) {
                if (lineNumber > 0) {
                    var lineSplited = linha.split(",");
                    var user = new User(lineSplited[0], lineSplited[1], lineSplited[2]);
                    users.add(user);
                }
                linha = lerArq.readLine();
                lineNumber++;
            }
            arq.close();
            return users;
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
            throw new FileNotFoundException("Erro na abertura do arquivo");
        }
    }
}
