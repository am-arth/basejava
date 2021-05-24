package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

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

    public void addResumeStorage(int index, Resume resume) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Массив storage заполнен, сохранить невозможно!", resume.getUuid());
        }
        addResume(resume, index);
        size++;
    }

    public void deleteResumeStorage(int index) {
        deleteResume(index);
        storage[size - 1] = null;
        size--;
    }

    public void updateResumeStorage(int index, Resume resume) {
        storage[index] = resume;
    }

    public Resume getResumeStorage(int index) {
        return storage[index];
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void addResume(Resume resume, int index);

    protected abstract void deleteResume(int index);

}