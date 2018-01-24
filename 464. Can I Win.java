/*

    1. canIWinHelper() : 对于当前total, 以及当前还剩余的数字state, 当前玩家是否会赢【同一个strategy，二人普适】
    2. 如果直接用set来解题的话，会超时；原因在于重复搜索，
        2.1 因此要把【搜索状态 ：结果】保存为Map，就不能直接用set了，reference不能直接表示其内部的状态
        2.2 采用 String:Boolean 来保存结果， String是由 state状态，Arrays.toString(state) 过来的
        2.3 如下，可以有效避免
                         [ 2 3 4 ]+5 --> [2 3 4 5]  <--[ 2 3 5 ]+4
    3.  hashMap对状态置true: 对第一名选手而言，该状态是true／false
*/

public class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal<=0) return true;
        if (maxChoosableInteger*(maxChoosableInteger+1)/2<desiredTotal) return false;
        
        return canIWinHelper(desiredTotal, new int[maxChoosableInteger], new HashMap<>());
    }
    
    
    // 来回换角色体验一下该函数工作方式： 每个人都想赢，想让对方输
    private boolean canIWinHelper(int total, int[] state, HashMap<String, Boolean> hashMap) {
        String curr=Arrays.toString(state);
        if (hashMap.containsKey(curr)) return hashMap.get(curr);   // 访问过，直接返回该结果
        for (int i=0;i<state.length;i++) {                         // 对每个未被访问过的元素尝试
            if (state[i]==0) {
                state[i]=1; 
                // 本次 ith值 (i+1) >= total 胜利, OR 下一次对手失败 
                if (total<=i+1 || !canIWinHelper(total-(i+1), state, hashMap)) {
                    hashMap.put(curr, true);     // 将该state(cur) 标为true【这是因为：对手情况下也可为true】
                    state[i]=0;                  // 在返回前，还原状态，供上名选手使用
                    return true;
                }
                state[i]=0;                      // 如果，没有尝试成功，则还原该数字，供后续选择
            }
        }
        hashMap.put(curr, false);                // 所有情况都尝试不成功，将该情况置为false
        return false;
    }
}