import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Process {
			private int max_time;			
			private int priority;
			private String id;
			private List<Thread> thread;		
			public Process(String id, int priority) {
				this.id = id;
				this.priority = priority;
				Random randomNumber = new Random();
				thread = new ArrayList<>();
				for (int i = 0; i < randomNumber.nextInt(5) + 1; i++) {
					thread.add(new Thread((i + 1) + " thread", 8 * i + 1,priority, id));
					this.max_time += thread.get(i).getmax_time();
				}
			}		
			public int getmax_time() {
				return max_time;
			}		
			public void setmax_time(int max_time) {
				this.max_time = max_time;
			}	
			public int getpriority() {
				return priority;
			}		
			public List<Thread> getlist() {
				return thread;
			}		
			public String getid() {
				return id;
			}
			public static final int quant = 5;		
			public void startThread(int q) {
				while (!thread.isEmpty()) {
					for (int i = 0; i < thread.size(); i++) {
						q = quant;
						while (thread.get(i).isTime() && q > 0) {
							print(" active",i);
							thread.get(i).minusTime();
							q--;
							if (q == 0 && thread.get(i).getmax_time() != 0) {
								print(" stop",i);
								System.out.println();
								break;
							}
							if (thread.get(i).getmax_time() == 0) {
								print(" final",i);
								System.out.println();
								thread.remove(i);
								--i;
								break;
							}
						}
					}
				}
			}
			
			public void print(String str,int i){
				System.out.println(thread.get(i).getpid() + " "
						+ thread.get(i).getId() + " priority: "
						+ thread.get(i).getpriority() + " max_time: "
						+ thread.get(i).getmax_time()
						+ str);
			}
	}

