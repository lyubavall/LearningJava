package ru.academits.lapteva.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CSV {
    public static void main(String[] args) {
        getHTMLFromCSV(args[0], args[1]);
    }

    private static void getHTMLFromCSV(String fileInPath, String fileOutPath) {
        try (Scanner scanner = new Scanner(new FileInputStream(fileInPath));
             PrintWriter writer = new PrintWriter(fileOutPath)) {
            writer.println("<!DOCTYPE html><html><head><meta charset=\"utf-8\"><title>HTML table from CSV</title></head><body>");
            writer.println("<table border=\"1\" cellpadding=\"5\">");

            boolean isNewCell = true;
            boolean isNewRow = true;
            int doubleQuotesCount = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (isNewRow) {
                    writer.println("<tr>");
                }

                for (int i = 0; i < line.length(); ++i) {
                    char character = line.charAt(i);

                    if (isNewCell) {
                        writer.print("<td>");

                        doubleQuotesCount = 0;
                    }

                    if (character == ',') {
                        if (i == line.length() - 1) {
                            writer.print("<td>");

                            isNewCell = true;
                        } else if (doubleQuotesCount % 2 != 0) {
                            writer.print(',');
                        } else {
                            writer.print("</td>");

                            isNewCell = true;
                        }

                    } else if (character == '"') {
                        if (doubleQuotesCount % 2 == 0 && doubleQuotesCount != 0) {
                            writer.print("&quot;");
                        }

                        isNewCell = false;
                        ++doubleQuotesCount;
                    } else if (character == '<') {
                        writer.print("&lt;");
                    } else if (character == '>') {
                        writer.print("&gt;");
                    } else if (character == '&') {
                        writer.print("&amp;");
                    } else {
                        writer.print(character);

                        isNewCell = false;
                    }

                    if (i == line.length() - 1) {
                        if (doubleQuotesCount % 2 != 0) {
                            writer.println("<br>");

                            isNewCell = false;
                            isNewRow = false;
                        } else {
                            isNewCell = true;
                            isNewRow = true;

                            writer.print("</td>");
                            writer.println("</tr>");
                        }
                    }
                }
            }

            writer.println("</table>");
            writer.println("</body></html>");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
    }
}
