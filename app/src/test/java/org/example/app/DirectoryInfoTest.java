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
    void testFilesExist() {
        Optional<DirectoryInfo> directoryInfo = DirectoryInfo.of("./");
        assertTrue(directoryInfo.get().files().size() != 0);
    }

    @Test
    void testFilesNotExist() {
        Optional<DirectoryInfo> directoryInfo = DirectoryInfo.of(NON_EXISTENT_DIRECTORY);
        assertTrue(directoryInfo.isEmpty());
    }
}
