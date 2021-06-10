function avistoSimples()
{
    var div = document.getElementById('meulog');
    div.innerHTML = div.innerHTML + 'Seu valor foi verificado ';
}

function somarValores() {
    var vc = document.getElementById("formProduto:idValc").value;
    var pct = document.getElementById("formProduto:idPerc").value;
    document.getElementById("formProduto:resulPreVend").value = parseFloat(vc) + (parseFloat(vc) * parseFloat(pct) / 100);
}

function contarLongs(){    
       var vcx = 0;
    document.getElementById("formMarcas:inputxContar").value = ++ vcx;
}
function contabiliza(){
    
}


function transfValDoInputParaOutroInput() {
    var valorName = document.getElementById("formIntensDaVenda:idDoItenVd").value;
    document.getElementById("formIntensDaVenda:removerItem").value = valorName;
}

function selecionaData() {
    document.getElementById("formProduto:dataEscolhida").value = new Date();

}

function mudarCorPorHorario() {
    const now = new Date;
    const tempo = now.getHours();
    if (tempo < 16) {
        const conteudo = document.querySelector(".container");
        conteudo.style.background = "#fed02e";
    } else {
        const conteudo = document.querySelector(".container");
        conteudo.style.background = "#1000ff";
    }
}

function  confirma() {
    var resposta = window.confirm("Você está certo disso? ");
    if (['true', 'OK', '1'].includes(resposta)) {
        'true';
    } else if (['false', 'Cancelar', '0'].includes(resposta)) {
        'false';
    }
}

 function alternativa(){
    var mensagem = window.confirm("Clique em um dos botões!");
   
        if (mensagem === true)
    {
  document.getElementById("formIntensDaVenda:idRecebMens").value = 'sim';
         mensagem = 'sim';
    } else{
  document.getElementById("formIntensDaVenda:idRecebMens").value = 'não';
        mensagem = 'não';
    }
}


function responder() {
    
      var answer = window.confirm("Excluir o item? ");
    if (['true', 'yes', '1'].includes(answer)) {
        return  true;
    } else if (['false', 'no', '0'].includes(answer)) {
        return  false;
    }
}
function call() {

  document.form[0].action="loginAction.do?str=" + "HelloWorld";
  document.form[0].submit();

}

