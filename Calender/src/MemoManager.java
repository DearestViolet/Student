import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MemoManager implements Serializable {
    private List<Memo> all_memos;

    public MemoManager(){
        all_memos = new ArrayList<>();
    }

    public List<Memo> getMemos() {
        return all_memos;
    }

    public void setMemos(List<Memo> memos) { this.all_memos = memos; }

    public Boolean createMemo(String title){
        if(findMemo(title) != null){
            return false;
        }
        else {
            Memo memo = new Memo(title);
            all_memos.add(memo);
            return true;
        }
    }
    public Boolean createMemo(String parentMemo, String title){
        if(createMemo(title)) {
            if (findMemo(parentMemo) != null) {
                findMemo(parentMemo).add(findMemo(title));
                return true;
            }
            return true;
        }
        return false;
    }

    public Memo findMemo(String title){
        for (Memo memo: all_memos){
            if (memo.getName().equals(title)){
                return memo;
            }
        }
        return null;
    }

    public void deleteMemo(String m){
        all_memos.removeIf(i -> (" "+ i.getName()).equals(m));
    }

    public List<Memo> findAssociatedMemos(Memo memo){
        ArrayList<Memo> temp = new ArrayList<>();
        temp.add(memo);
        try {
            temp.addAll(findAssociatedMemos(memo.getParent()));
        } catch (NullPointerException e) {
            temp.remove(temp.size()-1);
            return temp;
        }
        return temp;
    }

    public void addEvents(ArrayList<Reminder> event, int memo){
        all_memos.get(memo).add(event);
    }

    public Memo storeMemo(Memo memo){
        all_memos.add(memo);
        return null;
    }

    public void addMemo(int a, int b){
        if(a == b){
            System.out.println("Cannot add these memos due to hierachy issues.");
        }
        else {
            all_memos.get(b).setParent(all_memos.get(a));
            all_memos.get(a).add(all_memos.get(b));
        }

    }

    public Memo getMemo(int memo){
        return all_memos.get(memo);
    }

    public void viewAll(){
        int counter = 1;
        for(Memo memo: all_memos){
            System.out.println(counter + ". " + memo.getName());
            counter++;
        }
    }

    // update

    public void updateName(Memo memo, String name) {
        memo.setName(name);
    }

    public void addReminder(Memo memo, Reminder reminder) {
        memo.add(reminder);
    }

    public void removeReminder(Memo memo, Reminder reminder) {
        memo.remove(reminder);
    }

    public void updateParent(Memo memo, Memo parent) {
        memo.setParent(parent);
    }

    // remove
    public void removeMemo(Memo memo) {
        List<Memo> memos = this.getMemos();

        List<Memo> container = new ArrayList<>();
        container.add(memo);
        memos.removeAll(container);

    }
}

