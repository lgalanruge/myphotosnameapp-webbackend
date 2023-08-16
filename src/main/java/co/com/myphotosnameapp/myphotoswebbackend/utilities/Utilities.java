package co.com.myphotosnameapp.myphotoswebbackend.utilities;

import java.util.UUID;

public class Utilities {

    public static String getId(){
        String value = UUID.randomUUID().toString();
        value = value.replace("-", "");
        return value.substring(2);
    }
}
