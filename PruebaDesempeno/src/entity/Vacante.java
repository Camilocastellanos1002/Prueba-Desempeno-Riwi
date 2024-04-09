package entity;

public class Vacante {
    private int id;
    private String titulo;
    private String descripcion;
    private String duracion;
    private String estado;
    private String tecnologia;

    private int empresa_id;
    private Empresa objEmpresa;

    public Vacante() {
    }

    public Vacante(String titulo, String descripcion, String duracion, String estado, String tecnologia, int empresa_id, Empresa objEmpresa) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.estado = estado;
        this.tecnologia = tecnologia;
        this.empresa_id = empresa_id;
        this.objEmpresa = objEmpresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public int getEmpresa_id() {
        return empresa_id;
    }

    public void setEmpresa_id(int empresa_id) {
        this.empresa_id = empresa_id;
    }

    public Empresa getObjEmpresa() {
        return objEmpresa;
    }

    public void setObjEmpresa(Empresa objEmpresa) {
        this.objEmpresa = objEmpresa;
    }

    @Override
    public String toString() {
        return "Vacante: \n" +
                "titulo: " + titulo + '\n' +
                "descripcion: " + descripcion + ' ' +", duracion: " + duracion + ' ' +", estado='" + estado + '\n' +
                "tecnologia: " + tecnologia + ' ' +",empresa: " + objEmpresa.getNombre();
    }
}
