package model;
import java.util.ArrayList;
public class Jornada {
    private ArrayList<OrdenDeServicio> ordenes;
    private String codigo;
    private int numJornadas = 0;

    public Jornada() {
        this.codigo = "JOR -" + (++numJornadas);
        this.ordenes = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public double contarHoras(){
        double totalHoras = 0;
        for (int i = 0; i < ordenes.size(); i++) {
            OrdenDeServicio orden = ordenes.get(i);
            totalHoras += orden.getHoraEstimada();
        }
        return totalHoras;
    }

    public double contarCosto(){
        double totalCosto = 0;
        for (int i = 0; i < ordenes.size(); i++) {
            OrdenDeServicio orden = ordenes.get(i);
            totalCosto += orden.getCostoServicio();
        }
        return totalCosto;
    }

    public double contarIngresoTotal() {
        double totalIngreso = 0;
        for (int i = 0; i < ordenes.size(); i++) {
            OrdenDeServicio orden = ordenes.get(i);
            totalIngreso += orden.getCostoServicio();
        }
        return totalIngreso;
    }

    public void agregarOrden(OrdenDeServicio orden) {
        this.ordenes.add(orden);
    }

    public ArrayList<OrdenDeServicio> getOrdenes() {
        return ordenes;
    }

    public String getEstado() {
        for(int i = 0; i < ordenes.size(); i++) {
            if (ordenes.get(i).getEstado().equals("cerrada")) {
                return "cerrada";
            }
        }
        return "abierta";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jornada:\n");
        for (OrdenDeServicio orden : ordenes) {
            sb.append(orden.toString()).append("\n");
        }
        return sb.toString();
    }
}
