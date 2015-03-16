package pt.c02classes.s01knowledge.s02app.actors;

import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;
import java.util.ArrayList;

public class EnquirerMaze implements IEnquirer {

	IResponder responder;
	
	public void connect(IResponder responder) {
		this.responder = responder;
	}
	
	ArrayList<String> mapa = new ArrayList<String> ();
	
	public boolean finder(String mov, int i, int j) {
		boolean a = false, b = false, c = false, d = false, T;
		String antmov = new String();
		antmov = "nada";
		
		responder.move(mov);
		
		mapa.add(i + "," + j);
		
		if (responder.ask("aqui").equalsIgnoreCase("saida"))
			return true;
		
		/* Realiza o movimento para oeste */
		if (!mov.equalsIgnoreCase("leste")) {
			if (!responder.ask("oeste").equalsIgnoreCase("parede"))
				if (!mapa.contains(i + "," + (j - 1)))
					a = finder("oeste", i, j - 1);
		} else
			antmov = "oeste";
		
		/* Realiza o monvimento para leste */
		if (!mov.equalsIgnoreCase("oeste")) {
			if (!responder.ask("leste").equalsIgnoreCase("parede"))
				if (!mapa.contains(i + "," + (j + 1)))
					b = finder("leste", i, j + 1);
		} else
			antmov = "leste";
		
		/* Realiza o monvimento para norte */
		if (!mov.equalsIgnoreCase("sul")) {
			if (!responder.ask("norte").equalsIgnoreCase("parede"))
				if (!mapa.contains((i + 1) + "," + j))
					c = finder("norte", i + 1, j);
		} else
			antmov = "norte";
		
		/* Realiza o monvimento para sul */
		if (!mov.equalsIgnoreCase("norte")) {
			if (!responder.ask("sul").equalsIgnoreCase("parede"))
				if (!mapa.contains((i - 1) + "," + j))
					d = finder("sul", i - 1, j);
		} else
			antmov = "sul";
		
		T = a || b || c || d;
		
		if (T)
			return true;
		
		responder.move(antmov);
		
		return false;
	}
	
	public boolean discover() {
		
		if (finder("nada", 0, 0))
			System.out.print("Voce está na " + responder.ask("aqui"));
		    
		return true;
	}
	
}
