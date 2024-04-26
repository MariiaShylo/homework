package org.example.app;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DirectoryInfo {
    private File directory;

    private DirectoryInfo(String path) {
        this.directory = new File(path);
    }

    public static DirectoryInfo of(String path) {
        return new DirectoryInfo(path);
    }

    public static DirectoryInfo of(Path path) {
        return new DirectoryInfo(path.toString());
    }

    public Optional<String> getDirectoryPath() {
        if (directory.exists() && directory.isDirectory()) {
            return Optional.of(directory.getAbsolutePath());
        } else {
            return Optional.empty();
        }
    }

    public Optional<Integer> getNumberOfFiles() {
        if (directory.exists() && directory.isDirectory()) {
            return Optional.of(directory.list().length);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Long> getTotalSize() {
        if (directory.exists() && directory.isDirectory()) {
            long totalSize = 0;
            for (File file : directory.listFiles()) {
                totalSize += file.length();
            }
            return Optional.of(totalSize);
        } else {
            return Optional.empty();
        }
    }

    public List<Path> directories() {
        List<Path> directoriesList = new ArrayList<>();
        if (directory.exists() && directory.isDirectory()) {
            File[] subDirectories = directory.listFiles(File::isDirectory);
            if (subDirectories != null) {
                for (File subDir : subDirectories) {
                    directoriesList.add(subDir.toPath());
                }
            }
        }
        return directoriesList;
    }

    public List<Path> files() {
        List<Path> fileList = new ArrayList<>();
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles(File::isFile);
            if (files != null) {
                for (File file : files) {
                    fileList.add(file.toPath());
                }
            }
        }
        return fileList;
    }
}
