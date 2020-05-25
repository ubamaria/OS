package laba4_done;


public class IndexKnot {
	private int [] positions;
	private String fileName;
	private int fileSize;
	private IndexKnot knot;

	public IndexKnot(int [] positions, String fileName, int fileSize) {
		this.positions = positions;
		this.fileName = fileName;
		this.fileSize = fileSize;

	}

	public void indexKnot(int [] ps) {
		knot=new IndexKnot(positions,"имя", -1);
	}

	public int [] getPositions() {
		return positions;
	}

	public String getName() {
		return fileName;
	}

	public int fileSize() {
		if(knot.fileSize == -1)
			return fileSize;
		return fileSize + knot.fileSize;
	}

	public String toStr() {
		String s = "";
		for (int i = 0; i < positions.length; i++) {
			s += i + " : I " + positions[i]
					+" имя: "+ fileName +" размер: "+ fileSize + "\n";
		}
		return s;
	}

	public void setNode(int [] ps, String name, int size) {
		knot = new IndexKnot(ps,name, size);
	}

	public IndexKnot nextKnot() {
		return knot;
	}
}

