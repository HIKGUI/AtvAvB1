package br.unipar.programacaoweb.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private Double valorTotal;
    private String observacoes;
    private String status;

//    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ItensPedido> itens;


    public Pedido(Usuario usuario, Double valorTotal, String observacoes, String status) {
        this.usuario = usuario;
        this.valorTotal = valorTotal;
        this.observacoes = observacoes;
        this.status = status;

    }

    public Pedido() { }
}
