/* smart bucket sort: demand-based bucket number.
   0.0 bucket is used to store frequecy of a specific number

   1.0 - Normal Case: bucket number is the largest element in the collection to be sort
   1.1 - This Case : bucket number needn't be larger than the length of collection, due to defination of H-index.
                     The outcome is sorted bucket
   2.0 - Step through max value to min value, to see if cumulative bucket value is larger than or equal to
         current step index, if so, continue; if not, break loop.
*/
public class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] bucket = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (citations[i] >= n) {
                bucket[n]++;
            }
            else {
                bucket[citations[i]]++;    
            }
        }
        int tot = 0;
        for (int i = n; i >= 0; i--) {
            tot += bucket[i];
            if (tot >= i) {
                return i;
            }
        }
        return 0;
    }
}

