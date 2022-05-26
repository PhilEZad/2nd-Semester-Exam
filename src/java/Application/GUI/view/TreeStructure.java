package Application.GUI.view;

import Application.BE.Category;
import Application.GUI.Models.CategoryEntryModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.util.List;

public class TreeStructure<T> extends TreeItem<T>
{
    public TreeStructure(T categoryEntryModel)
    {
        super(categoryEntryModel);
    }

    private boolean isLeaf;
    private boolean isFirstTimeChildren = true;
    private boolean isFirstTimeLeaf = true;

    @Override public ObservableList<TreeItem<T>> getChildren()
    {
        if (isFirstTimeChildren)
        {
            isFirstTimeChildren = false;
            super.getChildren().setAll(buildChildren(this));
        }
        return super.getChildren();
    }

    @Override public boolean isLeaf() {
        if (isFirstTimeLeaf) {
            isFirstTimeLeaf = false;
            T entryModel = getValue();
            //isLeaf = entryModel.getContentEntry().getCategory().getChildren().isEmpty();
        }

        return isLeaf;
    }

    private ObservableList<TreeItem<T>> buildChildren(TreeItem<T> TreeItem)
    {
        T entryModel = TreeItem.getValue();

       // if (entryModel != null && !entryModel.getContentEntry().getCategory().getChildren().isEmpty())
        {
            //List<T> subcategories = entryModel.getContentEntry().getCategory().getChildren();

           // if (subcategories != null)
            {
                ObservableList<TreeItem<T>> children = FXCollections.observableArrayList();

                //for (T childFile : subcategories)
                {
                   // children.add(new TreeStructure(childFile));
                }

                return children;
            }
        }

      //  return FXCollections.emptyObservableList();
    }
}
