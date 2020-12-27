package question0154_find_minimum_in_rotated_sorted_array_ii;

/**
 * 二分法。
 *
 * left指向左半边升序数组的第一个元素。right指向右半边升序数组的最后一个元素。
 *
 * 时间复杂度是O(n)，其中n为nums数组的长度。空间复杂度是O(1)。
 *
 * 执行用时：1ms，击败99.52%。消耗内存：37.1MB，击败49.82%。
 */
public class Solution2 {

    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[right] > nums[left]) {
                return nums[left];
            }
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == nums[left]) {
                left++;
            } else if (nums[mid] > nums[left]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }

}
