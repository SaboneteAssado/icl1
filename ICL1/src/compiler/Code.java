package compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for the comp code
 * @author Miguel Araujo 45699
 *
 */
public class Code {

	private static final String EXTENSION = ".j";

	private static final String IDENT = "	";

	private List<String> commands;
	private List<Frame> frames;
	
	private int frameCounter, currFrame;

	public Code() {
		this.commands = new LinkedList<String>();
		frameCounter = 0;
		currFrame = -1;
		frames = new ArrayList<Frame>();
		commands.add("aconst_null");
		commands.add("astore 0");
	}

	/**
	 * Creates a new frame.
	 * @return the name of the frame.
	 */
	public Frame newFrame(String sl){
		Frame frame = new Frame(frameCounter, sl);
		if (!frames.isEmpty()) {
			frame.setStaticLink(frames.get(frameCounter-1).getFrameName());	
		}
		frames.add(frame);
		frameCounter++;
		currFrame++;
		return frame;
	}
	

	public Frame getFrame(int i) {
		return frames.get(i);
	}

	/**
	 * Get method for the number of the current frame.
	 * @return an integer representing the number of the current frame.
	 */
	public int getCurrFrameNumber() {
		return currFrame;
	}

	/**
	 * Get method for the current frame.
	 * @return an object type Frame representing the current frame.
	 */
	public Frame getCurrFrame() {
		if (frames.isEmpty())
			return null;
		return frames.get(frameCounter - 1);
	}

	/**
	 * Adds a command.
	 * @param command to be added.
	 */
	public void emit(String command) {
		commands.add(command);
	}

	/**
	 * The header of the file
	 * @param out PrintStream to the file
	 * @param name of the .j file
	 */
	public void dumpHeader(PrintStream out, String name) {
		out.print(".class public " + name + "\r\n");
		out.print(".super java/lang/Object\r\n"); 
		out.print("\r\n");
		out.print(".method public <init>()V\r\n");
		out.print("aload_0\r\n");
		out.print("   invokenonvirtual java/lang/Object/<init>()V\r\n" );
		out.print("   return\r\n");
		out.print(".end method\r\n");
		out.print("\r\n");
		out.print(".method public static main([Ljava/lang/String;)V\r\n");
		out.print("       .limit locals 10\r\n");
		out.print("       .limit stack 256\r\n");
		out.print("       getstatic java/lang/System/out Ljava/io/PrintStream;\r\n");
		out.println("");
	}

	/**
	 * The footer of the file
	 * @param out PrintStream to the file
	 */
	public void dumpFooter(PrintStream out) {
		out.println("");
		out.print("invokestatic java/lang/String/valueOf(I)Ljava/lang/String;\r\n");
		out.print("       invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V\r\n");
		out.print("\r\n");
		out.print("       return\r\n");
		out.print(".end method");
	}

	/**
	 * Dump utils to the file
	 * @param filename - name of the file.
	 * @throws FileNotFoundException if the file is not found.
	 */
	public void dump(String filename) throws FileNotFoundException {
		File file = new File(filename + EXTENSION);
		PrintStream out = new PrintStream(file);
		dumpHeader(out, filename);
		for (String command: commands)
			out.println(IDENT + command);
		dumpFooter(out);
		dumpFrames();
	}

	/**
	 * Dumps the frames
	 * @throws FileNotFoundException if the file is not found.
	 */
	private void dumpFrames() throws FileNotFoundException {
		for (Frame frame: frames)
			frame.dump();
	}
}