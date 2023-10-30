package com.example.pcos_journey;

public class UserSession {
    private static User loggedInUser;

    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static boolean isUserLoggedIn() {
        return loggedInUser != null;
    }

    public static void logout() {
        loggedInUser = null;
    }
}

