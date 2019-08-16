package question0319;

/**
 * 第1个灯泡会在第1轮反转。
 *
 * 第2个灯泡会在第1、2轮反转。
 *
 * 第3个灯泡会在第1、3轮反转。
 *
 * 第4个灯泡会在第1、2、4轮反转。
 *
 * 第5个灯泡会在第1、5轮反转。
 *
 * 第6个灯泡会在第1、2、3、6轮反转。
 *
 * 第7个灯泡会在第1、7轮反转。
 *
 * 第8个灯泡会在第1、2、4、8轮反转。
 *
 * 第9个灯泡会在第1、3、9轮反转。
 *
 * ……
 *
 * 找出规律，只有完全平方数的反转次数是奇数，其余的反转次数是偶数，因此灯泡亮的个数就是小于等于n的完全平方数的个数。
 *
 * 时间复杂度是O(logn)。空间复杂度是O(1)。
 *
 * 执行用时：0ms，击败100.00%。消耗内存：34.1MB，击败11.38%。
 */
public class Solution2 {
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}
