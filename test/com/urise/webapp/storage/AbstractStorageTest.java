package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {

    Storage storage;

    private static final String UUID1 = "uuid1";
    protected static final Resume RESUME1 = new Resume(UUID1, "fullName1");
    private static final String UUID2 = "uuid2";
    protected static final Resume RESUME2 = new Resume(UUID2, "fullName2");
    private static final String UUID3 = "uuid3";
    protected static final Resume RESUME3 = new Resume(UUID3, "fullName3");;
    private static final String UUID4 = "uuid4";
    private static final Resume RESUME4 = new Resume(UUID4, "fullName4");
    private static final String UUID5 = "uuid5";

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME4);
        assertEquals(4, storage.size());
        assertEquals(RESUME4, storage.get(UUID4));
    }

    @Test
    public void saveExist() throws ExistStorageException {
        assertThrows(ExistStorageException.class, () -> storage.save(RESUME1));
    }

    @Test
    public void delete() throws NotExistStorageException {
        assertThrows(NotExistStorageException.class, () -> {
            storage.delete(UUID1);
            assertEquals(2, storage.size());
            storage.get(UUID1);
        });
    }

    @Test
    public void deleteNotExist() throws NotExistStorageException {
        assertThrows(NotExistStorageException.class, () -> storage.delete(UUID5));
    }

    @Test
    public void update() throws Exception {
        Resume resume = new Resume(UUID1, "fullName");
        storage.update(resume);
        assertSame(resume, storage.get(UUID1));
    }

    @Test
    public void updateNotExist() throws NotExistStorageException {
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID5));
    }

    @Test
    public void getNotExist() throws NotExistStorageException {
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID5));
    }

    @Test
    public void get() throws Exception {
        assertEquals(RESUME1, storage.get(UUID1));
        assertEquals(RESUME2, storage.get(UUID2));
        assertEquals(RESUME3, storage.get(UUID3));
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> actualResumes = storage.getAllSorted();
        assertEquals(3, actualResumes.size());
        assertEquals(actualResumes, Arrays.asList(RESUME1, RESUME2, RESUME3));
    }

    @Test
    public void size() throws Exception {
        assertEquals(3, storage.size());
    }
}