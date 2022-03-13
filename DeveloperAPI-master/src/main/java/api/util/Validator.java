package api.util;

public class Validator {

    public boolean nameValidator(String name) {
        return name.length() >= 3 && name.length() <= 50 && name.substring(0, 1).matches("^[a-zA-Z]*$");
    }
}
