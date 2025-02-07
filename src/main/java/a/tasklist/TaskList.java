package a.tasklist;
import java.util.ArrayList;

import a.task.*;

import a.storage.*;

public class TaskList {
    ArrayList<Task> list;
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }
    public int size(){
        return list.size();
    }
    public Task get(int index){
        return list.get(index);
    }
    public void add(Task task){
        list.add(task);
    }
    public Task remove(int index){
        return list.remove(index);
    }

    public ArrayList<Task> getTasks() {
        return list;
    }
    public void showTasks() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
    }
}
