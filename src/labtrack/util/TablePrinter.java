package labtrack.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for rendering data into professional ASCII tables.
 * Supports dynamic column width calculation and optional title headers.
 */
public class TablePrinter {
    /**
     * Prints a formatted ASCII table to the console.
     *
     * @param title   The title of the table, displayed above the header. Can be null or empty.
     * @param headers An array of strings representing the column headers. Must not be null or empty.
     * @param rows    A list of string arrays, where each inner array represents a row of data.
     *                Each element in the inner array corresponds to a column.
     */
    public static void printTable(String title, String[] headers, List<String[]> rows) {
        if (headers == null || headers.length == 0) return;

        // Calculate column widths
        int[] widths = new int[headers.length];
        for (int i = 0; i < headers.length; i++) {
            widths[i] = headers[i].length();
        }

        for (String[] row : rows) {
            for (int i = 0; i < Math.min(row.length, widths.length); i++) {
                if (row[i] != null && row[i].length() > widths[i]) {
                    widths[i] = row[i].length();
                }
            }
        }

        // Build horizontal separator
        StringBuilder sb = new StringBuilder("+");
        for (int w : widths) {
            sb.append("-".repeat(w + 2)).append("+");
        }
        String separator = sb.toString();

        // Print header
        if (title != null && !title.isEmpty()) {
            Colors.header(title);
        }

        System.out.println(Colors.colorize(separator, Colors.CYAN));
        System.out.print(Colors.colorize("|", Colors.CYAN));
        for (int i = 0; i < headers.length; i++) {
            System.out.printf(Colors.colorize(" %-" + widths[i] + "s ", Colors.YELLOW_BOLD) + Colors.colorize("|", Colors.CYAN), headers[i]);
        }
        System.out.println();
        System.out.println(Colors.colorize(separator, Colors.CYAN));

        // Print rows
        if (rows.isEmpty()) {
            System.out.print(Colors.colorize("|", Colors.CYAN));
            int totalWidth = separator.length() - 2;
            System.out.printf(" %-" + totalWidth + "s ", "No data available");
            System.out.println(Colors.colorize("|", Colors.CYAN));
        } else {
            for (String[] row : rows) {
                System.out.print(Colors.colorize("|", Colors.CYAN));
                for (int i = 0; i < headers.length; i++) {
                    String val = (i < row.length) ? row[i] : "";
                    System.out.printf(" %-" + widths[i] + "s " + Colors.colorize("|", Colors.CYAN), val);
                }
                System.out.println();
            }
        }
        System.out.println(Colors.colorize(separator, Colors.CYAN));
        System.out.println();
    }

    /**
     * Prints a CSV file as a formatted ASCII table.
     * @param title The table title.
     * @param fileName Path to the CSV file.
     * @param headers Array of column headers.
     */
    public static void printCsvAsTable(String title, String fileName, String[] headers) {
        List<String> lines = FileManager.readAllLines(fileName);
        List<String[]> rows = new ArrayList<>();
        for (String line : lines) {
            rows.add(line.split(","));
        }
        printTable(title, headers, rows);
    }
}
