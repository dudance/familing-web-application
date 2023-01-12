package com.project.project.services;

import com.project.project.models.File;
import com.project.project.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {
    private Path imagePath = null;
    private Path documentPath = null;
    @Autowired
    private FileRepository fileRepository;

    @Override
    public void init() {
        try {
            imagePath = Path.of(new java.io.File("./src/main/resources/images").getCanonicalPath());
            documentPath = Path.of(new java.io.File("./src/main/resources/documents").getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    @Transactional
    public File save(MultipartFile file) {
        try {
            String fileExtension = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            Long nextId = fileRepository.getNextId();
            String newFileName = nextId.toString() + "_" + file.getOriginalFilename();
            byte[] bytes = file.getBytes();

            if (fileExtension.equals("pdf")) {
                String insPath = documentPath + "/" + newFileName;
                Files.write(Paths.get(insPath), bytes);
                File document = new File();
                document.setFileUrl(newFileName);
                document.setFileType("document");
                return fileRepository.save(document);
            } else {
                String insPath = imagePath + "/" + newFileName;
                Files.write(Paths.get(insPath), bytes);
                File photo = new File();
                photo.setFileUrl(newFileName);
                photo.setFileType("photo");
                return fileRepository.save(photo);
            }

        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            String fileExtension = filename.substring(filename.lastIndexOf(".") + 1);
            Path file;
            if (fileExtension.equals("pdf")) {
                file = documentPath.resolve(filename);
            } else {
                file = imagePath.resolve(filename);
            }
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(imagePath.toFile());
        FileSystemUtils.deleteRecursively(documentPath.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Stream.concat(Files.walk(this.imagePath, 1).filter(path -> !path.equals(this.imagePath)).map(this.imagePath::relativize),
                    Files.walk(this.documentPath, 1).filter(path -> !path.equals(this.documentPath)).map(this.documentPath::relativize));
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}
