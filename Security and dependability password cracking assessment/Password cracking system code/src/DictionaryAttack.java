
/**
 * This class is designed to hold my dictionary attack algorithm which will frind ten random passwrods with the use of their hash code.
 **/

import java.io.*;
import java.util.*;

public class DictionaryAttack extends Main {
    public static String test;//Contains the target passwords to be tested.

public static void main() throws IOException {
  Random RandomNum = new Random();//Creating RandomNum object from class random.

  //These arraylists are used for the collection of elements from the text file.
  ArrayList<String> DictionaryPlaintext = new ArrayList();//Copy elements from Dictionary.txt.
  //The plaintext array will be used to iterate through every word during the attack.
  ArrayList<String> DictionaryHashcode = new ArrayList();//Copy elements from DictionaryHashcode.txt.
  //The hashcode array will be used to generate 10 random target passwords to be tested.
  //These arrays are used to create and store the target passwords and their results.
  ArrayList<String> TargetPasswords = new ArrayList();//Store all the hashed target password the system will test.
  ArrayList<String> Cracked = new ArrayList();//Stores all the hashed cracked target passwords details.

  File DictionaryPlain = new File("D:\\Intelli j\\Projects\\Security and dependability Password Cracker\\src\\Dictionary.txt");
  //Used to tell the compiler the location of where the dictionary plaintext file is stored and to be read from.
  File DictionaryHash = new File("D:\\Intelli j\\Projects\\Security and dependability Password Cracker\\src\\DictionaryHashcode.txt");
  //Used to tell the compiler the location of where the dictionary hash code file is stored and to be read from.
  File output = new File("D:\\Intelli j\\Projects\\Security and dependability Password Cracker\\src\\DictionaryOutput.txt");
  //Used to tell the compiler the location of where the the output file for the compiler to print too.
  //This will print out the iterated elements along with their hash codes to a text file which can be accessed from the directory folder.

  //This next section is designed to provide object which help with reading and writing elements to and from a text file.
  Scanner Scan1 = new Scanner(DictionaryPlain);//This will be used to scan the dictionary text file.
  //This will be used to add the elements form the plaintext file into the plaintext array for later use.
  Scanner Scan2 = new Scanner(DictionaryPlain);//This will be used to scan the Dictionary hash code text file.
  //This will be used to hash evey element within the plaintext file and then print that hash code into the hashed text file.
  FileWriter Writer1 = new FileWriter(DictionaryHash);//This will be used to the tell the location of where the print2 object to print.
  PrintWriter Printer1 = new PrintWriter(Writer1);//This will be used to print the iterated elements produced by the tool into the Dictionary output.
  Scanner Scan3 = new Scanner(DictionaryHash);//this will be used to scan the dictionaryHashed text file.
  //This will scan the elements of the hashed text file and add them to the hashed arraylist.
    while(Scan1.hasNextLine()){ //Scans the Dictionary.txt file line by line. This while loop means search the the file as long as their is a next element.
            DictionaryPlaintext.add(Scan1.next());// adds every plaintext word to the arrayList DictionaryPlaintext.
        }
    while(Scan2.hasNextLine()){
            Printer1.println(getMd5(Scan2.next())); //This hashes the plaintext elements and adds them to the hashed text file.
        }
        Printer1.close();//Closes printer has it done being used.
    while(Scan3.hasNextLine()){ //Scans the DictionaryHashcode.txt file line by line. This while loop means search the the file as long as their is a next element.
            DictionaryHashcode.add(Scan3.nextLine());//adds every Hash code to the arrayList DictionaryHashcode.
        }

    //This section will be generating the 10 random passwords for the dictionary attack to crack
    int a;
    for(a = 0; a <=9;   a++){//Adds a random hashed target per iteration.
       int num = RandomNum.nextInt(DictionaryHashcode.size());//Generates a random number which is between 0 and the size of the array.
          TargetPasswords.add(DictionaryHashcode.get(num));
          //gets that random number generated and adds the element within that position to the array
         }
    //Prints to screen the 10 hash codes being tested
    System.out.println("\nThe System has picked 10 random hashed passwords to be tested by the tool below are their hash codes:");
    int b;
    for(b =0; b < TargetPasswords.size();   b++){//Prints each element within the target password array which are gonna be tested.
         test = TargetPasswords.get(b);//sets keyword to a random index within the hash code array which will pick a random hashcode to be tested.
         System.out.println("\n"+(b+1)+": "+test);
        }

    Scanner scan = new Scanner(System.in);//New scanner object created.
    System.out.println("\nClick enter to start, and my Dictionary attack will try and crack the keyword given");
    String start = scan.nextLine();//Once the user interacts with the input it window it will start the iteration of the tool.

    FileWriter Writer2 = new FileWriter(output);//Creates Writer1 object from the write class. This will be ise to the tell the location of where the print1 object to print.
    PrintWriter Printer2 = new PrintWriter(Writer2);//This creates Printer1 object from the printer class. This will be used  print the hash code of the words from the dictionary text file.
    //These objects are used to scan the dictionary text file and to store the hash codes elements once the plaintext versions have been hashed.

  int j;
  for(j =0; j < TargetPasswords.size(); j++) {//Outer loop will iterate to test each target password created.
      test = TargetPasswords.get(j);//Sets test to index target password stored.
  int i;
  for (i = 0; i < DictionaryPlaintext.size(); i++) {
     //Inner loop performs the iteration attack using the dictionary text file.
     System.out.println(DictionaryPlaintext.get(i));//Prints the tested elements from the dictionary file.
     Printer2.println(DictionaryPlaintext.get(i) + ":" + getMd5(DictionaryPlaintext.get(i)));
     //Prints the plaintext word of each element followed by they're hashcode into DictionaryOutput.txt.
     if(getMd5(DictionaryPlaintext.get(i)).equals(test)) {
         //Compares the elements hashed value to the targets hashed value.
         Cracked.add("Keyword:" + DictionaryPlaintext.get(i) + "\nHashcode:" + test);
         //Add details into cracked arraylist.
         break;//break inner loop.
     } }
    //If the target password wasn't in the input provided then it will display this error
    // which means the target password was not found.
    if (i == DictionaryHashcode.size()) {
    Cracked.add("Error:"+test+" was not found please use a different dataset of Hashcodes.");
           } }//Repeat for all ten target password.
        System.out.println("\nPasswords Found!");
    int l;//iterates through the crack arraylist to display all ten results.
    for(l =0; l < Cracked.size(); l++){
        System.out.println(Cracked.get(l));//prints all cracked passwords
       }
        Printer2.close();//Close printer.
    }
}



