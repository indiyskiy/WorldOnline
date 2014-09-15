package model.additionalentity.admin;

import java.util.ArrayList;

public class PagesArray {
    private int page = 0;
    private ArrayList<Integer> start = new ArrayList<>();
    private ArrayList<Integer> mid = new ArrayList<>();
    private ArrayList<Integer> end = new ArrayList<>();
    public static final int MAX_AT_ONE = 40;
    public static final int MAX_IN_PART = 5;

    public PagesArray(int pages, int page) {
        this.page = page;
        if (pages < MAX_AT_ONE) {
            setArray(start, 0, pages);
        } else {
            if (page <= MAX_IN_PART * 2) {
                setArray(start, 0, page + MAX_IN_PART * 2);
            } else {
                setArray(start, 0, MAX_IN_PART);
            }
            if (page >= pages - MAX_IN_PART * 2) {
                setArray(end, page - MAX_IN_PART * 2, pages);
            } else {
                setArray(end, pages - MAX_IN_PART, pages);
            }
            if (pages > page + 2 * MAX_IN_PART && page > MAX_IN_PART * 2) {
                setArray(mid, page - MAX_IN_PART, page + MAX_IN_PART);
            }
        }
    }

    public void setArray(ArrayList<Integer> array, int start, int end) {
        for (int i = start; i <= end; i++) {
            array.add(i);
        }
    }

    public String print(String prefix, String postfix) {
        if (start.size() + mid.size() + end.size() < 2) {
            return "";
        }
        String res = "Страница ";
        for (int page : start) {
            if (page != this.page) {
                res += (prefix.replaceAll("\\$\\{i\\}", String.valueOf(page)) + page + postfix.replaceAll("\\$\\{i\\}", String.valueOf(page)));
            } else {
                res += " " + page + " ";
            }
        }
        res += ("...");
        for (int page : mid) {
            if (page != this.page) {
                res += (prefix.replaceAll("\\$\\{i\\}", String.valueOf(page)) + page + postfix.replaceAll("\\$\\{i\\}", String.valueOf(page)));
            } else {
                res += " " + page + " ";
            }
        }
        res += ("...");
        for (int page : end) {
            if (page != this.page) {
                res += (prefix.replaceAll("\\$\\{i\\}", String.valueOf(page)) + page + postfix.replaceAll("\\$\\{i\\}", String.valueOf(page)));
            } else {
                res += " " + page + " ";
            }
        }
        if (mid.isEmpty()) {
            res = res.replaceAll("\\.\\.\\.\\.\\.\\.", "...");
        }
        if (start.isEmpty() || end.isEmpty()) {
            res = res.replaceAll("\\.\\.\\.", "");
        }
        return res;
    }

}
