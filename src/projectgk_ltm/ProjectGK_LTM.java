/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectgk_ltm;
import java.util.Scanner;
import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 *
 * @author Tron Tron
 */
public class ProjectGK_LTM {

    public static String readFile(String fname){
      
        String line = null;
        String result = null; 
        try
        {

            FileReader fileReader = new FileReader(fname);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while((line = bufferedReader.readLine()) != null)
            {
                result = line;
                System.out.println("aaa" + line);
            }
            System.out.println("1" + result);
            bufferedReader.close();
            
        }
        catch(IOException ex)
        {
            System.out.println("Error reading file named '" + fname + "'");
        }
  //      System.out.println("aaa 1" + line);
      return result;
    }
    public static void writeFile(){
         String fname;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter File Name with Extension (like demo.txt) : ");
        fname = scan.nextLine();
        
        try
        {
            FileWriter fileWriter = new FileWriter(fname);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
          
            int len;
            System.out.print("How many Sentence/Line you Want to Enter ? ");
            len = scan.nextInt();
            int i;
            String sentence;
         
            System.out.print("Enter " +len+ " Lines of Sentences followed by Enter : ");
            for(i=0; i<len+1; i++)
            {
                sentence = scan.nextLine();
                bufferedWriter.write(sentence);
                bufferedWriter.newLine();
            }
            
            bufferedWriter.close();
        }
        catch(IOException ex)
        {
            System.out.println("Error writing to file named '" +fname+ "' ..!!");
        }
    }
    public static String encrypt(final String message, final String key) {
    StringBuilder result = new StringBuilder();

    for (int i = 0, j = 0; i < message.length(); i++) {
      char c = message.charAt(i);
      if (Character.isLetter(c)) {
        if (Character.isUpperCase(c)) {
          result.append((char) ((c + key.toUpperCase().charAt(j) - 2 * 'A') % 26 + 'A'));

        } else {
          result.append((char) ((c + key.toLowerCase().charAt(j) - 2 * 'a') % 26 + 'a'));
        }
      } else {
        result.append(c);
      }
      j = ++j % key.length();
    }
    return result.toString();
  }

  public static String decrypt(final String message, final String key) {
    StringBuilder result = new StringBuilder();

    for (int i = 0, j = 0; i < message.length(); i++) {

      char c = message.charAt(i);
      if (Character.isLetter(c)) {
        if (Character.isUpperCase(c)) {
          result.append((char) ('Z' - (25 - (c - key.toUpperCase().charAt(j))) % 26));

        } else {
          result.append((char) ('z' - (25 - (c - key.toLowerCase().charAt(j))) % 26));
        }
      } else {
        result.append(c);
      }

      j = ++j % key.length();
    }
    return result.toString();
  }
  public static void findCharacterAppearMost(String message){
    Map<Character, Integer> numCharMap = new HashMap<Character, Integer>();
    for(int i = 0; i < message.length(); i++){
      char c = message.charAt(i);
      if(c == ' ')
        continue;
      if(numCharMap.containsKey(c)){
        numCharMap.put(c, numCharMap.get(c) + 1);
      }else{
        numCharMap.put(c, 1);
      }
    }
    int max = Collections.max(numCharMap.values());
    System.out.println("max " + max);
    Set<Map.Entry<Character, Integer>> numSet = numCharMap.entrySet();
    for(Map.Entry<Character, Integer> m : numSet){
        if (m.getValue() == max) {
        System.out.println("The character " + m.getKey() + " appear " + m.getValue());
        }
    //    System.out.println("The character " + m.getKey() + " appear " + m.getValue());
    }
  }
    public static void main(String[] args) {
        String key = "trontron";        
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter File Name to Open (with extension like file.txt) : ");
        String fname = scan.nextLine();
        String text = readFile(fname);
        String ciphertext = encrypt(text, key);
        System.out.println("Text after encrypt: " + ciphertext);
        String text_decrypt = decrypt(ciphertext, key);
        System.out.println("Text after decrypt: " + text_decrypt);
        findCharacterAppearMost(text_decrypt);
   //     writeFile();
    }    
}
