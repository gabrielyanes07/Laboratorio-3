package model;

import java.util.ArrayList;

public class Cuadrilla {
    private double maxHora;
    private ArrayList<Jornada> jornadas;
    private boolean certificada;
    private static int numCuadrillas = 0;
    private String codigo;

    public Cuadrilla(double maxHora, boolean certificada) {
        this.maxHora = maxHora;
        this.jornadas = new ArrayList<>();
        this.certificada = certificada;
        if (maxHora > 0) {
            this.codigo = "CU -" + (++numCuadrillas);
        } else {
            this.codigo = "CU-0";
        }
    }

    public boolean tieneJornadaSinCerrar() {
        for (Jornada j : jornadas) {
            if (!j.getEstado().equalsIgnoreCase("cerrada")) {
                return true;
            }
        }
        return false;
    }

    public Jornada getJornadaActiva() {
        for (Jornada j : jornadas) {
            if (!j.getEstado().equalsIgnoreCase("cerrada")) {
                return j;
            }
        }
        return null;
    }

    public boolean agregarJornada(Jornada jornada) {
        if (tieneJornadaSinCerrar()) {
            return false;
        }
        jornada.setCuadrilla(this);
        this.jornadas.add(jornada);
        return true;
    }

    public String obtenerJornadasSinCerrar() {
        StringBuilder sb = new StringBuilder();
        for (Jornada jornada : jornadas) {
            if (!jornada.getEstado().equalsIgnoreCase("cerrada")) {
                sb.append("  - Cuadrilla: ").append(codigo)
                  .append(" (Capacidad Max: ").append(String.format("%.2f", maxHora)).append(" hrs)")
                  .append(" | Jornada: ").append(jornada.getCodigo())
                  .append(" | Estado: ").append(jornada.getEstado())
                  .append(" | Horas Asignadas: ").append(String.format("%.2f", jornada.contarHoras()))
                  .append(" | Capacidad Disponible: ").append(String.format("%.2f", jornada.getCapacidadDisponible()))
                  .append("\n");
            }
        }
        return sb.length() > 0 ? sb.toString() : null;
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
