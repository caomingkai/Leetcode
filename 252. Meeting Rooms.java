/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
        
        Arrays.sort(intervals, (i1,i2)->(i1.start-i2.start) );
        int len = intervals.length;
        for( int i = 1; i < len; i++ ){
            Interval prev = intervals[i-1];
            Interval curr = intervals[i];
            if( prev.end > curr.start )
                return false;
        }
        return true;
    }
}