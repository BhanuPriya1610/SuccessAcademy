package utils;

public class AppLogger {

    public static void log(String message){ System.out.println(message); }

    public static void warn(String message){ System.out.println("WARN: "+message); }

    public static void err(String message){
        System.err.println("ERROR: "+message);
    }

    public static void err(Exception exception){ System.err.println("ERROR: " +exception); }

    public static void log(Exception exception){ System.out.println(exception); }
}
