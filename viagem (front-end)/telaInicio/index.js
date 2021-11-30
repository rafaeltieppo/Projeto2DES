const valida = JSON.parse(localStorage.getItem('valida'))
console.log(valida);
//const valida2 = JSON.parse(localStorage.getItem('valida2'))
//localStorage.removeItem('validaBusca');

/*
function validarInput() {
    const origemm = document.querySelector('#origem');
    const destinoo = document.querySelector('#destino');
   

    let mensagem = []
    if (origemm.value == '' || destinoo.value == '') {
        mensagem.push('Complete os dados');
        alert(mensagem);
    } else if (origemm.value != '' || destinoo.value != '') {
       // window.location.href = '/telaVoos/index.html'
    }
}*/


function buscar() {
    let origemBu = document.querySelector('#origem').getAttribute('class')
    let destinoBu = document.querySelector('#destino').getAttribute('class')
    let dataIdBu = document.querySelector('#dataIda').value
    let dataVoBu = document.querySelector('#dataVolta').value
    
    
    let dataBusca = {
        "idOrigem":Number(origemBu),
        "idDestino":Number(destinoBu),
        "dataIda":dataIdBu,
        "dataVolta":dataVoBu
    }
    console.log(dataBusca);
    localStorage.setItem('validaBusca', JSON.stringify(dataBusca))

    if (document.location.pathname == "/telaInicio/index.html") {
        document.location.href = '/telaVoos/index.html'
    }
}
  
function validaAcesso() {
    let imags = ["assets/cliente.png", "assets/adm.png"]
    let opcaoCli = ["Perfil", "Area Cliente", "Sair"]
    let opcaoAdm = ["Perfil", "Area Adm", "Sair"]
    let imgSessao = document.querySelector('#img-sessao')
   
    if (valida.status) {
        imgSessao.parentNode.removeAttribute('href')
        console.log(valida.nome);
        document.querySelector('.p').innerHTML = valida.nome
        if (valida.nvl == 1) {
            imgSessao.src = imags[0]
            imgSessao.addEventListener('click',() => {
                for (let i = 0; i < 3; i++) {
                    createBox(opcaoCli[i])
                }
            })
        }else if (valida.nvl == 2) {
            imgSessao.src = imags[1]
            imgSessao.addEventListener('click',() => {
                for (let i = 0; i < 3; i++) {
                    createBox(opcaoAdm[i])
                }        
            })
        }    
    }
}

function createBox(tex) {
    let conteudo = document.querySelector('.box-options')
    let options = document.querySelector('.options')
    let close = document.querySelector('#close')
    let body = document.querySelector('body')
    let area = document.querySelector('.area-cliente')

    // acionando a area das opções
    conteudo.style.display = 'block'
    conteudo.style.visibility = 'visible'
    body.style.overflow = 'hidden'
    // ocultando area do cliente
    area.style.display = 'none'
    // reativando a area de opcao
    options.style.display = 'block'
    
    close.addEventListener('click', ()=> {
        close.parentNode.style.display = 'none'
        close.parentNode.style.display = 'hidden'
        options.innerHTML = ""
        body.style.overflow = "scroll"
    })
    let p = document.createElement('p')

    if(tex == 'Area Adm') {
        p.addEventListener('click', () => {
            document.location.href = '/telaADM/index.html'
        })
    }
    if(tex == 'Area Cliente') {
        p.addEventListener('click', () => {
            areaCliente()
        })
    }
    if(tex == 'Sair') {
        p.addEventListener('click', () => {
            localStorage.removeItem('valida')
            document.location.href = '/telaInicio/index.html'
        })
    }
    p.innerHTML = tex
    options.appendChild(p)
}

function areaCliente() {
    let options = document.querySelector('.options')
    let area = document.querySelector('.area-cliente')
    options.innerHTML = ""
    
    let p = document.createElement('p')
    p.innerHTML = 'teste'
    area.appendChild(p)
    options.style.display = 'none'
    area.style.display = 'block'
}

function selecionar(bot) {
    let input01 = document.querySelector("#input1")
    let input02 = document.querySelector("#input2")
    let dataIda = document.querySelector("#dataIda")
    let dataVolta = document.querySelector("#dataVolta")

    let id = bot.getAttribute('id')

    if(id === 'input2') {
        input02.style.backgroundColor = 'gray'
        input01.style.backgroundColor = 'white'

        dataVolta.removeAttribute('disabled', '')
        dataIda.removeAttribute('disabled', '')
    }
    if(id === 'input1') {
        input01.style.backgroundColor = 'gray'
        input02.style.backgroundColor = 'white'

        dataIda.removeAttribute('disabled')
        dataVolta.setAttribute('disabled', '')
    }
    
}