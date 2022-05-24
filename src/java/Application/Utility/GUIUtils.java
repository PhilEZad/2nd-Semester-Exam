package Application.Utility;


import Application.BE.Category;
import Application.GUI.Models.AccountModel;
import Application.GUI.Models.CategoryEntryModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TreeItem;

import java.util.*;
import java.util.function.UnaryOperator;

public final class GUIUtils {

    private static AccountModel selectedStudent;
    private static AccountModel selectedTeacher;

    private GUIUtils() {
        // Private constructor to prevent instantiation
    }

    /**
     * Utility method for unwrapping the CategoryEntryModel from a TreeItem.
     *
     * @param root
     * @return a list of CategoryEntryModels present in the TreeItem root.
     */
    public static List<CategoryEntryModel> getTreeItemsFromRoot(TreeItem<CategoryEntryModel> root) {
        List<CategoryEntryModel> catList = new ArrayList<>(); //List to store the categories
        Thread thread = new Thread(() -> {

            ObservableList<TreeItem<CategoryEntryModel>> treeItems = root.getChildren(); //Get the children of the root
            for (TreeItem<CategoryEntryModel> treeItem : treeItems) { //For each child
                if (treeItem != null) {
                    if (treeItem.getChildren().size() > 0) { //If the child has children
                        catList.addAll(getTreeItemsFromRoot(treeItem)); //Get the children of the child
                    } else {
                        CategoryEntryModel categoryEntryModel = treeItem.getValue(); //Get the value of the child
                        catList.add(categoryEntryModel); //Add the value to the list
                    }

                }
            }
        });
        thread.run();
        return catList;
    }


    /**
     * And interger TextFormatter to only allow numbers to be entered.
     *
     * @return
     */
    public static UnaryOperator<TextFormatter.Change> getIntegerFilter() {
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            //if (newText.matches("-?([1-9][0-9]*)?")) {
            if (newText.matches("-?([1-9][0-9]*)?")) {

                return change;
            }
            return null;
        };

        return integerFilter;
    }

    /**
     * Applies a filter to a list view through a listener on the text field.
     * The list of items is filtered based on the text in the text field.
     * Items are automatically updated by a listener on the ListViews itemsProperty.
     *
     * @param searchField
     * @param listView
     * @param <T>
     */

    public static <T> FilteredList<T> searchListener(TextField searchField, ListView<T> listView) {
        //Wrap ObservableList of UserInfo in a FilteredList.
        FilteredList<T> filteredData = new FilteredList<>(listView.getItems(), b -> true);

        //Make sure the filtered list always contains the same elements as the original list.
        listView.itemsProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.getSource().clear();
            filteredData.addAll(newValue);
        });

        //Sets the filter predict when filter changes.
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(data -> {

                //If filter is empty, display all accounts.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                //Compare Account name with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (data.toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else return false;
            });
        });

        SortedList<T> sortedUsers = new SortedList<>(filteredData);
        listView.setItems(sortedUsers);
        return filteredData;
    }

    public static <T> void removeListListeners(ListView<T> listView, TextField searchField) {
        listView.itemsProperty().removeListener(searchListenerListView(listView));
        FilteredList<T> filteredData = new FilteredList<T>(listView.getItems(), b -> false);
        searchField.textProperty().removeListener(searchListenerSearchField(filteredData));

        ObservableList<T> items = listView.getItems();
        listView.setItems(items);

    }

    private static <T> ChangeListener<? super ObservableList<T>> searchListenerListView(ListView<T> listView) {
        return new ChangeListener<ObservableList<T>>() {
            @Override
            public void changed(ObservableValue<? extends ObservableList<T>> observable, ObservableList<T> oldValue, ObservableList<T> newValue) {
                FilteredList<T> filteredData = new FilteredList<>(listView.getItems(), b -> true);
                filteredData.clear();
                filteredData.addAll(newValue);
            }
        };
    }

    private static <T> ChangeListener<? super String> searchListenerSearchField(FilteredList<T> filteredData) {
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filteredData.setPredicate(data -> {

                    //If filter is empty, display all accounts.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    //Compare Account name with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (data.toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else return false;
                });
            }
        };
    }

            public static TreeItem<CategoryEntryModel> mapToTreeItem(Map<Category, CategoryEntryModel> map) {
                //Find the root category
                Category rootCategory = null;
                for (Map.Entry<Category, CategoryEntryModel> entry : map.entrySet()) {
                    if (entry.getKey().getParent() == null) {
                        rootCategory = entry.getKey();
                    }
                }

                TreeItem<CategoryEntryModel> treeRoot = new TreeItem<>(map.get(rootCategory));

                TreeItem<CategoryEntryModel> returnRoot = null;
                if (rootCategory != null) {
                    returnRoot = getChildrenToTreeItem(map, treeRoot, rootCategory.getChildren());
                    returnRoot = sortTreeItem(returnRoot);
                }

                return returnRoot;
            }

            private static TreeItem<CategoryEntryModel> getChildrenToTreeItem(Map<Category, CategoryEntryModel> map, TreeItem<CategoryEntryModel> parent, List<Category> children) {
                if (children.size() != 0) {
                    for (Category child : children) {
                        TreeItem<CategoryEntryModel> childTreeItem = new TreeItem<>(map.get(child));
                        parent.getChildren().add(childTreeItem);
                        getChildrenToTreeItem(map, childTreeItem, child.getChildren());
                    }
                }
                return parent;
            }

            private static TreeItem<CategoryEntryModel> sortTreeItem(TreeItem<CategoryEntryModel> treeItem) {
                if (treeItem.getChildren().size() > 0 && treeItem != null && treeItem.getValue() != null) {
                    treeItem.getChildren().sort(Comparator.comparing(o -> o.getValue().getCategoryName()));
                    for (TreeItem<CategoryEntryModel> child : treeItem.getChildren()) {
                        sortTreeItem(child);
                    }
                }
                return treeItem;
            }
}


