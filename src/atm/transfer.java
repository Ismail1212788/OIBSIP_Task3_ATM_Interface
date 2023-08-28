/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atm;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author kali-i
 */
public class transfer {
 
    
 
    public void transferAmount(String senderId, String receiverId, int amount) throws IOException {
        File file = new File("Users.txt");
        if (!file.exists()) {
            System.out.println("Accounts file not found.");
            return;
        }

        Map<String, String[]> accounts = new HashMap<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] accountInfo = scanner.nextLine().split(":");
                accounts.put(accountInfo[0], accountInfo);
            }
        }

        if (!accounts.containsKey(receiverId)) {
            System.out.println("Receiver account not found.");
            return;
        }

        String[] senderInfo = accounts.get(senderId);
        String[] receiverInfo = accounts.get(receiverId);

        String senderPin = senderInfo[1];
        int senderBalance = Integer.parseInt(senderInfo[2]);

        if(amount<0){
             System.out.println("Amount must be greater than 0");
            return;
        }
        else if (senderBalance < amount) {
            System.out.println("Insufficient balance in sender's account.");
            return;
        }
       

        int receiverBalance = Integer.parseInt(receiverInfo[2]);

        senderBalance -= amount;
        receiverBalance += amount;

        senderInfo[2] = String.valueOf(senderBalance);
        receiverInfo[2] = String.valueOf(receiverBalance);

        try (PrintWriter writer = new PrintWriter(file)) {
            for (String[] account : accounts.values()) {
                writer.println(String.join(":", account));
            }
        }

        System.out.println("Amount transferred successfully.");
        transactionHistory.recordTransferTransaction(senderId, receiverId, amount);
    }
}
