
/**
 * This class is the parent which provides user interactoin and shares the getMD5 method to the child classes.
 **/

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;//
import java.security.NoSuchAlgorithmException;//
import java.util.Scanner;

public  class Main {

public static void main(String agrs[]) throws IOException {

    Scanner scan = new Scanner(System.in);System.out.println("\nPlease pick which password cracking method you wish to use (enter the name or number of the tool you wish to use): \n 1). Dictionary attack \n 2). Brute force attack");
    String method = scan.nextLine();
    boolean tool;//Will be used to decide which password cracking method the user wants to do.
    while(true) {//Conditional decides which method
        if (method.equalsIgnoreCase("brute force attack") || method.equalsIgnoreCase("brute force") || method.equals("2")) {
            System.out.println("You have chosen to perform a brute force attack.\n");
            tool = true;
            break;
        } else if (method.equalsIgnoreCase("Dictionary attack") || method.equalsIgnoreCase("dictionary") || method.equals("1")) {
            System.out.println("You have chosen to perform a Dictionary attack.");
            tool = false;
            break;
        } else {
            System.err.println("Error: unable to determine which method to use please pick an option above.");
            method = scan.nextLine();
        }
    }
        if (tool == false) {
            DictionaryAttack.main();
        } else {
            BruteForceAttack.main();
        }
    }
//This method is designed as my hash function
public static String getMd5(String input){
    // This methods takes a string as input which will hash that
    try {
        // Static getInstance method is called with hashing MD5
        MessageDigest md = MessageDigest.getInstance("MD5");
        // digest() method is called to calculate message digest of an input digest() return array of byte
        byte[] messageDigest = md.digest(input.getBytes());
        // Convert byte array into signum representation
        BigInteger no = new BigInteger(1, messageDigest);
        // Convert message digest into hex value
        StringBuilder hashtext = new StringBuilder(no.toString(16));
        while (hashtext.length() < 32) {
            hashtext.insert(0, "0");
        }
        return hashtext.toString();
    }
    // For specifying wrong message digest algorithms
    catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
        }
    }
}




