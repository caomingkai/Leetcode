// 与 Paint House I 一样，都需要遍历所有房屋、所有颜色
// 然而，是在找最小值的过程中进行优化的
// -- Paint House I:  Math.min( ), 从所有元素里，找到最小值
// -- Paint House II: 对一个房子，只维护两个var： prevMin , prevSec 就可以供后续房子使用了--【【 非此即彼 】】
//                    同时，记录preMin的index：prevIdx，用于后续节点避开相同颜色，that's all !!
//                    另一个技巧： 使用costs数组作为DP的二维数组，求 accumulative cost
public class Solution {
    public int minCostII(int[][] costs) {
        if(costs != null && costs.length == 0) return 0;
        int prevMin = 0, prevSec = 0, prevIdx = -1;
        for(int i = 0; i < costs.length; i++){
            int currMin = Integer.MAX_VALUE, currSec = Integer.MAX_VALUE, currIdx = -1;
            for(int j = 0; j < costs[0].length; j++){
                costs[i][j] = costs[i][j] + (prevIdx == j ? prevSec : prevMin);
                
                // 找出最小和次小的，最小的要记录下标，方便下一轮判断
                if(costs[i][j] < currMin){
                    currSec = currMin;
                    currMin = costs[i][j];
                    currIdx = j;
                } else if (costs[i][j] < currSec){
                    currSec = costs[i][j];
                }
            }
            prevMin = currMin;
            prevSec = currSec;
            prevIdx = currIdx;
        }
        return prevMin;
    }
}