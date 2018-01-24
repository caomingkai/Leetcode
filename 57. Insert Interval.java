/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */


/*
    1. maintain two list : startArr and endArr for all the intervals
    2. For the startNum of newInterval, find the last ending item in endArr, who is less than it
    3. For the endNum of newInterval, find the first starting item in startArr, who is larger than it
    4. thus, for left & right part, stay the same; Focus on middle parts, combining them into one parts
      [ 1 2 ] [ 2 3 ]  [ 5   7 ] [ 8  10 ] [ 11    13 ]  [ 15 19 ]   [ 20 29 ]
                  |       [6                   12]          |
           last endItem<6                              1st startItem>12
               idxE=1                                      idxS=5
    5. find smaller one between startNum and startItem for (idxE+1)th interval
    6. find larger one between endNum and endItem for (idxS-1)th interval
*/


class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval>  res = new ArrayList<>();
        if( newInterval == null  )
            return intervals;
        if( intervals == null || intervals.size() == 0 ){
            res.add( newInterval );
            return res;
        }
        
        int l = intervals.size();
        int[] startArr = new int[l];
        int[] endArr   = new int[l];
        for( int i = 0; i < l; i++ ){
            startArr[i] = intervals.get(i).start;
            endArr[i]   = intervals.get(i).end;
        }
        
        int lastEnd    = findLastIntervalWithEndLessThan( newInterval.start, endArr );
        int firstStart = findFirstIntervalWithStartLargerThan( newInterval.end, startArr );
        
        return combineIntoOne(lastEnd, firstStart, newInterval, intervals);
    }
    
    //
    //       1 3 4 6 8 9  :  6  -> 4  // corner case 0 -> -1
    //       n[m] <  t,  l = m
    //      n[m] >= t,  r = m-1
            
    //      m = l + (r-l+1)/2
    //      return l
    private int findLastIntervalWithEndLessThan( int target, int[] arr ){
        int len = arr.length;
        int l = 0, r = len-1;
        while( l < r ){
            int m = l + (r-l+1)/2;
            if( arr[m] < target )
                l = m;
            else
                r = m-1;
        }
        return arr[l]<target ? l : -1;
    }
    
   
    //         2 4 6 8 9  :  3  -> 4  // corner case 13 -> -1
    //         n[m] <= t,  l = m+1
    //         n[m] >  t,  r = m
        
    //         m = l + (r-l)/2
    //         return r
    private int findFirstIntervalWithStartLargerThan( int target, int[] arr ){
        int len = arr.length;
        int l = 0, r = len-1;
        while( l < r ){
            int m = l + (r-l)/2;
            if( arr[m] <= target )
                l = m+1;
            else
                r = m;
        }
        return arr[r]>target ? r : -1;
    }
    
    
    
    private  List<Interval> combineIntoOne(int left, int right, Interval newInterval, List<Interval> intervals){
        // List<Interval> res = new ArrayList<>(intervals);
        List<Interval> res = new ArrayList<>();
        int len = intervals.size();
        int newStart = 0;
        int newEnd = 0;

        // left == -1;  intervals' start larger than first interval's end
        // so newEnd is between newInterval's end and last interval's end
        if( left == -1 ) 
            newStart = Math.min( newInterval.start, intervals.get(0).start);
        else{
            if(left+1 < len)
                newStart = Math.min( newInterval.start, intervals.get(left+1).start);
            else
                newStart = newInterval.start;
        }

        // right == -1,  intervals' end less than last interval's start
        // so newStart is between newInterval's start and last interval's start
        if( right == -1 ) 
            newEnd = Math.max( newInterval.end, intervals.get(len-1).end);
        else{
            if(right-1 >= 0)
                newEnd = Math.max( newInterval.end, intervals.get(right-1).end );
            else
                newEnd = newInterval.end;
        }

        int i = 0;
        while( i <= left && left >= 0 )
            res.add(intervals.get(i++));
        
        Interval newFormedInterval = new Interval( newStart, newEnd );
        res.add(newFormedInterval);

        i = right;
        while( i < len && right >= 0 )
            res.add(intervals.get(i++));

        return res;
    }
}


/*
// version 2:  deal as scan forward
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new LinkedList<>();
        int i = 0;
        // add all the intervals ending before newInterval starts
        while (i < intervals.size() && intervals.get(i).end < newInterval.start)
            result.add(intervals.get(i++));
        
        // merge all overlapping intervals to one considering newInterval
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval = new Interval( // we could mutate newInterval here also
                    Math.min(newInterval.start, intervals.get(i).start),
                    Math.max(newInterval.end, intervals.get(i).end));
            i++;
        }
        
        result.add(newInterval); // add the union of intervals we got
        // add all the rest
        while (i < intervals.size()) result.add(intervals.get(i++)); 
        return result;
    }
}
*/