package model;
import java.util.ArrayList; 

public class Cuadrilla {
    private double maxHora;
    private ArrayList<Jornada> jornadas;
    private boolean certificada;
    private int numCuadrillas = 0;
    private String codigo;

    public Cuadrilla(double maxHora, boolean certificada) {
        this.maxHora = maxHora;
        this.jornadas = new ArrayList<>();
        this.certificada = certificada;
        this.codigo = "CU -"+(++numCuadrillas);
    }

    public void agregarJornada(Jornada jornada) {
        jornada.contarHoras();
        if (jornada.contarHoras() <= maxHora) {
            this.jornadas.add(jornada);
        } 
    }

    public String obtenerJornadasSinCerrar() {
        for (Jornada jornada : jornadas) {
            if (jornada.getOrdenes().isEmpty()) {
                if(!jornada.getEstado().equals("cerrada")){
                    return jornada.getEstado();
                }
            }
        }
        return null;
    }

    public double calcularIngresoTotal() {
        double totalIngreso = 0;
        for (Jornada jornada : jornadas) {
            totalIngreso += jornada.contarIngresoTotal();
        }
        return totalIngreso;
    }

    public ArrayList<Jornada> getJornadas() {
        return jornadas;
    }

    public double getMaxHora() {
        return maxHora;
    }

    public boolean isCertificada() {
        return certificada;
    }

    public String getCodigo() {
        return codigo;
    }
}
