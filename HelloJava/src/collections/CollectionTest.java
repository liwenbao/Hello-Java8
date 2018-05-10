package collections;
import java.util.*;
import thinkInJava.tool.*;

public class CollectionTest {
	public static void main(String[] args) {
		CollectionTest ct = new CollectionTest();
		ct.useCollection(new Vector<String>());
		ct.useCollection(new ArrayList<String>());
		ct.useCollection(new LinkedList<String>());
		ct.useCollection(new HashSet<String>());
		ct.useCollection(new LinkedHashSet<String>());
	}
	
	final String[] sent = {" Java"," is"," fun!", " i'm enjoy in", " Java"};
	
	void useCollection(java.util.Collection<String> c) {
		c.add(c.getClass().getTypeName() + " is a collection");
		c.addAll(Arrays.asList(sent));
		Iterator<String> i = c.iterator();
		while(i.hasNext()) {
			Tool.print(i.next());
		}
		Tool.println("");
	}
}
