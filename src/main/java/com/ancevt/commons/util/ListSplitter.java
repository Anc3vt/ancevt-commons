package com.ancevt.commons.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class ListSplitter {

    public static <T> List<List<T>> split(List<T> sourceList, int partCount) {
        List<List<T>> result = new ArrayList<>(partCount);

        if (partCount > sourceList.size()) {
            return sourceList.stream().map(Arrays::asList).collect(Collectors.toList());
        }

        final int partSize = sourceList.size() / partCount;

        for (int i = 0; i < partCount; i++) {
            int rangeA = i * partSize;
            int rangeB = (i + 1) * partSize;

            boolean lastIter = i == partCount - 1;
            boolean hasElementLeft = rangeB < sourceList.size();

            if (lastIter && hasElementLeft) {
                rangeB = sourceList.size();
            }

            List<T> sublist = sourceList.subList(rangeA, rangeB);
            if (!sublist.isEmpty()) {
                result.add(new ArrayList<>(sublist));
            }
        }

        return result;
    }


    public static void main(String[] args) throws InterruptedException {
        final int partCount = 4;

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(Math.random() + "");
        }

        List<List<String>> s = split(list, 100);

        System.out.println(s);

    }


}
