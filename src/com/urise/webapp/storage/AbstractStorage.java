package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public void update(Resume resume) {
        LOG.info("Update " + resume);
        SK searchKey = getKeyExists(resume.getUuid());
        updateResume(resume, searchKey);
    }

    public void save(Resume resume) {
        LOG.info("Save " + resume);
        SK searchKey = getKeyNoExists(resume.getUuid());
        saveResume(resume, searchKey);
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK searchKey = getKeyExists(uuid);
        return getResume(searchKey);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK searchKey = getKeyExists(uuid);
            deleteResume(searchKey);
    }

    private SK getKeyExists(String uuid) {
        SK searchKey = getKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " не найден");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getKeyNoExists(String uuid) {
        SK searchKey = getKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " уже существует");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
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