/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */


// it is the same as " find the maximus number of non-overlapping schedules ", using greedy
// O(nlogn)
class Solution {
    
    class IntervalComp implements Comparator<Interval>{
        @Override
        public int compare( Interval i1, Interval i2 ){
            return i1.end - i2.end;
        }
    }
    
    public int eraseOverlapIntervals(Interval[] intervals) {
        
        int res = 0;
        // edge case:
        if( intervals == null || intervals.length == 0 )
            return 0;
        
        Arrays.sort(intervals,new IntervalComp() );
        
        int prevEnd = intervals[0].end;
        int i = 1;
        int len = intervals.length ;
        while( i < len ){
            if( intervals[i].start < prevEnd )
                res++;
            else
                prevEnd = intervals[i].end;
            i++;
            
        }
        return res;
    }
}