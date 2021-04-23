package server;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task getAndRemoveNextTask() {
        return taskList.remove(0);
    }

    public int size() {
        return taskList.size();
    }

    @Override public String toString() {
        return taskList.toString();
    }
}
