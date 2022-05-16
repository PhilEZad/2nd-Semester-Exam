package Application.BE;

import Application.DAL.TemplateMethod.Annotations.SQLColumn;
import Application.DAL.TemplateMethod.Annotations.SQLGetter;
import Application.DAL.TemplateMethod.Annotations.SQLSetter;
import Application.DAL.TemplateMethod.Annotations.SQLTable;

@SQLTable(name = "classes")
public class Class {

    @SQLColumn(name = "classId")
    private int id;

    @SQLColumn(name = "className")
    private String className;

    @SQLColumn(name = "teacherID")
    private int teacherID;

    @SQLColumn(name = "school")
    private int schoolID;

    public Class(int id, String className)
    {
        this.className = className;
        this.id = id;
    }

    public Class(int id, String className, int teacherID, int schoolID)
    {
        this.className = className;
        this.id = id;
        this.teacherID = teacherID;
        this.schoolID = schoolID;
    }

    @SQLGetter(name = "classId")
    public int getId()
    {
        return id;
    }

    @SQLSetter(name = "classId")
    public void setId(int id)
    {
        this.id = id;
    }


    @SQLGetter(name = "className")
    public String getName()
    {
        return className;
    }

    @SQLSetter(name = "className")
    public void setName(String name)
    {
        this.className = name;
    }


    @SQLGetter(name = "school")
    public int getSchoolID() {
        return schoolID;
    }

    @SQLSetter(name = "school")
    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }


    @SQLGetter(name = "teacherID")
    public int getTeacherID() {
        return teacherID;
    }

    @SQLSetter(name = "teacherID")
    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }
}
