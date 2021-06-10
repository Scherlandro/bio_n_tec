package br.com.skep.beans;

import br.com.skep.dao.ItensVendaDAO;
import br.com.skep.dao.ProdutoDAO;
import br.com.skep.entity.ItensDoCarrinho;
import br.com.skep.entity.ItensVenda;
import br.com.skep.entity.Produto;
import br.com.skep.entity.Vendas;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean
@ViewScoped
@SessionScoped
public class ItensBean implements Serializable{

    private ItensVenda itensVdEntity = new ItensVenda();
    private final ItensVendaDAO itensVdDAO = new ItensVendaDAO();
    private List<ItensVenda> itensVendaList = new ArrayList<>();
    private Vendas vendaEntity = new Vendas();
    private List<Produto> produtosList;

    public void salvarItens() {
        itensVdDAO.salvar(itensVdEntity, vendaEntity);
        itensVdEntity = new ItensVenda();
    }

    public void editarItens(Vendas i) {
        vendaEntity = i;
    }

    public void setItensVendaList(List<ItensVenda> itensVendaList) {
        this.itensVendaList = itensVendaList;
    }

    public List<ItensVenda> listaItensDaVdPorID(String itvd) {
        if (!itvd.equals("")) {
            itensVendaList = itensVdDAO.listarItensDaVendaPorID(Integer.parseInt(itvd));
        }
        return itensVendaList;
    }

    public List<ItensVenda> listaItensDaVdPorCod() {
        if (vendaEntity.getCod_venda() == null) {
            return itensVendaList;
        } else {
            itensVendaList = itensVdDAO.listarItensDaVenda(vendaEntity.getCod_venda());
            return itensVendaList;
        }
    }

    public List<ItensVenda> listaItensVdPorCod(String itvd) {
        if (!itvd.equals("")) {
            itensVdEntity.setCodDaVenda(itvd);
            itensVendaList = itensVdDAO.listarItensDaVenda(itvd);
        }
        return itensVendaList;
    }

    public void adicionarIten(ActionEvent evento) {
        Produto prodSelecionado = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
        Vendas vendaSelecionada = (Vendas) evento.getComponent().getAttributes().get("vendaSelecionada");

        int positionFinder = -1;
        itensVendaList = itensVdDAO.listarItensDaVenda(vendaSelecionada.getCod_venda());
        ItensVenda itemTemp = new ItensVenda();
        for (int pos = 0; pos < itensVendaList.size() && positionFinder < 0; pos++) {
            itemTemp = itensVendaList.get(pos);
            if (itemTemp.getCodProdutoItens().equals(prodSelecionado.getCod_produto())) {
                positionFinder = pos;
                itemTemp.setQtdItem(itemTemp.getQtdItem() + 1);
                itemTemp.setValorParcial(prodSelecionado.getValor_venda() * itemTemp.getQtdItem());
                double novoVal = vendaSelecionada.getTotal_geral() + itemTemp.getValorItem();
                vendaSelecionada.setSubtotal(novoVal);
                double total = novoVal - vendaSelecionada.getDesconto();
                vendaSelecionada.setTotal_geral(total);
                itensVdDAO.alteraQTDeSubtVenda(itemTemp, vendaSelecionada);
                itemTemp = new ItensVenda();
                prodSelecionado = new Produto();
                vendaSelecionada = new Vendas();
            }
        }
        if (positionFinder < 0) {
            itemTemp.setId_itensVd(null);
            itemTemp.setCodDaVenda(vendaSelecionada.getCod_venda());
            itemTemp.setCodProdutoItens(prodSelecionado.getCod_produto());
            itemTemp.setDescricaoDoIntem(prodSelecionado.getNome_produto());
            itemTemp.setValorItem(prodSelecionado.getValor_venda());
            itemTemp.setQtdItem(1);
            itemTemp.setValorParcial(prodSelecionado.getValor_venda() * itemTemp.getQtdItem());
            double novoVal = vendaSelecionada.getTotal_geral() + itemTemp.getValorParcial();
            vendaSelecionada.setSubtotal(novoVal);
            double total = novoVal - vendaSelecionada.getDesconto();
            vendaSelecionada.setTotal_geral(total);
            itensVdDAO.salvar(itemTemp, vendaSelecionada);
        }
        VendaBean vdB = new VendaBean();
        vdB.listarVendas();
    }

    public double SomarItens() {
        if (itensVendaList != null) {
            Double tot = new Double(0);
            for (ItensVenda somar : itensVendaList) {
                tot += somar.getValorParcial();
            }
            BigDecimal rt = new BigDecimal(tot);
            rt = rt.setScale(2, RoundingMode.UP);
            return Double.parseDouble(rt.toString());
        }
        return 0.0;
    }

    /*
         public void incrementaEstoque() {
             this.qtdItem++;
            }
         public void diminuiEstoque() {
                this.qtdItem--;
            }
     */
    public int quantidadeTotalDeItens() {
        if (itensVendaList != null) {
            int tot = 0;
            for (ItensVenda somar : itensVendaList) {
                tot += somar.getQtdItem();
            }
            return tot;
        }
        return 0;
    }

    
    public void removerItem(ItensVenda itvd) {
 VendaBean vdB = new VendaBean();
            if (itvd != null  ) {
                itensVdEntity = itvd;

                if (itensVdEntity.getQtdItem() > 1) {
                    itensVdEntity.setQtdItem(itensVdEntity.getQtdItem() - 1);
                    itensVdEntity.setValorParcial(itensVdEntity.getValorParcial() - itensVdEntity.getValorItem());
                    vendaEntity.setSubtotal(vendaEntity.getSubtotal()- itensVdEntity.getValorItem() );
              //      double novoValTotalVd = vendaEntity.getTotal_geral() - itensVdEntity.getValorItem();
                    vendaEntity.setTotal_geral( vendaEntity.getSubtotal() - vendaEntity.getDesconto());
                   itensVdDAO.alteraQTDeSubtVenda(itensVdEntity, vendaEntity);
                        vdB.listarVendas();
                } else {
                    double novoValTotalVd = vendaEntity.getTotal_geral() - itensVdEntity.getValorItem();
                    vendaEntity.setSubtotal(novoValTotalVd);
                    vendaEntity.setTotal_geral(novoValTotalVd - vendaEntity.getDesconto());
                     itensVdDAO.excluirItenDaVenda(itensVdEntity.getId_itensVd(), vendaEntity);
                     itensVdDAO.listarItensDaVenda(vendaEntity.getCod_venda());
                     vdB.listarVendas();
                }
            } else {
                vdB.listarVendas();
            }
    }

    public void addItemNoCarrinho() {
        ItensDoCarrinho itemCar = new ItensDoCarrinho();
        List<ItensDoCarrinho> listCarrinho = new ArrayList<ItensDoCarrinho>();
        listCarrinho.add(itemCar);
        itemCar = new ItensDoCarrinho();
    }

    public Vendas getVendaEntity() {
        return vendaEntity;
    }

    public void setVendaEntity(Vendas vendaEntity) {
        this.vendaEntity = vendaEntity;
    }

    public ItensVenda getItensVdEntity() {
        return itensVdEntity;
    }

    public void setItensVdEntity(ItensVenda itensVdEntity) {
        this.itensVdEntity = itensVdEntity;
    }

    public List<Produto> getProdutosList() {
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtosList = produtoDAO.listarTodosProduto();
            //  listaItensVd = new ArrayList<>();
        } catch (RuntimeException erro) {
            erro.printStackTrace();
        }
        return produtosList;
    }

    public void setProdutosList(List<Produto> produtosList) {
        this.produtosList = produtosList;
    }

}
/*
    public String limpaPagina(ActionEvent evento) {
        evento.getComponent().getAttributes().get("dialogoSelecionado");
        // horizonteTemporal = new HorizonteTemporal();
        // horizontesTemporais = null;
        return "dialogoSelecionado" + Parametros.REDIRECT_TRUE.getLimpaTela();
    }
    private enum Parametros {
        REDIRECT_TRUE("?faces-redirect=true");
        private String tela;
        
        private Parametros(String tela) {
            this.tela = tela;
        }
        
        public String getLimpaTela() {
            return tela;
        }
        
    }
 */
