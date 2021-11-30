function companhia(button) {
    let nome = document.querySelector('#companhia-nome').value
    let cnpj = document.querySelector('#companhia-cnpj').value

    if (nome && cnpj != '') {
        let data = {
            'idCompanhia':0,
            'cnpj':cnpj,
            'nome':nome
        }
        fetch("http://localhost:8080/cadCompanhia", {
                method: "POST",
                headers: {"Content-Type":"application/json"},
                body: JSON.stringify(data)  
        })
        .then(resp => {return resp.status})
        .then(data => {
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

var click = 0

function lisCompanhia() {
    let companhia = document.querySelector('#companhia')

    fetch("http://localhost:8080/lisCompanhia", {
                method: "GET",
                headers: {"Content-Type":"application/json"}
    })
    .then(resp => {return resp.json()})
    .then(data => {
        let optionAll = companhia.querySelectorAll('option')
        data.forEach(comp => {
            if(click == 1) {
                let option = document.createElement('option')
                option.value = comp.idCompanhia
                option.innerHTML = comp.nome
                companhia.appendChild(option)
            }
        });
        if (optionAll.length != 0) {
            if (data.length != optionAll.length) {
                let tamanho = data.length - 1
                let option = document.createElement('option')
                option.value = data[tamanho].idCompanhia
                option.innerHTML = data[tamanho].nome
                companhia.appendChild(option)
            }
        }  
    })
    .catch(err => console.log(err))
    click++
}