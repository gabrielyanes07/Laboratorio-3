package model;
public class InstalacionElectrica extends OrdenDeServicio {
    private double costoServicio;
    private String codigo;
    private int cantInstalaciones = 0;
    private int diasEstimados;

    public InstalacionElectrica(int cantPuntos, double costMateriales, String nombreCliente, int horaEstimada, boolean esMetropolitano, int cantidadVisitas, String estado, double total) {
        super(nombreCliente, horaEstimada, esMetropolitano, cantidadVisitas, estado, total);
        cantInstalaciones++;
        this.codigo = "IE -" + cantInstalaciones;
        if (!esMetropolitano) {
            this.diasEstimados = 7;
            this.costoServicio = 220 + (horaEstimada + Math.round(cantPuntos*.75))*90 + costMateriales*0.05;
        }
        else{
            this.diasEstimados = 5;
            this.costoServicio = 295 + (horaEstimada + Math.round(cantPuntos*.75))*90 + costMateriales*0.05;
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
        return super.toString() + ", Tipo: Instalación Eléctrica, Costo Servicio: " + costoServicio;
    }
}
