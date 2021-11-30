drop database viagens;
create database viagens charset=UTF8 collate utf8_general_ci;
use viagens;

CREATE TABLE clientes (
	id_cliente INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	rg BIGINT UNIQUE,
	nvl INTEGER NOT NULL,
	id_bancario_cli INTEGER,
	usuario VARCHAR(30) NOT NULL UNIQUE,
	senha VARCHAR(30) NOT NULL,
	nome VARCHAR (150) NOT NULL,
	sexo CHAR,
	endereco VARCHAR(200),
	telefone INTEGER,
	nascimento DATE
)default charset = utf8;

CREATE TABLE passagens (
	id_passagem INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	id_card_p INTEGER NOT NULL,
	id_cliente INTEGER NOT NULL,
	img_p VARCHAR(500) NOT NULL,
	id_assento_p INTEGER NOT NULL,
	id_origem INTEGER NOT NULL,
	id_destino INTEGER NOT NULL,
	id_desconto_p INTEGER,
	id_companhia INTEGER NOT NULL,
	id_bagagem_p INTEGER NOT NULL,
	h_embarque TIME NOT NULL,
	h_desembarque TIME NOT NULL,
	h_compra TIME NOT NULL,
	preco DECIMAL (7,2) NOT NULL
)default charset = utf8;


CREATE TABLE dados_bancarios (
	id_bancario INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	numero_cartao BIGINT NOT NULL,
	nome_cli_cartao VARCHAR (50) NOT NULL,
	validade varchar(15) NOT NULL,
	numero_conta BIGINT,
	chave INTEGER NOT NULL
)default charset = utf8;

CREATE TABLE assentos (
  	id_assento INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	id_card_assento INTEGER NOT NULL,
	assento INTEGER NOT NULL
)default charset = utf8;

CREATE TABLE cidades (
	id_cidade INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
	aeroporto VARCHAR(200) NOT NULL UNIQUE,
	img VARCHAR(500) NOT NULL
)default charset = utf8;

CREATE TABLE descontos (
	id_desconto INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR (30) NOT NULL,
	valor DECIMAL (7,2) NOT NULL
)default charset = utf8;

CREATE TABLE companhia_aereas (
	id_companhia_aerea INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	cnpj INTEGER NOT NULL,
	nome VARCHAR (30) NOT NULL UNIQUE
)default charset = utf8;

CREATE TABLE bagagens (
	id_bagagem INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	descricao VARCHAR(50),
	peso DECIMAL(10,2) NOT NULL,
	custo DECIMAL (10,2) NOT NULL
)default charset = utf8;


-- SCRIPT ADM


CREATE TABLE adm(
	id_adm INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nvl INTEGER NOT NULL,
	usuario VARCHAR(30) NOT NULL,
	senha varchar(30) NOT NULL
)default charset = utf8;


CREATE TABLE cards(
	id_card INTEGER AUTO_INCREMENT PRIMARY KEY,
	id_adm INTEGER NOT NULL,
	img VARCHAR(500) NOT NULL,
	id_origem INTEGER NOT NULL,
	id_destino INTEGER,
	id_bagagem_c INTEGER NOT NULL,
	data_ida DATE NOT NULL,
	data_volta DATE,
	id_companhia INTEGER NOT NULL,
	ih_embarque TIME NOT NULL,
	ih_desembarque TIME NOT NULL,
	vh_embarque TIME,
	vh_desembarque TIME,
	valor DECIMAL(10,2) NOT NULL,
	pre_adulto DECIMAL(10,2) NOT NULL,
	pre_crianca DECIMAL(10,2) NOT NULL,
	tot_assentos INTEGER NOT NULL
)default charset = utf8;

alter table passagens add
constraint fk_assento foreign key (id_assento_p)
references assentos (id_assento);

alter table passagens add
constraint fk_cliente foreign key (id_cliente)
references clientes (id_cliente);

alter table passagens add
constraint fk_origem foreign key (id_origem)
references cidades (id_cidade);

alter table passagens add
constraint fk_destino foreign key (id_destino)
references cidades (id_cidade);

alter table passagens add
constraint fk_desconto foreign key (id_desconto)
references descontos (id_desconto);

alter table passagens add
constraint fk_companhia foreign key (id_companhia)
references companhia_aereas (id_companhia_aerea);

alter table passagens add
constraint fk_bagagem foreign key (id_bagagem_p)
references bagagens (id_bagagem);

alter table passagens add
constraint fk_card foreign key (id_card_p)
references cards (id_card);

alter table clientes add
constraint fk_passagem foreign key (id_passagem)
references  passagens (id_passagem);

alter table clientes add
constraint fk_bancario foreign key (id_bancario)
references dados_bancarios (id_bancario);

alter table assentos add
constraint fk_card_assento foreign key (id_card_assento)
references cards (id_card);

alter table cards add
constraint fk_adm foreign key (id_adm)
references adm (id_adm);

alter table cards add
constraint fk_cards_origem foreign key (id_origem)
references cidades (id_cidade);

alter table cards add
constraint fk_cards_destino foreign key (id_destino)
references cidades (id_cidade);

alter table cards add
constraint fk_cards_companhia foreign key (id_companhia)
references companhia_aereas (id_companhia_aerea);

alter table cards add
constraint fk_cards_bagagem foreign key (id_bagagem_c)
references bagagens (id_bagagem);

insert into adm set usuario = "michael", senha = "123", nvl = 2;


SELECT passagens.*, ad.id_assento, ad.assento, co.nome, co.aeroporto, co.img, cd.nome, cd.aeroporto,  cd.img,
ds.tipo, ds.valor, ca.id_companhia_aerea, ca.nome, ca.cnpj, bg.descricao, bg.peso, bg.custo FROM passagens
INNER JOIN assentos as ad ON id_assento_p = ad.id_assento
INNER JOIN cidades as co ON id_origem = co.id_cidade
INNER JOIN cidades as cd ON id_destino = cd.id_cidade
INNER JOIN descontos as ds ON id_desconto_p = ds.id_desconto
INNER JOIN companhia_aereas as ca ON id_companhia = ca.id_companhia_aerea
INNER JOIN bagagens as bg ON id_bagagem_p = bg.id_bagagem;

SELECT cards.*, co.nome, co.aeroporto, cd.nome, cd.aeroporto, ca.id_companhia_aerea, ca.nome, ca.cnpj, bg.descricao, bg.peso, bg.custo  FROM cards
INNER JOIN cidades as co ON id_origem = co.id_cidade 
INNER JOIN cidades as cd ON id_destino = cd.id_cidade
INNER JOIN companhia_aereas as ca ON id_companhia = ca.id_companhia_aerea
INNER JOIN bagagens as bg ON id_bagagem_c = bg.id_bagagem ;
INNER JOIN companhia_aereas as ca ON id_companhia = ca.id_companhia_aerea where id_companhia = 2;

/*
	id_passagem INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	id_card_p INTEGER NOT NULL,
	id_cliente INTEGER NOT NULL,
	img_p VARCHAR(500) NOT NULL,
	id_assento_p INTEGER NOT NULL,
	id_origem INTEGER NOT NULL,
	id_destino INTEGER NOT NULL,
	id_desconto_p INTEGER,
	id_companhia INTEGER NOT NULL,
	id_bagagem_p INTEGER NOT NULL,
	h_embarque TIME NOT NULL,
	h_desembarque TIME NOT NULL,
	h_compra TIME NOT NULL,
	preco DECIMAL (7,2) NOT NULL
*/

INSERT INTO passagens() VALUES (0,3,1,"sasdssdasdssd",2,1,3,1,1,1,"18:10:09","18:10:09","18:10:09",15);

INSERT INTO cards() values (0,1,"adssssssssssqrwwrwqwqrqrwrqw",1,5,1,"2021-11-13","2021-12-20",1,"18:10:09","20:10:09","18:10:09","20:10:09",500,100,50,20);

SELECT assentos.*, ps.id_assento_p, cd.id_card  from assentos 
inner join passagens as ps on id_assento = ps.id_assento_p
inner join cards as cd on id_card = ps.id_card_p where cd.id_card = ?;

select clientes.id_bancario_cli, dd.nome_cli_cartao, dd.numero_cartao from clientes
INNER JOIN dados_bancarios as dd on id_bancario = clientes.id_bancario_cli  where clientes.id_cliente = 1;

