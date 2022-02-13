
package bank.system;

import java.io.*;
import java.util.*;

/**
 *
 * @author eslam
 */
public final class client extends Person {

    private String Balance;
    private String Deposit;
    private String withDrawAmount;
   
    public client() {
    }

    public client(String Name,String Address , String Phone, String passWord,String Balance) {
        super(Name,Address, Phone,passWord);      
        this.Balance = Balance;
    }
    
    public void setBalance(String Balance) {
        if(Long.parseLong(Balance)>=0){
        this.Balance = Balance;
        }
    }

    public void setDeposit(String Deposit) {
        if(Long.parseLong(Deposit)>0){
        this.Deposit = Deposit;
        }
        else{
            this.Deposit = "0";
            System.out.println("Please Enter correct value");
        }
    }

    public void setWithDrawAmount(String withDrawAmount) {
        if(Long.parseLong(withDrawAmount)>0){
        this.withDrawAmount = withDrawAmount;
        }
        else {
            this.withDrawAmount="0";
            System.out.println("Please Enter correct value");
        }
    }

    public String getBalance() {
        return Balance;
    }

    public String getWithDrawAmount() {
        return withDrawAmount;
    }
    public String getDeposit(){
        return Deposit;
    }
    /**
     * To read previously recorded user data
     * @return 
     */
    public Boolean readFile() {
        File myFile = new File(Name + ".txt");
        boolean ca = false;
        try {
            if(!myFile.createNewFile()){
                ca = true;
               Scanner Reader = new Scanner(myFile);
               int x = 0;
            while (Reader.hasNextLine()) {
                if (x == 0) {
                    Name = Reader.nextLine();
                }
                if (x == 1) {
                    passWord = Reader.nextLine();
                }
                if (x == 2) {
                    Phone = Reader.nextLine();
                }
                if (x == 3) {
                    Address = Reader.nextLine();
                }
                if (x == 4) {
                    Balance = Reader.nextLine();
                }
                x++;
            }
            Reader.close();
            }else{
                myFile.deleteOnExit();
            }
        }catch(IOException e ){
            System.out.println("An error occurred.");
        }
        
        return ca;
    }
    public void writeData(){
        File newFile = new File(Name + ".txt");
        try {
            FileWriter newWriter = new FileWriter(newFile);
            newWriter.write(Name + "\n" + passWord + "\n" + Phone + "\n" + Address + "\n" + Balance);
            newWriter.close();
        } catch (IOException ex) {
            System.out.println("Error");
        }
    }
    /**
     * To find out the user's balance
     */
    public void balanceEnquiry() {
        readFile();
        System.out.println("Your Balance = " + Balance);
    }
    /**
     * To withdraw an amount from the balance
     */
    public void withDrawAmount() {
        readFile();
        Long WDA = Long.parseLong(withDrawAmount);
        Long B = Long.parseLong(Balance);
        if (B >= WDA) {
            Balance = String.valueOf(B - WDA);
            System.out.println("Successful process Your Balance = "+Balance);
        }
        else{
            System.out.println("Falied process Your Balance = "+Balance);
        }
       writeData();
    }
    /**
     * To deposit an amount in the balance
     */
    public void depositAmount() {
        readFile();
        Long WDA = Long.parseLong(Deposit);
        Long B = Long.parseLong(Balance);
        Balance = String.valueOf(B + WDA);
        System.out.println("Successful process Your Balance = "+Balance);
        writeData();
    }
    public void transferFromHisAccountToAntherAccount(String Name,String Amount){
        client user = new client();
        user.setName(Name);
        if(Person.checkAdminOrUser("Users", Name)){
        user.readFile();
        this.readFile();
        if(Long.parseLong(Balance)>=Long.parseLong(Amount)){
            this.Balance = String.valueOf(Long.parseLong(Balance)-Long.parseLong(Amount));
            user.Balance = String.valueOf(Long.parseLong(user.Balance)+Long.parseLong(Amount));
            System.out.printf("Successful Process Your Balance = %s his Balance = %s \n",this.Balance,user.Balance);
             user.writeData();
             this.writeData();
        }
        else{
            System.out.println("Faild Process Because Balance Less Than Amount");
        }
        }
        else{
            System.out.println("The name does not exist");
        }
    }
}