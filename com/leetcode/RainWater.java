package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class RainWater {
	public enum Direction {
		Up,
		Down,
		Flat
	}
	
	public int trap(int[] height)
	{
	    int ans = 0;
	    int size = height.length;
	    for (int i = 1; i < size - 1; i++) {
	        int max_left = 0, max_right = 0;
	        for (int j = i; j >= 0; j--) { //Search the left part for max bar size
	            max_left = Math.max(max_left, height[j]);
	        }
	        for (int j = i; j < size; j++) { //Search the right part for max bar size
	            max_right = Math.max(max_right, height[j]);
	        }
	        ans += Math.min(max_left, max_right) - height[i];
	    }
	    return ans;
	}
	
	public int trap2(int[] height) {
		
		if(height.length<3) {
			return 0;
		}
		
		Direction previous = Direction.Flat;
		Direction current = Direction.Flat;
		
		//Identify Peaks
		List<Integer> peaks = new ArrayList<>();
		for (int i=1; i<height.length; i++) {
			if (height[i]==height[i-1]) { 
				current = Direction.Flat; 
			} else if (height[i]>height[i-1]) {
				current = Direction.Up;
			} else {
				current = Direction.Down;
			}
			//Identify Peaks
			if (previous==Direction.Flat && current==Direction.Down ||
				previous==Direction.Up && current==Direction.Flat ||
				previous==Direction.Up && current==Direction.Down
			) {
				peaks.add(i-1);
			}
			previous=current;
		}
		
		//If last position was going up, consider it a peak
		if (previous==Direction.Up) {
			peaks.add(height.length-1);
		}
		
		//Remove peaks surrounded by taller peaks
		if (peaks.size() > 2) {
			int i=1;
			while (i<peaks.size()-1) {
				int peakLeft = height[peaks.get(i-1)];
				int peakCenter = height[peaks.get(i)];
				int peakRight = height[peaks.get(i+1)];
				if(peakLeft>peakCenter && peakRight>peakCenter) {
					peaks.remove(i);
				} else {
					i++;
				}
			}
		}
		
		//Calculate water
		int total=0;
		int i=0;
		while (i<peaks.size()-1) {
			int peakLeft = peaks.get(i);
			int peakRight = peaks.get(++i);
			int lowerPeak = height[peakLeft]<height[peakRight] ? height[peakLeft] : height[peakRight];
			for (int j=peakLeft+1; j<peakRight; j++) {
				int waterVol = lowerPeak - height[j];
				total += waterVol>0?waterVol : 0;
			}
		}
		return total;
	}
	
	public static void main(String[] args) {
		RainWater p = new RainWater();
//		System.out.println(p.trap(new int[] {6,4,2,0,3,2,0,3,1,4,5,3,2,7,5,3,0,1,2,1,3,4,6,8,1,3}));
		System.out.println(p.trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
	}

}
