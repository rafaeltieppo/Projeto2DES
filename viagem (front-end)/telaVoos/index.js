var valPes = JSON.parse(localStorage.getItem('validaBusca'))
var dataCompanhia = JSON.parse(localStorage.getItem('idCompanhia'))
console.log(valida);
var cards = document.querySelector(".cards")


function validaCompanhiaCard() {
    var container = document.querySelector('.container-cards')
    container.innerHTML = ''

    let url = new URL('http://localhost:8080/lisCard')
    
    if (dataCompanhia.idCompanhia != null && valPes == null) {
        url.searchParams.append("idComp",dataCompanhia.idCompanhia)
        fetch(url, {
            method: "GET",
            headers: {"Content-Type":"application/json"}
        })
        .then(resp => {return resp.json()})
        .then(data => {
            data.forEach(card => {
                if (card.dataIda != null && card.dataVolta == null) {
                    creCardIda(card)
                    console.log('entrou 1');
                    valPes = null
                }else if (card.dataVolta != null) {
                    creCardVolta(card)
                    console.log('entrou 2');
                    valPes = null
                }   
            });
        })
    }
}

function validaCards() {
    let origem = document.querySelector('#origem').getAttribute('class')
    let destino = document.querySelector('#destino').getAttribute('class')
    let dataI = document.querySelector('#dataIda').value
    let dataV = document.querySelector('#dataVolta').value
    var container = document.querySelector('.container-cards')

    let url = new URL('http://localhost:8080/lisCard')

    
    container.innerHTML = ''

    fetch(url, {
        method: "GET",
        headers: {"Content-Type":"application/json"}
    })
    .then(resp => {return resp.json()})
    .then(data => {
        data.forEach(card => {
            let oriIdCi = card.idOrigem.idCidade
            let desIdCi = card.idDestino.idCidade
            let dataIda = card.dataIda
            let dataVol = card.dataVolta

            
            // card com o filtro da outra pagina e da mesma
            if (valPes != null) { // incluir os inputs da mesma na condição
                //dataCompanhia = null
                console.log('val '+valPes);
                if(oriIdCi == valPes.idOrigem && desIdCi == valPes.idDestino) {
                    // ida
                    if (card.dataIda == valPes.dataIda && card.dataVolta == valPes.dataVolta) {
                        creCardVolta(card) 
                        valPes = null
                    }else if (valPes.dataVolta == '' && card.dataVolta == null) {
                        creCardIda(card)
                        valPes = null
                    }
                    
                }
            }

            if (origem != ' ') { // listar com filtro da pagina
                if (oriIdCi == origem && desIdCi == destino) {
                    let idavorta = (document.querySelector("#input2").style.backgroundColor == 'gray') ? true : false;
                    
                    if(idavorta == true && card.dataVolta != null) {
                        if (card.dataIda == dataI && card.dataVolta == dataV) {
                            creCardVolta(card)
                        }
                    }else if(idavorta == false && card.dataVolta == null){
                        if (dataIda == dataI) {
                            creCardIda(card);
                        }
                    }
                }
            }
        });
    })
    .catch(err => console.log(err))
}

function comprar(card) {
    localStorage.setItem('comprarCard', JSON.stringify(card))
    window.location.href = '/telaCompra/index.html'
}

function creCardIda (card) {
    let container = document.querySelector('.container-cards')
    let imagens = ['assets/latam.png','assets/gol.png','assets/azul.png']
    let model = document.querySelector('.cards').cloneNode(true)
    model.style.display = 'flex'

    model.querySelector('h3').innerHTML = card.idDestino.nome
    let divs = model.querySelectorAll('div')
    let divComp = divs[0]
    let imgComp = divComp.querySelector('img')

    if (card.idCompanhia.nome.toLowerCase() == 'latam') {
        imgComp.src = imagens[0]
    }else if (card.idCompanhia.nome.toLowerCase() == 'gol') {
        imgComp.src = imagens[1]
    }else if (card.idCompanhia.nome.toLowerCase() == 'azul') {
        imgComp.src = imagens[2]
    }

    let vetData = card.dataIda.split('-')
    let data = Intl.DateTimeFormat('pt-BR', {
        weekday:'short',
        day:'numeric',
        month:'short',
        year:'numeric'
    }).format(new Date(vetData[0],vetData[1] - 1, vetData[2]))
    divs[1].querySelectorAll('p')[1].innerHTML = data

    divs[3].querySelectorAll('div')[0].querySelectorAll('h4')[0].innerHTML = card.idOrigem.nome
    divs[3].querySelectorAll('div')[0].querySelectorAll('h4')[1].innerHTML = card.idDestino.nome
    let horaForEm = card.ihEmbarque.split(':')
    let horaForDe = card.ihDesembarque.split(':')
    divs[3].querySelectorAll('div')[1].querySelectorAll('p')[0].innerHTML = horaForEm[0]+':'+horaForEm[1]
    divs[3].querySelectorAll('div')[1].querySelectorAll('p')[1].innerHTML = horaForDe[0]+':'+horaForDe[1]
    let tempoIda= Math.abs(Number(horaForEm[0] - horaForDe[0])) + "h, " + Math.abs(Number(horaForEm[1] - horaForDe[1])) + "m"
    divs[3].querySelectorAll('p')[2].innerHTML = "Duração: "+tempoIda

    divs[6].querySelectorAll('div')[0].querySelectorAll('p')[0].innerHTML = "Criança: R$ "+card.precoCrianca
    divs[6].querySelectorAll('div')[0].querySelectorAll('p')[1].innerHTML = "Adulto: R$ "+card.precoAdulto
    divs[6].querySelectorAll('div')[0].querySelectorAll('p')[2].innerHTML = "Preço: R$ "+card.valor
    divs[6].querySelectorAll('div')[1].querySelectorAll('p')[3].innerHTML = "Assentos: "+card.totalAssentos
    divs[6].querySelectorAll('div')[1].querySelectorAll('div')[4].addEventListener('click', () => {
        document.querySelector('.assen-baga').style.display = 'none'
        let divBagagem = document.querySelector('.div-bagagem')
        
        divBagagem.querySelectorAll('div')[1].querySelectorAll('p')[0].innerHTML = "tipo: "+card.idBagagens.desc
        divBagagem.querySelectorAll('div')[1].querySelectorAll('p')[1].innerHTML = "peso: "+card.idBagagens.peso+"Kg"
        divBagagem.querySelectorAll('div')[1].querySelectorAll('p')[2].innerHTML = "valor: R$ "+card.idBagagens.valor
        
        if (card.idBagagens.desc = 'despachar') {
            divBagagem.querySelectorAll('img')[0].src = 'assets/malas.png'
        }
        divBagagem.querySelectorAll('img')[1].addEventListener('click', () => {
            divBagagem.style.display = 'none'
            document.querySelector('.assen-baga').style.display = 'block'
        })
        divBagagem.style.display = 'flex'
    })
    model.querySelector('button').addEventListener('click', () => {
        comprar(card)
    })
    container.appendChild(model)
}

function creCardVolta(card) {
    let container = document.querySelector('.container-cards')
    let imagens = ['assets/latam.png','assets/gol.png','assets/azul.png']
    let model = document.querySelector('.cardsVolta').cloneNode(true)
    model.style.display = 'flex'

    model.querySelectorAll('h3')[0].innerHTML = card.idOrigem.nome
    model.querySelectorAll('h3')[1].innerHTML = card.idDestino.nome

    let divs = model.querySelectorAll('div')
    let divComp = divs[0]
    let imgComp = divComp.querySelectorAll('img')

    if (card.idCompanhia.nome.toLowerCase() == 'latam') {
        imgComp[0].src = imagens[0]
        imgComp[2].src = imagens[0]
    }else if (card.idCompanhia.nome.toLowerCase() == 'gol') {
        imgComp[0].src = imagens[1]
        imgComp[2].src = imagens[1]
    }else if (card.idCompanhia.nome.toLowerCase() == 'azul') {
        imgComp[0].src = imagens[2]
        imgComp[2].src = imagens[2]
    }

    let vetDataI = card.dataIda.split('-')
    let dataI = Intl.DateTimeFormat('pt-BR', {
        weekday:'short',
        day:'numeric',
        month:'short',
        year:'numeric'
    }).format(new Date(vetDataI[0],vetDataI[1] - 1, vetDataI[2]))
    divs[1].querySelectorAll('p')[1].innerHTML = dataI
    
    divs[3].querySelectorAll('div')[0].querySelectorAll('h4')[0].innerHTML = card.idOrigem.nome
    divs[3].querySelectorAll('div')[0].querySelectorAll('h4')[1].innerHTML = card.idDestino.nome
    
    let horaForEm = card.ihEmbarque.split(':')
    let horaForDe = card.ihDesembarque.split(':')
    divs[3].querySelectorAll('div')[1].querySelectorAll('p')[0].innerHTML = horaForEm[0]+':'+horaForEm[1]
    divs[3].querySelectorAll('div')[1].querySelectorAll('p')[1].innerHTML = horaForDe[0]+':'+horaForDe[1]
    let tempoIda= Math.abs(Number(horaForEm[0] - horaForDe[0])) + "h, " + Math.abs(Number(horaForEm[1] - horaForDe[1])) + "m"
    divs[3].querySelectorAll('p')[2].innerHTML = "Duração: "+tempoIda
     
    divs[6].querySelectorAll('div')[0].querySelectorAll('p')[0].innerHTML = "Criança: R$ "+card.precoCrianca
    divs[6].querySelectorAll('div')[0].querySelectorAll('p')[1].innerHTML = "Adulto: R$ "+card.precoAdulto
    divs[6].querySelectorAll('div')[0].querySelectorAll('p')[2].innerHTML = "Preço: R$ "+card.valor
    divs[6].querySelectorAll('div')[1].querySelectorAll('p')[0].innerHTML = "Assentos: "+card.totalAssentos
   
    divs[6].querySelectorAll('div')[1].querySelectorAll('div')[4].addEventListener('click', () => {
        document.querySelector('.assen-baga').style.display = 'none'
        let divBagagem = document.querySelector('.div-bagagem')
        //console.log(divs[6].querySelectorAll('div')[1].querySelectorAll('div')[4]);
        divBagagem.querySelectorAll('div')[1].querySelectorAll('p')[0].innerHTML = "tipo: "+card.idBagagens.desc
        divBagagem.querySelectorAll('div')[1].querySelectorAll('p')[1].innerHTML = "peso: "+card.idBagagens.peso+"Kg"
        divBagagem.querySelectorAll('div')[1].querySelectorAll('p')[2].innerHTML = "valor: R$ "+card.idBagagens.valor
        
        if (card.idBagagens.desc = 'despachar') {
            divBagagem.querySelectorAll('img')[0].src = 'assets/malas.png'
        }
        divBagagem.querySelectorAll('img')[1].addEventListener('click', () => {
            divBagagem.style.display = 'none'
            document.querySelector('.assen-baga').style.display = 'block'
        })
        divBagagem.style.display = 'flex'
    })

    if (card.dataVolta != null && card.dataVolta != "") {
        divs[17].querySelectorAll('h4')[0].innerHTML = card.idDestino.nome
        divs[17].querySelectorAll('h4')[1].innerHTML = card.idOrigem.nome

        let vetDataV = card.dataVolta.split('-')
        let dataV = Intl.DateTimeFormat('pt-BR', {
            weekday:'short',
            day:'numeric',
            month:'short',
            year:'numeric'
        }).format(new Date(vetDataV[0],vetDataV[1] - 1, vetDataV[2]))
        divs[14].querySelectorAll('p')[1].innerHTML = dataV    

        var horaForEmV = card.vhEmbarque.split(':')
        var horaForDeV = card.vhDesembarque.split(':')
        divs[18].querySelectorAll('p')[0].innerHTML = horaForEmV[0]+':'+horaForEmV[1]
        divs[18].querySelectorAll('p')[1].innerHTML = horaForDeV[0]+':'+horaForDeV[1]

        let tempoVol = Math.abs(Number(horaForEmV[0] - horaForDeV[0])) + "h, " + Math.abs(Number(horaForEmV[1] - horaForDeV[1])) + "m"
        
        divs[16].querySelectorAll('p')[2].innerHTML = "Duração: "+tempoVol
        model.querySelector('button').addEventListener('click', () => {
            comprar(card)
        })
      
    }
    container.appendChild(model)
}


function abrirCard() {
    let modal = document.querySelector('.modal')

    modal.style.display = 'block';
}

function fechar() {
    let modal = document.querySelector('.modal')

    modal.style.display = 'none';
}