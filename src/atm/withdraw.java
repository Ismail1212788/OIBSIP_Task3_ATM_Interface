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
import java.util.Scanner;

public class withdraw {
    Scanner sc=new Scanner(System.in);
    int totalbalance=-1;
    static String id="";
    String filename = "Users.txt";
    String pin="";
    
    public withdraw(){
        getBalance(id);
    }
    
    public void withdrawAmount() throws IOException{
        System.out.println("Enter your amount to withdraw");
        int amount=sc.nextInt();
        if(amount>0){
            
            if(totalbalance>=amount){
                totalbalance-=amount;
                System.out.println(amount+" has been withdrawn Successfully Remaining balance "+totalbalance);
                updateRecordByID();
                transactionHistory.recordWithdrawTransaction(withdraw.id, amount);
            }
            else{
                System.out.println("Your Account balance is low");
            }
        }
        else{
            System.out.println("Enter your amount greater than 0");
        }
        
        
    }
    
    public void getBalance(String id){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 2 && parts[0].equals(id)) {
                    totalbalance=Integer.parseInt(parts[2]);
                    pin=parts[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void updateRecordByID() {
        ArrayList<String> updatedRecords = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 2 && parts[0].equals(id)) {
                    updatedRecords.add(id+":"+pin+":"+String.valueOf(totalbalance));
                } else {
                    updatedRecords.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String record : updatedRecords) {
                writer.write(record);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
