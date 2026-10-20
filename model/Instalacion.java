package model;
public class Instalacion extends OrdenDeServicio{
    private double costoServicio;
    private int cantInstalaciones = 0;
    private int diasEstimados;
    private String codigo;

    public Instalacion(int cantPuntos, String nombreCliente, int horaEstimada, double minutosEstimados, boolean esMetropolitano, int cantidadVisitas, String estado, double total) {
        super(nombreCliente, horaEstimada, minutosEstimados, esMetropolitano, cantidadVisitas, estado, total);
        cantInstalaciones++;
        this.codigo = "I -" + cantInstalaciones;
        if(cantidadVisitas > 3) {
            if (!esMetropolitano) {
                this.diasEstimados = 5;
                this.costoServicio = 215 + (horaEstimada + minutosEstimados / 60.0 + Math.round(cantPuntos * 0.75)) * 90;
            }
            else{
                this.diasEstimados = 3;
                this.costoServicio = 140 + (horaEstimada + minutosEstimados / 60.0 + Math.round(cantPuntos * 0.75)) * 90;
            }
        }
        else{
            if (!esMetropolitano) {
                this.diasEstimados = 5;
                this.costoServicio = 175 + (horaEstimada + minutosEstimados / 60.0 + Math.round(cantPuntos * 0.75)) * 90;
            }
            else{
                this.diasEstimados = 3;
                this.costoServicio = 100 + (horaEstimada + minutosEstimados / 60.0 + Math.round(cantPuntos * 0.75)) * 90;
            }
        }
    }
    
    public double getCostoServicio() {
        return costoServicio;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public int getDiasEstimados() {
        return diasEstimados;
    }

    public static double cotizar(int cantidadVisitas, int cantPuntos,int horaEstimada, double minutosEstimados, boolean esMetropolitano) {
        if(cantidadVisitas > 3) {
            if (!esMetropolitano) {
                return 215 + (horaEstimada + minutosEstimados / 60.0 + Math.round(cantPuntos * 0.75)) * 90;
            }
            else{
                return 140 + (horaEstimada + minutosEstimados / 60.0 + Math.round(cantPuntos * 0.75)) * 90;
            }
        }
        else{
            if (!esMetropolitano) {
                return 175 + (horaEstimada + minutosEstimados / 60.0 + Math.round(cantPuntos * 0.75)) * 90;
            }
            else{
                return 100 + (horaEstimada + minutosEstimados / 60.0 + Math.round(cantPuntos * 0.75)) * 90;
            }
        }
    }
    @Override
    public String toString() {
        return super.toString() + ", Tipo: Instalación, Costo Servicio: " + costoServicio;
    }
}   