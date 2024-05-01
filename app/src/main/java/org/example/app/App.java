package org.example.app;

import java.nio.file.Path;

public class App {
    public static void main(String[] args) {
        DirectoryInfo.of("./").ifPresent(directoryInfo -> {

           System.out.println("Directory: " + directoryInfo.getDirectoryPath());

            System.out.println("Number of files: " + directoryInfo.getNumberOfFiles());

            System.out.println("Total size: " + directoryInfo.getTotalSize() + " bytes");

            System.out.println("Subdirectories:");
            for (Path subDir : directoryInfo.directories()) {
                System.out.println(subDir);
            }

            System.out.println("Files:");
            for (Path file : directoryInfo.files()) {
                System.out.println(file);
            }
        });
    }

}

