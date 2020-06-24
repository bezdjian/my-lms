package com.springapp.mylms.helpers;

import java.util.Scanner;

public class CryptoUtils {

    //Password encryption code test.
    public static void main(String arg[]) {
        try {
            Scanner reader = new Scanner(System.in);

            System.out.print("Input your secret password : ");

            String password = reader.next();
            String hash = byteArrayToHexString(CryptoUtils.computeHash(password));
            System.out.println("the computed hash (hex string) : " + hash);
            boolean ok = true;
            String inputHash = "";
            while (ok) {
                System.out.print("Now try to enter a password : " );
                String passwordAgain = reader.next();
                inputHash = byteArrayToHexString(computeHash(passwordAgain));
                if (hash.equals(inputHash)){
                    System.out.println("You got it!");
                    ok = false;
                }
                else
                    System.out.println("Wrong, try again...!");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static byte[] computeHash(String x)
    {
        try{
            java.security.MessageDigest d =null;
            d = java.security.MessageDigest.getInstance("SHA-1");
            d.reset();
            d.update(x.getBytes());
            return  d.digest();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String byteArrayToHexString(byte[] b){
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++){
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }
}