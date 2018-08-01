package com.leetcode;

/*
 * https://leetcode.com/explore/interview/card/microsoft/30/array-and-strings/171/ 
 */
public class Atoi {
    
	public static final String MAX_INT_VALUE = Integer.toString((Integer.MAX_VALUE));
    public static final String MIN_INT_VALUE = Integer.toString((Integer.MIN_VALUE)).substring(1);
    public static final int MAX_INT_LENGTH = MAX_INT_VALUE.length();
    public static final int MIN_INT_LENGTH = MIN_INT_VALUE.length();
    
    
    public int myAtoi(String str) {
    	
    	StringBuilder sb = new StringBuilder();
        int signal = 0;
        for(int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if (signal==0) {
                if (c=='+') {
                    signal=1;
                } else if (c=='-') {
                    signal =-1;
            	} else if (Character.isDigit(c)){
                    signal=1;
                    sb.append(c);
                } else if (c!=' ') {
                    return 0;
                }
            } else {
                if (Character.isDigit(c)) {
                	if(sb.length()==1 && sb.charAt(0)=='0') { sb.delete(0, 1); }
                	sb.append(c);
                } else {
                    break;
                }
            }
        }
    
        if (sb.length()==0) { return 0; }
    
        if (signal==1) {
            if (sb.length() > MAX_INT_LENGTH || (sb.length() == MAX_INT_LENGTH && MAX_INT_VALUE.compareTo(sb.toString())<0)) {return Integer.MAX_VALUE;}
        } else {
            if (sb.length() > MIN_INT_LENGTH || (sb.length() == MIN_INT_LENGTH && MIN_INT_VALUE.compareTo(sb.toString())<0)) {return Integer.MIN_VALUE;}
        }
        
        int power=1;
        int result=0;
        for (int i=sb.length()-1; i>=0; i--) {
        	power = i<sb.length()-1 ? power*10 : power;
            result += power * (sb.charAt(i)-'0');
        }
        return result*signal;
    }
    
    public static void main(String[] args) {
		Atoi atoi = new Atoi();
		System.out.println(atoi.myAtoi("    -42 a")); //-42
		System.out.println(atoi.myAtoi("  0000000000012345678")); //12345678
		System.out.println(atoi.myAtoi("-000000000000001")); //-1
		System.out.println(atoi.myAtoi("0-1")); // 0
	}
}
