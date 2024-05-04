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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static Optional<DirectoryInfo> of(Path path) {
        return of(path.toString());
    }

    /**
     * Returns information about the subdirectories of the current directory.
     * @return A Set containing information about the subdirectories.
     */
    public Set<DirectoryInfo> directoriesInfo() {
        Set<DirectoryInfo> subDirectories = new HashSet<>();
        for (Path path : directories()) {
            subDirectories.add(of(path).get());
        }
        return subDirectories;
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
        return getPaths(directory.listFiles(File::isDirectory));
    }

    public List<Path> files() {
        return getPaths(directory.listFiles(File::isFile));
    }

    private List<Path> getPaths(File[] files) {
        List<Path> paths = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                paths.add(file.toPath());
            }
        }
        return paths;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectoryInfo that = (DirectoryInfo) o;
        return Objects.equals(directory, that.directory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directory);
    }


}
