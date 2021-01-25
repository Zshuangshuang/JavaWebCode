class Solution {
    public String addBinary(String a, String b) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = a.length()-1;
        int j = b.length()-1;
        int ret = 0;//负责判断是否存在进位
        while(i>=0 && j>=0){
            int sum = ret;
            sum += a.charAt(i--)-'0';
            sum += b.charAt(j--)-'0';
            ret = sum/2;
            stringBuilder.append(sum%2);
        }
        while(i >= 0){
            int sum = ret;
            sum += a.charAt(i--)-'0';
            ret = sum/2;
            stringBuilder.append(sum%2);
        }
        while(j >= 0){
            int sum = ret;
            sum += b.charAt(j--)-'0';
            ret = sum/2;
            stringBuilder.append(sum%2);
        }
        if (ret == 1){
            stringBuilder.append(ret);
        }
        return stringBuilder.reverse().toString();
    }
}