public class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack < Integer > stack = new Stack < > ();
        stack.push(-1);
        int maxarea = 0;
        for (int i = 0; i < heights.length; ++i) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
                maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
            stack.push(i);
        }
        while (stack.peek() != -1)
            maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() -1));
        return maxarea;
    }
}

/*
public class Solution {
    public int largestRectangleArea(int[] height) {
        if (height==null) return 0;//Should throw exception
        if (height.length==0) return 0;

        Stack<Integer> index= new Stack<Integer>();
        index.push(-1);
        int max=0;

        for  (int i=0;i<height.length;i++){
                //Start calculate the max value
            while (index.peek()>-1)
                if (height[index.peek()]>height[i]){
                    int top=index.pop();                    
                    max=Math.max(max,height[top]*(i-1-index.peek()));  
                }else break;

            index.push(i);
        }
        while(index.peek()!=-1){
            int top=index.pop();
            max=Math.max(max,height[top]*(height.length-1-index.peek()));
        }        
        return max;
    }
}
*/