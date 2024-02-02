/*
 * Copyright (C) 2023 niushuai233 niushuai.cc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cc.niushuai.demo.leetcode.T00002.两数相加;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 两数相加
 * <br/>
 * <url>https://leetcode.cn/problems/add-two-numbers/</url>
 * <pre>
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 *
 * 示例 2：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 *
 * 示例 3：
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 * </pre>
 *
 * @author niushuai233
 * @date 2024/2/2 9:33
 * @since 0.0.1
 */
public class AddTwoNumberSolution {

    private static final Logger log = LoggerFactory.getLogger(AddTwoNumberSolution.class);

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public int[] toArrayChain() {
            List<Integer> list = new ArrayList<Integer>();
            ListNode tmp = this;
            while (null != tmp) {
                list.add(tmp.val);
                tmp = tmp.next;
            }

            return list.stream().mapToInt(val -> val).toArray();
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        Long val = toNumberVal(l1) + toNumberVal(l2);
        char[] cArray = val.toString().toCharArray();
        int[] iArray = new int[cArray.length];
        for (int i = 0; i < cArray.length; i++) {
            iArray[cArray.length - 1 - i] = Integer.parseInt(String.valueOf(cArray[i]));
        }
        ListNode root = new ListNode(iArray[0]);
        nextNode(root, iArray, 1);
        return root;
    }

    private long toNumberVal(ListNode node) {
        List<Long> val = new ArrayList<>();
        ListNode tmp = node;
        while (tmp != null) {
            val.add((long) tmp.val);
            tmp = tmp.next;
        }
        Collections.reverse(val);
        return Long.valueOf(val.stream().map(item -> item.toString()).collect(Collectors.joining()));
    }


    private ListNode prepareNode(int[] nodeVals) {

        if (nodeVals.length == 1) {
            return new ListNode(nodeVals[0]);
        }

        ListNode root = new ListNode(nodeVals[0]);

        nextNode(root, nodeVals, 1);

        return root;
    }

    private ListNode nextNode(ListNode node, int[] nodeVals, int index) {
        if (index < nodeVals.length) {
            ListNode newNode = new ListNode(nodeVals[index]);
            node.next = newNode;
            ListNode nextNode = nextNode(newNode, nodeVals, index + 1);
            if (null == nextNode) {
                return node;
            }
            return nextNode;
        }
        return null;
    }


    @Test
    public void test() {
        log.info("{}", addTwoNumbers(prepareNode(new int[]{2, 4, 3}), prepareNode(new int[]{5, 6, 4})).toArrayChain());
        log.info("{}", addTwoNumbers(prepareNode(new int[]{0}), prepareNode(new int[]{0})).toArrayChain());
        log.info("{}", addTwoNumbers(prepareNode(new int[]{9, 9, 9, 9, 9, 9, 9}), prepareNode(new int[]{9, 9, 9, 9})).toArrayChain());

        Assertions.assertArrayEquals(new int[]{7, 0, 8}, addTwoNumbers(prepareNode(new int[]{2, 4, 3}), prepareNode(new int[]{5, 6, 4})).toArrayChain());
        Assertions.assertArrayEquals(new int[]{0}, addTwoNumbers(prepareNode(new int[]{0}), prepareNode(new int[]{0})).toArrayChain());
        Assertions.assertArrayEquals(new int[]{8, 9, 9, 9, 0, 0, 0, 1}, addTwoNumbers(prepareNode(new int[]{9, 9, 9, 9, 9, 9, 9}), prepareNode(new int[]{9, 9, 9, 9})).toArrayChain());
    }
}
