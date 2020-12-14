package com.example.attendancemanager_1;

public class ClassesInfoHolder {
    private String subjectCode;
    private String subjectName;
    private String section;

    public ClassesInfoHolder(String subjectCode, String subjectName, String section)
    {
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

    public String getSection()
    {
        return section;
    }
}
