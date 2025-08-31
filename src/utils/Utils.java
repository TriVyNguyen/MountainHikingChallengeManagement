
package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.StudentMountain;

public class Utils {

    public final static int MIN = 0;
    public final static int MAX = 3000;
    public final static int BASE_FEE = 6000000;
    public final static int DISCOUNT_FEE = 35;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final String ID_REGEX = "^(SE|HE|DE|QE|CE)\\d{6}$";
    private static final String PHONE_REGEX = "^(?:\\+84|0)(3|5|7|8|9)[0-9]{8}$";
    private static final String NAME_REGEX = "^[A-Za-zÀ-ỹ\\s]{2,20}$";

    public static boolean isValidateID(String id) {
        return id.matches(ID_REGEX);
    }

    public static boolean isValidateName(String name) {
        return name.matches(NAME_REGEX);
    }

    public static boolean isValidateEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public static boolean isValidatePhone(String phone) {
        return phone.matches(PHONE_REGEX);
    }

    public static String getValidStudentInfor(String type) {
        boolean check = false;
        String result = "";
        switch (type) {
            case "id":
                do {
                    result = getString("Enter student id: ");
                    check = result.matches(ID_REGEX);
                    System.out.println(check);
                } while (!check);
                break;
            case "name":
                do {
                    result = getString("Enter student name: ");
                    check = result.matches(NAME_REGEX);
                } while (!check);
                break;
            case "phone number":
                do {
                    result = getString("Enter student's phone number: ");
                    check = result.matches(PHONE_REGEX);
                } while (!check);
                break;
            case "email":
                do {
                    result = getString("Enter student email: ");
                    check = result.matches(EMAIL_REGEX);
                } while (!check);
                break;
            default:
                throw new AssertionError();
        }
        return result;
    }
    
        public static String getUpdatedStudentInfor(String type, String oldData) {
        boolean check = false;
        String result = "";
        switch (type) {
            case "id":
                do {
                    result = updateString("Enter student id: ", oldData);
                    if(result.isEmpty()) {
                        result = oldData;
                    }
                    check = result.matches(ID_REGEX);
                    System.out.println(check);
                } while (!check);
                break;
            case "name":
                do {
                    result = updateString("Enter student name: ", oldData);
                    if(result.isEmpty()) {
                        result = oldData;
                    }
                    check = result.matches(NAME_REGEX);
                } while (!check);
                break;
            case "phone number":
                do {
                    result = updateString("Enter student's phone number: ", oldData);
                    if(result.isEmpty()) {
                        result = oldData;
                    }
                    check = result.matches(PHONE_REGEX);
                } while (!check);
                break;
            case "email":
                do {
                    result = updateString("Enter student email: ", oldData);
                    if(result.isEmpty()) {
                        result = oldData;
                    }
                    check = result.matches(EMAIL_REGEX);
                } while (!check);
                break;
            default:
                throw new AssertionError();
        }
        return result;
    }
    
    

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

    public static void displayStatus(boolean status, String success, String failed) {
        if (status == true) {
            System.out.println(success);
        } else {
            System.out.println(failed);
        }
    }

    public static String updateString(String welcome, String oldData) {
        String result = oldData;
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = tmp;
        }
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

    public static int updateInt(String welcome, int min, int max, int oldData) {
        boolean check = true;
        int number = oldData;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Integer.parseInt(tmp);
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static boolean confirmYesNo(String welcome) {
        boolean result = false;
        String confirm = Utils.getString(welcome);
        if ("Y".equalsIgnoreCase(confirm)) {
            result = true;
        }
        return result;
    }

    public static boolean writeListObjectToFile(String path, List<StudentMountain> list) {
        boolean result = false;
        try {
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(file);
            try {

                for (Object sm : list) {
                    oos.writeObject(sm);
                }
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (oos != null) {
                    oos.close();
                }
            }
            if (file != null) {
                file.close();
            }
        } catch (Exception e) {
        }

        return result;
    }

    public static ArrayList<Object> readListObjectFromFile(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<Object> list = new ArrayList();
        try {
            Object obj = null;
            while (fis.available() > 0) {
                obj = (Object) ois.readObject();
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return list;
    }

    private static class StudentMountain {

        public StudentMountain() {
        }
    }
}