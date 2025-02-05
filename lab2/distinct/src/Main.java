import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 示例数组
        int[] nums = {1, 2, 3, 2, 1};
        int median = findMedianOfUniquenessArray(nums);
        System.out.println("中位数是: " + median);
    }

    public static int findMedianOfUniquenessArray(int[] nums) {
        // 用于存储所有子数组中不同元素的数量
        List<Integer> uniquenessCounts = new ArrayList<>();

        // 遍历所有子数组
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                // 使用HashSet统计子数组中不同元素的数量
                Set<Integer> distinctElements = new HashSet<>();
                for (int k = i; k <= j; k++) {
                    distinctElements.add(nums[k]);
                }
                uniquenessCounts.add(distinctElements.size());
            }
        }

        // 对唯一性数组进行排序
        Collections.sort(uniquenessCounts);

        // 找到中位数
        int n = uniquenessCounts.size();
        if (n % 2 == 1) {
            // 如果长度为奇数，返回中间元素
            return uniquenessCounts.get(n / 2);
        } else {
            // 如果长度为偶数，返回中间两个元素中较小的那个
            return uniquenessCounts.get(n / 2 - 1);
        }
    }
}