package question1199_minimum_time_to_build_blocks;

import java.util.PriorityQueue;

/**
 * 一个街区：blocks[0]
 *
 * 两个街区：split + mas(blocks[0], blocks[1])
 *
 * N个街区：
 * 转换思路：分裂工人->合并街区
 * 对 N 个街区进行合并，选择任意两个街区 i 和 j 合并后，得到的新街区的建造时间为 split + max(blocks[i], blocks[j])
 *
 * 问题转换：
 * 有 n(n >= 2) 个数，合并任意两个数 x 和 y 后得到一个新的数 s + max(x, y)，其中 s > 0 且与 x, y 无关。那么，合并
 * n - 1 次后最终得到的数的最小值是多少？
 *
 * 贪心算法：
 * 每次选取最小的两个数进行合并，直到只剩下一个数为止。
 *
 * 引理：
 * 将合并过程表示为一棵节点的度为 0 或 2 的二叉树，其叶子节点为初始的数且至少有两个叶子节点，父节点的值为 s + max(x, y)，
 * 其中 x, y 为其两个子节点的值。假设 n(n >= 2) 个数中最小的两个数为 x1, x2(x1 <= x2)，则在最优合并对应的二叉树中，
 * x1, x2 对应的叶子节点一定具有最大的深度，且为兄弟节点。
 *
 * 引理证明：
 * 假设存在一个叶子节点 x3，其深度 d3 大于 x1 的深度 d1，且 x3 >= x1。假设 x3 的深度为 d1 的祖先节点为 y，则
 * y >= x3 + (d3 - d1)s > x3 >= x1。考虑所有深度为 d1 的节点的最大值 M，可以得到：
 * M = max(xd1) = max(y, x1, ...) = max(y, y, ...)
 * 尝试交换 x1 和 x3.交换后 y' <= y
 * M' = max(y', x3, ...) <= max(y, y, ...) = M
 * 由 M' <= M 可知，交换后二叉树的根节点 R' <= R。因此，我们总可用通过若干次交换，使得 x1 具有最大深度，并且根节点的
 * 值不大于原本根节点的值。
 * 由于二叉树的节点度不能为 1，所以深度最深的叶节点至少有两个。因此，我们可以再通过若干次交换，使得 x2 具有最大深度，并且
 * 根节点的值不大于原本根节点的值。由于 x2 的深度不能超过 x1 的深度，所以此时它们的深度一定相等。
 * 若二叉树深度最深的一层只有两个叶节点，它们必定为 x1 和 x2 且为兄弟。
 * 假设在二叉树最深的一层还有其他叶子节点，则由节点的度为 0 或 2 可知，至少还有两个叶子节点 x3 和 x4，且满足
 * x1 <= x2 <= x3, x1 <= x2 <= x4。若 x1 和 x2 不为兄弟节点，不妨假设 x1 和 x3 为兄弟节点，x2 和 x4 为兄弟节点，
 * 则 x1 和 x3 的父节点 y = s + x3，x2 和 x4 的父节点 z = s + x4.考虑倒数第二层的所有节点的最大值 M，可以得到：
 * M = max(y, z, ...)
 * 若将 x2 和 x3 交换，则 y' = s + x2 <= y, z' = s + x4 = z，从而：
 * M' = max(y', z', ...) <= max(y, z, ...) = M
 * 因此这一交换可以得到更优的合并方案。从而 x1 和 x2 一定为兄弟节点。
 *
 * 贪心算法的证明：
 * 数学归纳法
 * 1. 在 n = 2 时，使用贪心策略可以得到最优合并。
 * 2. 假设 n = k (k >= 2) 时，使用贪心策略可以得到最优合并。证明：在 n = k + 1 时，使用贪心策略可以得到最优合并。
 *
 * 证明过程：
 * 1. 假设 x1, x2 (x1 <= x2) 是 k + 1 个数中最小的两个数。根据贪心策略，将这两个数合并，得到一个新的数 x' = s + max(x1, x2)。
 * 2. 将 x' 和剩余 k - 1 个数组合在一起，使用贪心策略可以得到这 k 个数的最优合并，假设其对应的二叉树表示为 T，根节点
 * 的值 为 RT，则使用探测策略合并 k + 1 个数的代价也为 RT。
 * 3. 我们将 T 中 s + max(x1, x2) 对应的叶节点拆分出两个新的叶节点 x1 和 x2，可以得到一颗新的二叉树 T'，根节点的值
 * 为 RT' = RT。
 * 4. 假设存在一个比贪心策略得到的二叉树更优的合并二叉树 S，则其根节点 RS < RT。由引理可知，x1 和 x2一定具有最大深度，
 * 且为兄弟节点，因此我们可以把 x1 和 x2 合并得到 x' = s + max(x1, x2)，这样就得到了一个具有 k 个叶子节点的新二叉树
 * S'，其对应了 x' 和剩余 k - 1 个数的一种合并方式，且其根节点 RS' = RS < RT。这与我们的归纳假设，即 T 是 k 个数的
 * 最优合并，相矛盾。因此假设不成立，贪心策略得到合并 k + 1 个数的方法是最优合并。
 *
 * 时间复杂度是 O(nlogn)，其中 n 为数组 blocks 的长度。空间复杂度是 O(n)。
 *
 * 执行用时：17ms，击败55.00%。消耗内存：39.4MB，击败100.00。
 */
public class Solution {
    public int minBuildTime(int[] blocks, int split) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int block : blocks) {
            pq.add(block);
        }
        while (pq.size() > 1) {
            int num1 = pq.poll(), num2 = pq.poll();
            pq.add(split + Math.max(num1, num2));
        }
        return pq.poll();
    }
}