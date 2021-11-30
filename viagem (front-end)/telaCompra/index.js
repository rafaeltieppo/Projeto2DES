const data = JSON.parse(localStorage.getItem('comprarCard'))
const da = JSON.parse(localStorage.getItem('valida'))
var form = document.querySelector('.form')

const validaTrue = {
    "idCliente":da.idCliente,
    "nvl":1,
    "cad":true,
    "status":true,
    "nome":da.nome
}

console.log(da);
let chave = 0

document.querySelector('.caixa-chave').addEventListener('click',(e) => {
    let divChave = document.querySelector('.code-chave')
    divChave.classList.remove('model')
    chave = document.querySelector('#nCha').value
    
    divChave.querySelector('button').addEventListener('click', (e)=> {
        if(document.querySelector('#nCha').value != '') {
            divChave.style.display = 'none'
            divChave.style.visibility = 'hidden'
            document.querySelector('.caixa-chave').querySelector('label').innerHTML = '***'
        }
    })
})

function proximoBanco() {
    if(valida != null && valida.cad == false) {
        document.querySelector('#btnBanco').addEventListener('click', () => {
            cadastrarBanc();
        })
    }else {
        form.innerHTML = ''
        lisDadosBancarios()
    }
}

var idBanco = 0

function cadastrarBanc(e) {
    let nCar = document.querySelector('#nCart').value
    let nNum = document.querySelector('#nNume').value
    let nVali = document.querySelector('#nVali').value

    let dadosPessoal = document.querySelector('.cadastrar-pessoais')
    if (nCar != '' && nNum != '' && nVali != '' && chave != 0) {
        console.log(nCar+" numero"+nNum+" validade "+ chave + " validade"+nVali );
        let dadosBanco = {
            "idBancario":0,
            "numeroCartao":nNum,
            "nomeCliCartao":nCar,
            "validade":nVali,
            "numeroConta":null,
            "chave":chave
        }
    
        fetch('http://localhost:8080/cadCadastroBank',{
            method:'POST',
            headers: {"Content-Type":"application/json"},
            body: JSON.stringify(dadosBanco)
        })
        .then(res => { return res.json()})      
        .then(data => { console.log(data)})
        setTimeout(()=> {
            document.querySelector('.caixa-form').querySelector('h3').innerHTML = 'Dados Pessoais'
            form.innerHTML = ''
            dadosPessoal.style.display = 'flex'
            let butao = document.createElement('button')
            butao.type = 'button'
            butao.innerHTML = 'proximo'
            butao.addEventListener('click',() => {
                lisDadosBank()
                setTimeout(()=> { 
                    document.querySelector('.caixa-form').querySelector('h3').innerHTML = 'Escolha seu assento'
                    form.innerHTML = ''
                }, 2000)
                            
            })
            form.appendChild(dadosPessoal)
            form.appendChild(butao)
        }, 4000)
    }
}

function lisDadosBank() {
    let url = new URL('http://localhost:8080/lisCadastroBank')
    url.searchParams.append("idCliente",valida.idCliente)

    fetch(url, {method:'GET'})
    .then((resp) => resp.json())
    .then(data => {
        console.log(data);
        cadDadosPessoais(data[0].idBancario)
    })
}
/*
function lisClienteBanco() {
    let url = new URL('http://localhost:8080/lisClienteBankDao')
    url.searchParams.append("idCliente",valida.idCliente)
    fetch(url, {method:'GET'})
    .then((resp) => resp.json())
    .then(data => {
        console.log(data);
        cadDadosPessoais(data[0])
    })
}*/

function cadDadosPessoais(id) {
    console.log('idBanco '+id);
    let dadosPessoais = {
        "idCliente":valida.idCliente,
        "idBancario":id,
        "rg":Number(document.querySelector('#inRg').value),
        "sexo":document.querySelector('#selSexo').value,
        "endereco":document.querySelector('#inEnd').value,
        "telefone":Number(document.querySelector('#inTel').value),
        "nascimento":document.querySelector('#inNas').value
    }
    console.log(dadosPessoais);
    fetch('http://localhost:8080/altClienteBank', {
        method:'POST',
        headers: {"Content-Type":"application/json"},
        body: JSON.stringify(dadosPessoais)
    })
    .then((resp) => resp.status)
    .then(data => {console.log(data);})

    setTimeout(()=>{
        lisAssentos()
    }, 5000)
}


function lisDadosBancarios() {
    let url = new URL('http://localhost:8080/lisCadastroBankDados')
    url.searchParams.append("idCliente",valida.idCliente)
    fetch(url, {method:'GET'})
    .then((resp) => resp.json())
    .then(data => {
        data.forEach(d => {
            if(d.id != 0) {
                let dadosbancario = document.querySelector('.dados-cadastrado').cloneNode(true)
                dadosbancario.querySelectorAll('p')[0].innerHTML = d.nomeCliCartao
                dadosbancario.querySelectorAll('p')[1].innerHTML = d.numeroCartao
                dadosbancario.style.display = 'flex'  

                let butao = document.createElement('button')
                butao.type = 'button'
                butao.innerHTML = 'proximo'
                butao.addEventListener('click',() => {
                    document.querySelector('.caixa-form').querySelector('h3').innerHTML = 'Dados Pessoais'
                    form.innerHTML = ''
                    lisDadosPessoais()
                })

                form.appendChild(dadosbancario)
                form.appendChild(butao)
            }
        })
    })
}

function lisDadosPessoais () {
    let url = new URL('http://localhost:8080/lisClientesDao')
    url.searchParams.append("idCliente",valida.idCliente)

    fetch(url, {method:'GET'})
    .then((resp) => resp.json())
    .then(data => {
        let dadosPessoais = document.querySelector('.dados-pessoais').cloneNode(true)
        data.forEach(p => {
            
            dadosPessoais.querySelectorAll('p')[0].innerHTML = p.rg
            dadosPessoais.querySelectorAll('p')[1].innerHTML = p.nome
            dadosPessoais.querySelectorAll('p')[2].innerHTML = p.sexo
            dadosPessoais.querySelectorAll('p')[3].innerHTML = p.endereco
            dadosPessoais.querySelectorAll('p')[4].innerHTML = p.telefone
            dadosPessoais.style.display = 'flex'

            let butao = document.createElement('button')
            butao.type = 'button'
            butao.innerHTML = 'proximo'
            butao.addEventListener('click',() => {
                document.querySelector('.caixa-form').querySelector('h3').innerHTML = 'Escolha seu Assento'
                form.innerHTML = ''
                lisAssentos()
            })

            form.appendChild(dadosPessoais)
            form.appendChild(butao)
        })  
    })
}

function lisAssentos() {
    let url = new URL('http://localhost:8080/lisAssento')
    url.searchParams.append("idCard",data.idCard)
    console.log('oi');
    let divAssentos = document.querySelector('.div-assentos')
    for (let i = 1; i <= data.totalAssentos; i++) {
        let caixa = document.createElement('input')
        caixa.id = i
        caixa.type = 'radio'
        caixa.name = 'assento'
        divAssentos.appendChild(caixa)
    }
    divAssentos.style.display = 'block'
    console.log(divAssentos);
    form.appendChild(divAssentos)
    
    fetch(url, {method:'GET'})
    .then((resp) => resp.json())
    .then(data => {
        let inp = document.querySelector('.div-assentos').querySelectorAll('input')
        data.forEach(e => {
            inp.forEach(t => {
                if (t.getAttribute('id') == e.assento) {
                    t.checked = true
                    t.disabled = true
                    t.setAttribute("idAssento", e.idAssento);
                }
            })
        })
        let butao = document.createElement('button')
        butao.type = 'button'
        butao.innerHTML = 'selecionar'
        butao.addEventListener('click',() => {
            valAssentos()
        })

        form.appendChild(butao)
    })
}


function compAssento (assento) {
    let dataAssento = {
        "idAssento":0,
        "idCard":data.idCard,
        "assento":Number(assento)
    }
    //console.log("assentos ", dataAssento);
    fetch('http://localhost:8080/cadAssento', {
        method:'POST',
        headers: {"Content-Type":"application/json"},
        body: JSON.stringify(dataAssento)
    })
    .then((resp) => resp.status)
    .then(data => {console.log(data);})
    setTimeout(()=> {
        lisAssentosComp()
    },5000)
}

function valAssentos() {
    let inp = document.querySelector('.div-assentos').querySelectorAll('input')
    let descontos = document.querySelector('.caixa-dados').cloneNode(true)
    
    inp.forEach((t) => {
        if (t.checked && t.getAttribute('disabled') == null) {
            document.querySelector('.caixa-form').querySelector('h3').innerHTML = 'Passo final'
            form.innerHTML = ''
            descontos.querySelectorAll('p')[0].innerHTML ="Assento:  "+t.getAttribute('id')
   
            descontos.style.display = 'block'
            document.querySelector('#selDescontos').addEventListener('click', () => {
                lisDescontos()
            })

            let butao = document.createElement('button')
            butao.type = 'button'
            butao.innerHTML = 'comprar'
            butao.addEventListener('click',() => {
                compAssento(t.id)
            })
            form.appendChild(descontos)
            form.appendChild(butao)
           
        }else {
            console.log('Selecione um Assento');
        }
    })
}


function lisAssentosComp() {
    let url = new URL('http://localhost:8080/lisAssentoComp')
    url.searchParams.append("idCard",data.idCard)

    fetch(url, {method:'GET'})
    .then((resp) => resp.json())
    .then(data => {
        console.log(data);
        comprar(data[0].idAssento)
    })
    
}

function comprar (id) {
   if (id != 0 && id != undefined) {

        let horarioAtual = new Date();
        let atualFormat = horarioAtual.getHours()+":"+horarioAtual.getMinutes()+":"+horarioAtual.getSeconds()
        let origem = data.idOrigem.idCidade
        let destino = data.idDestino.idCidade
        let oriSe = origem
        let desSe = destino
        let desembarque = data.vhDesembarque
        let embarque = data.vhDesembarque
        let vhDes = desembarque
        let vhEmb = embarque

        let desconto = document.querySelector('#selDescontos').value.split(' ')
        let somaPreco = data.precoAdulto + data.valor
        let total = somaPreco - (somaPreco * desconto[1])/100
        if (data.dataVolta != null) {
            let cont = 0
            while (cont < 2) {
                if (cont == 1) {
                    desSe = origem
                    oriSe = destino
                    vhDes = desembarque
                    vhEmb = embarque
                }else {
                    oriSe = origem
                    desSe = destino
                    vhDes = embarque
                    vhEmb = desembarque
                }
                let dataPassagem = {
                    "idPassagem":0,
                    "idCard":data.idCard,
                    "idCliente":valida.idCliente,
                    "idAssento":id,
                    "idOrigem":oriSe,
                    "idDestino":desSe,
                    "idDesconto": desconto[0],
                    "companhiaAerea":data.idCompanhia.idCompanhia,
                    "idBagagem":data.idBagagens.idBagagem,
                    "imagePass":"",
                    "horaEmbarque":vhEmb,
                    "horaDesembarque":vhDes,
                    "horaCompra":atualFormat,
                    "preco":total
                }
                console.log(dataPassagem);
                fetch('http://localhost:8080/cadPassagem', {
                    method:'POST',
                    headers: {"Content-Type":"application/json"},
                    body: JSON.stringify(dataPassagem)
                })
                .then((resp) => resp.status)
                .then(data => {console.log(data);})
                cont++
            }
            document.querySelector('.fundo-compra').style.display = 'flex'
            setTimeout(()=> {
                localStorage.clear()
                localStorage.setItem('valida', JSON.stringify(validaTrue))
                window.location.href = '/telaInicio/index.html'
            },2000)
        }else {
            let dataPassagem = {
                "idPassagem":0,
                "idCard":data.idCard,
                "idCliente":valida.idCliente,
                "idAssento":id,
                "idOrigem":origem,
                "idDestino":destino,
                "idDesconto": desconto[0],
                "companhiaAerea":data.idCompanhia.idCompanhia,
                "idBagagem":data.idBagagens.idBagagem,
                "imagePass":"",
                "horaEmbarque":data.ihEmbarque,
                "horaDesembarque":data.ihDesembarque,
                "horaCompra":atualFormat,
                "preco":total
            }
            console.log(dataPassagem);
            fetch('http://localhost:8080/cadPassagem', {
                method:'POST',
                headers: {"Content-Type":"application/json"},
                body: JSON.stringify(dataPassagem)
            })
            .then((resp) => resp.status)
            .then(data => {console.log(data);})

            document.querySelector('.fundo-compra').style.display = 'flex'
            setTimeout(()=> {
                localStorage.clear()
                localStorage.setItem('valida', JSON.stringify(validaTrue))
                window.location.href = '/telaInicio/index.html'
            },2000)
        }
        
   }
}

function lisDescontos() {
    let url = new URL('http://localhost:8080/lisDesconto')
    fetch(url, {method:'GET'})
    .then((resp) => resp.json())
    .then(data => {
        data.forEach(d => {
            let selectDes = document.querySelector('#selDescontos').querySelectorAll('option')
            if (selectDes.length != data.length) {
                let p = document.createElement('option')
                p.innerHTML = "Tipo: "+d.tipo+" "+d.valor+'%'
                p.value = d.idDescontos + " "+d.valor
                document.querySelector('#selDescontos').appendChild(p)
            }
        })
    })

}

/*
function bagagens() {
    let url = new URL('http://localhost:8080/lisAssento')
    url.searchParams.append("idCard",data.idCard)

    for (let i = 1; i <= data.totalAssentos; i++) {
        let caixa = document.createElement('input')
        caixa.id = i
        caixa.type = 'radio'
        caixa.name = 'assento'
        document.querySelector('.caixa-checks').appendChild(caixa)
    }

    fetch(url, {method:'GET'})
    .then((resp) => resp.json())
    .then(data => {
        let inp = document.querySelector('.caixa-checks').querySelectorAll('input')
        data.forEach(e => {
            inp.forEach(t => {
                if (t.getAttribute('id') == e.assento) {
                    t.checked = true
                    t.disabled = true
                    t.setAttribute("idAssento", e.idAssento);
                }
            })
        })
    })   
}

// tela proximo => compra Assento
function proximo() {
    let inp = document.querySelectorAll('input')
    console.log("tsts: ",valida);
    if (valida != null) {
        inp.forEach((t) => {
            if (t.checked && t.getAttribute('disabled') == null) {
                if (valida.cad) {
                    document.querySelector('.pessoal').style.display = 'none';
                    document.querySelector('.caixao').style.display = 'none'
                    document.querySelector('.selectPag').style.display = 'block'
                    consDadosPes(); //check
                    lisBank(); // check
                    document.querySelector('#btnComp').addEventListener('click', () => {
                        compAssento(t.id) // check
                        comprar()
                        //valida.cad = false
                    }) 
                    
                }else {
                    document.querySelector('.pessoal').style.display = 'block'
                    
                    document.querySelector('#btnComp').addEventListener('click', () => {
                        compAssento(t.id)
                        cadDadosBank()
                        lisDadosBank()
                        setTimeout(()=>{ 
                            comprar()
                        }, 2000);
                        valida.cad = true
                        console.log(valida);
                    })
                    
                }   
                lisDescontos()
                document.querySelector('.cards').style.display = 'block'
                document.querySelector('.assentos').style.display = 'none'
            }
        })
    }else {
        
        alert('Você não esta cadastrado')
        document.location.href = '/telaLogin/index.html'
    }
     
}




// Comprar Assento
function compAssento (assento) {
    let dataAssento = {
        "idAssento":0,
        "idCard":data.idCard,
        "assento":Number(assento)
    }
    //console.log("assentos ", dataAssento);
    fetch('http://localhost:8080/cadAssento', {
        method:'POST',
        headers: {"Content-Type":"application/json"},
        body: JSON.stringify(dataAssento)
    })
    .then((resp) => resp.status)
    .then(data => {console.log(data);})
}

function lisDescontos() {
    let url = new URL('http://localhost:8080/lisDesconto')
    fetch(url, {method:'GET'})
    .then((resp) => resp.json())
    .then(data => {
        data.forEach(d => {
            let p = document.createElement('option')
            p.innerHTML = "Tipo: "+d.tipo+" "+d.valor+'%'
            p.value = d.idDescontos
            document.querySelector('.seletorDes').appendChild(p)
        })
    })

}

// listar dados bancarios



function cadDadosPessoais(id) {
    let dadosPessoais = {
        "idCliente":valida.idCliente,
        "idBancario":id,
        "rg":Number(document.querySelector('#rg').value),
        "sexo":document.querySelector('#sexo').value,
        "endereco":document.querySelector('#end').value,
        "telefone":Number(document.querySelector('#tel').value),
        "nascimento":document.querySelector('#nasc').value
    }
    console.log("novos dados", dadosPessoais);
    fetch('http://localhost:8080/altClienteBank', {
        method:'POST',
        headers: {"Content-Type":"application/json"},
        body: JSON.stringify(dadosPessoais)
    })
    .then((resp) => resp.status)
    .then(data => {console.log(data);})
}


function lisDadosBank() {
    let url = new URL('http://localhost:8080/lisCadastroBank')
    url.searchParams.append("idCliente",valida.idCliente)

    fetch(url, {method:'GET'})
    .then((resp) => resp.json())
    .then(data => {
        cadDadosPessoais(data[0].idBancario)
    })
}


// cadastrar dados bancarios
function cadDadosBank() {
    let dadosBank = {
        "idBancario":0,
	    "numeroCartao":document.querySelector('#cartao').value,
	    "nomeCliCartao":document.querySelector('#conta').value,
	    "validade":document.querySelector('#val').value,
	    "numeroConta":null,
	    "chave":document.querySelector('#chave').value
    }
    console.log("bank ", dadosBank);
    fetch('http://localhost:8080/cadCadastroBank', {
        method:'POST',
        headers: {"Content-Type":"application/json"},
        body: JSON.stringify(dadosBank)
    })
    .then((resp) => resp.status)
    .then(data => {console.log(data);})
}

function cadPassagem (id) {
    /*
    let url = new URL('http://localhost:8080/lisClientesDao')
    url.searchParams.append("idCliente",valida.idCliente)
    fetch(url, {method:'GET'})
    .then((resp) => resp.json())
    .then(data => {
        data.forEach(e => {
            console.log(e.nascimento);
            let anoNas = e.nascimento.split('-')
            let anoAtual = new Date().getFullYear()
            console.log("");
            let idade = anoAtual - anoNas[0]
            console.log(idade);

            let total = 0
            if (idade > 18) {
                data.precoAdulto
            }else {

            }
            
            
        })
    ;})
    let dataPassagem = {
        "idPassagem":0,
        "idCard":data.idCard,
        "idCliente":valida.idCliente,
        "idAssento":id,
        "idOrigem":data.idOrigem.idCidade,
        "idDestino":data.idDestino.idCidade,
        "idDesconto":document.querySelector('.seletorDes').value,
        "companhiaAerea":data.idCompanhia.idCompanhia,
        "idBagagem":data.idBagagens.idBagagem,
        "imagePass":"",
        "horaEmbarque":data.ihEmbarque,
        "horaDesembarque":data.ihDesembarque,
        "horaCompra":"18:10:09",
        "preco":600
    }
    console.log(dataPassagem);
    fetch('http://localhost:8080/cadPassagem', {
        method:'POST',
        headers: {"Content-Type":"application/json"},
        body: JSON.stringify(dataPassagem)
    })
    .then((resp) => resp.status)
    .then(data => {console.log(data);})

    
}

function comprar () {
    let url = new URL('http://localhost:8080/lisAssentoComp')
    url.searchParams.append("idCard",data.idCard)

    fetch(url, {method:'GET'})
    .then((resp) => resp.json())
    .then(data => {
        cadPassagem(data[0].idAssento)
        
    })
    document.querySelector('.Comprar').style.display = 'flex'
    setTimeout(()=>{
        localStorage.clear()
        const valida2 = valida
        localStorage.setItem('valida', JSON.stringify(valida2))
        //window.location.href = '/telaInicio/index.html'
    }, 2000);
   
}*/