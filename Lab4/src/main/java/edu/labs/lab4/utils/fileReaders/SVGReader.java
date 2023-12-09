package edu.labs.lab4.utils.fileReaders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SVGReader {

    public static String readSVGFile(String filePath) {
        StringBuilder content = new StringBuilder();

        try {
            // Read the entire SVG file
            String svgContent = new String(Files.readAllBytes(Paths.get(filePath)));

            // Create a Pattern object for the SVG path data
            Pattern pattern = Pattern.compile("<path[^>]*d=\"([^\"]*)\"[^>]*>");

            // Create a Matcher object
            Matcher matcher = pattern.matcher(svgContent);

            // Find all SVG path data
            while (matcher.find()) {
                content.append(matcher.group(1)).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading SVG file: " + e.getMessage());
        }

        return content.toString();
    }

}