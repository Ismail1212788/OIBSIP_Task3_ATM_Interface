/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author kali-i
 */
public class Deposit {
    
      public  void depositAmount(String userId, int amount) {
        String filePath = "Users.txt";

        try {
            // Read the existing account data from the file
            ArrayList<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();

            // Find the user's account and update the balance
            for (int i = 0; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(":");
                String existingUserId = parts[0];
                int balance = Integer.parseInt(parts[2]);

                if (existingUserId.equals(userId)) {
                    if(amount>0){
                    balance += amount;
                    lines.set(i, existingUserId + ":" + parts[1] + ":" + balance);
                    System.out.println("Amount deposited successfully.");
                    transactionHistory.recordDepositTransaction(userId, amount);
                    }
                    else{
                        System.out.println("Please enter amount greater than 0");
                    }
                    break;
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            writer.close();


        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

