
public class Knot {

	private int knotId;
	private int fileId;
	private Knot next_knot;

	public Knot(int fileId, int knotId) {
		this.fileId = fileId;
		this.knotId = knotId;
	}

	public void setFile(int fileId) {
		this.fileId = fileId;
	}

	public int getKnotId() {
		return knotId;
	}
	
	public void setKnot(int fileId, int knotId) {
		next_knot = new Knot(fileId, knotId);
	}

	public Knot getNextKnot() {
		return next_knot;
	}

	public int getId() {
		return fileId;
	}
}