/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package atm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author kali-i
 */
public class ATM {

  Scanner sc=new Scanner(System.in);

    public static void decorate(){
        System.out.println("*********************************************************");
        System.out.println("\tOASIS INFOBYTE INTERNSHIP TASK");
        System.out.println("*********************************************************");
        System.out.println("*\t\t\t\t\t\t\t*");
        System.out.println("*\t\t\t\t\t\t\t*");
        System.out.println("*********************************************************");
        
         
    }
    
    public static void AtmInterface(){
        System.out.println("\t\t   ATM INTERFACE");
        System.out.println("*********************************************************"); 
        System.out.println("1. Transaction History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
        
         
    }
    
    public boolean login(){
        System.out.println("Enter your login ID");
        String userId=sc.nextLine();
        System.out.println("Enter your PIN Code");
        String pin=sc.nextLine();        
         try (BufferedReader reader = new BufferedReader(new FileReader("Users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 3) {
                    String storedUserId = parts[0];
                    String storedPin = parts[1];

                    if (userId.equals(storedUserId) && pin.equals(storedPin)) {
                        System.out.println("User Verified. Balance: " + parts[2]);
                        withdraw.id=storedUserId;
                        return true;
                    }

       
                }

            }
            System.out.println("Incorrect UserId or Password Try Again");
           
        } catch (IOException e) {
            System.err.println("Error reading user accounts file: " + e.getMessage());
        }
        return false;
       
    }
    
    public int getInput() throws IOException{
        ATM.AtmInterface();
        System.out.println("Enter your choice ");
        Scanner sc1=new Scanner(System.in);
        int option=sc1.nextInt();
            switch (option) {
                
                case 1 -> {
                    System.out.println("\nTransaction History\n");
                    transactionHistory a1=new transactionHistory();
                    a1.displayTransactionHistory(withdraw.id);
                    System.out.println("\n");
                }
                
                case 2 -> {
                    withdraw a2=new withdraw();
                    a2.withdrawAmount();
                }
                
                case 3 -> {
                Deposit a3=new Deposit();
                System.out.println("Enter Amount to deposit :");
                int a=sc.nextInt();
                a3.depositAmount(withdraw.id, a);
                }
                
                case 4 -> {
                    transfer a4=new transfer();
                    Scanner s1=new Scanner(System.in);
                    System.out.println("Enter Receiver Account Number");
                    String ano=s1.nextLine();
                    System.out.println("Enter Amount :");
                    int amo=s1.nextInt();
                    a4.transferAmount(withdraw.id, ano, amo);
                }
                case 5 -> {
                    quit a5=new quit();
                }
                default -> {
                    System.out.println("Please Input the value between 1 - 5\n");
                }
            }
         
        return -1;
    }
    public static void main(String[] args) throws IOException {
        
        ATM.decorate();
        ATM a=new ATM();
        boolean x=true;
        while(x){
        boolean chk=a.login();  
        if(chk==true){
        x=false;
        }else{
           chk=a.login();  
           if(chk==true){
        x=false;}
        }
        }
        while(true){
        a.getInput();
        }
}
 }
