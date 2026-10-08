package frontend;

import backend.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        new DatabaseConnection(); // initialize DB first
        new SplashScreen();
    }
}