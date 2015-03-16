package pt.c02classes.s01knowledge.s02app.app;

import java.util.Scanner;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.impl.Statistics;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;
import pt.c02classes.s01knowledge.s01base.inter.IStatistics;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerAnimals;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerMaze;
import pt.c02classes.s01knowledge.s02app.actors.ResponderAnimals;
import pt.c02classes.s01knowledge.s02app.actors.ResponderMaze;

public class OrchestratorInit {
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.print("Entre com o jogo desejado (Maze ou Animals)");
		String jogo = scanner.nextLine();
		
		if (jogo.equalsIgnoreCase("Maze")) {
			IEnquirer enq;
			IResponder resp;
			IStatistics stat;
		
			System.out.println("Entre com o nome do mapa");
			String mapa = scanner.nextLine(); 
			System.out.println("Enquirer com " + mapa + "...");
			stat = new Statistics();
			resp = new ResponderMaze(stat, mapa);
			enq = new EnquirerMaze();
			enq.connect(resp);
			enq.discover();
			System.out.println("----------------------------------------------------------------------------------------\n");
		
		} else if (jogo.equalsIgnoreCase("Animals")) {

			IEnquirer enq;
			IResponder resp;
			IStatistics stat;
			
			IBaseConhecimento base = new BaseConhecimento();

			System.out.println("Entre com o cenraio");
			String mapa = scanner.nextLine();
			
			base.setScenario(mapa);
			
			String listaAnimais[] = base.listaNomes();
	        for (int animal = 0; animal < listaAnimais.length; animal++) {
				System.out.println("Enquirer com " + listaAnimais[animal] + "...");
				stat = new Statistics();
				resp = new ResponderAnimals(stat, listaAnimais[animal]);
				enq = new EnquirerAnimals();
				enq.connect(resp);
				enq.discover();
				System.out.println("----------------------------------------------------------------------------------------\n");
	        }		
		}
	}
}
