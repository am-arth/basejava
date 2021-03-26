package com.lesson1;
import java.util.Arrays;
/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        int index = getIndexArray(r.getUuid());
        if (size == storage.length) {
            System.out.println("Выход за пределы допустимого диапазона!");
        } else if (index != -1) {
            System.out.println("Данный " + r.getUuid() + " уже существует!");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndexArray(uuid);
        if (index == -1) {
            System.out.println("Данный " + uuid + " не найден!");
            return null;
        } else {
            return storage[index];
        }
    }

    public void delete(String uuid) {
        int index = getIndexArray(uuid);
        if (index == -1) {
            System.out.println("Данный " + uuid + " не найден!");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    public void update(Resume r) {
        int index = getIndexArray(r.getUuid());
        if (index == -1) {
            System.out.println("Данный " + r.getUuid() + " не найден!");
        } else {
            storage[index] = r;
        }
    }

    private int getIndexArray(String uuid) {
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
        result = Arrays.copyOfRange(storage, 0, size);
	    return result;
    }

    public int size() {
        return size;
    }
}
