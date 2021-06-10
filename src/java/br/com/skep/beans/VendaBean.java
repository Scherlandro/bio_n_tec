package br.com.skep.beans;

import br.com.skep.relatorios.GerarRelatorios;
import br.com.skep.dao.VendaDAO;
import br.com.skep.entity.Cliente;
import br.com.skep.entity.Funcionario;
import br.com.skep.entity.ItensDoCarrinho;
import br.com.skep.entity.Vendas;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.event.SelectEvent;

@ManagedBean
@ViewScoped
@SessionScoped
public class VendaBean implements Serializable{

    private Vendas vendEntity = new Vendas();
    private Cliente clienteEntity = new Cliente();
    private ClienteBean clienteB = new ClienteBean();
    private Funcionario funcionarioEntity = new Funcionario();
    private FuncionarioBean funcB = new FuncionarioBean();
    private VendaDAO vendDAO = new VendaDAO();
    private List<Vendas> vendList;
    private ItensDoCarrinho itemCar = new ItensDoCarrinho();
    private List<ItensDoCarrinho> listCarrinho = new ArrayList<>();

    public void addItemAoCarrinho() {
        listCarrinho.add(itemCar);
        this.itemCar = new ItensDoCarrinho();
    }

    public void limparVd() {
        vendEntity = new Vendas();
        vendList.clear();
    }

    public void iniciarVenda(ActionEvent evento) {
        clienteEntity = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
        funcionarioEntity = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionada");
      
        String codGerado = String.valueOf(vendDAO.buscaUltimoIDdaVenda() + 1);
        Date dataSist = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyy");
        vendEntity.setCod_venda(codGerado);
        vendEntity.setId_cliente(clienteEntity.getId_cliente());
        vendEntity.setNome_cliente(clienteEntity.getNome_cliente());
        vendEntity.setId_funcionario(funcionarioEntity.getId_funcionario());
        vendEntity.setNome_funcionario(funcionarioEntity.getNome_funcionario());
        vendEntity.setDt_venda(formato.format(dataSist));
    }


@PostConstruct
        public void listar() {

    }
 

    public void adicionar() {
        vendDAO.salvarVenda(vendEntity);
        vendEntity = new Vendas();
    }

    public void listarVendas() {
        vendList = vendDAO.listarTodasVendas();
    }

    public void editar(Vendas v) {
        vendEntity = v;
    }

    public void remover(Vendas v) {
        vendEntity = v;
        vendDAO.excluirVenda(vendEntity.getId_venda());
        vendEntity = new Vendas();
        vendList.clear();
    }

    public void gerarRelatorioAction(String codVendas) {
        GerarRelatorios relatorio = new GerarRelatorios();
        relatorio.printFatDeVenda(Integer.parseInt(codVendas));
    }

    public void gerarRelatorio() {
        GerarRelatorios relatorio = new GerarRelatorios();
        relatorio.getRelatorio();
    }

    public List<Vendas> getVendList() {
        vendList = vendDAO.listarTodasVendas();
        return vendList;
    }

    public void setVendList(List<Vendas> vendList) {
        this.vendList = vendList;
    }

  
    public List<Vendas> listaVendasPorCod(Vendas v) {
        vendEntity = v;     
        return vendList = vendDAO.listarVendaPorCodigo(vendEntity.getCod_venda());
    }

    public Cliente getClienteEntity() {
        return clienteEntity;
    }

    public void setClienteEntity(Cliente clienteEntity) {
        this.clienteEntity = clienteEntity;
    }

    public Funcionario getFuncionarioEntity() {
        return funcionarioEntity;
    }

    public void setFuncionarioEntity(Funcionario FuncionarioEntity) {
        this.funcionarioEntity = FuncionarioEntity;
    }

    public Vendas getVendEntity() {
        return vendEntity;
    }

    public void setVendEntity(Vendas vendEntity) {
        this.vendEntity = vendEntity;
    }
  
 
    public ClienteBean getClienteB() {
        return clienteB;
    }

    public void setClienteB(ClienteBean clienteB) {
        this.clienteB = clienteB;
    }

    public FuncionarioBean getFuncB() {
        return funcB;
    }

    public void setFuncB(FuncionarioBean funcB) {
        this.funcB = funcB;
    }

    public void onRowSelect(SelectEvent event) {
        Vendas us = (Vendas) event.getObject();
        setVendas(us);
    }

    public void setVendas(Vendas vd) {
        this.vendEntity = vd;
    }

}
