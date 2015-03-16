package pt.c02classes.s01knowledge.s02app.actors;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IDeclaracao;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;

import java.util.*;

public class EnquirerAnimals implements IEnquirer
{
    IObjetoConhecimento obj;
	
    IResponder responder;
    
	@Override
	public void connect(IResponder responder) {
		this.responder = responder;
	}
	
	public boolean discover() {
		IBaseConhecimento bc = new BaseConhecimento();
		
		Vector<String> questoes = new Vector<String>();
		Vector<String> resp = new Vector<String>();
		
		String nAnimais[] = bc.listaNomes();
		
		int i;
		for (i = 0; i < nAnimais.length; i++) {
			obj = bc.recuperaObjeto(nAnimais[i]);
			
			IDeclaracao decl = obj.primeira();
			
			boolean animalEsperado = true;
			
			while (	decl != null && animalEsperado) {
				String pergunta = decl.getPropriedade();
				
				boolean flag = true;
				if(questoes.contains(new String(pergunta)))
				  flag = false;
				
				String respostaEsperada = decl.getValor();
				String resposta;
				
				if(flag) {
					resposta = responder.ask(pergunta);
					questoes.addElement(new String(pergunta));
					resp.addElement(new String(resposta));
				} else {
					
					resposta = resp.elementAt(questoes.indexOf(pergunta));
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
		return true;
	}
	
}