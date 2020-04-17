
public class Thread {
	private String id;
	private int max_time;
	private int priorTh;
	private String pid;		
	public Thread(String id, int max_time,int priorTh, String p) {
		this.id = id;
		this.pid = p;
		this.priorTh=priorTh;
		this.max_time = max_time;
	}
	

	public int getmax_time() {
		return max_time;
	}		
	public int getpriority() {
		return priorTh;
	}		
	public String getId() {
		return id;
	}		
	public String getpid() {
		return pid;
	}		
	public boolean isTime() {
		if (max_time <= 0) {
			return false;			
			}
		return true;
	}		
	public void minusTime(){
		max_time--;
	}
}

