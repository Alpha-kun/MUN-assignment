package pack;

import java.util.ArrayList;

public class Committee {

	int NUM_OF_COUNTRIES = 4;
	int NUM_OF_SUBCMT = 4;
	
	ArrayList<Student>[] ctr = new ArrayList[NUM_OF_COUNTRIES];
	
	//students who are int ctr[i]<ptr[i] are admitted
	int[] ptr=new int[NUM_OF_COUNTRIES];
	
	public Committee() {
		for (int i = 0; i < ctr.length; i++) {
			ctr[i] = new ArrayList<>();
		}
	}
}
