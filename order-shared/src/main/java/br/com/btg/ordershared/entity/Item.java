package br.com.btg.ordershared.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {

    private String produto;
    private Integer quantidade;
    private Double preco;

}
