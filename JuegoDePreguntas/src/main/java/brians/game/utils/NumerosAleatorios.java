/**
 * 
 */
package brians.game.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

/**
 * @author Gonzalez Brian Leonel
 *
 */

@Component
public class NumerosAleatorios {
	
	private static Random r = new Random();
	
	public List<Integer> numerosAleatorios(int min, int max){
		
		List<Integer> aux = new ArrayList<Integer>();
		
		//10 es la cantidad de preguntas, entonces genera 10 numeros aleatorios
		for (int i = 0; i < 10; i++) {
			aux.add(getNumeroAleatorio(min, max));
		}
		
		return aux;
	}
	
	public int getNumeroAleatorio(int min, int max) {

		return r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();

	}

}
