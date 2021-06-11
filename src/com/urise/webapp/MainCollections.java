package com.urise.webapp;

import com.urise.webapp.model.Resume;
import java.util.*;

public class MainCollections {
    private static final String UUID1 = "uuid1";
    private static final Resume RESUME1 = new Resume(UUID1,"fullName1");

    private static final String UUID2 = "uuid2";
    private static final Resume RESUME2 = new Resume(UUID2, "fullName2");

    private static final String UUID3 = "uuid3";
    private static final Resume RESUME3 = new Resume(UUID3, "fullName3");

    private static final String UUID4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID4, "fullName4");

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();
        collection.add(RESUME1);
        collection.add(RESUME2);
        collection.add(RESUME3);

        for (Resume r : collection) {
            System.out.println(r);
            if (Objects.equals(r.getUuid(), UUID1)) {
//                collection.remove(r);
            }
        }

        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Resume r = iterator.next();
            System.out.println(r);
            if (Objects.equals(r.getUuid(), UUID1)) {
                iterator.remove();
            }
        }
        System.out.println(collection.toString());


        Map<String, Resume> map = new HashMap<>();
        map.put(UUID1, RESUME1);
        map.put(UUID2, RESUME2);
        map.put(UUID3, RESUME3);

        // Bad!
        for (String uuid : map.keySet()) {
            System.out.println(map.get(uuid));
        }

        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }

        List<Resume> resumes = Arrays.asList(RESUME1, RESUME2, RESUME3);
        resumes.remove(1);
        System.out.println(resumes);
    }
}