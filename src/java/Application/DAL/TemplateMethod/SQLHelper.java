package Application.DAL.TemplateMethod;

import Application.DAL.TemplateMethod.Annotations.SQLColumn;
import Application.DAL.TemplateMethod.Annotations.SQLGetter;
import Application.DAL.TemplateMethod.Annotations.SQLSetter;
import Application.DAL.TemplateMethod.Annotations.SQLTable;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.List;

public final class SQLHelper
{
    public static String getName(Annotation clazz)
    {
        if (clazz.annotationType() == SQLTable.class) {
            return ((SQLTable)clazz).name();
        } else if (clazz.annotationType() == SQLColumn.class) {
            return ((SQLColumn)clazz).name();
        } else if (clazz.annotationType() == SQLGetter.class) {
            return ((SQLGetter)clazz).name();
        } else if (clazz.annotationType() == SQLSetter.class) {
            return ((SQLSetter)clazz).name();
        }

        return null;
    }

    public static String tableName(Class<?> clazz)
    {
        if (clazz.isAnnotationPresent(SQLTable.class))
        {
            return SQLHelper.getName(clazz.getAnnotation(SQLTable.class));
        }

        return "";
    }


    public static String columnCSV(AnnotatedElement[] array, Class<? extends Annotation> annotationType)
    {
        StringBuilder entity = new StringBuilder();

        if (array.length >= 1 && array[0].isAnnotationPresent(annotationType))
        {
            entity.append(getName(array[0].getAnnotation(annotationType)));
        }

        for (int i = 1 ; i < array.length; i++)
        {
            if (array[i].isAnnotationPresent(annotationType))
            {
                if (!entity.isEmpty())
                    entity.append(", ");

                entity.append(getName(array[i].getAnnotation(annotationType)));
            }
        }

        return entity.toString();
    }

    public static List<String> columns(AnnotatedElement[] array, Class<? extends Annotation> annotationType)
    {
        var out = new ArrayList<String>();

        if (array.length >= 1 && array[0].isAnnotationPresent(annotationType))
        {
            out.add(getName(array[0].getAnnotation(annotationType)));
        }

        for (int i = 1 ; i < array.length; i++)
        {
            if (array[i].isAnnotationPresent(annotationType))
            {
                out.add(getName(array[i].getAnnotation(annotationType)));
            }
        }

        return out;
    }

    public static void createInsertStatement(String tableName, List<String> columns)
    {
        
        String parameters = "?, ".repeat(columns.size());
        //System.out.println("INSERT INTO " + tableName + " (" + columns + ") VALUES (" + a.substring(0, a.length() - 2) + ")");
        /*
        String csv = ;



         */
    }
}
