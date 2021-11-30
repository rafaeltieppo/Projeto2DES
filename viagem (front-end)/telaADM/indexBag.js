function bagagem(button) {
    let desc = document.querySelector('#bagagem-desc').value
    let peso = document.querySelector('#bagagem-peso').value
    let custo = document.querySelector('#bagagem-custo').value
    
    if (desc && peso != '') {
        let data = {
            "idBagagem":0,
            "desc":desc,
            "peso":peso,
            "valor":custo
        }
        fetch("http://localhost:8080/cadBagagem", {
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

var clickB = 0

function lisBagagem() {
    let bagagem = document.querySelector('#bagagem')
    fetch("http://localhost:8080/lisBagagem", {
        method: "GET",
        headers: {"Content-Type":"application/json"}
    })
    .then(resp => {return resp.json()})
    .then(data => {
        let optionAllB = bagagem.querySelectorAll('option')
        data.forEach(bag => {
            if(clickB == 1) {
                let option = document.createElement('option')
                option.value = bag.idBagagem
                option.innerHTML = bag.desc
                bagagem.appendChild(option)
            }
        });
        if (optionAllB.length != 0) {
            if (data.length != optionAllB.length) {
                let tamanho = data.length - 1
                let option = document.createElement('option')
                option.value = data[tamanho].idBagagem
                option.innerHTML = data[tamanho].desc
                bagagem.appendChild(option)
            }
        }  
    })
    .catch(err => console.log(err))
    clickB++
}