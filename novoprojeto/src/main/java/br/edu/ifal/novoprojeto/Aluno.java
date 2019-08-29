package br.edu.ifal.novoprojeto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Aluno{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String email;
    private double cpf;
    private String modulo;
    private String[] areas;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getCpf() {
        return cpf;
    }

    public void setCpf(double cpf) {
        this.cpf = cpf;
    }


    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String[] getAreas() {
        return areas;
    }

    public String getAreasPorString(){
        String ret = "";
        for (String var : areas) {
            ret = ret + var + ",";
        }
        return ret;
    }

    public void setAreas(String[] areas) {
        this.areas = areas;
    }

	public String getTodosCampos() {
		return nome + ", " + email + ", " + modulo + ", " + getAreasPorString();
	}

    @Override
    public String toString() {
        return getTodosCampos();
    }
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}