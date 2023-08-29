package co.com.myphotosnameapp.myphotoswebbackend.utilities;

import io.azam.ulidj.ULID;

import java.util.UUID;

public class Utilities {

    /**
     * generate a new Id for PK
     * @return
     */
    public static synchronized String getId(){
        return ULID.random();
    }
}
