package org.example.app;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DirectoryInfoTest {
    @Test
    void testPositiveScenario() {
        DirectoryInfo directoryInfo = DirectoryInfo.of("./");


        Optional<String> directoryPath = directoryInfo.getDirectoryPath();
        assertTrue(directoryPath.isPresent());


        Optional<Integer> numberOfFiles = directoryInfo.getNumberOfFiles();
        assertTrue(numberOfFiles.isPresent());


        Optional<Long> totalSize = directoryInfo.getTotalSize();
        assertTrue(totalSize.isPresent());


        List<Path> directories = directoryInfo.directories();
        assertFalse(directories.isEmpty());


        List<Path> files = directoryInfo.files();
        assertFalse(files.isEmpty());
    }


    @Test
    void testNegativeScenarioForNonExistingDirectory() {
        DirectoryInfo directoryInfo = DirectoryInfo.of("/non/existent/directory");


        Optional<String> directoryPath = directoryInfo.getDirectoryPath();
        assertTrue(directoryPath.isEmpty());


        Optional<Integer> numberOfFiles = directoryInfo.getNumberOfFiles();
        assertTrue(numberOfFiles.isEmpty());


        Optional<Long> totalSize = directoryInfo.getTotalSize();
        assertTrue(totalSize.isEmpty());


        List<Path> directories = directoryInfo.directories();
        assertTrue(directories.isEmpty());


        List<Path> files = directoryInfo.files();
        assertTrue(files.isEmpty());
    }
}
