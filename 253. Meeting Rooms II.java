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
    第一类问题： “所有会议用尽量少的房间” ： 
                因为所有会议都要安排上，那么尽早安排开始时间造的会议，没理由先安排开始时间晚的会议！
                况且，早安排有可能早结束，给后边的会议腾出地方
    
    
    第二类问题： “一个房间安排尽量多的会议”：
                结束越早的，越优先排入计划 => 这样一个房间可以安排更多的会议
    
*/
/*
class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if( intervals == null || intervals.length == 0 )
            return 0;
        
        Arrays.sort( intervals, (i1, i2)->(i1.start-i2.start));
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();
        endTimes.offer( intervals[0].end );
        
        int len = intervals.length;
        for( int i = 1; i < len; i++ ){
            int s = intervals[i].start;
            int e = intervals[i].end;
            int minEnd = endTimes.peek();
            if(  s >= minEnd ){
                endTimes.poll();
            }
            endTimes.offer(e);
        }
        return endTimes.size();
    }
}
*/

/*
    version 2: two pointers
    关键点在于：所有会议都要安排，那么起始结束时间都是定死的了！！
              按照起始时间由小到大，对会议进行分配，肯定优先分配到已经开完的会议上
              而且已经开完的会议房间，一经再安排，就不再available，指针指向下一个开完的会议
              
    +----------------------> new room #1 for meeting "a"
    |+---------------------> no meeting finish, new room #2 for meeting "b"
    ||    +----------------> one meeting finished at time "w", maybe #1, maybe #2, not important!
    ||    |                           meeting "c" use this old room.
    ||    |+---------------> no more meeting finish, new room #3 for meeting "d"
    ab    cd
    ||    ||                <- start time (4 meetings begun at time a,b,c,d)
    0    4 5 6   8  9
         |   |   |  |       <- end time (they finished at w,x,y,z, order not important)
         w   x   y  z
         
   (0,6) (0,4) (5,8) (5,9)    
*/

/*
public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for(int i=0; i<intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        int endsItr = 0;
        for(int i=0; i<starts.length; i++) {
            if(starts[i]<ends[endsItr])   // 目前，有没有“开完的”会议呀？？
                rooms++;
            else
                endsItr++;
        }
        return rooms;
    }
}
*/



/*
    version 3: 最多征用房间数目 == 同时进行的会议最大数
    
    (0,6) (0,4) (5,8) (5,9)  
    0    4  5  6    8  9
    ------------
    ------  ---------
            ------------
    1   -1  1  -1  -1 -1
    1       1
cur:2    1  3   2   1  0    
           max
    + 开始时刻 +1， 结束时刻 -1
    + 按照时间顺序对所有时刻（开始、结束）进行扫描时，遇到“开始”则加一，遇到“结束”则减一
    + 该过程遇到的最大同时进行的会议数，则是需要的房间数
*/

public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        Map<Integer, Integer> map = new TreeMap<Integer, Integer>(); // Sort Key based on nature order
        for (Interval i : intervals) {
            if (map.containsKey(i.start)) {
                map.put(i.start, map.get(i.start)+1);
            } else {
                map.put(i.start, 1);
            }
            if (map.containsKey(i.end)) {
                map.put(i.end, map.get(i.end)-1);
            } else {
                map.put(i.end, -1);
            }
        }
        int maxRoom = 0; int curRoom = 0;
        for (int i : map.keySet()) {
            maxRoom = Math.max(maxRoom, curRoom += map.get(i));
        }
        return maxRoom;
    }
}