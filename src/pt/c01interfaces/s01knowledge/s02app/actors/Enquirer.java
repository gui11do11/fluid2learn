package pt.c01interfaces.s01knowledge.s02app.actors;

import pt.c01interfaces.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IDeclaracao;
import pt.c01interfaces.s01knowledge.s01base.inter.IEnquirer;
import pt.c01interfaces.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IResponder;

public class Enquirer implements IEnquirer
{
    IObjetoConhecimento obj;
	
	@Override
	public void connect(IResponder responder)
	{
		IBaseConhecimento bc = new BaseConhecimento();
		
		String nAnimais[] = bc.listaNomes();
		String questoes[] = new String[50];
		String resp[] = new String[50];
		
		int i, j = 0;
		for (i = 0; i < nAnimais.length; i++) {
			obj = bc.recuperaObjeto(nAnimais[i]);
			
			IDeclaracao decl = obj.primeira();
			
			boolean animalEsperado = true;
			
			while (decl != null && animalEsperado) {
				String pergunta = decl.getPropriedade();
				int a;
				boolean flag = true;
				for(a = 0; (a < j) && (flag); a++) {
					if (questoes[a].equalsIgnoreCase(pergunta))
						flag = false;
				}
				
				String respostaEsperada = decl.getValor();
				String resposta;
				if(flag) {
					resposta = responder.ask(pergunta);
					questoes[j] = pergunta;
					resp[j] = resposta;
					j++;
				} else {
					a--;
					resposta = resp[a];
				}
				if (resposta.equalsIgnoreCase(respostaEsperada))
					decl = obj.proxima();
				else
					animalEsperado = false;
			}
			
			if(animalEsperado) {
				boolean acertei = responder.finalAnswer(nAnimais[i]);
			
				if (acertei)
					System.out.println("Oba! Acertei!");
				else
					System.out.println("fuem! fuem! fuem!");

			}
	        
		}
		
	}
}