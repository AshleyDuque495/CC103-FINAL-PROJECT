package cc103.pkgfinal.project;
public class Validator {
   
    public static boolean validateName(String name) {
        return name.matches("[A-Za-z ]*"); 
    }

    public static boolean validateNumber(String number) {
        return number.matches("regex"); 
    }
}

