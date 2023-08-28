/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author kali-i
 */
public class transactionHistory {
    
      static File file = new File("thistory.txt");
      public static void recordTransferTransaction(String senderId, String receiverId, int amount) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = dateFormat.format(new Date());

        String transactionRecord = senderId + " -> Transfered to " + receiverId + " | Amount: " + amount + " | " + timestamp;

        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            writer.println(transactionRecord);
        }
      }
      
        public static void recordDepositTransaction(String senderId, int amount) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = dateFormat.format(new Date());

        String transactionRecord = senderId + " -> Deposited : " + " | Amount: " + amount + " | " + timestamp;

        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            writer.println(transactionRecord);
        }
      }
        
        public static void recordWithdrawTransaction(String senderId, int amount) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = dateFormat.format(new Date());

        String transactionRecord = senderId + " -> Withdrawn : " + " | Amount: " + amount + " | " + timestamp;

        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            writer.println(transactionRecord);
        }
      }  
        
        public void displayTransactionHistory(String userId) throws IOException {

            try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String transactionRecord = scanner.nextLine();
                if (transactionRecord.contains(userId)) {
                    System.out.println(transactionRecord);
                }
            }
        }
        }
}
