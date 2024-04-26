package org.example.app;

import java.nio.file.Path;

public class App {
    public static void main(String[] args) {
        DirectoryInfo directoryInfo = DirectoryInfo.of("./");

        directoryInfo.getDirectoryPath().ifPresent(path -> System.out.println("Directory: " + path));

        directoryInfo.getNumberOfFiles().ifPresent(count -> System.out.println("Number of files: " + count));

        directoryInfo.getTotalSize().ifPresent(size -> System.out.println("Total size: " + size + " bytes"));

        System.out.println("Subdirectories:");
        for (Path subDir : directoryInfo.directories()) {
            System.out.println(subDir);
        }

        System.out.println("Files:");
        for (Path file : directoryInfo.files()) {
            System.out.println(file);
        }
    }

}

