package entity;

public class Contratacion {

    private int id;
    private String fecha_aplicacion;
    private String estado;
    private float salario;

    private int coder_id;
    private Coder objCoder;
    private int vacante_id;
    private Vacante objVacante;

    public Contratacion() {
    }

    public Contratacion(String fecha_aplicacion, String estado, float salario, int coder_id, Coder objCoder, int vacante_id, Vacante objVacante) {
        this.fecha_aplicacion = fecha_aplicacion;
        this.estado = estado;
        this.salario = salario;
        this.coder_id = coder_id;
        this.objCoder = objCoder;
        this.vacante_id = vacante_id;
        this.objVacante = objVacante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha_aplicacion() {
        return fecha_aplicacion;
    }

    public void setFecha_aplicacion(String fecha_aplicacion) {
        this.fecha_aplicacion = fecha_aplicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public int getCoder_id() {
        return coder_id;
    }

    public void setCoder_id(int coder_id) {
        this.coder_id = coder_id;
    }

    public Coder getObjCoder() {
        return objCoder;
    }

    public void setObjCoder(Coder objCoder) {
        this.objCoder = objCoder;
    }

    public int getVacante_id() {
        return vacante_id;
    }

    public void setVacante_id(int vacante_id) {
        this.vacante_id = vacante_id;
    }

    public Vacante getObjVacante() {
        return objVacante;
    }

    public void setObjVacante(Vacante objVacante) {
        this.objVacante = objVacante;
    }

    @Override
    public String toString() {
        return "Contratacion: \n" +
                "Vacante: " + objVacante.getTitulo() + ", descripcion: "+ objVacante.getDescripcion() +
                "\nEmpresa: " + objVacante.getObjEmpresa().getNombre() + ", ubicacion: " + objVacante.getObjEmpresa().getUbicacion() +
                "fecha de aplicacion: " + fecha_aplicacion + ' ' +  ", estado: " + estado + ' ' + ", salario: " + salario +"\n";
    }
}
