package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {

    public void update(Resume r) {
        SK searchKey = getKeyExists(r.getUuid());
        updateResume(r, searchKey);
    }

    public void save(Resume r) {
        SK searchKey = getKeyNoExists(r.getUuid());
        saveResume(r, searchKey);
    }

    public Resume get(String uuid) {
        SK searchKey = getKeyExists(uuid);
        return getResume(searchKey);
    }

    public void delete(String uuid) {
        SK searchKey = getKeyExists(uuid);
            deleteResume(searchKey);
    }

    private SK getKeyExists(String uuid) {
        SK searchKey = getKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getKeyNoExists(String uuid) {
        SK searchKey = getKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = getAllResume();
        Collections.sort(list);
        return list;
    }


    protected abstract SK getKey(String uuid);

    protected abstract void updateResume(Resume r, SK searchKey);

    protected abstract boolean isExist(SK searchKey);

    protected abstract void saveResume(Resume r, SK searchKey);

    protected abstract void deleteResume(SK searchKey);

    protected abstract Resume getResume(SK searchKey);

    protected abstract List<Resume> getAllResume();

}