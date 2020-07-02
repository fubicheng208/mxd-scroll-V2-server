package com.fubic.scrollservercalculateservice.utils;


import com.fubic.model.IdAttr;

import java.util.*;

public class BFSCombination {

    public static final List<String> randomScroll = new ArrayList<>();

    static {
        randomScroll.add("3");
        randomScroll.add("4");
        randomScroll.add("9");
        randomScroll.add("10");
        randomScroll.add("11");
        randomScroll.add("12");
        randomScroll.add("13");
        randomScroll.add("14");
    }

    public static List<List<String>> getCombination(List<IdAttr> list, int target, int scrollNum) {
        List<List<String>> res = new ArrayList<>();
//        降序排序方便剪枝
        Collections.sort(list, new Comparator<IdAttr>() {
            @Override
            public int compare(IdAttr o1, IdAttr o2) {
                return o1.getAttr() - o2.getAttr();
            }
        });
        dfs(list, 0, target, new ArrayDeque<>(), res, scrollNum);
        for (List<String> ls : res) {
            Collections.sort(ls, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
        }
        return res;
    }

    public static void dfs(List<IdAttr> list, int begin, int rest, Deque<String> path, List<List<String>> res, int depth) {
        if (depth == 0) {
            if (rest == 0) {
                res.add(new ArrayList<>(path));
            }
            return;
        }
        //到目前为止卷轴太小，导致就算全取最大属性卷轴仍无法加满----剪枝1
        if (rest > depth * list.get(list.size() - 1).getAttr())
            return;
        for (int i = begin; i < list.size(); i++) {
//            //如果指定不包含X卷或星火卷，则dfs到X卷或星火卷时便剪枝
//            if(randomScroll.contains(list.get(i).getId()) && hasX==0)
//                break;
            // 当前卷轴太大剪枝---剪枝2,//剪枝1不能在此处剪枝，因为res-list.get(i).getAttr()大于，但是res-list.get(i+1).getAttr()更小了，不能保证大于
            if (rest - list.get(i).getAttr() < 0)
                break;
            //将卷轴id加入到路径中
            path.addLast(list.get(i).getId());
            dfs(list, i, rest - list.get(i).getAttr(), path, res, depth - 1);
            path.removeLast();
        }
    }
}
