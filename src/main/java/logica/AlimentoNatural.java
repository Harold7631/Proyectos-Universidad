package logica;

import java.util.Date;

public class AlimentoNatural extends Alimento {

    protected String tipoAlimento;
    
    public AlimentoNatural(){
    }

    public AlimentoNatural(String tipoAlimento, String codAlimento, String nomAlimento, double cantProteinas, double cantCarbohidratos, double cantGrasas, Date fechaLargaDate) {
        super(codAlimento, nomAlimento, cantProteinas, cantCarbohidratos, cantGrasas, fechaLargaDate);
        this.tipoAlimento = tipoAlimento;
    }

    public String getTipoAlimento() {
        return tipoAlimento;
    }

    public void setTipoAlimento(String tipoAlimento) {
        this.tipoAlimento = tipoAlimento;
    }

    @Override
    public String toString() {
        return "AlimentoNatural{" + " Codigo: " + codAlimento + " Nombre: " + nomAlimento
                + " Proteinas: " + cantProteinas + " Carbohidratos: " + cantCarbohidratos
                + " Grasas: " + cantGrasas + " Fecha: " + fecha + " tipoAlimento: " + tipoAlimento;
    }
}
