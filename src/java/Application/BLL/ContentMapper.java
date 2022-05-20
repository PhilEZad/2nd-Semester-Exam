package Application.BLL;

import Application.BE.Category;
import Application.BE.CategoryType;
import Application.BE.ContentEntry;

import java.util.HashMap;
import java.util.List;

public class ContentMapper
{
    public HashMap<Category, ContentEntry> getContent(CategoryType type, List<ContentEntry> entries)
    {
        if (type == null)
        {
            return getAllContent(entries);
        }

        return switch (type)
        {
            case HEALTH_CONDITION -> getHealthContent(entries);
            case FUNCTIONAL_ABILITY -> getFunctionalContent(entries);
            case GENERAL_INFORMATION -> getGeneralContent(entries);
            default -> getAllContent(entries);
        };
    }

    private HashMap<Category, ContentEntry> getAllContent(List<ContentEntry> entries)
    {
        var result = new HashMap<Category, ContentEntry>();

        result.putAll(getGeneralContent(entries));

        result.putAll(getFunctionalContent(entries));

        result.putAll(getHealthContent(entries));

        return result;
    }

    private void setContent(List<ContentEntry> entries)
    {
        getContent(null, null);
    }

    private HashMap<Category, ContentEntry> getGeneralContent(List<ContentEntry> entries)
    {
        getContent(null, null);
        return null;
    }

    private HashMap<Category, ContentEntry> getFunctionalContent(List<ContentEntry> entries)
    {
        return null;
    }

    private HashMap<Category, ContentEntry> getHealthContent(List<ContentEntry> entries)
    {
        return null;
    }
}
