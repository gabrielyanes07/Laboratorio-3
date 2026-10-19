package model;
public abstract class OrdenDeServicio implements Comparable<OrdenDeServicio> {
    private String codigo;
    private String nombreCliente;
    private int horaEstimada;
    private boolean esMetropolitano;
    private int cantidadVisitas;
    private String estado;
    private double costoServicio;
    private double total;

    public OrdenDeServicio( String nombreCliente, int horaEstimada,
        boolean esMetropolitano, int cantidadVisitas, String estado, double total){
            this.nombreCliente = nombreCliente;
            this.horaEstimada = horaEstimada;
            this.esMetropolitano = esMetropolitano;
            this.cantidadVisitas = cantidadVisitas;
            this.estado = estado;
            this.total = total;
    }
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    
    public String getCodigo(){
        return codigo;
    }

    public void setNombreCliente(String nombreCliente){
        this.nombreCliente = nombreCliente;
    }

    public String getNombreCliente(){
        return nombreCliente;
    }

    public void setHoraEstimada(int horaEstimada){
        this.horaEstimada = horaEstimada;
    }

    public int getHoraEstimada(){
        return horaEstimada;
    }

    public void setEsMetropolitano(boolean esMetropolitano){
        this.esMetropolitano = esMetropolitano;
    }

    public boolean isEsMetropolitano(){
        return esMetropolitano;
    }

    public void setCantidadVisitas(int cantidadVisitas){
        this.cantidadVisitas = cantidadVisitas;
    }

    public int getCantidadVisitas(){
        return cantidadVisitas;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    public String getEstado(){
        return estado;
    }

    public void setCostoServicio(double costoServicio){
        this.costoServicio = costoServicio;
    }

    public double getCostoServicio(){
        return costoServicio;
    }

    public void setTotal(double total){
        this.total = total;
    }

    public double getTotal(){
        return total;
    }
    @Override
    public int compareTo(OrdenDeServicio otro) {
        return this.codigo.compareTo(otro.getCodigo());
    }
    @Override
    public String toString(){
        return "Codigo: " + codigo + ", Nombre Cliente: " + nombreCliente + ", Hora Estimada: " + horaEstimada +
        ", Es Metropolitano: " + esMetropolitano + ", Cantidad Visitas: " + cantidadVisitas + ", Estado: " + estado +
        ", Costo Servicio: " + costoServicio + ", Total: " + total;
    } 
}
