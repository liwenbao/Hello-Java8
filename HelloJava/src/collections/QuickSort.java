package collections;

import java.util.*;

public class QuickSort {
	public static void main(String[] args) {
		Collection<String> c = Arrays.asList("java","is","fun","i","love","java");
		
		//new a object which class implements Comparator<T>
		SortedList<String> sl = new SortedList<String>(c);
		sl.quickSort(new StrComparator());
		System.out.println(String.join(" ", sl));

		//use Anonymous inner class
		SortedList<String> s2 = new SortedList<String>(c);
		s2.quickSort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(o2);
			}
		});
		System.out.println(String.join(" ", s2));

		//use lamdba
		SortedList<String> s3 = new SortedList<String>(c);
		s3.quickSort((x, y)-> x.compareTo(y));
		System.out.println(String.join(" ", s3));
		
	}
}

class StrComparator implements Comparator<String>{

	@Override
	public int compare(String o1, String o2) {
		// TODO Auto-generated method stub
		return o1.compareTo(o2);
	}
	
}

class SortedList<T> extends ArrayList<T>{

	public SortedList(Collection<T> elements){
		this.addAll(elements);
	}
	
	public void quickSort(Comparator<T> comp) {
		doQuickSort(0, size() - 1, comp);
	}
	
	private void doQuickSort(int left, int right, Comparator<T> comp) {
		if (left  >= right)
			return;
		T o = this.get(right);
		int i = left - 1, j = right;
		while(true) {
			//find first object that not less than o.
			while (comp.compare(this.get(++i), o) < 0);
			//find last object that not large than o;
			while (j > 0) {
				if (comp.compare(this.get(--j), o) <= 0)
					break;
			}
			if (i >= j)
				break;	//find one index i, the elements between 0 to i-1 is less than o, the elements between i to right-1 is large or equal to o.
			swap(i, j);
		}
		swap(i, right);
		doQuickSort(left, i - 1, comp);
		doQuickSort(i + 1, right, comp);
	}
	
	private void swap(int i, int j) {
		if (i == j)
			return;
		T temp = this.get(i);
		this.set(i, this.get(j));
		this.set(j, temp);
	}
}
