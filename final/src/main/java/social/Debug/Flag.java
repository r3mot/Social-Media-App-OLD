package social.Debug;

public class Flag {
    
    public static boolean DEBUGGING = true;

    public static void DBG(String message, String className, String method){
        if(DEBUGGING){
            System.out.println("Tesing class ( " + className + " )                ");
            System.out.println("Using the method: " + method);
            System.out.println("-----------------------------------------------");
            System.out.println("Message: " + message);
            System.out.println(message);
        }
    }

    // Account Creation
    public static final String USER_CREATION_SUCCESS = "User created successfully";
    public static final String IMAGE_UPLOAD_SUCCESS = "Image has been successfully uploaded";
    // Verifying Login
    public static final String VERIFYING = "Verifying Credentials";
    

 
}
