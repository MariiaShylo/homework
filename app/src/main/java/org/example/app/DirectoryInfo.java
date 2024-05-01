package org.example.app;

import java.io.File;
import java.nio.file.Path;
import java.util.*;

public class DirectoryInfo {
    private final File directory;

    private DirectoryInfo(File directory) {
        this.directory = directory;
    }

    public static Optional<DirectoryInfo> of(String path) {
        try {
            File file = new File(path);
            if (file.exists() && file.isDirectory()) {
                return Optional.of(new DirectoryInfo(file));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static Optional<DirectoryInfo> of(Path path) {
        return of(path.toString());
    }

    public String getDirectoryPath() {
        return directory.getAbsolutePath();
    }

    public Integer getNumberOfFiles() {
        return directory.list().length;
    }

    public Long getTotalSize() {
        long totalSize = 0;
        for (File file : directory.listFiles()) {
            totalSize += file.length();
        }
        return totalSize;


    }

    public List<Path> directories() {
        List<Path> directoriesList = new ArrayList<>();
        File[] subDirectories = directory.listFiles(File::isDirectory);
        if (subDirectories != null) {
            for (File subDir : subDirectories) {
                directoriesList.add(subDir.toPath());
            }
        }
        return directoriesList;
    }

    public List<Path> files() {
        List<Path> fileList = new ArrayList<>();
        File[] files = directory.listFiles(File::isFile);
        if (files != null) {
            for (File file : files) {
                fileList.add(file.toPath());
            }
        }
        return fileList;
    }
}
