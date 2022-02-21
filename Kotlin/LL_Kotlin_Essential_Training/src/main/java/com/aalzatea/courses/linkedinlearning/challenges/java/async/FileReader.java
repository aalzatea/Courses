package com.aalzatea.courses.linkedinlearning.challenges.java.async;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class FileReader {

    private FileReader() {}

    public static String readContentFileAsync(String filePath) {
        var asyncReadProcess = CompletableFuture.<String>supplyAsync(() -> {
            var path = Paths.get(filePath);

            try {
                return Files.readString(path);
            } catch (IOException e) {
                return "";
            }
        });

        try {
            return asyncReadProcess.get(2, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
            return "";
        }
    }
}
