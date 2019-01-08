package ua.khpi.baturin.util;

import java.util.regex.Pattern;

public class Validator {

    public static boolean loginCheck(String login) {
        Pattern pattern = Pattern.compile("[а-яА-ЯA-Za-z0-9_]+");
        return (login != null) && pattern.matcher(login).matches();
    }

    public static boolean passwordCheck(String password) {
        Pattern pattern = Pattern.compile("^[а-яА-Яa-zA-Z0-9]{8,}$");
        return (password != null) && pattern.matcher(password).matches();
    }

    public static boolean emailCheck(String email) {
        Pattern pattern = Pattern.compile("^[а-яА-ЯA-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        return (email != null) && pattern.matcher(email).matches();
    }

    public static boolean nameCheck(String name) {
        Pattern pattern = Pattern.compile("^[а-яА-Яa-zA-Z\\s]+");
        return (name != null) && pattern.matcher(name).matches();
    }

    public static boolean someCheck(String name) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9а-яА-Я\\s]+");
        return (name != null) && pattern.matcher(name).matches();
    }
    
    public static boolean digits(String name) {
        Pattern pattern = Pattern.compile("^[0-9]+");
        return (name != null) && pattern.matcher(name).matches();
    }
    
    public static boolean busNumberCheck(String name) {
        Pattern pattern = Pattern.compile("^[0-9A-ZА-Я]+");
        return (name != null) && pattern.matcher(name).matches();
    }
    
    public static boolean stationTitleCheck(String name) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9а-яА-Я\\s]+");
        return (name != null) && pattern.matcher(name).matches();
    }
}
