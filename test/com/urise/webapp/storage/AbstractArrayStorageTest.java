package com.urise.webapp.storage;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID1 = "uuid1";
    private static final Resume RESUME1 = new Resume(UUID1);
    private static final String UUID2 = "uuid2";
    private static final Resume RESUME2 = new Resume(UUID2);
    private static final String UUID3 = "uuid3";
    private static final Resume RESUME3 = new Resume(UUID3);
    private static final String UUID4 = "uuid4";
    private static final Resume RESUME4 = new Resume(UUID4);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void save() throws Exception {
        setArray();
        storage.save(RESUME4);
        assertEquals(4, storage.size());
    }

    @Test
    public void saveExist() throws ExistStorageException {
        storage.save(RESUME1);
    }

    @Test
    public void saveOver() throws StorageException {
        for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Resume());
        }
        storage.save(new Resume());
    }

    @Test
    public void delete() throws Exception {
        setArray();
        storage.delete(UUID1);
        assertEquals(2, storage.size());
    }

    @Test
    public void deleteNotExist() throws NotExistStorageException {
        Throwable thrown = assertThrows(NotExistStorageException.class, () -> {
            storage.delete("uuid5");
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void update() throws Exception {
        Resume resume = new Resume(UUID1);
        storage.save(resume);
        assertTrue(resume == storage.get(UUID1));
    }

    @Test
    public void updateNotExist() throws NotExistStorageException {
        Throwable thrown = assertThrows(NotExistStorageException.class, () -> {
            storage.get("uuid5");
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void get() throws Exception {
        setArray();
        assertEquals(RESUME1, storage.get(RESUME1.getUuid()));
        assertEquals(RESUME2, storage.get(RESUME2.getUuid()));
        assertEquals(RESUME3, storage.get(RESUME3.getUuid()));
    }

    @Test
    public void getAll() throws Exception {
        setArray();
        Resume[] array = storage.getAll();
        assertEquals(3, array.length);
        assertEquals(RESUME1, array[0]);
        assertEquals(RESUME2, array[1]);
        assertEquals(RESUME3, array[2]);
    }

    @Test
    public void size() throws Exception {
        setArray();
        assertEquals(3, storage.size());
    }

    private void setArray() {
        storage.clear();
        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
    }
}