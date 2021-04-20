package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void addResume(Resume resume, int index) {
        int insertIndex = - index - 1;
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        storage[insertIndex] = resume;
    }

    @Override
    protected void deleteResume(int index) {
        int removeIndex = size - index - 1;
        if (removeIndex > 0) {
            System.arraycopy(storage, index + 1, storage, index, removeIndex);
        }
    }
}