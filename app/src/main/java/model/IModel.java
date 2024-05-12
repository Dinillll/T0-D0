package model;

import java.util.List;

public interface IModel {

    List<ToDoItem> getAllToDoItem();

    void removeToDoItem(ToDoItem tdi);

    void removeAllToDOItem();

    void saveOrUdpate(ToDoItem tdi);
}
