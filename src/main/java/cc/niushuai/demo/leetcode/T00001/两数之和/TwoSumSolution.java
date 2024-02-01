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

package cc.niushuai.demo.leetcode.T00001.两数之和;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * <br/>
 * <url>https://leetcode.cn/problems/two-sum/description/</url>
 * <pre>
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 *
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 * </pre>
 *
 * @author niushuai233
 * @date 2024/2/1 17:13
 * @since 0.0.1
 */
public class TwoSumSolution {
    
    public static final Logger log = LoggerFactory.getLogger(TwoSumSolution.class);

    public int[] twoSum(int[] nums, int target) {
        if (nums.length == 2) {
            return new int[]{0,1};
        }
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[2];
    }

    @Test
    public void test() {
        TwoSumSolution solution = new TwoSumSolution();
        log.info("{}", solution.twoSum(new int[]{2, 7, 11, 15}, 9));
        log.info("{}", solution.twoSum(new int[]{3, 2, 4}, 6));
        log.info("{}", solution.twoSum(new int[]{3, 3}, 6));
    }
}
