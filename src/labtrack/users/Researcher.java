package labtrack.users;

import java.util.Scanner;
import labtrack.experiments.ExperimentService;
import labtrack.inventory.ItemService;

public class Researcher extends User {

    public Researcher(String id, String name) {
        super(id, name, "Researcher");
    }

    @Override
    public void showMenu() {
        System.out.println("---RESEARCHER MENU---");
        System.out.println("--- Experiments ---");
        System.out.println("1. Add Experiment");
        System.out.println("2. View Experiments");
        System.out.println("3. Modify Experiment");
        System.out.println("--- Version Control ---");
        System.out.println("6. View Version History");
        System.out.println("7. Restore Version");
        System.out.println("--- Items ---");
        System.out.println("10. View Available Items");
        System.out.println("11. Borrow Item");
        System.out.println("12. Request New Item");
        System.out.println("--- Other ---");
        System.out.println("20. Delete Experiment");
        System.out.println("21. Make Reservation");
    }

    @Override
    public void handleChoice(int choice, Scanner sc) {
        ExperimentService expService = new ExperimentService();
        ItemService itemService = new ItemService();
        switch (choice) {
            case 1:
                expService.addExperiment(sc);
                break;
            case 2:
                expService.viewExperiments();
                break;
            case 3:
                expService.modifyExperiment(sc);
                break;
            case 6:
                expService.viewVersionHistory(sc);
                break;
            case 7:
                expService.restoreVersion(sc);
                break;
            case 10:
                itemService.viewAvailableItems();
                break;
            case 11:
                itemService.borrowItem(sc, username);
                break;
            case 12:
                itemService.requestNewItem(sc, username);
                break;
            case 20:
                expService.deleteExperiment(sc);
                break;
            case 21:
                new labtrack.booking.BookingService().makeReservation(sc);
                break;
            default:
                break;
        }
    }
}
