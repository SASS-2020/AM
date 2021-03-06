package com.example.attendancemanager_1;

import java.io.Serializable;

public class ClassesInfoHolder implements Serializable {
    private String subjectCode;
    private String subjectName;
    private String section;
    private String docID;

    public ClassesInfoHolder() {
    }

    public ClassesInfoHolder(String subjectCode, String subjectName, String section) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.section = section;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSection() {
        return section;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }
}
