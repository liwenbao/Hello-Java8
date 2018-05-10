package collections;
import java.util.*;
import thinkInJava.tool.*;

public class ListTest {
	public static void main(String[] args) {
		ListTest lt= new ListTest();
		lt.useList(new ArrayList<String>());
		lt.useList(new LinkedList<String>());
	}
	
	void useList(List<String> l) {
		l.add(l.getClass().getTypeName() + " is a List");
		l.add("java is fun!");
		Tool.print(l.get(0));
		Tool.print(l.get(1));
		Tool.println("");
	}
}
