import java.util.*;

/**
 * Esta clase mantiene una cache de numeros primos, para
 * evitar calculos repetidos, y calcula si un numero es primo.
 * La cache conserva los primos calculados.

 */

public class Primos {
    private SortedSet<Integer> primos = new TreeSet<>();
    private int max = 1;

    /**
     * @author Irene Truchado , Marcos Valle Abascal
     *
     * @return cache con los primos calculados
     */
    public SortedSet<Integer> getPrimos(){
        return primos;
    }
    /**
     * @author Irene Truchado , Marcos Valle Abascal
     *
     * @return todos los primos introducidos
     */
    public String toString(){
        return "Primos hasta "+ max+ " = "+primos;
    }

    /**
     * @author Irene Truchado , Marcos Valle Abascal
     *
     * @param n un numero entero
     * @return si n es primo
     */
    public boolean esPrimo(int n){
        actualizaPrimos(n);
        return primos.contains(n);
    }

    /**
     * @author Irene Truchado , Marcos Valle Abascal
     * Este metodo llama a compruebaPrimo, y lo aniade, si es primo,
     * para todos los numeros entre max+1 y n. Actualiza max al terminar.
     * @param n
     * @return no devuelve nada
     */
    private void actualizaPrimos(int n){
    	if (primos.contains(n)) return; /* si el numero ya ha sido analizado, se sale*/
    	if (compruebaPrimo(n)==true) { /* el numero es primo*/
    		if (n > max) {
    			primos.add(n);
    			max = n;
    		} else primos.add(n);
    	}
    }

    /**
     * @author Irene Truchado , Marcos Valle Abascal
     * Comprueba si n es primo, asumiendo que el conjunto primos
     * esta actualizado con todos los anteriores
     * @param n valor a comprobar
     * @return si n es primo
     */
    private boolean compruebaPrimo(int n){
		for (int i=2; i<n; i++) {
	        if (n%i == 0) return false; /* el numero n NO es primo*/
	      }
	    return true; /* el numero n es primo*/
    }

    /**
     * @author Irene Truchado , Marcos Valle Abascal
     * Devuelve el conjunto de numeros primos que son divisores
     * del numero indicado por argumento
     * @param n valor a comprobar
     * @return conjunto de divisores de n
     */
    public SortedSet<Integer> divisoresPrimos(int n) {
		SortedSet<Integer> divisores = new TreeSet<>();
		for (int i=2; i<=n; i++) {
	        if (n%i == 0) {
	        	if (compruebaPrimo(i)==true) divisores.add(i);
	        }
	      }
		return divisores;
    }

    /**
     * @author Irene Truchado , Marcos Valle Abascal
     * Punto de entrada a la aplicacion.
     *
     * Este metodo ordena los numeros de la linea de comando
     * @param args Los argumentos de la linea de comando. Se esperan enteros, como cadenas
     */
	public static void main(String[] args) {
		if (args.length<2) {
			System.out.println("Se esperan al menos dos numeros como parametros.");
		}
		else {
			Primos p = new Primos();

      for (String s: args){ //recorremos el array
          int n = Integer.parseInt(s); //convertimos a entero
          if (n < 2) System.out.println (n+" no es un numero valido\n");
          else {
            if (p.esPrimo(n) == false) System.out.println(n+" no es primo, pero tiene estos divisores primos: "+p.divisoresPrimos(n)+"\n");
            else System.out.println(n+" es primo\n");
          }
          System.out.println("Primos encontrados hasta el momento: ");
          System.out.println(p.toString()+"\n"); // Imprimimos el conjunto de primos, por salida estandar
      }
		}
	}
}
