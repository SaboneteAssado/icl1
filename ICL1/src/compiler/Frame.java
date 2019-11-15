package compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * Class frame.
 * @author Miguel Araujo 45699
 *
 */
public class Frame {

	private static final String CLASS = ".class public ";

	private static final String EXTENSION = ".j";

	private static final String NAME = "frame_";

	private static final String PATH = "./ICL/src/";

	private static final String LABEL = "label_";

	private String name, sl;
	
	private int labelCounter;
	
	private HashMap<String, String> vars;
	
	/**
	 * Constructor
	 * @param frame number of the frame.
	 * @param vars - map of frame variables.
	 * @param sL - static link
	 */
	public Frame(int frame, String sl) {
		this.name = NAME + frame;
		vars = new HashMap<String, String>();
		this.sl = sl;
		labelCounter = -1;
	}
	
	/**
	 * Get methods
	 */
	public String getLabel() {
		return LABEL + labelCounter;
	}
	
	public int getNumLabel() {
		return labelCounter;
	}
	
	/**
	 * Method to generate new labels.
	 * @return
	 */
	public String getNewLabel() {
		labelCounter++;
		return LABEL + labelCounter;
	}
	
	public String getFrameName() {
		return name;
	}
	
	public String getStaticLink() {
		return sl;
	}
	
	public HashMap<String, String> getVars() {
		return vars;
	}
	
	public void setStaticLink(String sl) {
		this.sl = sl;
	}
	
	/**
	 * Method to add a var to frame
	 * @param var - variable 
	 * @param type - type of variable
	 */
	public void addVar(String var, String type) {
		vars.put(var, type);
	}
	
	/**
	 * Dump for jasmin
	 */
	public void dump() throws FileNotFoundException {
		String filename = name + EXTENSION;
		File file = new File(filename);
		PrintStream out = new PrintStream(file);
		
		out.println(CLASS + name);
		out.println(".super java/lang/Object");
		
		if(!sl.equals("")) {
			out.println(".field public sl L" + sl + ";");
		} else
			out.println(".field public sl Ljava/lang/Object;");
		Iterator<Entry<String, String>> iter = vars.entrySet().iterator();
		
		while(iter.hasNext()){
			Entry<String, String> next = iter.next();
			out.println(".field public " + next.getKey() + " " + next.getValue());
		}
		
		out.println();
		out.println(".method public <init>()V ");
		out.println("aload_0");
		out.println("invokenonvirtual java/lang/Object/<init>()V");
		out.println("return");
		out.println(".end method");
		out.close();
	}
}