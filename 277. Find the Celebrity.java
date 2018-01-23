/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

/*
因此每询问一次a是否认识b，都可以【【 排除 】】掉一个人，所以在O(n)时间内就可以排除掉n-1个人。
最后还要检查确认，是否其他人都认识这个人，以及这个人都不认识其他人。
  1. ask each person if his know somebody else
      - one Yes: delete this person from possible set
      - all No : put him into possible set
  2. for each person in the possible set, ask everyone if there know him
      - if anyone does'nt know him, delete it
      - if all knows him, output it
  3. if in then end, there is no person in the possible set, there is no celebity
 */

/*
public class Solution extends Relation {
    public int findCelebrity(int n) {
        Set<Integer> potential = new HashSet<>();
        Set<Integer> people = new HashSet<>();
        
        for( int i = 0; i < n; i++ ) people.add(i);
        
        for( int i = 0; i < n; i++ ){
            int j = 0;
            for( ; j < n; j++ ){
                if( i != j){
                    if( knows(i,j) ) break;
                }
            }
            if( j == n ) potential.add(i);
        }
        
        Iterator<Integer> iter = potential.iterator();
        while( iter.hasNext() ){
            int celebity = iter.next();
            int j = 0;
            for( ; j < n; j++ ){
                if( j != celebity){
                    if( !knows(j,celebity) ){
                        iter.remove();
                        break;
                    } 
                }
            }
            if( j == n ) return celebity;
        }
        
        return -1;
    }
}

*/


// version 2: two pass
/*
    关键点： 1. 不论问哪一个人，持续问，最后一定会指向 Celebrity， 因为每个人都认识（指向）celebrity
                - 即便朋友最少的人，也会指向celebrity
                - 之前的都认识别人，肯定不是celebrity 【扫描过的人已被排除】
                - 那么问到最后对剩余人都不认识的那个人，就是唯一有希望的celebrity
*/
public class Solution extends Relation {
    public int findCelebrity(int n) {
        int candidate = 0;
        for(int i = 1; i < n; i++){   //这样写可能有人问：万一这个不是celebrity呢？
            if(knows(candidate, i))   //答： 1. 如果存在celebrity的话，这个candidate一定会指向他的，
                candidate = i;        //    2. 之前的都认识别人，肯定不是celebrity 【扫描过的人已被排除】
                                      //    3. 因此，这是唯一一个candidate      
        }
        
        for(int i = 0; i < n; i++){
            // 候选者不认识任何一个人 ， 同时任何人都认识候选者
            if(i != candidate && (knows(candidate, i) || !knows(i, candidate))) return -1;
        }
        return candidate;
    }
}


/*
// version 3: use a stack pop twice, ask each other
public class Solution extends Relation {

    public int findCelebrity(int n) {
        // base case
        if (n <= 0) return -1;
        if (n == 1) return 0;

        Stack<Integer> stack = new Stack<>();

        // put all people to the stack
        for (int i = 0; i < n; i++) stack.push(i);

        int a = 0, b = 0;

        while (stack.size() > 1) {
            a = stack.pop(); b = stack.pop();

            if (knows(a, b)) 
                // a knows b, so a is not the celebrity, but b may be
                stack.push(b);
            else 
                // a doesn't know b, so b is not the celebrity, but a may be
                stack.push(a);
        }

        // double check the potential celebrity
        int c = stack.pop();

        for (int i = 0; i < n; i++)
            // c should not know anyone else
            if (i != c && (knows(c, i) || !knows(i, c)))
                return -1;

        return c;
    }
}

*/
