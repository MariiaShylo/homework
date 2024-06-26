package org.example.app;

import java.nio.file.Path;

public class App {
    public static void main(String[] args) {
        DirectoryInfo.of("./").ifPresent(directoryInfo -> {

           System.out.println("Directory: " + directoryInfo.getDirectoryPath());

            System.out.println("Number of files: " + directoryInfo.getNumberOfFiles());

            System.out.println("Total size: " + directoryInfo.getTotalSize() + " bytes");

            System.out.println("Subdirectories: " + directoryInfo.directories());

            System.out.println("Files: " + directoryInfo.files());
        });
    }
}

