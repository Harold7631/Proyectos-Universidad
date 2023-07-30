package logica;

import igu.cargarDatos.AgregaDatosNaturales;
import static igu.cargarDatos.AgregaDatosNaturales.asignacionModificarNatu;
import static igu.cargarDatos.AgregaDatosNaturales.tblAgregaNaturales;
import igu.cargarDatos.AgregaDatosProcesados;
import static igu.cargarDatos.AgregaDatosProcesados.asignacionModificarProce;
import static igu.cargarDatos.AgregaDatosProcesados.tblAgregaProcesados;
import static igu.mostrarDatos.ReporteCodigo.tblBuscarCod;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static persistencia.Persistencia.datosTxt;
import static persistencia.Persistencia.eliminarDatosTxT;
import static persistencia.Persistencia.guardarTxT;
import static logica.AlimentoProcesado.listaIngredientes;

public class Controladora {

    public static ArrayList<AlimentoNatural> listaNaturales = new ArrayList<>();
    public static ArrayList<AlimentoProcesado> listaProcesados = new ArrayList<>();

    static DefaultTableModel modeloAgregaNaturales = new DefaultTableModel();
    static DefaultTableModel modeloAgregaProcesados = new DefaultTableModel();
    static DefaultTableModel modeloBuscarCod = new DefaultTableModel();
    static DefaultTableModel modeloMostrarFechas = new DefaultTableModel();
    static DefaultTableModel modeloReporteGeneral = new DefaultTableModel();

    public static int indice = 0;

    public static void extraerDatosTxt() {
        datosTxt();
    }

    public static void limpiarDatosMemoria() {
        listaNaturales.clear();
        listaProcesados.clear();
        listaIngredientes.clear();
    }

    public static void validarDatosNaturales(String cod, String nom, String tipoAlimento, String proteinaString, String carbohiString, String grasaString, String diaString, String mesString, String anioString) {

        Double proteina = Double.valueOf(proteinaString);
        Double carbohidratos = Double.valueOf(carbohiString);
        Double grasas = Double.valueOf(grasaString);

        int diaa = Integer.parseInt(diaString);
        int mess = Integer.parseInt(mesString);
        int anioo = Integer.parseInt(anioString);

        if (cod.isEmpty() || nom.isEmpty() || tipoAlimento.isEmpty() || proteinaString.isEmpty() || carbohiString.isEmpty() || grasaString.isEmpty() || diaString.isEmpty() || mesString.isEmpty() || anioString.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alerta! No ingresó todos los datos");
        } else if (buscarCodRepetidos(cod) == true) {
            tablaVaciaNatu(tblAgregaNaturales);
            JOptionPane.showMessageDialog(null, "El código ya existe");
        } else if (proteina < 0 || carbohidratos < 0 || grasas < 0) {
            JOptionPane.showMessageDialog(null, "Las cantidades: Proteínas, Carbohidratos y Grasas deben ser mayores o iguales a 0");
        } else {
            validarFechaIngresada(diaString, mesString, anioString);
            agregaNaturales(tipoAlimento, cod, nom, proteina, carbohidratos, grasas, fechaUniversal(diaa, mess, anioo));
            tablaAgregaNaturales(cod, nom, tipoAlimento, proteinaString, carbohiString, grasaString, diaa, mess, anioo);
        }
    }

    public static void validarDatosProcesados(String cod, String nom, String listaString, String proteinaString, String carbohiString, String grasaString, String diaString, String mesString, String anioString) {

        Double proteinas = Double.valueOf(proteinaString);
        Double carbohidratos = Double.valueOf(carbohiString);
        Double grasas = Double.valueOf(grasaString);

        int diaa = Integer.parseInt(diaString);
        int mess = Integer.parseInt(mesString);
        int anioo = Integer.parseInt(anioString);

        if (cod.isEmpty() || nom.isEmpty() || listaString.isEmpty() || proteinaString.isEmpty() || carbohiString.isEmpty() || grasaString.isEmpty() || diaString.isEmpty() || mesString.isEmpty() || anioString.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alerta! No ingresó todos los datos");
        } else if (buscarCodRepetidos(cod) == true) {
            tablaVaciaProce(tblAgregaProcesados);
            JOptionPane.showMessageDialog(null, "El código ya existe");
        } else if (proteinas < 0 || carbohidratos < 0 || grasas < 0) {
            JOptionPane.showMessageDialog(null, "Las cantidades: Proteínas, Carbohidratos y Grasas deben ser mayores o iguales a 0");
        } else {
            validarFechaIngresada(diaString, mesString, anioString);
            agregaProcesados(cod, nom, proteinas, carbohidratos, grasas, fechaUniversal(diaa, mess, anioo), listaString);
            tablaAgregaProcesados(cod, nom, listaString, proteinaString, carbohiString, grasaString, diaa, mess, anioo);
        }
    }

    public static void agregaNaturales(String tipoAli, String codAli, String nomAli, double cantProte, double cantCarbohi, double cantGrasa, Date fechaLarga) {

        AlimentoNatural alimentoNatural = new AlimentoNatural(tipoAli, codAli, nomAli, cantProte, cantCarbohi, cantGrasa, fechaLarga);
        listaNaturales.add(alimentoNatural);
    }

    public static void agregaProcesados(String codAli, String nomAli, double cantProte, double cantCarbohi, double cantGrasa, Date fechaLarga, String listaIn) {

        AlimentoProcesado alimentoProcesado = new AlimentoProcesado(codAli, nomAli, cantProte, cantCarbohi, cantGrasa, fechaLarga);
        AlimentoProcesado.setListaIngredientes(listaIn);
        listaProcesados.add(alimentoProcesado);
    }

    public static Date fechaUniversal(int dia, int mes, int anio) {
        Date fecha = new Date((anio - 1900), (mes - 1), dia);
        return fecha;
    }

    public static boolean validarFechaIngresada(String dia, String mes, String anio) {

        boolean correcto = true;

        int diaa = Integer.parseInt(dia);
        int mess = Integer.parseInt(mes);
        int anioo = Integer.parseInt(anio);

        if (dia.isEmpty() || mes.isEmpty() || anio.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar la fecha en que se creó el alimento");
            correcto = false;
        } else if (diaa <= 0 || diaa > 31) {
            JOptionPane.showMessageDialog(null, "Los días van del 1 al 31");
            correcto = false;
        } else if (mess <= 0 || mess > 12) {
            JOptionPane.showMessageDialog(null, "Los meses van del 1 al 12");
            correcto = false;
        } else if (anioo <= 0) {
            JOptionPane.showMessageDialog(null, "El año debe ser mayor a 0");
            correcto = false;
        }
        return correcto;
    }

    public static void tablaVaciaNatu(JTable tblAgregaNaturales) {
        String[] etiquetas = new String[]{"Código", "Nombre", "Tipo", "Proteínas", "Carbohidratos", "Grasas", "Fecha"};
        modeloAgregaNaturales.setColumnIdentifiers(etiquetas);
        tblAgregaNaturales.setModel(modeloAgregaNaturales);
        modeloAgregaNaturales.setRowCount(0);
    }

    public static void tablaAgregaNaturales(String codigo, String nombre, String tipoAlimento, String proteinas, String carbohidratos, String grasas, int dias, int meses, int anios) {

        modeloAgregaNaturales.addRow(new Object[]{codigo, nombre, tipoAlimento, proteinas, carbohidratos, grasas, fechaUniversal(dias, meses, anios)});
    }

    public static void tablaVaciaProce(JTable tblAgregaProcesados) {
        String[] etiquetas = new String[]{"Código", "Nombre", "Lista de Ingredientes", "Proteínas", "Carbohidratos", "Grasas", "Fecha"};
        modeloAgregaProcesados.setColumnIdentifiers(etiquetas);
        tblAgregaProcesados.setModel(modeloAgregaProcesados);
        modeloAgregaProcesados.setRowCount(0);
    }

    public static void tablaAgregaProcesados(String codigo, String nombre, String listaIngredientes, String proteinas, String carbohidratos, String grasas, int dias, int meses, int anios) {

        modeloAgregaProcesados.addRow(new Object[]{codigo, nombre, listaIngredientes, proteinas, carbohidratos, grasas, fechaUniversal(dias, meses, anios)});
    }

    public static void buscarCodigo(String codigo) {

        if (buscarEnNatural(codigo) >= 0) {
            tablaBuscaNaturales(tblBuscarCod);
        } else if (buscarEnProcesado(codigo) >= 0) {
            tablaBuscaProcesados(tblBuscarCod);
        } else {
            JOptionPane.showMessageDialog(null, "El código no existe");
        }
    }

    public static void tablaVaciaCod(JTable tblBuscarCod) {
        String[] etiquetas = new String[]{""};
        modeloBuscarCod.setColumnIdentifiers(etiquetas);
        tblBuscarCod.setModel(modeloBuscarCod);
        modeloBuscarCod.setRowCount(0);
    }

    public static void tablaBuscaNaturales(JTable tblBuscarCod) {

        String[] etiquetas = new String[]{"Código", "Nombre", "Tipo", "Fecha", "Proteínas", "Carbohidratos", "Grasas"};
        modeloBuscarCod.setColumnIdentifiers(etiquetas);
        tblBuscarCod.setModel(modeloBuscarCod);

        modeloBuscarCod.addRow(new Object[]{
            listaNaturales.get(indice).getCodAlimento(),
            listaNaturales.get(indice).getNomAlimento(),
            listaNaturales.get(indice).getTipoAlimento(),
            listaNaturales.get(indice).getFecha(),
            listaNaturales.get(indice).getCantProteinas(),
            listaNaturales.get(indice).getCantCarbohidratos(),
            listaNaturales.get(indice).getCantGrasas()});
    }

    public static void tablaBuscaProcesados(JTable tblBuscarCod) {

        String[] etiquetas = new String[]{"Código", "Nombre", "Lista de ingredientes", "Fecha", "Proteínas", "Carbohidratos", "Grasas"};
        modeloBuscarCod.setColumnIdentifiers(etiquetas);
        tblBuscarCod.setModel(modeloBuscarCod);

        modeloBuscarCod.addRow(new Object[]{
            listaProcesados.get(indice).getCodAlimento(),
            listaProcesados.get(indice).getNomAlimento(),
            listaIngredientes.get(indice),
            listaProcesados.get(indice).getFecha(),
            listaProcesados.get(indice).getCantProteinas(),
            listaProcesados.get(indice).getCantCarbohidratos(),
            listaProcesados.get(indice).getCantGrasas()});
    }

    public static boolean buscarCodRepetidos(String codigo) {
        boolean repetido;

        if (buscarEnNatural(codigo) >= 0) {
            repetido = true;
        } else if (buscarEnProcesado(codigo) >= 0) {
            repetido = true;
        } else {
            repetido = false;
        }
        return repetido;
    }

    public static void buscarFechas(String diaa, String mess, String anioo) {
        boolean encontrado01 = false, encontrado02 = false;

        try {
            if (validarFechaIngresada(diaa, mess, anioo) == true) {

                for (int i = 0; i < listaNaturales.size(); i++) {
                    if (fechaFormato(diaa, mess, anioo).equals(listaNaturales.get(i).getFecha())) {
                        mostrarFechasNaturales(i);
                        encontrado01 = true;
                    }

                }
                for (int j = 0; j < listaProcesados.size(); j++) {
                    if (fechaFormato(diaa, mess, anioo).equals(listaProcesados.get(j).getFecha())) {
                        mostrarFechasProcesados(j);
                        encontrado02 = true;
                    }
                }

                if (encontrado01 == false && encontrado02 == false) {
                    JOptionPane.showMessageDialog(null, "La fecha ingresada no existe");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error al digitar la fecha");
        }
    }

    public static String fechaFormato(String dia, String mes, String anio) {

        int diaa = Integer.parseInt(dia);
        int mess = Integer.parseInt(mes);
        int anioo = Integer.parseInt(anio);

        Date fechaLarga = new Date((anioo - 1900), (mess - 1), diaa);
        SimpleDateFormat fechaFormato = new SimpleDateFormat("dd-MM-yyyy");
        String fechaFormateada = fechaFormato.format(fechaLarga);
        return fechaFormateada;
    }

    public static void tablaFechasVacia(JTable tblBuscarFechas) {

        String[] etiquetas = new String[]{"Fecha", "Código", "Nombre", "Tipo", "Lista de Ingredientes", "Proteínas", "Carbohidratos", "Grasas", "Categoría"};
        modeloMostrarFechas.setColumnIdentifiers(etiquetas);
        tblBuscarFechas.setModel(modeloMostrarFechas);
    }

    public static void mostrarFechasNaturales(int i) {
        modeloMostrarFechas.addRow(new Object[]{
            listaNaturales.get(i).getFecha(),
            listaNaturales.get(i).getCodAlimento(),
            listaNaturales.get(i).getNomAlimento(),
            listaNaturales.get(i).getTipoAlimento(),
            "Nulo",
            listaNaturales.get(i).getCantProteinas(),
            listaNaturales.get(i).getCantCarbohidratos(),
            listaNaturales.get(i).getCantGrasas(),
            "Alimento Natural"});
    }

    public static void mostrarFechasProcesados(int j) {
        modeloMostrarFechas.addRow(new Object[]{
            listaProcesados.get(j).getFecha(),
            listaProcesados.get(j).getCodAlimento(),
            listaProcesados.get(j).getNomAlimento(),
            "Nulo",
            listaIngredientes.get(j),
            listaProcesados.get(j).getCantProteinas(),
            listaProcesados.get(j).getCantCarbohidratos(),
            listaProcesados.get(j).getCantGrasas(),
            "Alimento Procesado"});
    }

    public static void tablaReporteGeneral(JTable tblReporteGeneral) {
        String[] etiquetas = new String[]{"Categoría", "Código", "Nombre", "Tipo", "Lista de Ingredientes", "Fecha", "Proteínas", "Carbohidratos", "Grasas"};
        modeloReporteGeneral.setColumnIdentifiers(etiquetas);
        tblReporteGeneral.setModel(modeloReporteGeneral);
        modeloReporteGeneral.setRowCount(0);
        limpiarDatosMemoria();
        extraerDatosTxt();
        
        for (int i = 0; i < listaNaturales.size(); i++) {
            modeloReporteGeneral.addRow(new Object[]{
                "Alimento Natural",
                listaNaturales.get(i).getCodAlimento(),
                listaNaturales.get(i).getNomAlimento(),
                listaNaturales.get(i).getTipoAlimento(),
                "Nulo",
                listaNaturales.get(i).getFecha(),
                listaNaturales.get(i).getCantProteinas(),
                listaNaturales.get(i).getCantCarbohidratos(),
                listaNaturales.get(i).getCantGrasas(),});
        }

        for (int i = 0; i < listaProcesados.size(); i++) {
            modeloReporteGeneral.addRow(new Object[]{
                "Alimento Procesado",
                listaProcesados.get(i).getCodAlimento(),
                listaProcesados.get(i).getNomAlimento(),
                "Nulo",
                listaIngredientes.get(i),
                listaProcesados.get(i).getFecha(),
                listaProcesados.get(i).getCantProteinas(),
                listaProcesados.get(i).getCantCarbohidratos(),
                listaProcesados.get(i).getCantGrasas(),});
        }
    }

    public static void eliminarDatos(String codigo) {

        if (buscarEnNatural(codigo) >= 0) {
            eliminarDatosTxT(true);
            listaNaturales.remove(indice);

        } else if (buscarEnProcesado(codigo) >= 0) {
            eliminarDatosTxT(false);
            listaProcesados.remove(indice);
        }
        guardarTxT();
    }

    public static void modificarDatos(String codigo) {

        if (buscarEnNatural(codigo) >= 0) {
            boolean modificarNaturales = true;
            AgregaDatosNaturales pantallaNaturales = new AgregaDatosNaturales(modificarNaturales);
            pantallaNaturales.setVisible(true);
            pantallaNaturales.setLocationRelativeTo(null);
            asignacionModificarNatu();
        } else if (buscarEnProcesado(codigo) >= 0) {
            boolean modificarProcesados = true;
            AgregaDatosProcesados pantallaProcesados = new AgregaDatosProcesados(modificarProcesados);
            pantallaProcesados.setVisible(true);
            pantallaProcesados.setLocationRelativeTo(null);
            asignacionModificarProce();
        }
    }

    public static int buscarEnNatural(String codigo) {

        indice = 0;

        if (!listaNaturales.isEmpty()) {

            for (int i = 0; i < listaNaturales.size(); i++) {
                String codNatural = listaNaturales.get(i).getCodAlimento();

                if (codigo.equals(codNatural)) {
                    indice = i;
                    break;
                } else {
                    indice = -1;
                }
            }
        } else {
            indice = -1;
        }
        return indice;
    }

    public static int buscarEnProcesado(String codigo) {

        indice = 0;

        if (!listaProcesados.isEmpty()) {
            for (int i = 0; i < listaProcesados.size(); i++) {
                String codProcesado = listaProcesados.get(i).getCodAlimento();

                if (codigo.equals(codProcesado)) {
                    indice = i;
                    break;
                } else {
                    indice = -1;
                }
            }
        } else {
            indice = -1;
        }
        return indice;
    }
}
