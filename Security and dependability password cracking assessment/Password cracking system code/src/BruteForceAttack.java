
/**
 * This class will hold my brute force algorithm and wil crack a random password with the use of its hash code
 * IMPORTANT : Due to the  length of some of these common passwords being tested it can take a long time to see results.
 * on line 52 and 53 you able to test any word you wish to see quicker results.
 **/

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BruteForceAttack extends Main{
    private static StringBuilder Builder = new StringBuilder();// Constructs a string builder with no characters in it and has an initial capacity of 16 characters. Used to construct strings by appending characters together to create words. Currently empty as we havent places anything with the parameter.

    private static String password;// used to store target password for testing.

    public static ArrayList<String> CommonPasswordsHashed = new ArrayList();//Copies elements from CommonPasswordsHashcodes.txt
    //This array will hold the hashed version of our famous passwords, and used to generate a hashed target password.
    public static ArrayList<Character> Dataset = new ArrayList();//Contains characters letter,numbers and special character.
    //This arraylist dataset will be used for the brute force attack by iterating through to use each character within.

    public static File BruteForcePlainText = new File("D:\\Intelli j\\Projects\\Security and dependability Password Cracker\\src\\CommonPasswords.txt");
    public static File BruteForceHashcode = new File("D:\\Intelli j\\Projects\\Security and dependability Password Cracker\\src\\CommonPasswordsHashcodes.txt");
    //Stores the location of the famous password plaintext and hash code text files.
    public static File BruteFile = new File("D:\\Intelli j\\Projects\\Security and dependability Password Cracker\\src\\BruteForceOutput.txt");
    //This will print all the outputs and plaintext string created along with its hashcode.

    public static void main() throws IOException {
        DataSetCreator();//Calls method DataSetCreator which stores the necessary characters within the dataset array.

        Scanner Scan2 = new Scanner(BruteForcePlainText);//Scans the famous plaintext file and hashes each elements and prints them to the hashcode text file .
        Scanner Scan3 = new Scanner(BruteForceHashcode);//Scans the famous password hash code file.
        //These scanners will read both text files so that each string will be stored within their appropriate arrays.

        FileWriter Writer1 = new FileWriter(BruteForceHashcode);//File writer object created with the location of the brute force hash code file.
        PrintWriter Printer = new PrintWriter(Writer1);//Printer object created which will use the previous writer object which tells the printer the location of where its printing its elements.
        ///These objects together will be used to print the hash codes into the hashcode text file.

    while(Scan2.hasNextLine()){
        Printer.println(Main.getMd5(Scan2.next()));//This will printer every word from the plaintext file hashed into common password hashcode file.
    }
    Printer.close();//Close the printer once its finished.

    while(Scan3.hasNextLine()){ //Scans the Common passwords Hashcode file line by line. This while loop means search the the file as long as their is a next element.
            CommonPasswordsHashed.add(Scan3.nextLine());// adds every word to the common passwords hashed arrayList .
    }

        Random RandomNum = new Random();//Creates a new random object.

            int num = RandomNum.nextInt(CommonPasswordsHashed.size());//Generates a random number which is between 0 and the size of the array.
          password = CommonPasswordsHashed.get(num);
          //password =getMd5(""); //Uncomment this line and comment line 51 above to test any word you wish. Make sure its in between the quotation marks.
        System.out.println("\nThe System has picked a random hashed password to be tested by the tool :");

      System.out.println("\n"+"Target password :"+password);

        Scanner scan = new Scanner(System.in);
        System.out.println("\nClick enter to start, and my bruteforce attack will try and crack the target password given");
        String start = scan.nextLine();

    while(true) {// This while loop will run infinitely and will only stop until the condition is met in method iterator which will stop the recursive call and will close the system.
      Builder.append(Dataset.get(1)); //starts with the first element within the data set array and appends it to the string builder object builder.
        for(int i = 0; i < Builder.length()-1; i++) { // outer loop iterates depending on the size of the word (grows over time).
            for(int j = 0; j  < Dataset.size(); j++) {//inner loop iterates through the dataset array.
                Builder.setCharAt(i,Dataset.get(j));//gets each character within the array and adds its to the StringBuilder object called builder. i is the index of the each letter position that the length of the builder is at.
                iterator(i+1 ); //Adds an extra column of iteration if the password is not found.
            }
        }
    }
 }
//This method is used to create the dataset used within the iteration of the strings. This is done to have am efficient arrangement of words instead of a randomised sequence.
public static void DataSetCreator() {
 //This method is designed to create the dataset. This is done by using ascii values to cast them into characters which will be stored into our dataset created.
 int start = 97;//Start of the lower case section i.e 'a'.
 int end =122;//End of the lower case section i.e 'z'.
     int b;
     for (b = 0; b < 3; b++) {
         if (b == 2) {
             start += 15;
             end -= 1; }
 int a;
 for (a = start; a <= end; a++) {
     if (a == end) {//Instead of storing every character we can tell which characters to miss as reducing the amount of character with in the data will increase the time taken to find the password.
            start -= 32;
            end -= 32;
     }Dataset.add((char) a); }}
 //This section adds the special characters to the end of data set.
 int c;
     for(c = 32; c < 126; c++){
         char x = (char) c;
        int d;
        for(d = 0; d < Dataset.size(); d++){
            if(Dataset.contains(x)){
                continue;
            }else{
              Dataset.add(x);
 } } } }

    public static FileWriter Writer;// file write object made.
    static {
        try {
            Writer = new FileWriter(BruteFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 public static PrintWriter Printer = new PrintWriter(Writer);//Printer object made to print everything into the output file.

//This is the iterator method that will perform the iteration of different characters constructing words.
public static void iterator(int i){
     for (int j = 0; j < Dataset.size(); j++) {//Iterates through the dataset array.
          Builder.setCharAt(i, Dataset.get(j));
     //i is the indexed position of the word that's held in builder
     // j is the character. every character that j gets from the dataset array
     //will be in the position of i until its found
     // or increments to a new position where it starts again.
              if (i < Builder.length() - 1) {
     //Once i reaches the size of the builder object it will increase the builder by 1 extra size
     // meaning another character position can be added for testing.
                  iterator(i + 1);
                 }
          String test;//Used to hold target password being tested.
          test = Main.getMd5(Builder.toString());
          System.out.println(Builder.toString());
     //Prints the iteration of character to screen starting
     // Where the first column is the first character within the word and so on.
          Printer.println(Builder + " : " + Main.getMd5(Builder.toString()));
     //Prints both the plaintext and hashed version of the string to the brute force output file.
              if (test.equals(password)) {
     //This will check the build String to the password we are cracking to check they are the same.
                  System.out.println("Password found! \nKeyword:" + Builder + "\nHashcode:" + test);
                  Printer.close();
                  System.exit(0);
     //This method is used to tell the compiler to end execution of the code.
     // Acts as a way to break the recursive call from performing infinitely.
                    }
                }
            }

}