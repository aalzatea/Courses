package com.aalzatea.courses.linkedinlearning.challenges.java.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public final class FileReader {

    private FileReader() {}

    public static List<String> readContentFileFromResources(String filePath) {
        var contentFile = FileReader.class.getClassLoader()
                .getResourceAsStream(filePath);
        return readContentFile(contentFile);
    }

    public static List<String> readContentFileUsingFilesNIO(String filePath) {
        try {
            var path = Paths.get(filePath);
            return Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private static List<String> readContentFile(InputStream inputStream) {
        try(
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
           ) {
            var content = new LinkedList<String>();
            String line;

            while((line = bufferedReader.readLine()) != null)
                content.add(line);

            inputStream.close();

            return content;
        } catch(IOException ioe) {
            return Collections.emptyList();
        }
    }
}
