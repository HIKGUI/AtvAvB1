package br.unipar.programacaoweb.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class ItensPedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tamanho;
    private Integer quantidade;
    private Double valorUnitario;
    private Double valorTotal;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    @ManyToOne
    @JoinColumn(name = "borda_id")
    private Borda borda;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public ItensPedido(String tamanho, Integer quantidade, Double valorUnitario, Double valorTotal, Pizza pizza, Borda borda, Pedido pedido) {
        this.tamanho = tamanho;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.pizza = pizza;
        this.borda = borda;
        this.pedido = pedido;



    }

    public ItensPedido() { }
}
