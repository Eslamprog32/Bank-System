package bank.system;

import java.util.*;
import java.io.*;

/**
 *
 * @author eslam
 */
public class BankSystem {

    public static void main(String[] args) { 
        boolean ma = false;
        int RL = 0;
        do{
        Scanner input = new Scanner(System.in);
        ma = false;
        boolean in;
        do{
             in = false;
        try{
        System.out.println("1-Register\n2-Login");
        RL = input.nextInt();
        }catch(Exception e){
            System.out.println("Please Enter 1 or 2");
            input.next();
            in = true;
        }
        }while(in);
        /**
         * Used to record data and create an account
         */
        if (RL==1) {
            boolean IN;
            int COA = 0;
            do{System.out.println("1-Admin\n2-client");
            IN = false;
            try{
                 COA = input.nextInt();
            }catch(InputMismatchException e){
                input.next();
                System.out.println("Please Enter 1 or 2");
                IN = true;
            }
            }while(IN);
            /**
             * To Register as a Client
             */
            if (COA==2) {
                String data[] = RegisterUser("client");
                client user = new client(data[0], data[1], data[2], data[3], data[4]);
                user.Register("Users", user.getBalance());
                /**
                 * To Register as a Admin
                 */
            } else if (COA==1) {
                String data[] = RegisterUser("Admin");
                Admin user = new Admin(data[0], data[1], data[2], data[3], data[4]);
                user.Register("Admins", user.getID());  
            } else {
                System.out.println("Error Not Found");
            }
        }

        /**
         * To open an account
         */
        else if (RL==2) {
            input.nextLine();
            System.out.println("Enter Your Name");
            
            String s = input.nextLine();
            boolean checkAdmin=Person.checkAdminOrUser("Admins",s);
            /**
             * To Check user Admin Or client
             */
            if (checkAdmin) {
                Admin user = new Admin();
                user.setName(s);
                System.out.println("Enter your Password");
                user.setPassWord(input.nextLine());
                if (user.Login()) {
                    System.out.println("Welcome to your Account");
                    boolean re = false;
                    do{   
                    System.out.println("1-Add New Account"
                            + "\n2-Close An Account"
                            + "\n3-List of Accounts"
                            + "\n4-Modify An Account");
                    int n = input.nextInt();
                    input.nextLine();
                    if (n == 1) {
                        String data[] = RegisterUser("client");
                        client newUser = new client(data[0], data[1], data[2], data[3], data[4]);
                        newUser.Register("Users", newUser.getBalance());
                    } else if (n == 2) {
                        System.out.println("Enter his Name");
                        user.closeAccount(input.nextLine());
                    } else if (n == 3) {
                        user.listOfAccounts();
                    } else if (n == 4) {
                        boolean c = false;
                        String Name ="";
                        do {
                            System.out.println("Enter his Name");
                             Name = input.nextLine();
                            c = Person.checkAdminOrUser("Users", Name);
                            if (!c) {
                                System.out.println("The Name is Not Found");
                            }
                        } while (!c);
                        System.out.println("1-User Name \n"
                                + "2-Password\n"
                                + "3-Phone Number\n"
                                + "4-Address\n"
                                + "5-Balance");
                        int num = input.nextInt();
                        input.nextLine();
                        String dataUser = "";
                        if(num==1){
                            System.out.println("Enter New Name : ");
                            dataUser = input.nextLine();
                        }
                        else if(num==2){
                            System.out.println("Enter New Password : ");
                            dataUser = input.nextLine();
                        }
                        else if(num==3){
                            System.out.println("Enter New PhoneNumber : ");
                            dataUser = input.nextLine();
                        }
                        else if(num==4){
                            System.out.println("Enter New Address : ");
                            dataUser = input.nextLine();
                        }
                        else if(num==5){
                            do{System.out.println("Enter New Balance : ");
                            dataUser = input.nextLine();
                            }while(Long.parseLong(dataUser)<0);
                        }
                        user.modifyAccount(Name, num, dataUser);
                        System.out.println("Successful Process");
                    }
                        System.out.println("To return to main page press 1 To logout press 2");
                        int N = input.nextInt();
                        
                        if(N==1)
                            re = true;
                        else 
                            re = false;
                }while(re);
                    
                }
                    
            } /**
             * user is Client
             */
            else {
                client user = new client();
                user.setName(s);
                System.out.println("Enter your Password");
                user.setPassWord(input.nextLine());
                if (user.Login()) {
                        int n = 0;
                        System.out.println("Welcome to your Account");
                        boolean re = false;
                        do{
                        System.out.println("1-balanceEnquiry\n"
                                + "2-withDrawAmount"
                                + "\n3-depositAmount"
                                + "\n4-transferToAnotherAccount");
                        n = input.nextInt();
                        input.nextLine();
                        if (n == 1) {
                            user.balanceEnquiry();
                        } else if (n == 2) {
                            do{
                             try{
                            System.out.println("Enter With Draw Amount");
                            user.setWithDrawAmount(input.nextLine());
                            }catch(NumberFormatException ex){
                                user.setWithDrawAmount("0");
                            }
                            }while(Long.parseLong(user.getWithDrawAmount())<=0);
                              user.withDrawAmount();    
                        } else if (n == 3) {                           
                            do{
                                try{
                            System.out.println("Enter Deposit Amount");
                            user.setDeposit(input.nextLine());
                            }catch (NumberFormatException ex){
                                user.setDeposit("0");
                            }
                            }while(Long.parseLong(user.getDeposit())<=0);
                            user.depositAmount();
                            
                        } else if (n == 4) {
                            String Name ="";
                            String Amount ="";
                            do{
                                try{
                                System.out.println("Enter his Name");
                            Name =input.nextLine();
                            System.out.println("Enter Amount");
                              Amount= input.nextLine();
                              Long.parseLong(Amount);
                            }catch(NumberFormatException ex){
                                    System.out.println("Please Enter Correct Value");
                                Amount = "0";
                            }
                            }while(Long.parseLong(Amount)<=0);
                            user.transferFromHisAccountToAntherAccount(Name,Amount);
                        }
                        System.out.println("To return to main page press 1 To logout press 2");
                        int N = input.nextInt();
                        
                        if(N==1)
                            re = true;
                        else 
                            re = false;
                        }while(re);
                }
            }
        }
            System.out.println("To return to the home page Press 1 To Logout Press 2");
            int N = input.nextInt();
            if(N==1)
                ma = true;
        }while(ma);
    }

    public static String[] RegisterUser(String s) {
        Scanner input = new Scanner(System.in);
        String data[] = new String[5];
        boolean c1 = false;
        boolean c2 = false;
        do{System.out.println("Enter Your Name");
        data[0] = input.nextLine();
         c1= Person.checkAdminOrUser("Users",data[0]);
         c2= Person.checkAdminOrUser("Admins",data[0]);
         if(c1||c2){
             System.out.println("The Name is Already Exists");
         }
        }while(c1||c2);
        System.out.println("Enter your Address");
        data[1] = input.nextLine();
        System.out.println("Enter your phone number");
        data[2] = input.nextLine();
        System.out.println("Enter New Password");
        data[3] = input.nextLine();
        if (s.equals("client")) {
            
            do{
            try{
            System.out.println("Minimum balance must be greater than or equal to 1000");
            System.out.println("Enter your Balance : ");
            data[4] = input.nextLine();
            }catch (NumberFormatException ex){
                data[4]="0";
                System.out.println("Please Enter correct value ");
            }
            }while(Long.parseLong(data[4])<1000);
            
        } else {
            System.out.println("Enter your ID");
            data[4] = input.nextLine();
        }    
        return data;
    }
}
