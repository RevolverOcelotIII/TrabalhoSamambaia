drop database samambaia;
create database samambaia;
use samambaia;

create table usuario(
	id int primary key not null auto_increment,
	nome varchar(100),
    email varchar(100),
    senha varchar(100)
);

create table planta(
	id int primary key not null auto_increment,
    nome_comum varchar(100),
	nome_personalizado varchar(100),
	nome_cientifico varchar(100),
	proxima_adubagem varchar(11),
	ciclo varchar(100),
	regagem varchar(100),
	banho_sol varchar(100),
	user_id int,
	planta_base_id int,
	imagem_url varchar(500),
	foreign key (user_id) references usuario(id));
    
select * from planta;