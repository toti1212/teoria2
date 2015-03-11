/**
 * Actividad 1, Teorica de la Computación y sistemas formales.
 * Grupo 6 - Pancreas.
 * 9/3/2015
 * Integrantes: Rodrigo Suarez, Sebastian Caredio, Fernando Torterolo, Gianni Iaquinta, José Ignacio Chartier.
 * Descripcion: Dado una entrada de un numero en base 10, que puede contener signo, decimales y separador de miles,
 * espacios al antes y despues del signo y de la entrada, debe verificar una entrada valida o no.
 *
 */

public class programa {


  public static void main(String args[]) {

    String [] validos = new String [] { "1", "3,14", "1.000", "-5", " - 4,6 ", " 5.000,8", " 500 ", "+2 " } ;

    String [] invalidos = new String [] {"abcdef","  123abc", "123abc", "<>9io", "+",
            "+-10", "10.","67 89","", "123.13,05", "eˆ2", " 3, " , "252,12,12"};

    System.out.println("\nValidos: =>" +  validos.length);

      for (String str : validos) {
      System.out.println( "El resultado de parsear la entrada valida: '" + str + "' es => " + Parser.parsear(str));
    }

    System.out.println("\n\nInvalidos=>" +  invalidos.length);

      for (String str : invalidos) {
      System.out.println("El resultado de parsear la entrada invalida: '" + str + "' es => " + Parser.parsear(str));
    }

  }

  public static class Parser {
    public static Boolean parsear(String entrada) {

      char car;
      char[] caracteres = entrada.toCharArray();
      int largo = entrada.length();
      int i = 0;
      int contador = 0;
      Boolean flagSigno = false, flagNumero = false, flagPunto = false, flagComa = false,
              finalizo = false, hayError = false, flagDecimalPart=false;

      while (!hayError && i < largo) {

        car = caracteres[i];
        switch (car) {

          case ' ':
            if (flagNumero) {
              finalizo = true;
            }
            break;

          case '+' : case '-':

            if (!finalizo) {
              if (flagSigno || flagNumero) {
                hayError = true;
              }
              else {
                flagSigno = true;
              }
            } else {
              hayError = true;
            }
            break;

          case '0' : case '1': case '2': case '3' : case '4' : case '5' : case '6' : case '7': case '8': case '9':
            flagNumero = true;
            flagPunto = false;
            flagComa = false;

            if (!finalizo) {
              if (contador > 0) contador--;
            } else {
              hayError = true;
            }
            break;

          case '.':
            flagPunto = true;
            flagComa = false;

            if (!finalizo) {
                contador = 3;
            } else {
              hayError = true;
            }
            break;

          case ',':

            flagComa = true;
            flagPunto = false;

            if (!flagDecimalPart) {
                flagDecimalPart = true;
            } else {
                hayError = true;
            }

            if (contador != 0) {
                hayError = true;
            }
            break;

          default:
            hayError = true;
        }

        if (i == largo-1 && flagNumero)
          finalizo=true;
        i++;
      }

      return finalizo && !hayError && !flagPunto && !flagComa && contador==0 ;
    }
  }

}
