package Application.BLL;

import Application.BE.Category;

import java.util.List;

/**
 *  Abstract class for the EntryManagers
 *
 * @author Mads Mandahl-Barth
 * */
public abstract class AbstractEntryManager<T>
{
    public static List<Category> categoriesCache;

    AbstractEntryManager()
    {
        if (categoriesCache == null)
        {
            categoriesCache = new CategoryLoader().load();
        }
    }

    public abstract List<T> getEntriesFor(int id);
}
