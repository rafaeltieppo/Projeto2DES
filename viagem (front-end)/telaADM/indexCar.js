
function direcionar () {
    document.location.href = '/telaInicio/index.html'
}

function card(button) {
    let origem = document.querySelector('#origem')
    let destino = document.querySelector('#destino')
    let companhia = document.querySelector('#companhia').value
    let dataHoEmbIda = document.querySelector('#date-ti-local1').value.split('T')
    let hoDesIda = document.querySelector('#hDes1').value
    let bagagem = document.querySelector('#bagagem').value

    let valor = document.querySelector('#card-vPassagem').value
    let adulto = document.querySelector('#card-adulto').value
    let crianca = document.querySelector('#card-crianca').value
    let assentos = document.querySelector('#card-tAssentos').value
    var image = '';

    if (destino.value != " ") {
        
        
    }

    if (origem && destino != '') {
        let dataHoEmbVol = document.querySelector('#date-ti-local2').value.split('T')
        let hoDesVol = document.querySelector('#hDes2').value
        let segundos = new Date().getSeconds()

        let dataVolta = dataHoEmbVol[0]
        let horaEV = dataHoEmbVol[1] + ":" + segundos
        let horaDV = hoDesVol
        

        if (dataHoEmbVol == '') {
            dataVolta = null
            horaEV = null
            horaDV = null
        }

        
        var data = {
            "idAdm": 1,
            "imageCard":image,
            "idOrigem":origem.getAttribute('class'),
            "idDestino":destino.getAttribute('class'),
            "idBagagens":bagagem,
            "dataIda":dataHoEmbIda[0],
            "dataVolta":dataVolta,
            "idCompanhia":companhia,
            "ihEmbarque":dataHoEmbIda[1] + ":00",
            "ihDesembarque":hoDesIda + ":00",
            "vhEmbarque":horaEV,
            "vhDesembarque":horaDV,
            "valor":Number(valor),
            "precoAdulto":Number(adulto),
            "precoCrianca":Number(crianca),
            "totalAssentos":Number(assentos)
        }
        
        
        fetch("http://localhost:8080/cadCard", {
            method: "POST",
            headers: {"Content-Type":"application/json"},
            body: JSON.stringify(data)  
        })
        .then(resp => {return resp.status})
        .then(data => {
            console.log(data);
            if(data == 201) {
                button.innerHTML = 'Sucesso'
                button.style.backgroundColor = '#90ee90'
                setTimeout(() => {
                    button.innerHTML = 'Adicionar'
                    button.style.backgroundColor = '#3d445c'
                }, 900)
            }else {
                button.innerHTML = 'Erro'
                button.style.backgroundColor = '#ff4040'
                setTimeout(() => {
                    button.innerHTML = 'Adicionar'
                    button.style.backgroundColor = '#3d445c'
                }, 900)
            }
        })
        .catch(err => console.log(err))
       
        
    }else {
        button.innerHTML = 'Adicione os dados'
        button.style.backgroundColor = '#B22222'
        setTimeout(() => {
            button.innerHTML = 'Adicionar'
            button.style.backgroundColor = '#3d445c'
        }, 900)
    }
}

/*


<div class="div-h">
                        <div class="hEm">
                            <label for="time-embarque">H. Embarque</label>
                            <input type="datetime-local" name="" id="date-time-local">
                        </div>
                        <div class="hDe">
                            <label for="time-desembarque">H. Desembarque</label>
                            <input type="time" name="time-desembarque">
                        </div>
                    </div>



function criarCard() {
    let data = date.value.split('T');

    data[1] += ":00"

    console.log(data);
}*/

var divTime = document.querySelector(".div-time")
var divH2 = document.querySelector(".div-h-2")

function selecionar(bot) {
    let radio01 = document.querySelector("#radio1")
    let radio02 = document.querySelector("#radio2")
    let id = bot.getAttribute('id')

    if(id === 'radio2') {
        radio02.style.backgroundColor = 'black'
        radio01.style.backgroundColor = 'white'
       
        divH2.style.display = 'flex'
        divH2.style.visibility = 'visible'

        valid = true
    }
    if(id === 'radio1') {
        radio01.style.backgroundColor = 'black'
        radio02.style.backgroundColor = 'white'

        divH2.style.display = 'none'
        divH2.style.visibility = 'hidden'
        
        valid = false
    }
    
}