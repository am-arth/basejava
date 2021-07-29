package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            Map<SectionType, AbstractSection> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                writeSection(dos, entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        int size;
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            size = dis.readInt();
            for (int i = 0; i < size; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.setSection(sectionType, readSection(dis, sectionType));
            }
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
                List<String> list = ((ListSection) section).getProgress();
                dos.writeInt(list.size());
                for (String item : list) {
                    dos.writeUTF(item);
                }
                break;
            case EDUCATION:
            case EXPERIENCE:
                List<Organization> organizations = ((OrganizationSection) section).getOrganizations();
                dos.writeInt(organizations.size());
                for (Organization organization : organizations) {
                    Link link = organization.getLink();
                    dos.writeUTF(link.getName());
                    dos.writeUTF(link.getUrl());
                    List<Organization.Experience> experiences = organization.getExperience();
                    dos.writeInt(experiences.size());
                    for (Organization.Experience experience : experiences) {
                        dos.writeUTF(experience.getFunction());
                        writeDate(dos, experience.getBeginDate());
                        writeDate(dos, experience.getEndDate());
                        dos.writeUTF(experience.getSpecification());
                    }
                }
                break;
        }
    }

    private AbstractSection readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        AbstractSection section = null;
        int size;
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                section = new TextSection(dis.readUTF());
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                size = dis.readInt();
                List<String> list = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    list.add(dis.readUTF());
                }
                section = new ListSection(list);
                break;
            case EDUCATION:
            case EXPERIENCE:
                size = dis.readInt();
                List<Organization> organizations = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    Link link = new Link(dis.readUTF(), dis.readUTF());
                    List<Organization.Experience> experiences = new ArrayList<>();
                    int count = dis.readInt();
                    for (int j = 0; j < count; j++) {
                        experiences.add(new Organization.Experience(
                                dis.readUTF(),
                                readDate(dis),
                                readDate(dis),
                                dis.readUTF()
                        ));
                    }
                    organizations.add(new Organization(link, experiences));
                }
                section = new OrganizationSection(organizations);
                break;
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
}