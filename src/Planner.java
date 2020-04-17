import java.util.ArrayList;
import java.util.Random;

public class Planner {
	public Planner() {
		ArrayList<Process> processes = new ArrayList<>();
		Random randomNumber = new Random();
		for (int i = 0; i < randomNumber.nextInt(3) + 1; i++) {
			int priority = randomNumber.nextInt(5) + 1;
			Process process = new Process((i + 1) + " process", priority);			
			processes.add(process);	
			process.priority_sort();
			process.startThread(Process.quant);
		}		
	}
}
