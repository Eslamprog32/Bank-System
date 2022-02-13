/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.system;

import java.io.*;
import java.util.*;

/**
 *
 * @author eslam
 */
public final class  Admin extends Person {

    private String ID;

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public Admin() {
    }

    public Admin( String Name, String Phone, String Address,String passWord,String ID) {
        super(Name, Phone, Address, passWord);
        this.ID = ID;
    }
    /**
     * To modify an Account of user
     * @param Name
     * @param n
     * @param data 
     */
    public void modifyAccount(String Name,int n,String data ){
        client user = new client();
        user.setName(Name);
        user.readFile();
        if(n==1){
            closeAccount(Name);
            user.setName(data);
            user.writeName("Users");
            user.writeData();
        }
        else if(n==2){
            user.setPassWord(data);
            user.writeData();
        }
        else if(n==3){
            user.setPhone(data);
            user.writeData();
        }
        else if(n==4){
            user.setAddress(data);
            user.writeData();
        }
        else if(n==5){
            user.setBalance(data);
            user.writeData();
        }
        else{
            System.out.println("Please Enter A Number From 1 To 5");
        }      
    }
    /**
     * To close an Account
     * @param Name 
     */
    public void closeAccount(String Name) {
        try {
            if(Person.checkAdminOrUser("Users", Name)){
            File userFile = new File(Name + ".txt");
            userFile.deleteOnExit();
            File user = new File("Users.txt");
            ArrayList<String> a = new ArrayList<>();
            Scanner re = new Scanner(user);
            while (re.hasNextLine()) {
                String s = re.nextLine();
                if (!(s.equals(Name))) {
                    a.add(s);
                }
            }
            re.close();
                FileWriter w = new FileWriter(user);
                for (int i = 0; i < a.size(); i++) {
                    w.write(a.get(i) + "\n");
                }
                w.close();
            }
            else{
                System.out.println("The Name Not Found");
            }
        } catch (Exception e){
            System.out.println("The Following Erorr Occurred :"+e.getMessage());
        }
        
    }
    /**
     * To display all the data of the registered accounts in the bank
     */
    public void listOfAccounts() {
        File users = new File("Users.txt");
        try {
            Scanner Reader = new Scanner(users);
            int y = 1;
            while (Reader.hasNextLine()){
                String s = Reader.nextLine();
                client user = new  client();
                user.Name=s;
                user.readFile();
                System.out.print(y+"-");
                         System.out.printf("The User Name : %s\n",user.Name);     
                         System.out.printf("The Password : %s\n",user.passWord); 
                         System.out.printf("The Phone Number : %s\n",user.Phone);     
                         System.out.printf("The Address : %s\n",user.Address);
                         System.out.printf("The Balance : %s\n",user.getBalance());     
                          y++;
                System.out.println("___________________");
                    }
            Reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
    }
    
}
