package logica;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Alimento{
    protected String codAlimento ;
    protected String nomAlimento;
    protected double cantProteinas;
    protected double cantCarbohidratos;
    protected double cantGrasas;
    protected SimpleDateFormat fechaFormato = new SimpleDateFormat("dd-MM-yyyy");
    protected String fecha;

    public Alimento(){  
    }
    
    public Alimento(String codAlimento, String nomAlimento, double cantProteinas, double cantCarbohidratos, double cantGrasas, Date fechaLargaDate) {
        this.codAlimento = codAlimento;
        this.nomAlimento = nomAlimento;
        this.cantProteinas = cantProteinas;
        this.cantCarbohidratos = cantCarbohidratos;
        this.cantGrasas = cantGrasas;
        this.fecha = fechaFormato.format(fechaLargaDate);
    }

    public String getCodAlimento() {
        return codAlimento;
    }

    public void setCodAlimento(String codAlimento) {
        this.codAlimento = codAlimento;
    }

    public String getNomAlimento() {
        return nomAlimento;
    }

    public void setNomAlimento(String nomAlimento) {
        this.nomAlimento = nomAlimento;
    }

    public double getCantProteinas() {
        return cantProteinas;
    }

    public void setCantProteinas(double cantProteinas) {
        this.cantProteinas = cantProteinas;
    }

    public double getCantCarbohidratos() {
        return cantCarbohidratos;
    }

    public void setCantCarbohidratos(double cantCarbohidratos) {
        this.cantCarbohidratos = cantCarbohidratos;
    }

    public double getCantGrasas() {
        return cantGrasas;
    }

    public void setCantGrasas(double cantGrasas) {
        this.cantGrasas = cantGrasas;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}



