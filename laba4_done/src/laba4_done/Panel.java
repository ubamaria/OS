package laba4_done;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Panel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int[] memory;
	private static int countElement;
	private static int countFree;
	private static ArrayList<File> files;

	public Panel(int[] memory, int countElement, int countFree, ArrayList<File> files) {
		Panel.memory = memory;
		Panel.countElement = countElement;
		Panel.countFree = countFree;
		Panel.files = files;
	}

	public boolean addFile(String name, int size) {
		if (files != null) {
			for (int i = 0; i < files.size(); i++) {
				if (files.get(i).getKnot().getName().equals(name)) {
					JOptionPane.showMessageDialog(null, "Такой файл уже есть.");
					return false;
				}
			}
		}
		if (size / 4 > countFree) {
			JOptionPane.showMessageDialog(null, "Слишком большой файл.");
			return false;
		}
		countFree -= size / 4;
		countElement = size / 4;
		int [] ps = new int [countElement];
		int ps_id = 0;
		for (int i = 0; i < memory.length && countElement > 0; i++) {
			if (memory[i] == 1) {
				countElement--;
				memory[i] = 2;
				ps[ps_id++] = i;
			}
		}

		int sizeLocal = 0;
		if(size / 4 > 3) {
			sizeLocal = 3*4;
			size -=sizeLocal;
		} else {
			sizeLocal = size;
			size = 0;
		}
		IndexKnot knot = new IndexKnot(ps,name, sizeLocal);
		knot.indexKnot(ps);
		while (size > 0) {
			if(size / 4 > 3) {
				sizeLocal = 3*4;
				size -=sizeLocal;
				knot.setNode(ps,name, sizeLocal);
			} else {
				sizeLocal = size;
				size = 0;
				knot.setNode(ps,name, sizeLocal);
			}
		}
		File file = new File(knot);
		files.add(file);
		return true;
	}

	public static void Delete(String s) {
		int [] ps = getfile(s).getKnot().getPositions();
		if (ps != null) {
			for (int i = 0; i < ps.length; i++) {
				memory[ps[i]] = 1;
			}
		}
		countFree += getfile(s).getKnot().fileSize() / 2;
		files.remove(getfile(s));
	}

	public static File getfile(String s) {
		if (files != null) {
			for (int i = 0; i < files.size(); i++) {
				if (files.get(i).getKnot().getName().equals(s)) {
					return files.get(i);
				}
			}
		}
		return null;
	}
}
