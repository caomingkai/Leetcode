/*

时间复杂度：O(n^2)
空间复杂度：O(n)

原理：
    1. 先用HashMap<Character,ArrayList<>>，将相同字符的index统计出来
    2. 循环：从头开始扫描字符串，对当前位置i的字符，在Map中找与其相字符的index，查看是否能构成重复子串
         - 循环：检查与当前字符相同的所有字符的index
             + 某个index不是重复子串，继续检查其后index的可能性
             + 如果在某个index出找到重复子串，持续检查后边index看是否继续重复，更新cnt值；
               直至遇到不重复index -> append重复子串与cnt，跳出循环
         - 循环检查完毕，根据cnt的值，更新扫描字符串的指针 i
             + if(cnt==1)  i++，并append之前未匹配成功的字符
             + else        i = cnt * l(重复子串长度)
        
    3. 返回     
*/
  

import java.io.*;
import java.util.*;

class StringSegmentCompress {
    
      private static String stringCompress(String s){
          if( s== null || s.length() == 0 ) return "";
          Map<Character, ArrayList<Integer>> map = new HashMap<>();
          StringBuilder sb = new StringBuilder();
          int len = s.length();
          
          // 统计重复字符的index，形成list放在map中
          for( int i = 0; i < len; i++ ){
              char c = s.charAt(i);
              ArrayList<Integer> indexList = null;
              if( !map.containsKey(c) )
                  indexList = new ArrayList<>();
              else
                  indexList = map.get(c);
              indexList.add(i);
              map.put(c, indexList);
          }
    
          // 从头逐个检查每个字符，看出现重复子串的可能性
          int i = 0;
          while(  i < len ){
              char c = s.charAt(i);
              ArrayList<Integer> indexList = map.get(c);
              if( indexList.size() == 1 ){ // 只出现过一次
                  sb.append(c+",");
                  i++;
              }else{
                  int l = 0;    //重复子串的长度
                  int cnt = 1;  //重复子串的个数
                  
                  // 检查重复字符是否有可能构成重复字串
                  for( int j = 0; j < indexList.size(); j++ ){
                      if( indexList.get(j) <= i ) continue;  // 只看比当前i大的重复index
                      int start1 = i;
                      l =  indexList.get(j) - start1;
                      while( start1+l < len ){
                          int start2 = start1+l;
                          if( start2+l <= len && s.substring(start1,start1+l).equals( s.substring(start2,start2+l) ) ){
                              cnt++;
                              start1 = start2;
                          }else{
                              break;
                          }
                      }
                      if( cnt == 1 ) continue; // 该长度下的子串没有符合条件的，看下一个长度
                      else{                    // 该长度下的字串统计完毕，没必要看下一个长度了
                          String dup = s.substring(start1,start1+l);
                          sb.append("("+dup+")"+"*"+cnt+",");
                          break;
                      }
                  }

                  if( cnt == 1 ){               // 更新字符串下一步的扫描位置
                      sb.append(c+",");
                      i++;
                  }else 
                      i += l * cnt;
              }
          }
          String res = sb.toString();
          return res.substring(0,res.length()-1);
          
      }


      public static void main (String[] args) {
            String test1 = "";
            String test7 = "a";
            String test5 = "abcd";
            String test3 = "aaaaa";
            String test6 = "abbbb";
            String test4 = "abcabdabcabd";
            String test2 = "abcabcaaaabdabc";
        
            System.out.println("test1:"+test1);
            System.out.println( stringCompress(test1) );
            System.out.println();
        
            System.out.println("test2:" + test2);
            System.out.println( stringCompress(test2) );
            System.out.println();
        
            System.out.println("test3:"+test3);
            System.out.println( stringCompress(test3) );
            System.out.println();
        
            System.out.println("test4:"+test4);
            System.out.println( stringCompress(test4) );
            System.out.println();
        
            System.out.println("test5:"+test5);
            System.out.println( stringCompress(test5) );
            System.out.println();

            System.out.println("test6:"+test6);
            System.out.println( stringCompress(test6) );
            System.out.println();
        
            System.out.println("test7:"+test7);
            System.out.println( stringCompress(test7) );
            System.out.println();
      }

    
}
