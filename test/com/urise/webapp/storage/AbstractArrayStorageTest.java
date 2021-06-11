package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    public void saveOverflow() throws StorageException {
        try {
            for (int i = 4; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("fullName" + i));
            }
        } catch (StorageException e) {
            fail("Переполнение массива произошло раньше времени.");
        }
        storage.save(new Resume("fullName"));
    }

}