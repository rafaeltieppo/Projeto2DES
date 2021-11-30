var titulo = document.querySelector("h2")
var labelNome = document.querySelector(".nome")
var botaoPrincipal = document.querySelector("#principal")
var containerLogin = document.querySelector(".container")

function cadastro(button) {
    button.remove()
    containerLogin.querySelector('h2').innerHTML = 'Cadastrar'

    botaoPrincipal.value = "Cadastrar"
    botaoPrincipal.removeAttribute('onclick');

    
    botaoPrincipal.addEventListener('click', ()=> {
        cadastrar()
    })

    let form = document.querySelector('.form-login')
    let div = document.createElement('div')
    div.setAttribute('class', 'formulario')
    let input = document.createElement('input')
    input.setAttribute('id', 'nome')
    input.placeholder = "Nome"
    let linha = document.createElement('div')
    linha.setAttribute('class','linha')
    console.log(div);
    div.appendChild(input)
    div.appendChild(linha)
    form.appendChild(div)
}

var inputUsaValue = document.querySelector("#usuario")
var inputSenValue = document.querySelector("#senha")

function cadastrar() {
    var inputNomValue = document.querySelector("#nome")
    let dataCadastro = { 
        "idCliente":0,
        "nvl":1,
        "usuario":inputUsaValue.value,
        "senha":inputSenValue.value,
        "nome":inputNomValue.value
    }

    fetch("http://localhost:8080/cadCliente", {
        method: "POST",
        headers: {"Content-Type":"application/json"},        
        body: JSON.stringify(dataCadastro)
    })
    .then(resp => {return resp.json()})
    .then(data => {
        if(data.status == true) {
            timeBox("./assets/check.png","Cadastro com Sucesso","/telaLogin/index.html")
        }else {
            timeBox("./assets/cross.png","usuario já existe"," ")
        }
    }).catch(err => console.log(err))
}

function loginCliente () {
    let dataLogin = { 
        "usuario":inputUsaValue.value,
        "senha":inputSenValue.value
    }
    fetch("http://localhost:8080/loginCli", {
        method: "POST",
        headers: {"Content-Type":"application/json"},
        body: JSON.stringify(dataLogin)
    })
    .then(resp => {return resp.json()})
    .then(data => {
        let dataValidacao = {
            "idCliente":data.idCliente,
            "nvl":data.nvl,
            "cad":data.cad,
            "status":data.status,
            "nome":data.nome
        }

        
        localStorage.setItem("valida",JSON.stringify(dataValidacao))

        if(data.status == true) {
            timeBox("./assets/check.png","Login com Sucesso!","/telaInicio/index.html")
        }else {
            timeBox("./assets/cross.png","Senha ou Usuário incorreto"," ")
        }
    })
    .catch(err => console.log(err))
}

function loginAdm() {
    let dataLogin = { 
        "usuario":inputUsaValue.value,
        "senha":inputSenValue.value
    }
    fetch("http://localhost:8080/loginAdm", {
        method: "POST",
        headers: {"Content-Type":"application/json"},
        body: JSON.stringify(dataLogin)
    })
    .then(resp => {return resp.json()})
    .then(data => {
        let dataValidacao = {
            "idAdm":data.idAdm,
            "status":data.status,
            "nvl":data.nvl
        }
        localStorage.setItem("valida",JSON.stringify(dataValidacao))
        
        if(data.status == true) {
            timeBox("./assets/check.png","Login com Sucesso!","/telaInicio/index.html")
        }else {
            timeBox("./assets/cross.png","Adm não cadastrado?!"," ")
        }
    })
    .catch(err => console.log(err))
}

function timeBox(imag, text,hre) {
    setTimeout(() => {
        let box = document.querySelector(".box")
        let p = box.querySelector("p")
        let img = box.querySelector("img")
        box.style.display = "flex"
        box.style.visibility = "visible"
        p.innerHTML = text
        img.src = imag
        inputUsaValue.value = ""
        inputSenValue.value = ""
        setTimeout(() => {
            if (hre != " ") {
                window.location.href = hre
            }
            box.style.display = "none"
            box.style.visibility = "hidden"
        }, 1500)
    }, 900)
}