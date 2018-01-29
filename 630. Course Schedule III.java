public class Solution {
    public int scheduleCourse(int[][] courses) {
        //按照课程截至日期排序
        Arrays.sort(courses,(a,b)->{return a[1]-b[1];});
        //用最大优先级队列存储已经选择了的课程
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        int time = 0;
        for(int[] course : courses){
            time += course[0];
            pq.add(course[0]);
            //如果当前考虑的课程超期了，就从已选择的课程中去掉课时最长的
            if(time > course[1]){
                time -= pq.poll();
            }
        }
        //最终优先级队列中的课程数，就是能够选择的最多课程
        return pq.size();
    }
}