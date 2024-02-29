import java.io.*;
import java.util.Locale;
import java.util.Scanner;
import java.io.File;

public class Main {
    static Scanner teclado = new Scanner(System.in);
    static Scanner scannerNum = new Scanner(System.in).useLocale(Locale.US);
    static String nombreCarpeta = "./Data/";
    public static void main(String[] args) {
        String nombreFichero = leerCarpeta();
        String texto = "";
        Boolean editarFichero = true;
        System.out.print("¿Quieres editar el fichero? (S/N)");
        String respuesta = teclado.nextLine();
        if (respuesta.toUpperCase().equals("N")) {
            editarFichero = false;
        }
        try {
            FileWriter fichero = new FileWriter(nombreFichero, editarFichero);
            BufferedWriter escritura = new BufferedWriter(fichero);
            do {
                System.out.println("Introduce texto (FIN para terminar)");
                texto = teclado.nextLine();
                if (!texto.toUpperCase().equals("FIN")) {
                    escritura.write(texto);
                    escritura.newLine();
                }
            } while (!texto.toUpperCase().equals("FIN"));
            escritura.close(); // Asegúrate de cerrar el BufferedWriter
            teclado.close(); // Asegúrate de cerrar el Scanner
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static String leerCarpeta() {
        File carpeta = new File(nombreCarpeta);
        File[] listaDeArchivos = carpeta.listFiles();
        int numFile;
        if (listaDeArchivos != null) {
            for (int i = 0; i < listaDeArchivos.length; i++) {
                if (listaDeArchivos[i].isFile()) {
                    System.out.println((i + 1) + ". " + listaDeArchivos[i].getName());
                }
            }
        } else {
            System.out.println("El directorio está vacío o no existe.");
        }
        System.out.println("Elige un archivo: ");
        numFile = scannerNum.nextInt();
        return (nombreCarpeta + listaDeArchivos[numFile - 1].getName());
    }

}