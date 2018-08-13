package com.ctci.problems.c8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class P13 {
	
	public class Box {
		public int height;
		public int width;
		public int depth;
		
		public Box(int w, int h, int d) {
			width=w;
			height=h;
			depth=d;
		}
		
		public boolean canBePlacedOnTopOf(Box box) {
			if (width<box.width && height<box.height && depth<box.depth) {
				return true;
			}
			return false;
		}
	}
	
	// Sort boxes by Height descending
	public class BoxComparator implements Comparator<Box> {
		@Override
		public int compare(Box b1, Box b2) {
			return b2.height-b1.height;
		}
	}
	
	public int maxStackHeight(List<Box> boxes) {
		Collections.sort(boxes, new BoxComparator());
		int maxHeight = 0;
		int[] memo = new int[boxes.size()];
		for(int bottom=0; bottom<boxes.size(); bottom++) {
			int currH = maxStackHeight(boxes, bottom, memo);
			maxHeight=Math.max(maxHeight, currH);
		}
		return maxHeight;
	}
	
	private int maxStackHeight(List<Box> boxes, int bottom, int[] memo) {
		if (memo[bottom]==0) {
			Box bottomBox = boxes.get(bottom);
			int maxHeight = 0;
			for(int currBox=bottom+1; currBox<boxes.size(); currBox++) {
				if(boxes.get(currBox).canBePlacedOnTopOf(bottomBox)) {
					int currH=maxStackHeight(boxes, currBox, memo);
					maxHeight=Math.max(maxHeight, currH);
				}
			}
			memo[bottom]=bottomBox.height+maxHeight;
		}
		return memo[bottom];
	}
	
	public static void main(String[] args) {
		
		P13 problem = new P13();
		
		List<Box> boxes = new ArrayList<Box>();
		boxes.add(problem.new Box(6, 4, 4));
		boxes.add(problem.new Box(8, 6, 2));
		boxes.add(problem.new Box(5, 3, 3));
		boxes.add(problem.new Box(7, 5, 1));
		
		int height = problem.maxStackHeight(boxes); // 11
		System.out.println(height);
	}

}
