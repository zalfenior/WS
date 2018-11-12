import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WebSearch {
	
	private static File toMap;
	private static int iter = 0;
	private static float scale = 0;
	
	public static void main(String[] args) {
		
		String line;
		
		if(args.length != 3) {
			System.out.println("ERROR: method to run: /java WebSearch <GML File name> <Int n: number of iterations> <Float z: Scaledown> ");
			System.exit(0);
		}
		
		toMap = new File(args[0]);
		iter = Integer.parseInt(args[1]);
		scale = Float.valueOf(args[2]);
		
		try (BufferedReader read = new BufferedReader(new FileReader(toMap))) {
		    while ((line = read.readLine()) != null) {
		    	
		    }
		} catch (IOException e) {
			System.out.println("That happened, wasn't supposed to though");
			e.printStackTrace();
		}
		
	}
}
