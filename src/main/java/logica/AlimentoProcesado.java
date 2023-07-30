package logica;

import java.util.ArrayList;
import java.util.Date;

public class AlimentoProcesado extends Alimento{
    public static ArrayList<String> listaIngredientes = new ArrayList<>();
    
    public AlimentoProcesado(){   
    }

    public AlimentoProcesado(String codAlimento, String nomAlimento, double cantProteinas, double cantCarbohidratos, double cantGrasas, Date fechaLargaDate) {
        super(codAlimento, nomAlimento, cantProteinas, cantCarbohidratos, cantGrasas, fechaLargaDate);
    }

    public static ArrayList<String> getListaIngredientes() {
        return listaIngredientes;
    }

    public static void setListaIngredientes(String lista) {
        listaIngredientes.add(lista);
    }
}
