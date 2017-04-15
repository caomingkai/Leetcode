/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
 
import java.util.Collections;
import java.lang.Math;
import java.util.Comparator;

public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        
        if(intervals == null || intervals.size() <= 1){
            return intervals;
        }
        
        Collections.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval i1, Interval i2){
                return i1.start - i2.start;
            }
        });
        
        List<Interval> result = new ArrayList<Interval>();
        
        Interval temp = intervals.get(0);
        int length = intervals.size();
        
        for(int i = 1; i < length; i++){
            Interval cur = intervals.get(i);
            if(temp.end >= cur.start){
                temp = new Interval(temp.start,  Math.max(temp.end, cur.end) );
            }else{
                result.add( temp );
                temp = cur;
            }
        }
        result.add( temp );
        return result;
    }
}