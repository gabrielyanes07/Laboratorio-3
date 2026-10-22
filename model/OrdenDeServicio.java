package model;

public abstract class OrdenDeServicio implements Comparable<OrdenDeServicio> {
    private String codigo;
    private String nombreCliente;
    private String direccion;
    private double horasEstimadas;
    private boolean esMetropolitano;
    private int cantidadVisitas;
    private String estado;
    private double costoServicio;
    private double total;

    public OrdenDeServicio(String nombreCliente, String direccion, double horasEstimadas, boolean esMetropolitano, String estado, double total) {
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.horasEstimadas = horasEstimadas;
        this.esMetropolitano = esMetropolitano;
        this.cantidadVisitas = 0;
        this.estado = estado;
        this.costoServicio = total;
        this.total = total;
    }

    public OrdenDeServicio(String nombreCliente, int horaEstimada, double minutosEstimados, boolean esMetropolitano, int cantidadVisitas, String estado, double total) {
        this.nombreCliente = nombreCliente;
        this.direccion = "Ciudad";
        this.horasEstimadas = horaEstimada + (minutosEstimados / 60.0);
        this.esMetropolitano = esMetropolitano;
        this.cantidadVisitas = cantidadVisitas;
        this.estado = estado;
        this.costoServicio = total;
        this.total = total;
    }

    public abstract double calcularCosto();
    public abstract int getTiempoEstimadoDias();
    public abstract int getMaxVisitasPermitidas();
    public abstract boolean requiereCertificacionRefrigeracion();
    public abstract String getCategoria();

    public void registrarVisita(boolean completada) {
        this.cantidadVisitas++;
        if (completada) {
            this.estado = "completada";
        } else if (this.cantidadVisitas >= getMaxVisitasPermitidas()) {
            this.estado = "cancelada";
        } else {
            this.estado = "pendiente";
        }
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getHorasEstimadas() {
        return horasEstimadas;
    }

    public void setHorasEstimadas(double horasEstimadas) {
        this.horasEstimadas = horasEstimadas;
    }

    public void setHoraEstimada(int horaEstimada) {
        this.horasEstimadas = horaEstimada;
    }

    public double getHoraEstimada() {
        return horasEstimadas;
    }

    public double getMinutosEstimados() {
        return (horasEstimadas - (int) horasEstimadas) * 60.0;
    }

    public void setMinutosEstimados(double minutosEstimados) {
        this.horasEstimadas = (int) horasEstimadas + (minutosEstimados / 60.0);
    }

    public void setEsMetropolitano(boolean esMetropolitano) {
        this.esMetropolitano = esMetropolitano;
    }

    public boolean isEsMetropolitano() {
        return esMetropolitano;
    }

    public void setCantidadVisitas(int cantidadVisitas) {
        this.cantidadVisitas = cantidadVisitas;
    }

    public int getCantidadVisitas() {
        return cantidadVisitas;
    }

    public int getVisitasRealizadas() {
        return cantidadVisitas;
    }

    public void setVisitasRealizadas(int visitas) {
        this.cantidadVisitas = visitas;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setCostoServicio(double costoServicio) {
        this.costoServicio = costoServicio;
    }

    public double getCostoServicio() {
        return costoServicio;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }
    
    @Override
    public int compareTo(OrdenDeServicio otro) {
        if (this.codigo == null && (otro == null || otro.getCodigo() == null)) return 0;
        if (this.codigo == null) return -1;
        if (otro == null || otro.getCodigo() == null) return 1;
        return this.codigo.compareTo(otro.getCodigo());
    }

    @Override
    public String toString() {
        return String.format("Codigo: %s | Categoria: %s | Cliente: %s | Direccion: %s | Horas: %.2f | Zona: %s | Visitas: %d/%d | Estado: %s | Costo: Q%.2f",
                codigo, getCategoria(), nombreCliente, direccion, horasEstimadas,
                (esMetropolitano ? "Metropolitana" : "Periférica"), cantidadVisitas, getMaxVisitasPermitidas(), estado, costoServicio);
    }
}
