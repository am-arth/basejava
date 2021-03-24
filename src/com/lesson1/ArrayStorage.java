package com.lesson1;
import com.lesson1.Resume;
/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        if (getIndexArray(uuid) == -1) {
            return null;
        } else {
            return storage[getIndexArray(uuid)];
        }
    }

    public void delete(String uuid) {
        if (getIndexArray(uuid) == -1) {
            System.out.println("Данный uuid не найден!");
        } else {
            storage[getIndexArray(uuid)] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    public void update(Resume r) {
        if (getIndexArray(r.getUuid()) == -1) {
            System.out.println("Данный uuid не найден!");
        } else {
            storage[getIndexArray(r.getUuid())] = r;
        }
    }

    public int getIndexArray(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid == storage[i].getUuid()) {
                return i;
            }
        }
        return -1;
    }
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] result = new Resume[size];
    	for (int i = 0; i < size; i++) {
         result[i] = storage[i];
        }        
	    return result;
    }

    public int size() {
        return size;
    }
}
