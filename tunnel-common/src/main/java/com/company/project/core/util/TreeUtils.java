package com.company.project.core.util;

import com.company.project.core.model.Tree;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;


public class TreeUtils {

    protected TreeUtils() {

    }

    public static <T> Tree<T> build(List<Tree<T>> nodes) {
        if (nodes == null) {
            return null;
        }
        List<Tree<T>> topNodes = new ArrayList<>();
        nodes.forEach(children -> {
            String pid = children.getParentId();
            if (pid == null || "0".equals(pid)) {
                topNodes.add(children);
                return;
            }
            for (Tree<T> parent : nodes) {
                String id = parent.getId();
                if (id != null && id.equals(pid)) {
                    parent.getChildren().add(children);
                    children.setHasParent(true);
                    parent.setChildren(true);
                    return;
                }
            }
        });

        Tree<T> root = new Tree<>();
        root.setId("0");
        root.setParentId("");
        root.setHasParent(false);
        root.setChildren(true);
        root.setChecked(true);
        root.setChildren(topNodes);
        root.setText("根节点");
        Map<String, Object> state = new HashMap<>(16);
        state.put("opened", true);
        root.setState(state);
        return root;
    }

    public static <T> List<Tree<T>> buildList(List<Tree<T>> nodes, String idParam) {
        if (nodes == null) {
            return new ArrayList<>();
        }
        List<Tree<T>> topNodes = new ArrayList<>();
        nodes.forEach(children -> {
            String pid = children.getParentId();
            if (pid == null || idParam.equals(pid)) {
                topNodes.add(children);
                return;
            }
            nodes.forEach(parent -> {
                String id = parent.getId();
                if (id != null && id.equals(pid)) {
                    parent.getChildren().add(children);
                    children.setHasParent(true);
                    parent.setChildren(true);
                }
            });
        });
        return topNodes;
    }

    public static void createMapData(List<Map> list, String id, String name, String desc) {
        Map map = Maps.newHashMap();
        map.put("id", id);
        map.put("name", name);
        map.put("desc", desc != null ? desc : "");
        map.put("childrens", Lists.newArrayList());
        list.add(map);
    }

    //根据id去重
    public static List getList(List<Map> list, String key) {
        Set<Map> treeSet = new TreeSet<Map>(new Comparator<Map>() {
            @Override
            public int compare(Map o1, Map o2) {
                int compareTo = o1.get(key).toString().compareTo(o2.get(key).toString());
                return compareTo;
            }
        });
        treeSet.addAll(list);
        return Lists.newArrayList(treeSet);
    }

    public static void addListData(List<Map> list, String id, String name, String desc) {
        Map map = Maps.newHashMap();
        map.put("id", id);
        map.put("name", name);
        map.put("desc", desc != null ? desc : "");
        map.put("childrens", Lists.newArrayList());
        list.add(map);
    }

    public static Set addSetData(Set set, String id, String name, String desc) {

        Map map = Maps.newHashMap();
        map.put("id", id);
        map.put("name", name);
        map.put("desc", desc != null ? desc : "");
        map.put("childrens", Lists.newArrayList());
        set.add(map);
        return set;
    }

}