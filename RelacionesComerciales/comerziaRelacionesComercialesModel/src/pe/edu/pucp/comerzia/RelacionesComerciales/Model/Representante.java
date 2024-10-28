package pe.edu.pucp.comerzia.RelacionesComerciales.Model;

public class Representante{

    private Integer idRepresentante;
    private static Integer id_correlativo = 1;
    private Integer idPersona;
    private Integer idEmpresa;

    public Representante(Integer idRepresentante, Integer idPersona, Integer idEmpresa) {
        this.idRepresentante = idRepresentante;
        this.idPersona = idPersona;
        this.idEmpresa = idEmpresa;
    }

    public Representante(Integer idPersona, Integer idEmpresa) {
        this.idRepresentante = id_correlativo;
        this.idPersona = idPersona;
        this.idEmpresa = idEmpresa;
        id_correlativo++;
    }

    // null
    public Representante() {
        this.idRepresentante = null;
        this.idPersona = null;
        this.idEmpresa = null;
    }

    public Integer getIdRepresentante() {
        return idRepresentante;
    }

    public void setIdRepresentante(Integer idRepresentante) {
        this.idRepresentante = idRepresentante;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

}
