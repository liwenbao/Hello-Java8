package collections;

import java.util.*;

import thinkInJava.tool.Tool;

public class MapTest {
	public static void main(String[] args) {
		MapTest mt= new MapTest();
		mt.useMap(new HashMap<String, String>());
		mt.useMap(new LinkedHashMap<String, String>());
	}
	
	void useMap(Map<String, String> l) {
		l.put("0", l.getClass().getTypeName());
		l.put("1", " is a Map");
		l.put("2", " java");
		l.put("3", " is");
		l.put("4", " fun!");
		Tool.print(l.get("0"));
		Tool.print(l.get("1"));
		Tool.print(l.get("2"));
		Tool.print(l.get("3"));
		Tool.print(l.get("4"));
		Tool.println("");
	}
}
