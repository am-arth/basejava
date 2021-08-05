package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            writeWithException(dos, contacts.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });
            writeWithException(dos, r.getSections().entrySet(), entry -> writeSection(dos, entry.getKey(), entry.getValue()));
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readWithException(dis, () -> resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readWithException(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.setSection(sectionType, readSection(dis, sectionType));
            });
            return resume;
        }

    }

    private void writeSection(DataOutputStream dos, SectionType sectionType, AbstractSection section) throws IOException {
        dos.writeUTF(sectionType.name());
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                dos.writeUTF(((TextSection) section).getContent());
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                writeWithException(dos, ((ListSection) section).getProgress(), dos::writeUTF);
                break;
            case EDUCATION:
            case EXPERIENCE:
                writeWithException(dos, ((OrganizationSection) section).getOrganizations(), organization -> {
                    Link link = organization.getLink();
                    dos.writeUTF(link.getName());
                    dos.writeUTF(link.getUrl());
                    writeWithException(dos, organization.getExperience(), experience -> {
                        dos.writeUTF(experience.getFunction());
                        writeDate(dos, experience.getBeginDate());
                        writeDate(dos, experience.getEndDate());
                        dos.writeUTF(experience.getSpecification());
                    });
                });
                break;

        }
    }

    private AbstractSection readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        AbstractSection section = null;
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                section = new TextSection(dis.readUTF());
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                List<String> content = new ArrayList<>();
                readWithException(dis, () -> content.add(dis.readUTF()));
                section = new ListSection(content);
                break;
            case EDUCATION:
            case EXPERIENCE:
                List<Organization> organizations = new ArrayList<>();
                readWithException(dis, () -> {
                    Link link = new Link(dis.readUTF(), dis.readUTF());
                    List<Organization.Experience> experiences = new ArrayList<>();
                    readWithException(dis, () -> experiences.add(new Organization.Experience(
                            dis.readUTF(),
                            readDate(dis),
                            readDate(dis),
                            dis.readUTF())));
                    organizations.add(new Organization(link, experiences));
                });
                section = new OrganizationSection(organizations);
                break;
            default:
        }
        return section;
    }

    private void writeDate(DataOutputStream dos, LocalDate date) throws IOException {
        dos.writeInt(date.getYear());
        dos.writeInt(date.getMonthValue());
        dos.writeInt(date.getDayOfMonth());
    }

    private LocalDate readDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt());
    }

    private interface Writer<T> {
        void write(T t) throws IOException;
    }

    private interface Reader {
        void read() throws IOException;
    }

    private <T> void writeWithException(DataOutputStream dos, Collection<T> collection, Writer<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T t : collection) {
            writer.write(t);
        }
    }

    private void readWithException(DataInputStream dis, Reader reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }
}