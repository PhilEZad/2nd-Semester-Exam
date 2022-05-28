package Application.BLL;

import Application.BE.Category;

import java.util.List;

public abstract class ContentEntryManager<T>
{
    public static List<Category> categoriesCache;

    ContentEntryManager()
    {
        if (categoriesCache == null)
        {
            categoriesCache = new CategoryLoader().load();
        }
    }

    public abstract List<T> getEntriesFor(int id);
}
