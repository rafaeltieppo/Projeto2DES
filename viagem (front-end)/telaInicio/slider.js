var counter = 1;
setInterval(function() {
    document.getElementById('radio' + counter).checked = true;
    counter ++;
    if(counter > 6) {
        counter = 1;
    }
}, 5000);

function companhialis (e) {
    fetch('http://localhost:8080/lisCompanhia', {
        method:"GET"
    })
    .then(resp => {return resp.json()})
    .then(data => {
        data.forEach(e => {
            let card = document.querySelector('.cca').cloneNode(true)
            let op = card.querySelector('option')
            if(e.nome.toLowerCase() == 'latam') {
                card.querySelector('img').src = "assets/latam_aviao.jpg"
                op.value = e.nome
                card.querySelector('button').innerHTML = 'Viagens '+e.nome
            }
            if(e.nome.toLowerCase() == 'gol') {
                card.querySelector('p').innerHTML = 'A Gol Linhas Aéreas Inteligentes é uma companhia aérea brasileira sediada no Rio de Janeiro, fundada em 2001'
                card.querySelector('img').src = "assets/imageGol.jpg"
                card.querySelector('button').innerHTML = 'Viagens '+e.nome
                op.value = e.nome
            }
            if(e.nome.toLowerCase() == 'azul') {
                card.querySelector('img').src = "assets/imageAzul.jpeg"
                card.querySelector('p').innerHTML = 'Azul Linhas Aéreas Brasileiras S.A. é uma companhia aérea brasileira fundada e homologada em 2008 por David Neeleman.'
                op.value = e.nome
                card.querySelector('button').innerHTML = 'Viagens '+e.nome
            }
            card.querySelector('button').addEventListener('click', ()=> {
                const idCompanhia = {
                    'idCompanhia':e.idCompanhia
                }

                localStorage.setItem("idCompanhia",JSON.stringify(idCompanhia))
                window.location.href = '/telaVoos/index.html'
            })

            card.style.display = 'block'
            document.querySelector('.companhia-aerea').appendChild(card)
        });
    })
}