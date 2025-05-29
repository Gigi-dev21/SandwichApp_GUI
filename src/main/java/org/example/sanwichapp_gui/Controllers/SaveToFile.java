package org.example.sanwichapp_gui.Controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.sanwichapp_gui.Classes.Transaction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SaveToFile {
    public void saveTransactionAsJson(Transaction newTransaction) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("transactions.json");
        List<Transaction> transactions = new ArrayList<>();

        try {
            // Check if file exists and is not empty before reading
            if (file.exists() && Files.size(Paths.get(file.getPath())) > 0) {
                transactions = mapper.readValue(file, new TypeReference<List<Transaction>>() {
                });
            }
            // Add the new transaction
            transactions.add(newTransaction);
            // Write the updated list back to the file
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, transactions);
        } catch (IOException e) {
            System.out.println("Error saving transaction to JSON: " + e.getMessage());
        }

    }
}
