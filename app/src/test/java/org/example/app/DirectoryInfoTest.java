package org.example.app;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class DirectoryInfoTest {
    public static final String NON_EXISTENT_DIRECTORY = "/non/existent/directory";

    @Test
    void testDirectoryExist() {
        Optional<DirectoryInfo> directoryInfo = DirectoryInfo.of("./");
        assertTrue(directoryInfo.isPresent());
    }

    @Test
    void testDirectoryNotExist() {
        Optional<DirectoryInfo> directoryInfo = DirectoryInfo.of(NON_EXISTENT_DIRECTORY);
        assertTrue(directoryInfo.isEmpty());
    }

    @Test
    void testDirectoryWithSubDirectories() throws Exception {
        Path subDir = Files.createTempDirectory("subDir");
        Files.createDirectories(Path.of(subDir.toString(), "one"));

        Optional<DirectoryInfo> directoryInfo = DirectoryInfo.of(subDir);
        List<Path> directories = directoryInfo.get().directories();
        assertEquals(1, directories.size());
    }

    @Test
    void testDirectoryWithNoSubDirectories() throws Exception {
        Path empty = Files.createTempDirectory("empty");
        Optional<DirectoryInfo> directoryInfo = DirectoryInfo.of(empty);

        List<Path> directories = directoryInfo.get().directories();
        assertTrue(directories.isEmpty());
    }

    @Test
    void testFilesExist() throws Exception {
        Path fileDir = Files.createTempDirectory("fileDir");
        Files.createFile(Path.of(fileDir.toString(), "one.txt"));

        Optional<DirectoryInfo> directoryInfo = DirectoryInfo.of(fileDir);
        List<Path> files = directoryInfo.get().files();
        assertEquals(1, files.size());
    }

    @Test
    void testFilesNotExist() throws Exception {
        Path empty = Files.createTempDirectory("emptyDir");

        Optional<DirectoryInfo> directoryInfo = DirectoryInfo.of(empty);
        List<Path> files = directoryInfo.get().files();
        assertTrue(files.isEmpty());
    }
}
