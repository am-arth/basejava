package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.util.Arrays;


public class MapStorageTest extends AbstractStorageTest{

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Test
    public void getAll() throws Exception {
       Resume[] expectedResumes = {RESUME1, RESUME2, RESUME3};
       Resume[] actualResumes = storage.getAll();
       Arrays.sort(actualResumes);
       Assert.assertArrayEquals(expectedResumes, actualResumes);
    }
}