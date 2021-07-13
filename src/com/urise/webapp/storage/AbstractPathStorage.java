package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private Path directory;

    protected abstract void doWrite(Resume r, OutputStream os) throws IOException;

    protected abstract Resume doRead(InputStream is) throws IOException;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::deleteResume);
        } catch (IOException e) {
            throw new StorageException("File delete error", null);
        }
    }

    @Override
    public int size() {
        return directory.getNameCount();
    }

    @Override
    protected Path getKey(String uuid) {
        return Paths.get(directory.toFile().getPath() + '/' + uuid);
    }

    @Override
    protected void updateResume(Resume resume, Path path) {
        try {
            doWrite(resume, new BufferedOutputStream(new FileOutputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("File update error", resume.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected void saveResume(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + path.toFile().getAbsolutePath(), resume.getUuid(), e);
        }
        updateResume(resume, path);
    }

    @Override
    protected Resume getResume(Path path) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("File read error", path.toFile().getName(), e);
        }
    }

    @Override
    protected void deleteResume(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new StorageException("File delete error", path.toFile().getName(), e);
        }
    }

    @Override
    protected List<Resume> getAllResume() {
        List<Resume> list;
        try {
            list = Files.list(directory).map(this::getResume).collect(Collectors.toList());
        } catch (IOException e) {
            throw new StorageException("Directory read error", directory.toFile().getPath(), e);
        }
        return list;
    }
}