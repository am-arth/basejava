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

    @Override
    protected void saveResume(Resume resume, Object sKey) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Массив storage заполнен, сохранить невозможно!", resume.getUuid());
        } else {
            addResume(resume, (Integer) sKey);
            size++;
        }
    }

    @Override
    protected void deleteResume(Object sKey) {
        deleteResumeStorage((Integer) sKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected void updateResume(Resume resume, Object sKey) {
        storage[(Integer) sKey] = resume;
    }

    @Override
    public Resume getResume(Object sKey) {
        return storage[(Integer) sKey];
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

    protected abstract int getKey(String uuid);

    protected abstract void addResume(Resume resume, int index);

    protected abstract void deleteResumeStorage(int index);

}