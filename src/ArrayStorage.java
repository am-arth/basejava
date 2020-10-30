/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        for (int j = 0; j < size; j++) {
            storage[j] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        int index = -1;
        if (r.uuid != null) {
            for (int i = 0; i < size; i++) {
                if (storage[i].uuid.equals(r.uuid)) {
                    index = i;
                } else {
                    break;

                }
            }
        }
        if (index == -1) {
            for (int i = 0; i < storage.length; i++) {
                if (storage[i] == null) {
                    storage[i] = r;
                    size++;
                    break;
                }
            }
        }
    }

    Object get(String uuid) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                index = i;
                return storage[index];
            }
        }
        return null;
    }

    void delete(String uuid) {

        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                size--;
                break;
            }
        }
        //После присваения элементу массива NULL сдвигаем влево значения
        //чтобы все NULL остались в конце
        for (int j = 0; j < storage.length; j++) {
            if (storage[j] == null) {
                for (int k = j + 1; k < storage.length; k++) {
                    storage[k - 1] = storage[k];
                }
                storage[storage.length - 1] = null;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    int size() {
        return 0;
    }
}
