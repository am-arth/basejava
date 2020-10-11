import com.sun.deploy.security.SelectableSecurityManager;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;
    void clear() {
        for (int j = 0; j < storage.length; j++) {
            storage[j] = null;
        }
    }

    void save(Resume r) {
          for (int i = 0; i < storage.length; i++) {

              //Сравниваекм введенное значение со значением элемента массива
              //При соспадении прерываем цикл
              //Вопрос. Почему не отрабатывает?
              if (r.equals(storage[i])) {
                  System.out.println("Такое значение уже существует в массиве");
                  break;
              }

              //Следующему элементу со значением NULL присваиваем введенное с клавиатуры.
              if (storage[i] == null) {
                  storage[i] = r;
                  System.out.println(storage[i]);
                  break;
              }

        }

    }

    Object get(String uuid) {
        //Если элемент массива не существует, то должен возвратиться NULL
        //Вопрос. Если не существует элемент массива, то выдается ошибка.
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].uuid == uuid) {
                 return storage[i];
            }
        }
       return null;
    }

    void delete(String uuid) {

            for (int i = 0; i < storage.length; i++) {
             if (storage[i].uuid == uuid) {
                 storage[i] = null;
                 break;
             }
         }
        //После присваения элементу массива NULL сдвигаем влево значения
        //чтобы все NULL остались в конце
        for (int j=0; j<storage.length; j++){
            if (storage[j]==null){
                for (int k=j+1; k<storage.length; k++){
                    storage[k-1] = storage[k];
                }
                storage[storage.length-1] = null;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
          return new Resume[0];
    }

    int size() {
        return 0;
    }
}
