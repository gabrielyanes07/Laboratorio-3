package model;

public class Diagnostico extends OrdenDeServicio {
    private double costoServicio;
    private String codigo;
    private int diasEstimados;
    int cantDiagnosticos = 0;

    public Diagnostico(int cantDiagnosticos, String nombreCliente, int horaEstimada, boolean esMetropolitano, int cantidadVisitas, String estado, double total) {
        super( nombreCliente, horaEstimada, esMetropolitano, cantidadVisitas, estado, total);
        cantDiagnosticos++;
        this.codigo = "D -" + cantDiagnosticos;
        if (!esMetropolitano) {
            this.diasEstimados = 2;
            this.costoServicio = 225;
        }
        else{
            this.costoServicio = 150;
            this.diasEstimados = 1;
        }
    }

    public double getCostoServicio() {
        return costoServicio;
    }
    public String getCodigo() {
        return codigo;
    }
    @Override
    public String toString() {
        return super.toString() + ", Tipo: Diagnóstico, Costo Servicio: " + costoServicio;
    }
}
