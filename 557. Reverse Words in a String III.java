class Solution {
    public String reverseWords(String s) {
        if (s == null) {
            return s;
        }
        char[] array = s.toCharArray();
        int l = 0, r = 0;
        while (r < array.length) {
            if (array[r] == ' ') {
                reverse(array, l, r - 1);
                l = r + 1;
            }
            r++;
        }
        reverse(array, l, array.length - 1);
        return new String(array);
    }
    
    
    private void reverse(char[] array, int l, int r) {
        while (l < r) {
            char temp = array[l];
            array[l++] = array[r];
            array[r--] = temp;
        }
    }
}