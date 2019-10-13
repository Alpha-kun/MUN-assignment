package pack;

public class Student implements Comparable<Student> {

	//individual student identifier
	int id;

	//their top three choice of nation and committee
	int[] nation = new int[3];
	int[] cmt = new int[3];

	// years of experience
	int yrexp;
	int grade;

	// time when they submit application
	long time;

	int countryChoice;
	int committeeChoice;
	
	public Student() {
		// TODO assign your variables here
	}

	@Override
	public int compareTo(Student o) {
		if (Integer.compare(yrexp, o.yrexp) != 0) {
			return -Integer.compare(yrexp, o.yrexp);
		}
		if (Integer.compare(grade, o.grade) != 0) {
			return -Integer.compare(grade, -o.grade);
		}
		return Long.compare(time, o.time);
	}
}
