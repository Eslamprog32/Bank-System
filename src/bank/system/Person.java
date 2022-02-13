package bank.system;

import java.io.*;
import java.util.*;

/**
 *
 * @author eslam
 */
public abstract class Person {

    protected String Name;
    protected String passWord;
    protected String Phone;
    protected String Address;

    public Person(String Name,String Address, String Phone,String passWord) {
        this.Name = Name;
        this.passWord = passWord;
        this.Phone = Phone;
        this.Address = Address;
    }

    public Person() {
    }
    
    public void setName(String Name) {
        this.Name = Name;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    
    /**
     * To record user data and create an account for him
     * @param FileName
     * @param B
     * @return 
     */
    public final void Register(String FileName, String B) {
        boolean ca = false;
        try {
            File userFile = new File(Name + ".txt");
                ca = true;
                FileWriter wirteData = new FileWriter(userFile);
                wirteData.write(Name + "\n" + passWord + "\n" + Phone + "\n" + Address + "\n" + B);
                wirteData.close();
                this.writeName(FileName);
                System.out.println("Successful Reigster Mister "+Name);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
    public final void writeName(String FileName){
        try{
            File myFile = new File(FileName+".txt");
            if(!myFile.createNewFile()){
                Scanner Reader = new Scanner(myFile);
                ArrayList<String> data = new ArrayList<>();
                data.add(Name);
                while(Reader.hasNextLine()){
                    data.add(Reader.nextLine());
                }
                Collections.sort(data);
                Reader.close();
                FileWriter wirteData = new FileWriter(myFile);
                for(int i = 0;i<data.size();i++){
                    wirteData.write(data.get(i)+"\n");
                }
                wirteData.close();
            }
            else{
                FileWriter wirteData = new FileWriter(myFile);
                wirteData.write(Name+"\n");
                wirteData.close();
            }
        }catch(FileNotFoundException e){
            System.out.println("File Not Found");
        }
        catch (IOException e){
            System.out.println("An error occurred.");
        }
    }
    /**
     * To open a user account
     * @return 
     */
    public final boolean Login() {
        boolean ca = false;
        File userFile = new File(Name + ".txt");
        int c = 0;
        try {
            if (!userFile.createNewFile()) {
                Scanner reader = new Scanner(userFile);
                int x = 0;
                while (x < 2) {
                    String s = reader.nextLine();
                    if (x == 1) {
                        if (s.equals(passWord)) {
                            c++;
                        }
                    }
                    x++;
                }
                if (c == 1) {
                    ca = true;
                } else {
                    ca = false;
                    System.out.println("Password incorrect");
                }
                reader.close();
            } else {
                System.out.println("Name Not Found");
                userFile.delete();
            }

        } catch (IOException ex) {

        }
        return ca;
    }
    public static final boolean checkAdminOrUser(String FileName,String Name){
            boolean checkAdminOrUser = false;
            File myFile = new File(FileName+".txt");
            try (Scanner Reader = new Scanner(myFile)) {
                while (Reader.hasNextLine()) {
                    String c = Reader.nextLine();
                    if(Name.compareTo(c)<0){
                        break;
                    }
                    if (Name.equals(c)) {
                        checkAdminOrUser = true;
                        break;
                    }
                }
                Reader.close();
            } catch (FileNotFoundException e) {
            }
            return checkAdminOrUser;
    }
}