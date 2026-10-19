package model;
public class Instalacion extends OrdenDeServicio{
    private double costoServicio;
    private int cantInstalaciones = 0;
    private int diasEstimados;
    private String codigo;

    public Instalacion(int cantPuntos, String codigo, String nombreCliente, int horaEstimada, boolean esMetropolitano, int cantidadVisitas, String estado, double total) {
        super(nombreCliente, horaEstimada, esMetropolitano, cantidadVisitas, estado, total);
        cantInstalaciones++;
        this.codigo = "I -" + cantInstalaciones;
        if (!esMetropolitano) {
            this.diasEstimados = 5;
            this.costoServicio = 175 + (horaEstimada + Math.round(cantPuntos*.75))*90;
        }
        else{
            this.diasEstimados = 3;
            this.costoServicio = 100 + (horaEstimada + Math.round(cantPuntos*.75))*90;
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
    @Override
    public String toString() {
        return super.toString() + ", Tipo: Instalación, Costo Servicio: " + costoServicio;
    }
}   
