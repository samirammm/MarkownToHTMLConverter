import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Converter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder markdown = new StringBuilder();
        String line;

        // Read Markdown content line by line until EOF
        System.out.println("Enter Markdown content (End input with Ctrl+D or specific command):");
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            markdown.append(line).append("\n");
        }

        String html = markdownToHtml(markdown.toString());
        System.out.println("\nConverted HTML:");
        System.out.println(html);

        scanner.close();
    }

    public static String markdownToHtml(String markdown) {
        StringBuilder html = new StringBuilder();
        String[] lines = markdown.split("\n");

        for (String line : lines) {
            // Handle headings
            if (line.matches("^#{1,6} .*")) {
                int headerLevel = 0;
                while (line.charAt(headerLevel) == '#') {
                    headerLevel++;
                }
                String headerContent = line.substring(headerLevel + 1).trim();
                html.append("<h").append(headerLevel).append(">").append(headerContent).append("</h").append(headerLevel).append(">\n");
            } else if (line.trim().isEmpty()) {
                continue; // Ignore blank lines
            } else {
                html.append(convertMarkdownLinks(line)).append("\n");
            }
        }

        return html.toString();
    }

    private static String convertMarkdownLinks(String line) {
        Pattern pattern = Pattern.compile("\\[(.*?)\\]\\((.*?)\\)");
        Matcher matcher = pattern.matcher(line);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String linkText = matcher.group(1);
            String linkUrl = matcher.group(2);
            matcher.appendReplacement(sb, "<a href=\"" + linkUrl + "\">" + linkText + "</a>");
        }
        matcher.appendTail(sb);
        return "<p>" + sb.toString() + "</p>";
    }

}

