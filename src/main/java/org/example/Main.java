package main.java.org.example;

import com.sun.source.tree.Tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


import java.util.*;


class Main {
    public static void main(String[] args) {
        return;
    }
}

class PathSumTwo {
    List<Integer> singlePath = new ArrayList<>();
    List<List<Integer>> allPaths = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        if (root == null) return allPaths;
        helper(root, target);
        return allPaths;
    }

     void helper(TreeNode root, int target) {
        if (root == null) return; // Base case

        singlePath.add(root.val);

        // Check if it's a leaf node and the target is met
        if (root.left == null && root.right == null && target == root.val) {
            allPaths.add(new ArrayList<>(singlePath)); // Add a copy of singlePath to allPaths
        }

        helper(root.left, target - root.val);
        helper(root.right, target - root.val);

        singlePath.remove(singlePath.size() - 1); // Backtrack to explore other paths
    }
}

class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return DFSTreeHeight(root, 0) != -1;
    }

    public int DFSTreeHeight(TreeNode node, int height) {
        if (node == null || height == -1) return height;
        int heightLeft = DFSTreeHeight(node.left, height + 1);
        int heightRight = DFSTreeHeight(node.right, height + 1);
        if (Math.abs(heightLeft - heightRight) > 1) return -1;

        return Math.max(heightLeft, heightRight);
    }
}

class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        return isSameTree(root.left, root.right);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        else if (p == null || q == null || p.val != q.val) return false;
        return isSameTree(p.left, q.right) && isSameTree(p.right, q.left);
    }
}

class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        else if (p == null || q == null || p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class SlidingWindowMaximum {
    public static void main(String[] args) {
        int[] arr = new int[] {1,-1};

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(maxSlidingWindow(arr, 1)));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        int[] result = new int[nums.length - k + 1];
        for (int j = 0; j < k; j++) {
            list.add(nums[j]);
        }
        quickSort(list, 0, k - 1);

        for (int i = 0; i < nums.length - k + 1; i++) {
        result[i] = list.get(k - 1);

        if (i != nums.length - k) {
            int index = Collections.binarySearch(list, nums[i]);
            //System.out.println("numToDel = " + nums[i]);
            //System.out.println("before = " + list);
            list.remove(index);
            //System.out.println("after = " + list);

            index = binarySearch(list, nums[i + k], k - 2);
            //System.out.println("num = " + nums[i + k]);
            //System.out.println("index = " + index);
            //System.out.println("before = " + list);
            list.add(index, nums[i + k]);
            //System.out.println("after = " + list);
        }
    }

        return result;
}

    public static int binarySearch(List<Integer> list, int num, int rightBorder) {
        int low = 0;
        int high = rightBorder;
        if (list.size() == 0) return 0;
        if (num <= list.get(0)) return 0;
        if (num >= list.get(rightBorder)) {
            return rightBorder + 1;
        }

        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (list.get(middle) >= num && list.get(middle - 1) <= num) {
                if (list.get(middle - 1) == num) return middle - 1;
                else if (list.get(middle) < num) return middle + 1;
                else return middle;
            }
            else if (list.get(middle) <= num) {
                low = middle + 1;
            }
            else {
                high = middle - 1;
            }
        }

        return 0;
    }


    public static void quickSort(List<Integer> nums, int low, int high) {
        if (nums.size() == 0 || low >= high) return;

        int middle = low + (high - low) / 2;
        int border = nums.get(middle);

        int i = low;
        int j = high;

        while (i <= j) {
            while (nums.get(i) < border) i++;
            while (nums.get(j) > border) j--;

            if (i <= j) {
                int temp = nums.get(i);
                nums.set(i, nums.get(j));
                nums.set(j, temp);
                i++;
                j--;
            }
        }

        if (low < j) quickSort(nums, low, j);
        if (high > i) quickSort(nums, i, high);
    }
}

class SlidingWindowMedian2 {
    public static void main(String[] args) {
        int[] arr = new int[] {9,7,0,3,9,8,6,5,7,6};

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(medianSlidingWindow(arr, 2)));
    }

    public static double[] medianSlidingWindow(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        double[] result = new double[nums.length - k + 1];
        for (int num : nums) {
            list.add(num);
        }
        quickSort(list, 0, k - 1);

        for (int i = 0; i < nums.length - k + 1; i++) {
            if (i != 0) {
                int index = binarySearch(list, nums[i + k - 1], k - 2);
                //System.out.println("num = " + nums[i + k - 1]);
                //System.out.println("index = " + index);
                //System.out.println("before = " + list);

                list.add(index, nums[i + k - 1]);
                //System.out.println("after = " + list);

                list.remove(k);
                //System.out.println("after = " + list);

            }
            if (k % 2 == 1) {
                result[i] = list.get(k / 2);
            } else {
                result[i] = list.get(k / 2) / 2.0 + list.get(k / 2 - 1) / 2.0;
            }

            if (i != nums.length - k) {
                //System.out.println("num = " + nums[i]);
                int index = binarySearch(list, nums[i], k - 1);
                //System.out.println("index = " + index);
                //System.out.println("before = " + list);

                list.remove(index);
                //System.out.println("after = " + list);
            }
        }

        return result;
    }

    public static int binarySearch(List<Integer> list, int num, int rightBorder) {
        int low = 0;
        int high = rightBorder;
        if (num <= list.get(0)) return 0;
        if (num >= list.get(rightBorder)) {
            return rightBorder + 1;
        }

        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (list.get(middle) >= num && list.get(middle - 1) <= num) {
                if (list.get(middle - 1) == num) return middle - 1;
                else if (list.get(middle) < num) return middle + 1;
                else return middle;
            }
            else if (list.get(middle) <= num) {
                low = middle + 1;
            }
            else {
                high = middle - 1;
            }
        }

        return 0;
    }


    public static void quickSort(List<Integer> nums, int low, int high) {
        if (nums.size() == 0 || low >= high) return;

        int middle = low + (high - low) / 2;
        int border = nums.get(middle);

        int i = low;
        int j = high;

        while (i <= j) {
            while (nums.get(i) < border) i++;
            while (nums.get(j) > border) j--;

            if (i <= j) {
                int temp = nums.get(i);
                nums.set(i, nums.get(j));
                nums.set(j, temp);
                i++;
                j--;
            }
        }

        if (low < j) quickSort(nums, low, j);
        if (high > i) quickSort(nums, i, high);
    }
}

//Quicksort and binary search, task will be completing too long
class SlidingWindowMedian {

    public static void main(String[] args) {
        int[] arr = new int[] {1,3,-1,-3,5,3,6,7};
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }

        quickSort(list, 0, 3);
        System.out.println(binarySearch(list, 4, 2));

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(medianSlidingWindow(arr, 3)));
    }

    public static void quickSort(List<Integer> nums, int low, int high) {
        if (nums.size() == 0 || low >= high) return;

        int middle = low + (high - low) / 2;
        int border = nums.get(middle);

        int i = low;
        int j = high;

        while (i <= j) {
            while (nums.get(i) < border) i++;
            while (nums.get(j) > border) j--;

            if (i <= j) {
                int temp = nums.get(i);
                nums.set(i, nums.get(j));
                nums.set(j, temp);
                i++;
                j--;
            }
        }

        if (low < j) quickSort(nums, low, j);
        if (high > i) quickSort(nums, i, high);
    }

    public static int binarySearch(List<Integer> list, int num, int rightBorder) {
        int low = 0;
        int high = rightBorder;
        if (num >= list.get(rightBorder)) return rightBorder;
        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (list.get(middle) >= num && list.get(middle - 1) <= num) return middle;
            else if (list.get(middle) <= num) {
                low = middle + 1;
            }
            else {
                high = middle - 1;
            }
        }

        return 0;
    }

    public static double[] medianSlidingWindow(int[] nums, int k) {
        List<Integer> newArr = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            newArr.add(nums[i]);
        }

        quickSort(newArr, 0, k - 1);
        System.out.println(newArr);

        double[] result = new double[nums.length - k + 1];

        for (int i = 0; i < nums.length - k; i++) {
            if (i != 0) {
                System.out.println(binarySearch(newArr, nums[k + i], k));
                newArr.add(nums[k + i], binarySearch(newArr, nums[k + i], k));
            }
            if (nums.length % 2 != 0) {
                result[i] = (newArr.get((i + k) / 2) + newArr.get((i + k) / 2) - 1) / 2.0;
            } else {
                result[i] = newArr.get((i + k) / 2);
            }

            newArr.remove(nums[i]);
        }

        return result;
    }
}

class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode node = new ListNode(0);

        for (int i = 1; i < 4; i++) {
            node = new ListNode(i, node);
        }
        System.out.println(node);
        System.out.println(reverseList(node));
    }

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}

class CharacterRepeating {
    public static void main(String[] args) {
        System.out.println(characterReplacement3("ABAABBBBB", 2));
    }

    public static int characterReplacement3(String s, int k) {
        // Initialising an empty array to store the count of the
        // characters in the given string s
        int[] arr = new int[26];
        int res = 0;
        int max = 0;

        // The left pointer for the sliding window is l AND r is the
        // right pointer
        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            // Counting the number of each character in the string s
            arr[s.charAt(r) - 'A']++;

            // Checking the character with max number of occurrence
            max = Math.max(max, arr[s.charAt(r) - 'A']);

            // Now we check if our current window is valid or not
            if (r - l + 1 - max > k) {
                // this means the no. of replacements is more than
                // allowed (k)
                // Decrementing the count of the character which was
                // at l because it is no longer in the window
                arr[s.charAt(l) - 'A']--;
                l++;
            }

            // The max our window can be
            res = Math.max(res, r - l + 1);
        }

        return res;
    }

    public static int characterReplacement2(String s, int k) {
        if (s.length() == 0) return 0;
        int result = 0;
        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            int letter = s.charAt(i) - 'A';
            if (list.isEmpty()) {
                list.add(Arrays.asList(letter, k, 1, i));
            } else {
                boolean addFlag = false;
                for (List<Integer> part: list) {
                    if (part.get(0) == -1) continue;
                    else if (part.get(0) == letter) {
                        part.set(2, part.get(2) + 1);
                    } else {
                        addFlag = true;
                        int partK = part.get(1);
                        if (partK > 0)  {
                            part.set(1, partK - 1);
                            part.set(2, part.get(2) + 1);
                        } else {
                            part.set(0, -1);
                        }
                    }
                    if (i == s.length() - 1) {
                        int partK = part.get(1);
                        int partI = part.get(3);
                        if (partK > 0 && partI > 0) {
                            int add = partI - partK;
                            if (add >= 0) {
                                add = partK;
                            } else {
                                add = partI;
                            }
                            part.set(2, part.get(2) + add);
                        }
                    }
                    result = Math.max(result, part.get(2));
                }
                if (addFlag) list.add(Arrays.asList(letter, k, 1, i));
            }
        }

        return result;
    }

    public static int characterReplacement(String s, int k) {
        if (s.length() == 0) return 0;
        int result = 1;

        char letter = s.charAt(0);
        int replacementsLeft = k;
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == letter) {
                count++;
            } else {
                if (replacementsLeft > 0) {
                    replacementsLeft--;
                    count++;
                } else {
                    count = 1;
                    replacementsLeft = k;
                    letter = s.charAt(i);
                }
            }
            if (count > result) result = count;

        }

        return result;
    }
}

class PartitionLabels {
    public static void main(String[] args) {
        System.out.println(partitionLabels("vhaagbqkaq"));
    }

    public static List<Integer> partitionLabels(String s) {
        int n = s.length();
        int end = 0; // the end of each max last endex
        int [] lastIndex = new int [26];
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++){
            lastIndex [s.charAt(i) - 'a'] = i;
            // this will be the last index of each characters
        }
        int prev = -1;
        for (int j = 0; j < n; j++) {
            end = Math.max(end, lastIndex[s.charAt(j) - 'a']);
            if (end == j) {
                ans.add(end - prev);
                prev = j;
            }
        }
        return ans;
    }

    /* funny
    public static void quickSort(int[][] sortArr, int low, int high) {
        if (sortArr.length == 0 || low >= high) return;

        int middle = low + (high - low) / 2;
        int border = sortArr[middle][0];

        int i = low, j = high;
        while (i <= j) {
            while (sortArr[i][0] < border) i++;
            while (sortArr[j][0] > border) j--;
            if (i <= j) {
                int[] swap = sortArr[i];
                sortArr[i] = sortArr[j];
                sortArr[j] = swap;
                i++;
                j--;
            }
        }

        if (low < j) quickSort(sortArr, low, j);
        if (high > i) quickSort(sortArr, i, high);
    }

    public static int[][] merge(int[][] intervals) {
        int[][] sortArr = intervals.clone();

        quickSort(sortArr, 0, sortArr.length - 1);

        int i = 1;
        int j = 0;
        int length = sortArr.length;
        while (i < length) {
            if (sortArr[j][1] >= sortArr[i][0]) {
                sortArr[j] = new int[] {sortArr[j][0], Math.max(sortArr[j][1], sortArr[i][1])};
                sortArr[i] = new int[] {-1, -1};

            } else {
                j++;
                sortArr[j] = sortArr[i];
                if (i != j) {
                    sortArr[i] = new int[]{-1, -1};
                }
            }

            i++;
        }

        int[][] ans = new int[j + 1][2];
        j = 0;
        for (int[] ints : sortArr) {
            if (ints[0] != -1) {
                ans[j] = ints;
                j++;
            }
        }
        return ans;
    }

    public static List<Integer> partitionLabels(String s) {
        HashMap<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character character = s.charAt(i);
            if (map.get(character) == null) {
                map.put(character, Arrays.asList(i, 0));
            } else {
                map.get(character).set(1, i);
            }
        }
        System.out.println(map);

        int[][] arr = new int[map.size()][2];
        int i = 0;
        for (Map.Entry<Character, List<Integer>> entry : map.entrySet()) {
            arr[i] = new int[] {entry.getValue().get(0), entry.getValue().get(1)};
            i++;
        }

        arr = merge(arr);
        List<Integer> result = new ArrayList<>();
        int previous = -1;

        for (int[] nums: arr) {
            if (nums[1] > 0) {
                result.add(nums[1] - previous);
                previous = nums[1];
            } else {
                result.add(nums[0] - previous);
                previous = nums[0];
            }
        }

        return result;
    }

     */
}

class MostWater {
    public static void main(String[] args) {
        int[] arr = new int[] {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(arr));
    }

    public static int maxArea(int[] height) {
        if(height.length == 2) return Math.min(height[0],height[1]);

        int max = 0;
        //find the maximum height value
        for(int i : height){
            if(i > max){
                max = i;
            }
        }

        int i = 0;
        int j = height.length - 1;
        int [] values = new int[max + 1]; // array for storing areas
        int levelIndex = 0;

        //iterate each time increasing the water level
        while (i < j){
            if(height[i] <= levelIndex){
                i++;
            }else if(height[j] <= levelIndex){
                j--;
            }else{
                values[levelIndex++] = (j - i) * levelIndex;
            }
        }

        int answer = 0;
        //find the maximum area value
        for(int temp : values){
            if (temp > answer) answer = temp;
        }

        return answer;
    }
}

class RemoveInvalidParentheses {
    public static void main(String[] args) {
        removeInvalidParentheses("())()()((a)");
    }

    public static List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        char[] check = new char[]{'(', ')'};
        dfs(s, res, check, 0, 0);
        return res;
    }

    public static void dfs(String s, List<String> res, char[] check, int last_i, int last_j) {
        int count = 0;
        int i = last_i;
        while (i < s.length() && count >= 0) {

            if (s.charAt(i) == check[0]) count++;
            if (s.charAt(i) == check[1]) count--;
            i++;
        }

        if (count >= 0) {
            // no extra ')' is detected. We now have to detect extra '(' by reversing the string.
            String reversed = new StringBuffer(s).reverse().toString();
            if (check[0] == '(') dfs(reversed, res, new char[]{')', '('}, 0, 0);
            else res.add(reversed);

        } else {  // extra ')' is detected and we have to do something
            i -= 1; // 'i-1' is the index of abnormal ')' which makes count<0
            for (int j = last_j; j <= i; j++) {
                if (s.charAt(j) == check[1] && (j == last_j || s.charAt(j - 1) != check[1])) {
                    dfs(s.substring(0, j) + s.substring(j + 1, s.length()), res, check, i, j);
                }
            }
        }
    }
}

class TopKFrequentElements {
    public static void main(String[] args) {
        int[] nums = new int[] {1,1,1,2,2,3};
        System.out.println(Arrays.toString(topKFrequent(nums, 2)));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue()
        );
        int[] ans = new int[k];

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.offer(entry);
        }

        for (int i = 0; i < k; i++) {
            ans[i] = pq.poll().getKey();
        }

        return ans;
    }
}

class TopKFrequentWords {
    public static void main(String[] args) {
       String[] words = new String[] {"i","love","leetcode","i","love","coding"};
        System.out.println(topKFrequent2(words, 2));
    }

    public static List<String> topKFrequent2(String[] words, int k) {
        // Step 1: Count the frequency of each word
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        // Step 2: Use a PriorityQueue to keep the top k frequent words
        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>(
                (a, b) -> {
                    if (a.getValue().equals(b.getValue())) {
                        return a.getKey().compareTo(b.getKey()); // lexicographically order
                    }
                    return b.getValue() - a.getValue(); // frequency order
                }
        );

        // Step 3: Add elements to the heap
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            maxHeap.offer(entry);
        }

        // Step 4: Extract the top k frequent words from the heap
        List<String> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(maxHeap.poll().getKey());
        }

        return result;
    }

    //very bad
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String str : words) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        List<String> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            String a = (map.entrySet().stream()
                    .sorted(Collections
                            .reverseOrder(
                                    Map.Entry.<String, Integer>comparingByValue())
                            .thenComparing(Map.Entry.comparingByKey()))).findFirst().get().getKey();
            map.remove(a);
            ans.add(a);
        }
        return ans;
    }
}

class MergeIntervals {
    public static void quickSort(int[][] sortArr, int low, int high) {
        if (sortArr.length == 0 || low >= high) return;

        int middle = low + (high - low) / 2;
        int border = sortArr[middle][0];

        int i = low, j = high;
        while (i <= j) {
            while (sortArr[i][0] < border) i++;
            while (sortArr[j][0] > border) j--;
            if (i <= j) {
                int[] swap = sortArr[i];
                sortArr[i] = sortArr[j];
                sortArr[j] = swap;
                i++;
                j--;
            }
        }

        if (low < j) quickSort(sortArr, low, j);
        if (high > i) quickSort(sortArr, i, high);
    }

    public static void main(String[] args) {
        int[][] sortArr = new int[][] {{2,3},{2,2},{3,3},{1,3},{5,7},{2,2},{4,6}};
        System.out.println(Arrays.deepToString(merge(sortArr)));
    }

    public static int[][] merge(int[][] intervals) {
        int[][] sortArr = intervals.clone();

        quickSort(sortArr, 0, sortArr.length - 1);

        int i = 1;
        int j = 0;
        int length = sortArr.length;
        while (i < length) {
            if (sortArr[j][1] >= sortArr[i][0]) {
                sortArr[j] = new int[] {sortArr[j][0], Math.max(sortArr[j][1], sortArr[i][1])};
                sortArr[i] = new int[] {-1, -1};

            } else {
                j++;
                sortArr[j] = sortArr[i];
                if (i != j) {
                    sortArr[i] = new int[]{-1, -1};
                }
            }

            i++;
        }

        int[][] ans = new int[j + 1][2];
        j = 0;
        for (int[] ints : sortArr) {
            if (ints[0] != -1) {
                ans[j] = ints;
                j++;
            }
        }
        return ans;
    }
}

class NumberOfIslands {
    public static void main(String[] args) {
        String str = (
                "[[\"1\",\"0\",\"0\",\"1\",\"1\",\"1\",\"0\",\"1\",\"1\",\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",\"0\"],[\"1\",\"0\",\"0\",\"1\",\"1\",\"0\",\"0\",\"1\",\"0\",\"0\",\"0\",\"1\",\"0\",\"1\",\"0\",\"1\",\"0\",\"0\",\"1\",\"0\"],[\"0\",\"0\",\"0\",\"1\",\"1\",\"1\",\"1\",\"0\",\"1\",\"0\",\"1\",\"1\",\"0\",\"0\",\"0\",\"0\",\"1\",\"0\",\"1\",\"0\"],[\"0\",\"0\",\"0\",\"1\",\"1\",\"0\",\"0\",\"1\",\"0\",\"0\",\"0\",\"1\",\"1\",\"1\",\"0\",\"0\",\"1\",\"0\",\"0\",\"1\"],[\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",\"1\",\"1\",\"1\",\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",\"0\"],[\"1\",\"0\",\"0\",\"0\",\"0\",\"1\",\"0\",\"1\",\"0\",\"1\",\"1\",\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",\"1\",\"0\",\"1\"],[\"0\",\"0\",\"0\",\"1\",\"0\",\"0\",\"0\",\"1\",\"0\",\"1\",\"0\",\"1\",\"0\",\"1\",\"0\",\"1\",\"0\",\"1\",\"0\",\"1\"],[\"0\",\"0\",\"0\",\"1\",\"0\",\"1\",\"0\",\"0\",\"1\",\"1\",\"0\",\"1\",\"0\",\"1\",\"1\",\"0\",\"1\",\"1\",\"1\",\"0\"],[\"0\",\"0\",\"0\",\"0\",\"1\",\"0\",\"0\",\"1\",\"1\",\"0\",\"0\",\"0\",\"0\",\"1\",\"0\",\"0\",\"0\",\"1\",\"0\",\"1\"],[\"0\",\"0\",\"1\",\"0\",\"0\",\"1\",\"0\",\"0\",\"0\",\"0\",\"0\",\"1\",\"0\",\"0\",\"1\",\"0\",\"0\",\"0\",\"1\",\"0\"],[\"1\",\"0\",\"0\",\"1\",\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",\"1\",\"0\",\"0\",\"1\",\"0\",\"1\",\"0\",\"1\",\"0\"],[\"0\",\"1\",\"0\",\"0\",\"0\",\"1\",\"0\",\"1\",\"0\",\"1\",\"1\",\"0\",\"1\",\"1\",\"1\",\"0\",\"1\",\"1\",\"0\",\"0\"],[\"1\",\"1\",\"0\",\"1\",\"0\",\"0\",\"0\",\"0\",\"1\",\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",\"1\",\"0\",\"0\",\"0\",\"1\"],[\"0\",\"1\",\"0\",\"0\",\"1\",\"1\",\"1\",\"0\",\"0\",\"0\",\"1\",\"1\",\"1\",\"1\",\"1\",\"0\",\"1\",\"0\",\"0\",\"0\"],[\"0\",\"0\",\"1\",\"1\",\"1\",\"0\",\"0\",\"0\",\"1\",\"1\",\"0\",\"0\",\"0\",\"1\",\"0\",\"1\",\"0\",\"0\",\"0\",\"0\"],[\"1\",\"0\",\"0\",\"1\",\"0\",\"1\",\"0\",\"0\",\"0\",\"0\",\"1\",\"0\",\"0\",\"0\",\"1\",\"0\",\"1\",\"0\",\"1\",\"1\"],[\"1\",\"0\",\"1\",\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",\"1\",\"0\",\"0\",\"0\",\"1\",\"0\",\"1\",\"0\",\"0\",\"0\",\"0\"],[\"0\",\"1\",\"1\",\"0\",\"0\",\"0\",\"1\",\"1\",\"1\",\"0\",\"1\",\"0\",\"1\",\"0\",\"1\",\"1\",\"1\",\"1\",\"0\",\"0\"],[\"0\",\"1\",\"0\",\"0\",\"0\",\"0\",\"1\",\"1\",\"0\",\"0\",\"1\",\"0\",\"1\",\"0\",\"0\",\"1\",\"0\",\"0\",\"1\",\"1\"],[\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",\"1\",\"1\",\"1\",\"1\",\"0\",\"1\",\"0\",\"0\",\"0\",\"1\",\"1\",\"0\",\"0\",\"0\"]]"
        )
                        .replace("[", "{").replace("]", "}").replace("\"","'").concat(";");
        System.out.println(str);


        char[][] grid = new char[][] {{'1','0','0','1','1','1','0','1','1','0','0','0','0','0','0','0','0','0','0','0'},{'1','0','0','1','1','0','0','1','0','0','0','1','0','1','0','1','0','0','1','0'},{'0','0','0','1','1','1','1','0','1','0','1','1','0','0','0','0','1','0','1','0'},{'0','0','0','1','1','0','0','1','0','0','0','1','1','1','0','0','1','0','0','1'},{'0','0','0','0','0','0','0','1','1','1','0','0','0','0','0','0','0','0','0','0'},{'1','0','0','0','0','1','0','1','0','1','1','0','0','0','0','0','0','1','0','1'},{'0','0','0','1','0','0','0','1','0','1','0','1','0','1','0','1','0','1','0','1'},{'0','0','0','1','0','1','0','0','1','1','0','1','0','1','1','0','1','1','1','0'},{'0','0','0','0','1','0','0','1','1','0','0','0','0','1','0','0','0','1','0','1'},{'0','0','1','0','0','1','0','0','0','0','0','1','0','0','1','0','0','0','1','0'},{'1','0','0','1','0','0','0','0','0','0','0','1','0','0','1','0','1','0','1','0'},{'0','1','0','0','0','1','0','1','0','1','1','0','1','1','1','0','1','1','0','0'},{'1','1','0','1','0','0','0','0','1','0','0','0','0','0','0','1','0','0','0','1'},{'0','1','0','0','1','1','1','0','0','0','1','1','1','1','1','0','1','0','0','0'},{'0','0','1','1','1','0','0','0','1','1','0','0','0','1','0','1','0','0','0','0'},{'1','0','0','1','0','1','0','0','0','0','1','0','0','0','1','0','1','0','1','1'},{'1','0','1','0','0','0','0','0','0','1','0','0','0','1','0','1','0','0','0','0'},{'0','1','1','0','0','0','1','1','1','0','1','0','1','0','1','1','1','1','0','0'},{'0','1','0','0','0','0','1','1','0','0','1','0','1','0','0','1','0','0','1','1'},{'0','0','0','0','0','0','1','1','1','1','0','1','0','0','0','1','1','0','0','0'}};
        //char[][] grid = new char[][] {{'1','0','0'},{'0','1','0'},{'0','0','1'}};

        StringBuilder visual = new StringBuilder();
        for (char[] chars : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                visual = new StringBuilder(visual.toString().concat((chars[j]) + " "));
            }
            visual.append("\n");
        }
        System.out.println(visual);
        System.out.println("answer = " + numIslands(grid));
    }

    public static int numIslands(char[][] grid) {
        char[][] gridCopy = grid.clone();
        int answer = 0;
        if (gridCopy.length < 1) return 0;

        for (int i = 0; i < gridCopy.length; i++) {
            for (int j = 0; j < gridCopy[0].length; j++) {

                if (gridCopy[i][j] == '1') {
                    islandFinder(gridCopy, i, j);
                    answer++;
                }
            }
        }

        return answer;
    }

    public static void islandFinder(char[][] grid, int i, int j) {
        grid[i][j] = '0';
        if (j < grid[0].length - 1 && grid[i][j+1] == '1') islandFinder(grid, i, j + 1);
        if (i < grid.length - 1 && grid[i+1][j] == '1') islandFinder(grid, i + 1, j);
        if (j > 0 && grid[i][j-1] == '1') islandFinder(grid, i, j - 1);
        if (i > 0 && grid[i-1][j] == '1') islandFinder(grid, i - 1, j);
    }
}

class IsValid {
    public static void main(String[] args) {
        System.out.println(isValidMethod4("()"));
    }

    public static boolean isValidMethod4(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }

    public static boolean isValidMethod3(String s) {
        String str = s;
        int counter = 0;
        int strIndex = -1;
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case ')' -> {
                    if (strIndex < 0 || str.charAt(strIndex) != '(') return false;
                    else {
                        StringBuilder tempStr = new StringBuilder(str);
                        tempStr.deleteCharAt(strIndex);
                        tempStr.deleteCharAt(strIndex);
                        str = tempStr.toString();
                        strIndex--;
                        strIndex--;
                        counter--;

                    }
                }
                case ']' -> {
                    if (strIndex < 0 || str.charAt(strIndex) != '[') return false;
                    else {
                        StringBuilder tempStr = new StringBuilder(str);
                        tempStr.deleteCharAt(strIndex);
                        tempStr.deleteCharAt(strIndex);
                        str = tempStr.toString();
                        strIndex--;
                        strIndex--;
                        counter--;

                    }
                }
                case '}' -> {
                    if (strIndex < 0 || str.charAt(strIndex) != '{') return false;
                    else {
                        StringBuilder tempStr = new StringBuilder(str);
                        tempStr.deleteCharAt(strIndex);
                        tempStr.deleteCharAt(strIndex);
                        str = tempStr.toString();
                        strIndex--;
                        strIndex--;
                        counter--;
                    }
                }
                default -> counter++;
            }

            strIndex++;
        }

        if (counter != 0) return false;
        return true;
    }

    public static boolean isValidMethod2(String s) {
        int firstTypeAmount = 0;
        int secondTypeAmount = 0;
        int thirdTypeAmount = 0;
        int currBracket = 0;

        for (int i = 0; i < s.length(); i++) {
            String letter = String.valueOf(s.charAt(i));
            if (i == 0) {
                switch (letter) {
                    case "(" -> {
                        currBracket = 1;
                        firstTypeAmount++;
                    }
                    case "[" -> {
                        currBracket = 2;
                        secondTypeAmount++;
                    }
                    case "{" -> {
                        currBracket = 3;
                        thirdTypeAmount++;
                    }
                    default -> {
                        return false;
                    }
                }
                continue;
            }

            int tempBracket = currBracket;
            switch (letter) {
                case "(" -> {
                    currBracket = 1;
                    firstTypeAmount++;
                }
                case "[" -> {
                    currBracket = 2;
                    secondTypeAmount++;
                }
                case "{" -> {
                    currBracket = 3;
                    thirdTypeAmount++;
                }
                case ")" -> {
                    currBracket = -1;
                    firstTypeAmount--;
                }
                case "]" -> {
                    currBracket = -2;
                    secondTypeAmount--;
                }
                case "}" -> {
                    currBracket = -3;
                    thirdTypeAmount--;
                }
            }

            if (currBracket < 0 && Math.abs(currBracket) != Math.abs(tempBracket)) return false;
        }

        return true;
    }

    public static boolean isValidMethod(String s) {
        String s1 = s.concat("9");

        System.out.println(Arrays.toString(s1.split("\\(")).length());
        System.out.println(Arrays.toString(s1.split("\\)")).length());
        System.out.println(Arrays.toString(s1.split("\\[")).length());
        System.out.println(Arrays.toString(s1.split("\\]")));


        if (s1.split("\\(").length != s1.split("\\)").length) return false;
        if (s1.split("\\[").length != s1.split("\\]").length) return false;

        return true;
    }
}

class ValidAnagram {
    public static void main(String[] args) {
        System.out.println(findAnagrams("abab", "ab"));
    }

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < s.length() - p.length() + 1; i++) {
            if (isAnagram(s.substring(i, i + p.length()), p)) {
                answer.add(i);
            }
        }

        return answer;
    }

    public static boolean isAnagram(String s, String t) {
        int[] count = new int[26];

        for (char x : s.toCharArray()) {
            count[x - 'a']++;
        }

        for (char x : t.toCharArray()) {
            count[x - 'a']--;
        }

        for (int val : count) {
            if (val != 0) {
                return false;
            }
        }

        return true;
    }
}

class GroupAnagram {

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> answer = new ArrayList<>();
        if (strs.length == 0) return answer;
        HashMap<String, List<String>> tempMap = new HashMap<>();
        for (String word: strs) {
            char[] charWord = word.toCharArray();
            Arrays.sort(charWord);
            String sortedWord = Arrays.toString(charWord);
            tempMap.putIfAbsent(sortedWord, new ArrayList<>());
            tempMap.get(sortedWord).add(word);
        }

        answer.addAll(tempMap.values());
        return answer;
    }
}

class HashTable {

    public int singleNumber(int[] nums) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int e:nums){
            map.put(e,map.getOrDefault(e,0)+1);
        }
        for(Map.Entry<Integer,Integer>entry:map.entrySet()){
            if(entry.getValue()!=2){
                return entry.getKey();
            }
        }
        return 0;
    }
}

class GuessGame {

    int guess(int num) {
        return 0;
    }

    public int guessNumber(int n) {
        int low = 1;
        int high = n;
        while (low <= high) {
            int mid = (low + high ) / 2;
            int res = guess(mid);
            if (res == -1) {
                high = mid - 1;
            } else if (res == 1) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {6,6,6,6,6,6,7,7,7,7,7,8,8,8,8,9,9,9,9,10,10,10,-10,-10,-10,-10,-10,-9,-9,-9,-9,-8,-8,-8,-7,-7,-7,-6,-6,-5,-5,-5,-5,-5,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-3,-3,-3,-3,-3,-2,-2,-2,-1,-1,-1,-1,0,0,0,0,0,1,1,1,1,2,2,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,5,5,5,5,5,6,6};
        int target = 8;
        System.out.println(searchBoolean(nums, target));
    }

    public static boolean searchBoolean(int[] nums, int target) {
        int low =0;
        int high = nums.length-1;

        while(low<=high){

            int mid = low + (high-low)/2;

            if(nums[mid]==target) return true;

            if(nums[low]==nums[mid]&& nums[mid]==nums[high]) {
                low++;
                high--;
                continue;
            }

            else if (nums[low]<= nums[mid]){

                if(target>=nums[low] && target<= nums[mid]){
                    high = mid-1;
                }
                else{
                    low = mid+1;
                }
            }
            else{

                if(target>=nums[mid] && target<=nums[high]){
                    low= mid+1;
                }
                else{
                    high = mid-1;
                }
            }
        }
        return false;
    }


    public static int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int leftBorder = nums[low];
        int answer = nums[0];
        if (nums[low] <= nums[high]) return answer;


        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] < nums[low]) {
                if (nums[mid] < answer) answer = nums[mid];
                high = mid - 1;
            } else if (nums[mid] >= nums[low]) {
                if (nums[mid] >= leftBorder) {
                    low = mid + 1;
                } else if (nums[mid] < leftBorder) {
                    if (nums[mid] < answer) answer = nums[mid];
                    high = mid - 1;
                }
            }
        }

        return answer;
    }

    public static int search(int[] nums, int target) {
        int leftBorder = nums[0];
        int rightBorder = nums[nums.length - 1];

        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < nums[low]) {
                if (target > nums[mid]) {
                    if (target > rightBorder) {
                        high = mid - 1;
                    } else if (target <= rightBorder) {
                        low = mid + 1;
                    }
                } else if (target < nums[mid]) {
                    high = mid - 1;
                }

            } else if (nums[mid] >= nums[low]){
                if (target > nums[mid]) {
                    low = mid + 1;
                } else if (target < nums[mid]) {
                    if (target < leftBorder) {
                        if (nums[low] <= target) {
                            high = mid - 1;
                        } else {
                            low = mid + 1;
                        }
                    } else if (target >= leftBorder) {
                        high = mid - 1;
                    }
                }
            }
        }

        return -1;
    }
}

class MergeKLists {
    public static void main(String[] args) {
        int min = 80;
        int max = 90;
        ListNode[] lists = new ListNode[2];
        ListNode firstCurrent = new ListNode(100);
        ListNode secondCurrent = new ListNode(100);
        for (int i = 0; i < 2; i++) {
            Random random = new Random();
            firstCurrent = new ListNode(random.ints(min, max).findFirst().getAsInt(), firstCurrent);
            secondCurrent = new ListNode(random.ints(min, max).findFirst().getAsInt(), secondCurrent);
            min-=10;
            max-=10;
        }
        lists[0] = firstCurrent;
        lists[1] = secondCurrent;
        System.out.println(firstCurrent);
        System.out.println(secondCurrent);
        mergeKLists(lists);
        System.out.println(Arrays.toString(lists));
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return divide(lists, 0, lists.length - 1);
    }

    public static ListNode divide(ListNode[] lists, int lo, int hi) {
        if (lo == hi) return lists[lo];
        int m = lo + (hi - lo) / 2;
        ListNode a = divide(lists, lo, m);
        ListNode b = divide(lists, m + 1, hi);
        return merge(a, b);
    }

    public static ListNode merge(ListNode a, ListNode b) {
        if (a == null) return b;
        if (b == null) return a;
        if (a.val < b.val) {
            a.next = merge(a.next, b);
            return a;
        }
        b.next = merge(a, b.next);
        return b;
    }
}

class SlyCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // создаём объект класса Scanner
        int i = 0;
        i = sc.nextInt();

        String[] arr = new String[i];
        for (int j = 0; j < i; j++) {
            arr[j] = sc.next();
        }

        for (int j = 0; j < i; j++) {
            int firstLetter = (26 - ('z' - arr[j].toLowerCase().charAt(0))) * 256;
            //System.out.println("first letter = " + firstLetter);

            HashSet<Character> lettersSet = new HashSet<>();
            String[] list = arr[j].split(",");
            int dayNum = list[3].charAt(0) - '0';
            if (list[3].length() > 1) {
                dayNum += list[3].charAt(1) - '0';
            }
            int monthNum = list[4].charAt(0) - '0';
            if (list[4].length() > 1) {
                monthNum += list[4].charAt(1) - '0';
            }
            int birthDateSum = (dayNum + monthNum) * 64;
            //System.out.println("birth = " + birthDateSum);

            for (int u = 0; u < 3; u++) {
                for (int k = 0; k < list[u].length(); k++) {
                    lettersSet.add(list[u].charAt(k));
                }
            }
            //System.out.println("letters = " + lettersSet.size());

            int num = firstLetter + lettersSet.size() + birthDateSum;
            String result = Integer.toHexString(num).toUpperCase();
            //System.out.println(result);

            char[] ans = new char[3];
            if (result.length() > 3) {
                result.getChars(result.length() - 3, result.length(), ans, 0);
            } else if (result.length() < 3) {
                for (int k = 0; k < 3; k++) {
                    ans[k] = '0';
                }

                for (int k = 2; k > 3 - result.length(); k--) {
                    ans[k] = result.charAt(k - 1);
                }
            }  else {
                ans = result.toCharArray();
            }
            System.out.println(ans);
        }
    }
}

class RocketTaxi {
    public static void main(String[] args) {
        String accepted = "A";
        String success = "S";
        String cancelled = "C";

        Scanner sc = new Scanner(System.in); // создаём объект класса Scanner
        int i = sc.nextInt();

        String[] arr = new String[i];
        for (int j = 0; j < i; j++) {
            String str = "";
            for (int k = 0; k < 5; k++) {
                str = str.concat(sc.next());
                if (k != 4) {
                    str = str.concat(" ");
                }
            }
            arr[j] = str;
        }
        Arrays.sort(arr);

        HashMap<String, String> rocketMap = new HashMap<>();
        HashMap<String, Integer> timeRocket = new HashMap<>();

        for (int j = 0; j < i; j++) {
            String[] lines = arr[j].split(" ");
            String timeLine = "";
            for (int k = 0; k < 3; k++) {
                timeLine = timeLine.concat(lines[k]);
                timeLine = timeLine.concat(" ");
            }

            if (Objects.equals(lines[4], accepted)) {
                rocketMap.put(lines[3], timeLine);
            } else if (Objects.equals(lines[4], success) || Objects.equals(lines[4], cancelled)) {
                int timeCount = calculateTime(rocketMap, timeLine, lines[3]);
                Integer additionalTime = timeRocket.get(lines[3]);
                if (additionalTime != null) {
                    timeCount += additionalTime;
                }
                timeRocket.put(lines[3], timeCount);
            }
        }

        int[] arrangement = new int[timeRocket.size()];
        int l = 0;
        for (Map.Entry<String, Integer> entry : timeRocket.entrySet()) {
            int key = Integer.parseInt(entry.getKey());
            arrangement[l] = key;
            l++;
        }
        Arrays.sort(arrangement);

        for (int j = 0; j < arrangement.length; j++) {
            System.out.print(timeRocket.get(String.valueOf(arrangement[j])));
            if (j != arrangement.length - 1) {
                System.out.print(" ");
            }
        }
    }

    public static int calculateTime(HashMap<String, String> map, String timeLine, String index) {
        String[] start = map.get(index).split(" ");
        String[] end = timeLine.split(" ");

        return (Integer.parseInt(end[0]) - Integer.parseInt(start[0])) * 24 * 60 +
                (Integer.parseInt(end[1]) - Integer.parseInt(start[1])) * 60 +
                (Integer.parseInt(end[2]) - Integer.parseInt(start[2]));
    }
}

class Substring {
    public static int lengthOfLongestSubstringSecond(String s) {
        int lenOfString = s.length();
        int ans = 0;
        if (lenOfString != 0) {
            ans = 1;
        }
        for (int i = 0; i < lenOfString; i++) {
            HashSet<Character> setOfChars = new HashSet<>();
            int fakeI = i;
            int lengthOfSubstring = 0;
            for (int j = 0; j < lenOfString; j++) {
                if (j == 0) {
                    lengthOfSubstring = 1;
                } else if (!setOfChars.contains(s.charAt(fakeI))) {
                    lengthOfSubstring += 1;
                    if (lengthOfSubstring > ans) {
                        ans = lengthOfSubstring;
                    }
                } else {
                    break;
                }
                setOfChars.add(s.charAt(fakeI));
                fakeI += 1;
                if (fakeI >= lenOfString) {
                    break;
                }
            }
        }

        return ans;
    }

    public static int lengthOfLongestSubstring(String s) {
        int ans = 0;
        HashSet<Character> setOfAllChars = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            setOfAllChars.add(s.charAt(i));
        }

        for (Character character: setOfAllChars) {
            String[] listOfStrings = s.split(character.toString());
            for (String listOfString : listOfStrings) {
                if (listOfString.length() > ans) {
                    ans = listOfString.length();
                }
            }
        }
        return ans + 1;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public String toString() {
        StringBuilder str = new StringBuilder();
        ListNode node = this;
        do {
            str.append(node.val).append(" ");
        } while ((node = node.next) != null);
        return str.toString();
    }
}

class LinkedLists {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
         ListNode ans = new ListNode();
         ListNode fakeNode = ans;
         int shift = 0;
         while (l1 != null || l2 != null || shift != 0) {
             int digitFirst = (l1 != null) ? l1.val : 0;
             int digitSecond = (l2 != null) ? l2.val : 0;

             int numRaw = digitFirst + digitSecond + shift;
             int num = numRaw % 10;
             shift = numRaw / 10;

             fakeNode.next = new ListNode(num);
             fakeNode = fakeNode.next;
             l1 = (l1 != null) ? l1.next : null;
             l2 = (l2 != null) ? l2.next : null;
         }
         ListNode aa = ans.next;
         ans.next = null;

         return aa;
    }
}

class Palindrome {
    public static boolean isPalindromeFirst(int x) {
        if (x < 0) {
            return false;
        } else if (x == 0){
            return true;
        }
        int amountOfDigits = 0;
        int fakeX = x;
        while (fakeX > 0) {
            amountOfDigits++;
            fakeX /= 10;
        }

        fakeX = x;
        int i = 0;

        while (fakeX > 0) {
            int leftDigit = (int) Math.floor(fakeX / Math.pow(10, amountOfDigits - i * 2 - 1));
            int rightDigit = fakeX % 10;
            if (leftDigit != rightDigit) {
                return false;
            }
            fakeX = (int) (fakeX % Math.pow(10, amountOfDigits - i * 2 - 1));
            fakeX /= 10;
            i++;
        }
        
        return true;
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        } else if (x == 0) {
            return true;
        }

        int fakeX = x;
        ArrayList<Integer> listOfDigits = new ArrayList<>();

        while (fakeX > 0) {
            listOfDigits.add(fakeX % 10);
            fakeX /= 10;
        }

        int length = listOfDigits.size();
        for (int i = 0; i < length / 2 ; i++) {
            if (listOfDigits.get(i) != listOfDigits.get(length - i - 1)) {
                return false;
            }
        }

        return true;
    }
}

class SolutionTwoSum {
    public static int[] twoSum(int[] nums, int target) {
        int indexOne = 0;
        int indexTwo = 0;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int neededNum = target - num;

            if (map.get(neededNum) != null) {
                indexOne = map.get(neededNum);
                indexTwo = i;
                break;
            }
            map.put(num, i);
        }
        return new int[]{indexOne, indexTwo};
    }

}

class CodeRun {
    public static void main(String[] args) throws IOException {
        uniqueArray();
    }

    public static void middleElement() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] line = reader.readLine().split(" ");
        List<Integer> arr = new ArrayList<>();
        arr.add(Integer.parseInt(line[0]));

        for (int j = 1; j < line.length; j++) {
            int num = Integer.parseInt(line[j]);
            if (num > arr.get(0)) {
                int size = arr.size();
                for (int i = 1; i < size; i++) {
                    if (num < arr.get(i)) {
                        arr.add(i, num);
                    } else {
                        if (i == arr.size() - 1) {
                            arr.add(i + 1, num);
                        }
                    }
                }
                if (arr.size() == 1) {
                    arr.add(1, num);
                }
            } else {
                arr.add(0, num);
            }
        }

        writer.write(String.valueOf(arr.get(arr.size() / 2)));
        reader.close();
        writer.close();
    }

    public static void uniqueArray() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int linesSize = Integer.parseInt(reader.readLine());
        String[] line = reader.readLine().split(" ");
        Set<Integer> set = new HashSet<>();
        Set<Integer> punishmentSet = new HashSet<>();


        for (String elementString: line) {
            Integer element = Integer.valueOf(elementString);
            if (set.contains(element)) {
                punishmentSet.add(element);
            } else {
                set.add(element);
            }
        }

        writer.write(String.valueOf(set.size() - punishmentSet.size()));

        reader.close();
        writer.close();
    }

    public static void closedKey() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int linesSize = Integer.parseInt(reader.readLine());
        String[] line = reader.readLine().split(" ");
        Set<Integer> set = new HashSet<>();
        Set<Integer> punishmentSet = new HashSet<>();


        for (String elementString: line) {
            Integer element = Integer.valueOf(elementString);
            if (set.contains(element)) {
                punishmentSet.add(element);
            } else {
                set.add(element);
            }
        }

        writer.write(String.valueOf(set.size() - punishmentSet.size()));

        reader.close();
        writer.close();
    }
}
