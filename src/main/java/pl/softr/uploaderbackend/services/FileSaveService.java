package pl.softr.uploaderbackend.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

@Service
public class FileSaveService {

    private static final String UPLOADER_FOLDER = "uploader";
    private static final Path INITIALIZED_BASE_PATH = initializeBasePath();

    private static Path initializeBasePath() {
        String userHomeDir = System.getProperty("user.home");
        Path basePath = Path.of(userHomeDir, UPLOADER_FOLDER);
        if (!Files.exists(basePath)) {
            try {
                return Files.createDirectory(basePath);
            } catch (IOException e) {
                throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occur when try create base uploader folder");
            }
        }
        return basePath;
    }

    public Path save(MultipartFile multipartFile, String uploadDate) {
        LocalDate date = uploadDate != null ? LocalDate.parse(uploadDate) : LocalDate.now();
        try {
            verifyDirectoryStructure(date);
            Path path = Path.of(INITIALIZED_BASE_PATH.toString(), multipartFile.getOriginalFilename());
            if (Files.exists(path)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File " + multipartFile.getOriginalFilename() + " already exist");
            }
            multipartFile.transferTo(path);
            return path;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void verifyDirectoryStructure(LocalDate date) throws IOException {
        Path path = Path.of(INITIALIZED_BASE_PATH.toString(), String.valueOf(date.getYear()), String.valueOf(date.getMonth()), String.valueOf(date.getDayOfMonth()));
        //TODO SAVE FILE IN FOLDER STRUCTURE LIKE YEAR/MONT/DAY
        if (!Files.exists(path)) {
            //Path p1 = Path.of(INITIALIZED_BASE_PATH,)
            Files.createDirectory(path);

        }
    }
}
