package pack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Algorithm {

	public static void main(String[] args) {
		//TODO initialize applicants and committees
		assign();
	}
	
	static ArrayList<Student> applicants;
	static Committee[] committees;
	static final int NUM_OF_COMITEE = 10;

	//Assuming the number of spots is greater than the student applicants
	//by the end of assign(), all students will be assigned to a committee
	//will throw ArrayIndexOutOfBounds exception if a student run out of choice
	//(unable to get even his/her third choice for committee and nation)
	static void assign() {
		ArrayList<Student>[] queue = new ArrayList[NUM_OF_COMITEE];
		for (int i = 0; i < queue.length; i++) {
			queue[i] = new ArrayList<>();
		}

		while (!applicants.isEmpty()) {
			// divide students into committees
			for (Student sdt : applicants) {
				queue[sdt.cmt[sdt.committeeChoice]].add(sdt);
			}
			applicants.clear();

			for (int i = 0; i < NUM_OF_COMITEE; i++) {
				applicants.addAll(comiteeAssign(queue[i], committees[i]));
			}

			for (Student student : applicants) {
				student.committeeChoice++;
				student.countryChoice = 0;
			}
		}
	}

	static ArrayList<Student> comiteeAssign(ArrayList<Student> q, Committee cmt) {
		// how many students to reject (because we are not able to "accommodate all qualified students")
		int rejection = q.size() - cmt.NUM_OF_COUNTRIES * cmt.NUM_OF_SUBCMT;

		while (q.size() > rejection) {
			for (Student student : q) {
				cmt.ctr[student.countryChoice].add(student);
			}
			q.clear();
			for (int i = 0; i < cmt.NUM_OF_COUNTRIES; i++) {
				if (cmt.ctr[i].size() > cmt.NUM_OF_SUBCMT) {
					sortTail(cmt.ctr[i], cmt.ptr[i]);
					List<Student> waitlist = truncate(cmt.ctr[i], cmt.NUM_OF_SUBCMT);
					for (Student student : waitlist) {
						student.countryChoice++;
					}
					q.addAll(waitlist);
				} else {
					cmt.ptr[i] = cmt.ctr[i].size();
				}
			}
		}
		return q;// <===these poor guys are REJECTED from the committee
	}

	//sort an Arraylist on interval [lo,size)
	static void sortTail(ArrayList<Student> list, int lo) {
		List<Student> sorted = list.subList(lo, list.size());
		Collections.sort(sorted);
		for (int i = lo; i < list.size(); i++) {
			list.remove(i);
		}
		list.addAll(sorted);
	}

	static List<Student> truncate(ArrayList<Student> list, int capacity) {
		List<Student> ret = list.subList(capacity, list.size());
		for (int i = capacity; i < list.size(); i++) {
			list.remove(i);
		}
		return ret;
	}

}
