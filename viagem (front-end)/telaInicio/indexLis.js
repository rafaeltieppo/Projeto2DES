var origem = document.querySelector("#origem");
var destino = document.querySelector("#destino");
var boxPop1 = document.querySelector(".box-origem-destino1");
var boxPop2 = document.querySelector(".box-origem-destino2");
var boxCidades1 = document.querySelector(".box-cidades-aero1");
var boxCidades2 = document.querySelector(".box-cidades-aero2");

var busca = ''

origem.addEventListener("keyup", () => {
    busca = origem.value.toLowerCase();
    if (busca.length >= 3) {
        boxPop1.style.display = 'block'
        boxPop1.style.visibility = 'visible'
        fetch("http://localhost:8080/lisCidade", {
            method: "GET",
            headers: {"Content-Type":"application/json"}     
        })
        .then(resp => {return resp.json()})
        .then(data => {
            boxCidades1.innerHTML = "";
            data.forEach(cidades => {
                if (origem.value != '') {     
                    if(cidades.nome.toLowerCase().includes(busca)) {
                        let p = document.createElement('option')
                        p.innerHTML = cidades.nome
                        boxCidades1.appendChild(p)
                        
                        p.addEventListener('click', () => {
                            origem.value = cidades.nome
                            origem.setAttribute('class', cidades.idCidade)
                            boxPop1.style.display = 'none'
                        })
                    }
                }
            });
        }).catch(err => console.log(err))
    }else {
        boxPop1.style.display = 'none'
        boxPop1.style.visibility = 'hidden'
    }
})



var busca2 = ''

destino.addEventListener("keyup", () => {
    busca2 = destino.value.toLowerCase();
    if (busca2.length >= 3) {
        boxPop2.style.display = 'block'
        boxPop2.style.visibility = 'visible'
        fetch("http://localhost:8080/lisCidade", {
            method: "GET",
            headers: {"Content-Type":"application/json"}     
        })
        .then(resp => {return resp.json()})
        .then(data => {
            boxCidades2.innerHTML = "";
            data.forEach(cidades => {
                if (destino.value != '') {     
                    if(cidades.nome.toLowerCase().includes(busca2)) {
                        let p = document.createElement('option')
                        p.innerHTML = cidades.nome
                        boxCidades2.appendChild(p)
                        
                        p.addEventListener('click', () => {
                            destino.value = cidades.nome
                            destino.setAttribute('class', cidades.idCidade)
                            boxPop2.style.display = 'none'
                        })
                    }
                }
            });
        }).catch(err => console.log(err))
    }else {
        boxPop2.style.display = 'none'
        boxPop2.style.visibility = 'hidden'
    }
})
