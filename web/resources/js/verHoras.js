var script = document.createElement("script");
script.type = "text/javascript";
script.src = "http://www.timeapi.org/utc/now.json?callback=setDtServer";

var dtServer = null;
var dtClient = null;

//se preferir pode informar um local especifico: ["pt-BR"]
//ou uma lista de locais esperados: ["pt-BR", "pt-PT", "en-US"]
//caso informe uma lista vazia, ele vai tentar inferir o local pelo sistema.
var locales = [];
var formater = new Intl.DateTimeFormat(locales , {
  //é possivel informar o time-zone usando uma string no formato IANA
  //você pode encontrar a lista completa em:
  // http://www.iana.org/time-zones
  // https://en.wikipedia.org/wiki/List_of_tz_database_time_zones
  //No exemplo abaixo estou usando o Timezone de Fortaleza, que difere um
  //pouco do de Brasília, por não fazer parte do Horário de Verão.
  //novamente o valor default vai refletir o que for informado pelo sistema.
  timeZone: "America/Fortaleza",

  year: "numeric",
  month: "numeric",
  day: "numeric",
  hour: "numeric",
  minute: "numeric",
  second: "numeric",
  timeZoneName: "long"
});

function getData() {
  var diff = dtServer.getTime() - dtClient.getTime();
  var data = new Date();
  data.setTime(data.getTime() + diff);
  return data;
}

function setDtServer(json) {
  dtServer = new Date(json.dateString);  
  dtClient = new Date();
  document.head.removeChild(script);  
}

document.head.appendChild(script);
window.setInterval(function () {
  var data = getData();
  console.log(formater.format(data));
}, 1234);

function  horaCerta() {
    var resposta = window.confirm("Você está certo disso? ");
    if (['true', 'OK', '1'].includes(resposta)) {
        'true';
    } else if (['false', 'Cancelar', '0'].includes(resposta)) {
        'false';
    }
}