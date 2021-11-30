

function cidade(button) {
    let nome = document.querySelector('#cidade-nome').value
    let aeroporto = document.querySelector('#cidade-aero').value
    let image = document.querySelector('#cidade-image').value
    
    if (nome && aeroporto != '') {
        let data = {
            'idCidade':0,
            'nome':nome,
            'aeroporto':aeroporto,
            'image':image
        }
        fetch("http://localhost:8080/cadCidade", {
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