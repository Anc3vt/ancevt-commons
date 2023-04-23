package com.ancevt.commons.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

@NoArgsConstructor
@AllArgsConstructor
public class TreeNode<T> {

    @Getter
    private final Map<String, Object> properties = new HashMap<>();

    @Getter
    private TreeNode<T> parent;

    private final List<TreeNode<T>> children = new ArrayList<>();

    @Getter
    @Setter
    private T value;

    private TreeNode(T value, Map<String, Object> properties) {
        this.value = value;
        getProperties().putAll(properties);
    }

    public void add(TreeNode<T> child) {
        children.add(child);
        child.parent = this;
    }

    public void addAll(Collection<TreeNode<T>> collection) {
        children.addAll(collection);
        collection.forEach(treeNode -> treeNode.parent = this);
    }


    public void remove(TreeNode<T> child) {
        children.remove(child);
        child.parent = null;
    }

    public void remove(int index) {
        children.remove(index).parent = null;
    }

    public void removeAll(Collection<TreeNode<T>> collection) {
        children.removeAll(collection);
        collection.forEach(treeNode -> treeNode.parent = null);
    }

    public void clear() {
        while (hasChildren()) {
            children.remove(0).parent = null;
        }
    }

    public TreeNode<T> get(int index) {
        return children.get(index);
    }

    public int size() {
        return children.size();
    }

    public boolean hasPrent() {
        return parent != null;
    }

    public boolean isRoot() {
        return !hasPrent();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean hasChildren() {
        return !isEmpty();
    }

    public String toTreeString() {
        return toTreeString(Object::toString);
    }

    public String toTreeString(Function<T, String> toTreeStringFunction) {
        return toTreeStringFunction.apply(this.getValue()) +
                System.lineSeparator() +
                walk(this, "", toTreeStringFunction);
    }

    private String walk(TreeNode<T> node, String prefix, Function<T, String> toTreeStringFunction) {
        StringBuilder stringBuilder = new StringBuilder();

        TreeNode<T> n;

        for (int index = 0; index < node.size(); index++) {
            n = node.get(index);

            if (index == node.size() - 1) {
                stringBuilder
                        .append(prefix)
                        .append("└─ ")
                        .append(toTreeStringFunction.apply(n.getValue()))
                        .append(System.lineSeparator());

                if (n.hasChildren()) {
                    stringBuilder.append(walk(n, prefix + "   ", toTreeStringFunction));
                }
            } else {
                stringBuilder
                        .append(prefix)
                        .append("├─ ")
                        .append(toTreeStringFunction.apply(n.getValue()))
                        .append(System.lineSeparator());

                if (n.hasChildren()) {
                    stringBuilder.append(walk(n, prefix + "│  ", toTreeStringFunction));
                }
            }
        }

        return stringBuilder.toString();
    }

    public static <T> TreeNode<T> of(T value) {
        return new TreeNode<>(value, Map.of());
    }

    public static <T> TreeNode<T> of(T value, Map<String, Object> properties) {
        return new TreeNode<>(value, properties);
    }

    public static void main(String[] args) {
        TreeNode<String> root = TreeNode.of("root", Map.of("type", "type1"));

        for (int i = 0; i < 3; i++) {
            TreeNode<String> level0 = TreeNode.of("level0_" + i, Map.of("type", "type2"));

            for (int j = 0; j < 4; j++) {
                TreeNode<String> level1 = TreeNode.of("level1_" + j, Map.of("type", "type3"));
                level0.add(level1);

                if (new Random().nextBoolean()) {
                    TreeNode<String> node = TreeNode.of("node_" + j, Map.of("type", "type3"));
                    level1.add(node);

                    if (new Random().nextBoolean()) {
                        TreeNode<String> node2 = TreeNode.of("node_" + j, Map.of("type", "type3"));
                        node.add(node2);
                    }
                }
            }

            root.add(level0);
        }

        System.out.println(root.toTreeString());
    }
}