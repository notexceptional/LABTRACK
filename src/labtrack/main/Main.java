package labtrack.main;

import java.util.Scanner;
import labtrack.auth.AuthService;
import labtrack.users.User;
import labtrack.util.InputHelper;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InputHelper.setScanner(sc);
        AuthService auth = new AuthService();

        System.out.println();
        System.out.println("**************************************************");
        System.out.println("*                                                *");
        System.out.println("*           Welcome to LABTRACK                  *");
        System.out.println("*                                                *");
        System.out.println("**************************************************");
        System.out.println();
        while (true) {
            User user = auth.login(sc);
            if (user == null) continue;

            boolean loggedIn = true;
            while (loggedIn) {
                System.out.println();
                user.showMenu();
                System.out.println();
                System.out.println("----------------------------------------------");
                System.out.println("  [0] Logout");
                System.out.println("  [999] Exit");
                System.out.println("----------------------------------------------");
                System.out.println();
                int choice;
                try {
                    choice = Integer.parseInt(InputHelper.readLine(""));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    continue;
                }
                if (choice == 0) {
                    System.out.println();
                    System.out.println(">>> Logged out successfully! <<<");
                    System.out.println();
                    loggedIn = false;
                } else if (choice == 999) {
                    System.out.println();
                    System.out.println("**************************************************");
                    System.out.println("*      Thank you for using LABTRACK!            *");
                    System.out.println("**************************************************");
                    System.out.println();
                    sc.close();
                    System.exit(0);
                } else {
                    user.handleChoice(choice, sc);
                }
            }

        }
    }
}
