package org.kshrd.service.implement;

import org.kshrd.exception.CustomNotFoundException;
import org.kshrd.service.FileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private final Path path = Paths.get("src/main/resources/images");

    @Override
    public String saveFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        fileName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(fileName);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        Files.copy(file.getInputStream(), path.resolve(fileName));
        return fileName;
    }
    @Override
    public Resource getFileByFileName (String fileName) throws IOException {
        Path filePath = path.resolve(fileName);
        if (!Files.exists(filePath) || !Files.isReadable(filePath)) {
            throw new CustomNotFoundException("File must be contain jpg, png, jpeg");
        }
        Path path = Paths.get("src/main/resources/images/" + fileName);
        return new ByteArrayResource(Files.readAllBytes(path));
    }
}
