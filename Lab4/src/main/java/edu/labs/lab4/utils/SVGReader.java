package edu.labs.lab4.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SVGReader {

    public static String readSVGFile(String filePath) {
        String content = "";

        try {
            // Read the entire SVG file
            String svgContent = new String(Files.readAllBytes(Paths.get(filePath)));

            // Create a Pattern object for the SVG path data
            Pattern pattern = Pattern.compile("<path[^>]*d=\"([^\"]*)\"[^>]*>");

            // Create a Matcher object
            Matcher matcher = pattern.matcher(svgContent);

            // Find the SVG path data
            if (matcher.find()) {
                content = matcher.group(1);
            }
        } catch (IOException e) {
            System.err.println("Error reading SVG file: " + e.getMessage());
        }

        return content;
    }
}