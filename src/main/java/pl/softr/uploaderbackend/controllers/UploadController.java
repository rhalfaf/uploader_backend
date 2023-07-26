package pl.softr.uploaderbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.softr.uploaderbackend.services.FileSaveService;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/upload")
public class UploadController {

    private FileSaveService fileSaveService;

    public UploadController(FileSaveService fileSaveService) {
        this.fileSaveService = fileSaveService;
    }

    @PostMapping
    public ResponseEntity<ArrayList<Path>> upload(@RequestParam("files") MultipartFile[] files, @RequestHeader(value = "uploadDate",required = false) String uploadDate) {
        var responseBody = new ArrayList<Path>();
        for (MultipartFile file : files) {
            responseBody.add(fileSaveService.save(file, uploadDate));
        }
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("test")
    public ResponseEntity<String> test2() {
        return ResponseEntity.of(Optional.of("TEST"));
    }
}
