/*
=========
===例子一：
=========
    0 1 2 3 4 5 6 7
    
    1 2 3 4 5 6       6    
    2 3 4 4 6 7 8 9 9   8
                            7 8
k=7        
        k/2=3       0+3-1 -> 2
        k/2=3       0+3-1 -> 4

k=7-3=4     
          4 5 6
    2 3 4 6 7 8 9 9
        
        4/2=2       3+2-1 = 4 -> 5
        4/2=2       0+2-1 = 1 -> 3

k=4-2=2        
          4 5 6
        4 6 7 8 9 9
        2/2=1       3+1-1 = 3 -> 4
        2/2=1       2+1-1 = 2 -> 4

k=2-1=1
          4 5 6         return min(a[start],b[start])
          6 7 8 9 9
        
=====================================================
=========
===例子二：为什么不可以 if(aMid == bMid) 时，直接返回aMid
=========
     0    1
     1    2    :2
     1    2    :2
k=2                   k=3
    k/2=1   0+1-1=0  -> 1
    k/2=1   0+1-1=0  -> 1
    
k=2-1=1
          2
     1    2       min = 1;
     
     --------
k=3
    k/2=1   0+1-1=0  -> 1
    k/2=1   0+1-1=0  -> 1
          2
     1    2      
    
k=3-1=2
    k/2=1   1+1-1=1  -> 2
    k/2=1   0+1-1=0  -> 1

          2
          2 

k=2-1 = 1     return 2

        
        
        
                          1. handle 奇偶的【万能公式】
                             不论 length为奇数、偶数，下方公式一定可以求的median
                             
                             left  = (len+1)/2
                             right = (len+2)/2
                             mean  = ( nums[left] +nums[right] ) / 2
                             
                          2. 即便a[mid] = b[mid]也不可以直接返回a[mid]
                             例如，找[1,2] [1,2]中第3个元素的时候， a[mid] = 1, b[mid] = 1；然而第三个是 2
                             因此不可以直接返回。
                             但有一点是肯定的，舍弃一半k/2，在另一半中找第k-k/2个元素一定没错！
                             如上例，舍弃任意数组中的‘1’，成为[2] [1,2],再找第(3-1)=2 个元素,
                             一步一步，直到k=1，绝对不会出错
                             
                          3. 在某个list太短，以至于没有 k/2 个元素时候，则取最后的最大元素， 先把短的list耗尽
                             这时，如果这个最大元素依然小于另外一个list中的第k/2个元素的话，
                             那么这个短的list直接删去，剩下一个list，只需要从剩下的这一个list中取第k-1个元素即可
                             
                          4. 【注意点】
                                  - 如果A或者B为空，则直接返回B[k-1]或者A[k-1]；
                                  - 如果k为1，我们只需要返回A[0]和B[0]中的较小值；
                                  - 如果A[k/2-1]=B[k/2-1]，不着急返回，扔掉另一半，接着找
*/


class Solution {
    public double findMedianSortedArrays(int[] A1, int[] A2) {
        int m = A1.length, n = A2.length;
        int mid1 = (m + n + 1) / 2;
        int mid2 = (m + n + 2) / 2;
        return ( findKth(A1, 0, m, A2, 0, n, mid1) + findKth(A1, 0, m, A2, 0, n, mid2)) / 2;
    }
    
    private double findKth(int[] A1, int s1, int m, int[] A2, int s2, int n, int k) {
        if (m > n) return findKth(A2, s2, n, A1, s1, m, k);         // 先把短的list耗尽
        if (m == 0) return A2[s2 + k - 1];
        if (k == 1) return Math.min(A1[s1], A2[s2]);
        
        int i = Math.min(m, k / 2);
        int j = k / 2;
        if (A1[s1 + i - 1] <= A2[s2 + j - 1]) {
            return findKth(A1, s1 + i, m - i, A2, s2, n, k - i);
        } else {
            return findKth(A1, s1, m, A2, s2 + j, n - j, k - j);
        }
    }
}