/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class Utils {

    public static String getString(String welcome) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("Input text!!!");
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static int getInt(String welcome, int min, int max) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }
    
    public static double getDouble(String welcome, double min, double max) {
        double result = 0.0;
        boolean check = false;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println(welcome);
                result = Double.parseDouble(sc.nextLine());
                check = true;
            } catch(Exception e) {
                System.out.println("Input number!!!");
            }
            
        } while(!check || result < min || result > max);  
        return result;
    }
    
    public static double getDouble(String welcome, double min, double max, double oldValue) {
        double result = 0.0;
        boolean check = false;
        do {
            try {
                System.out.println(welcome);
                Scanner scanner = new Scanner(System.in);
                String tmp = scanner.nextLine();
                if (tmp.isEmpty()) {
                    result = oldValue;
                    check = true;
                } else {
                    result = Double.parseDouble(tmp);
                    check = true;
                }
            } catch(Exception e) {
                System.out.println("Input number!!!");
            }
        } while(!check || result < min || result > max);
        
        return result;
    }

    public static int getInt(String welcome, int min, int max, int oldValue) {
        int result = oldValue;
        boolean check = false;
        try {
            do {
                System.out.println(welcome);
                Scanner scanner = new Scanner(System.in);
                String tmp = scanner.nextLine();
                if (tmp.isEmpty()) {
                    result = oldValue;
                    check = true;
                } else {
                    result = Integer.parseInt(tmp);
                    check = true;
                }
            } while (!check || result < min || result > max);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;

    }

    public static String getString(String welcome, String oldValue) {
        String result = oldValue;
        try {
                Scanner scanner = new Scanner(System.in);
                System.out.println(welcome);
                String tmp = scanner.nextLine();
                if (!tmp.isEmpty()) {
                    result = tmp;
                }
                

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static void display(String str) {
        System.out.println(str);
    }

}
