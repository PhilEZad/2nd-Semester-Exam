package Application.BLL;

import Application.BE.Category;
import Application.BE.CategoryType;
import Application.BE.FunctionalEntry;

import java.util.HashMap;
import java.util.List;

public class ContentMapper
{
    public HashMap<Category, FunctionalEntry> getContent(CategoryType type, List<FunctionalEntry> entries)
    {
        if (type == null)
        {
            return getAllContent(entries);
        }

        return switch (type)
        {
            case HEALTH_CONDITION -> getHealthContent(entries);
            case FUNCTIONAL_ABILITY -> getFunctionalContent(entries);
            default -> getAllContent(entries);
        };
    }

    private HashMap<Category, FunctionalEntry> getAllContent(List<FunctionalEntry> entries)
    {
        var result = new HashMap<Category, FunctionalEntry>();

        result.putAll(getGeneralContent(entries));

        result.putAll(getFunctionalContent(entries));

        result.putAll(getHealthContent(entries));

        return result;
    }

    private void setContent(List<FunctionalEntry> entries)
    {
        getContent(null, null);
    }

    private HashMap<Category, FunctionalEntry> getGeneralContent(List<FunctionalEntry> entries)
    {
        getContent(null, null);
        return null;
    }

    private HashMap<Category, FunctionalEntry> getFunctionalContent(List<FunctionalEntry> entries)
    {
        return null;
    }

    private HashMap<Category, FunctionalEntry> getHealthContent(List<FunctionalEntry> entries)
    {
        return null;
    }
}
