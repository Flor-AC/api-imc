package com.uv.apirest.person;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "tb_persona")
public class Persona {
    
    @Id 
    @GeneratedValue(generator = "persona_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "persona_generator", sequenceName = "s_persona_sequence", initialValue = 1, allocationSize = 1)
    @ApiModelProperty(value = "The id of the persona")
    private Long idPersona;

    @Size(max = 20)
    @ApiModelProperty(value = "The genero of the persona")
    private String generoPersona;

    @Size(max = 20)
    @ApiModelProperty(value = "The estura of the persona")
    private String estaturaPersona;
    
    @Size(max = 20)
    @ApiModelProperty(value = "The peso of the persona")
    private String pesoPersona;
    
    @Size(max = 20)
    @ApiModelProperty(value = "The edad of the persona")
    private String edadPersona;
    
    public Persona() {
    }

    public Persona(Long idPersona, String generoPersona, String estaturaPersona, String pesoPersona, String edadPersona) {
        this.idPersona = idPersona;
        this.generoPersona = generoPersona;
        this.estaturaPersona = estaturaPersona;
        this.pesoPersona = pesoPersona;
        this.edadPersona = edadPersona;
    }
    
    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public String getGeneroPersona() {
        return generoPersona;
    }

    public void setGeneroPersona(String generoPersona) {
        this.generoPersona = generoPersona;
    }

    public String getEstaturaPersona() {
        return estaturaPersona;
    }

    public void setEstaturaPersona(String estaturaPersona) {
        this.estaturaPersona = estaturaPersona;
    }

    public String getPesoPersona() {
        return pesoPersona;
    }

    public void setPesoPersona(String pesoPersona) {
        this.pesoPersona = pesoPersona;
    }

    public String getEdadPersona() {
        return edadPersona;
    }

    public void setEdadPersona(String edadPersona) {
        this.edadPersona = edadPersona;
    }
}
