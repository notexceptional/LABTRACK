package labtrack.users;

import java.util.Scanner;
import labtrack.booking.BookingService;
import labtrack.inventory.ItemService;
import labtrack.reports.ReportService;

public class LabManager extends User {
    public LabManager(String id, String name) {
        super(id, name, "LabManager");
    }

    @Override
    public void showMenu() {
        System.out.println("+----------------------------------------------+");
        System.out.println("|            LAB MANAGER PANEL                 |");
        System.out.println("+----------------------------------------------+");
        System.out.println();
        System.out.println("  ~~~ Reservations ~~~");
        System.out.println("  [1] Approve Reservation");
        System.out.println();
        System.out.println("  ~~~ Reports ~~~");
        System.out.println("  [2] Generate Report");
        System.out.println();
        System.out.println("  ~~~ Item Requests ~~~");
        System.out.println("  [3] View Item Requests");
        System.out.println("  [4] Approve Item Request");
    }

    @Override
    public void handleChoice(int choice, Scanner sc) {
        BookingService bookingService = new BookingService();
        ReportService reportService = new ReportService();
        ItemService itemService = new ItemService();

        if (choice == 1) {
            bookingService.listPending();
            bookingService.approveById(sc);
            return;
        }
        if (choice == 2) {
            reportService.printSummaryReport();
            return;
        }
        if (choice == 3) {
            itemService.viewAllRequests();
            return;
        }
        if (choice == 4) {
            itemService.approveRequest(sc);
            return;
        }
    }
}