package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import java.util.Arrays;
import java.util.List;


/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void saveResume(Resume resume, Object index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Массив storage заполнен, сохранить невозможно!", resume.getUuid());
        } else {
            addResumeStorage(resume, (Integer) index);
            size++;
        }
    }

    @Override
    protected void deleteResume(Object index) {
        deleteResumeStorage((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected void updateResume(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }

    @Override
    public Resume getResume(Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    @Override
    public List<Resume> getAllResume() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    public int size() {
        return size;
    }

    protected abstract void addResumeStorage(Resume resume, int index);

    protected abstract void deleteResumeStorage(int index);

}