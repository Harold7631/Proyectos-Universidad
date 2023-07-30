package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.AlimentoNatural;
import logica.AlimentoProcesado;
import static logica.AlimentoProcesado.listaIngredientes;
import static logica.Controladora.indice;
import static logica.Controladora.listaNaturales;
import static logica.Controladora.listaProcesados;

public class Persistencia {

    public static void guardarTxT() {
        File archivo;
        FileWriter escribirEnArchivo = null;
        PrintWriter guardarInfo;
        archivo = new File("Alimento.txt");

        try {
            archivo.createNewFile();
            escribirEnArchivo = new FileWriter(archivo, false);
            guardarInfo = new PrintWriter(escribirEnArchivo);
            guardar(guardarInfo);
            guardarInfo.flush();
            guardarInfo.close();
            escribirEnArchivo.close();

        } catch (IOException | NullPointerException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void guardar(PrintWriter guardarDatos) {

        for (int i = 0; i < listaNaturales.size(); i++) {
            guardarDatos.print("$natural$" + "|");
            guardarDatos.print(listaNaturales.get(i).getTipoAlimento() + "|");
            guardarDatos.print(listaNaturales.get(i).getCodAlimento() + "|");
            guardarDatos.print(listaNaturales.get(i).getNomAlimento() + "|");
            guardarDatos.print(listaNaturales.get(i).getCantProteinas() + "|");
            guardarDatos.print(listaNaturales.get(i).getCantCarbohidratos() + "|");
            guardarDatos.print(listaNaturales.get(i).getCantGrasas() + "|");
            guardarDatos.print(listaNaturales.get(i).getFecha() + "\n");
        }
      
        for (int i = 0; i < listaProcesados.size(); i++) {
            guardarDatos.print("$procesado$" + "|");
            guardarDatos.print(listaProcesados.get(i).getCodAlimento() + "|");
            guardarDatos.print(listaProcesados.get(i).getNomAlimento() + "|");
            guardarDatos.print(listaProcesados.get(i).getCantProteinas() + "|");
            guardarDatos.print(listaProcesados.get(i).getCantCarbohidratos() + "|");
            guardarDatos.print(listaProcesados.get(i).getCantGrasas() + "|");
            guardarDatos.print(listaProcesados.get(i).getFecha() + "|");
            guardarDatos.print(listaIngredientes.get(i) + "\n");
        }
    }

    public static void datosTxt() {

        try {
            FileReader archivo = new FileReader("Alimento.txt");
            BufferedReader leerDatos = new BufferedReader(archivo);

            String lineaDatos = null;

            while (!(lineaDatos = leerDatos.readLine()).isEmpty()) {
                StringTokenizer tokenDatos = new StringTokenizer(lineaDatos, "|");
                String validador = tokenDatos.nextToken();

                if (validador.equals("$natural$")) {
                    AlimentoNatural alimentoNaturalTxT = new AlimentoNatural();
                    alimentoNaturalTxT.setTipoAlimento(tokenDatos.nextToken());
                    alimentoNaturalTxT.setCodAlimento(tokenDatos.nextToken());
                    alimentoNaturalTxT.setNomAlimento(tokenDatos.nextToken());
                    alimentoNaturalTxT.setCantProteinas(Double.parseDouble(tokenDatos.nextToken()));
                    alimentoNaturalTxT.setCantCarbohidratos(Double.parseDouble(tokenDatos.nextToken()));
                    alimentoNaturalTxT.setCantGrasas(Double.parseDouble(tokenDatos.nextToken()));
                    alimentoNaturalTxT.setFecha(tokenDatos.nextToken());
                    listaNaturales.add(alimentoNaturalTxT);

                } else if (validador.equals("$procesado$")) {
                    AlimentoProcesado alimentoProcesadoTxT = new AlimentoProcesado();
                    alimentoProcesadoTxT.setCodAlimento(tokenDatos.nextToken());
                    alimentoProcesadoTxT.setNomAlimento(tokenDatos.nextToken());
                    alimentoProcesadoTxT.setCantProteinas(Double.parseDouble(tokenDatos.nextToken()));
                    alimentoProcesadoTxT.setCantCarbohidratos(Double.parseDouble(tokenDatos.nextToken()));
                    alimentoProcesadoTxT.setCantGrasas(Double.parseDouble(tokenDatos.nextToken()));
                    alimentoProcesadoTxT.setFecha(tokenDatos.nextToken());
                    alimentoProcesadoTxT.setListaIngredientes(tokenDatos.nextToken());
                    listaProcesados.add(alimentoProcesadoTxT);
                }
            }
        } catch (IOException | NullPointerException | IndexOutOfBoundsException e) {
        }
    }

    public static void eliminarDatosTxT(boolean eliminadoNatural) {

        File archivo;
        FileWriter escribirEnArchivo;
        PrintWriter guargarDatos;
        archivo = new File("AlimentosEliminados.txt");

        try {
            archivo.createNewFile();
            escribirEnArchivo = new FileWriter(archivo, true);
            guargarDatos = new PrintWriter(escribirEnArchivo);

            if (eliminadoNatural == true) {
                guargarDatos.print("$natural$" + "|");
                guargarDatos.print(listaNaturales.get(indice).getTipoAlimento() + "|");
                guargarDatos.print(listaNaturales.get(indice).getCodAlimento() + "|");
                guargarDatos.print(listaNaturales.get(indice).getNomAlimento() + "|");
                guargarDatos.print(listaNaturales.get(indice).getCantProteinas() + "|");
                guargarDatos.print(listaNaturales.get(indice).getCantCarbohidratos() + "|");
                guargarDatos.print(listaNaturales.get(indice).getCantGrasas() + "|");
                guargarDatos.print(listaNaturales.get(indice).getFecha() + "\n");
            } else {
                guargarDatos.print("$procesado$" + "|");
                guargarDatos.print(listaProcesados.get(indice).getCodAlimento() + "|");
                guargarDatos.print(listaProcesados.get(indice).getNomAlimento() + "|");
                guargarDatos.print(listaProcesados.get(indice).getCantProteinas() + "|");
                guargarDatos.print(listaProcesados.get(indice).getCantCarbohidratos() + "|");
                guargarDatos.print(listaProcesados.get(indice).getCantGrasas()+ "|");
                guargarDatos.print(listaProcesados.get(indice).getFecha() + "|");
                guargarDatos.print(listaIngredientes.get(indice) + "\n");
            }
            guargarDatos.close();
            escribirEnArchivo.close();
        } catch (IOException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
