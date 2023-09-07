package co.com.myphotosnameapp.myphotoswebbackend.utilities;

import io.azam.ulidj.ULID;


public class Utilities {

    private Utilities(){}

    /**
     * generate a new Id for PK
     * @return
     */
    public static synchronized String getId(){
        return ULID.random();
    }


    public static final String PROVIDER_ID = "01H8YW8N9KY0S00SHXMXB8AKHM" ;

    public static final String CUSTOMER_ID = "01H8YVXF5XD7MMF8B1JCZ2HJYF" ;


}
