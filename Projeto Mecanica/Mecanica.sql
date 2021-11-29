/**
	Agenda de contatos
    @author Gabriel Santana Padovesi
*/
create database dboficina;
------------------------------
use dboficina;
------------------------------
-- 1 -> 2 tabelas com relacionamento

create table servico(
os int primary key auto_increment,
Modecar varchar(100) not null,
servico varchar(100) not null,
valor decimal(10,2),
statusos varchar(100) not null,
datsaid date,
ano year not null,
placa varchar(8) not null unique,
mecanico varchar(50),
obs varchar(200),
idcli int not null,
foreign key(idcli) references clientes(idcli)
);
------------------------------

describe servico;
------------------------------
insert into servico(Modecar,servico,statusos,datsaid,valor,ano,placa,mecanico,obs,idcli)
values ('Golf GTI','Troca de Oleo','Aguardando aprovação',20211013,250,2022,'ABC-1234','Zé','Mecanico conclui com um otimo serviço','1');
insert into servico(Modecar,servico,statusos,datsaid,valor,ano,placa,mecanico,obs,idcli)
values ('Jaguar','Pintura','Em Andamento',20211013,200,2020,'MIN-1010','Adalto','Otimo profissional','2');
insert into servico(Modecar,servico,statusos,datsaid,valor,ano,placa,mecanico,obs,idcli)
values ('HB20','Manutenção do Motor','Retirado',20211014,450,2021,'PIN-3030','jair','Serviço concluido com rapidez','3');
insert into servico(Modecar,servico,statusos,datsaid,valor,ano,placa,mecanico,obs,idcli)
values ('Jetta','Manutenção de Embreagem','Aguardando aprovação',20211018,390,2018,'ABC-7845','Adalto','Embregem normalizada','4');
insert into servico(Modecar,servico,statusos,datsaid,valor,ano,placa,mecanico,idcli)
values ('Fusca','Alinhamento e Balanceamento','Retirado',20211013,170,1990,'OUD-1234','jair','5');
insert into servico(Modecar,servico,statusos,datsaid,valor,ano,placa,mecanico,idcli)
values ('Corsa','Troca de pneu','Em andamento',20211013,130,2003,'QWE-5565','Zé',6);
insert into servico(Modecar,servico,statusos,datsaid,valor,ano,placa,mecanico,obs,idcli)
values ('Parati','troca de Pneu','Finalizado',20211016,450.00,2005,'AQA-5879','Zé','Pneu trocado com sucesso',7);
insert into servico(Modecar,servico,statusos,datsaid,valor,ano,placa,mecanico,obs,idcli)
values ('Fiorino','Balanceamento','Em andamento',20211013,110,2010,'ASD-9012','Jair','Concluido',8);
insert into servico(Modecar,servico,statusos,datsaid,valor,ano,placa,mecanico,obs,idcli)
values ('Palio','Troca de Escapamento','Aguardando Pagamento',20211016,150,1992,'AZX-2313','Adalto','Trocado',9);



select * from servico;


-------------------------------------

create table clientes(
idcli int primary key auto_increment,
nome varchar(50) not null,
email varchar(100) ,
telefone varchar(15) not null,
telefone2 varchar(15),
cpf varchar(12) not null unique,
cep char(8) not null,
endereco varchar(50) not null,
numero varchar(12) not null,
complemento varchar(30),
bairro varchar(50) not null,
cidade varchar(50) not null,
uf char(2) not null
);


describe clientes;

insert into clientes(nome,email,telefone,telefone2,cpf,cep,endereco,numero,complemento,bairro,cidade,uf)
values ('Gabriel Santana','gabriel@email.com',11999999999,25569987,51435433223,5842-292,'Rua Goiaba',555,'Avenida','Tatuapé','São paulo','sp');
insert into clientes(nome,email,telefone,telefone2,cpf,endereco,cep,numero,complemento,bairro,cidade,uf)
values ('Henrique Moraes','henique@email.com','11999999998',12521799687,'51435433221','Rua Fluencis',5478-203,478,'Padaria','Vila matilde','São Paulo','sp');
insert into clientes(nome,email,telefone,telefone2,cpf,endereco,cep,numero,complemento,bairro,cidade,uf)
values ('Osvaldo Sandoli','osvaldo@email.com','11999999997',54778854745,'51435433222','Rua Tuiuti',5478-785,456,'Metro','Penha','São Paulo','sp');
insert into clientes(nome,email,telefone,telefone2,cpf,endereco,cep,numero,complemento,bairro,cidade,uf)
values ('Emanoel Ferreira','emanoel@email.com','11999999996',41554514211,'51435433224','Rua Lacradoura',5445-586,278,'Mercado','Belem','São Paulo','sp');
insert into clientes(nome,email,telefone,cpf,endereco,cep,numero,complemento,bairro,cidade,uf)
values ('Ricardo Pereira','ricardo@email.com','11999999995','51435433228','Rua Maria',5478-297,788,'Parque','Bela Vista','São Paulo','sp');
insert into clientes(nome,email,telefone,telefone2,cpf,endereco,cep,numero,complemento,bairro,cidade,uf)
values ('Andre Silva','andre@gmail.com',11999999998,547892445,51435443228,'Rua Joao',54552-203,632,'Padaria','Itaquera','São Paulo','sp');
insert into clientes(nome,email,telefone,cpf,endereco,cep,numero,complemento,bairro,cidade,uf)
values ('Marcos Antonio','marcos@gmail.com',11999999989,51435433230,'Rua Jorge',5456-123,478,'Mercado','Jabaquara','São Paulo','sp');
insert into clientes(nome,email,telefone,cpf,endereco,cep,numero,complemento,bairro,cidade,uf)
values ('Rafael Portugal','rafael@gmail.com',11999999988,51435433231,'Rua kataka',5774-203,231,'Padaria','Mooca','São Paulo','sp');
insert into clientes(nome,email,telefone,cpf,endereco,cep,numero,complemento,bairro,cidade,uf)
values ('Sergio Kikuta','sergio@gmail.com',11999999987,51435433232,'Rua Japa',4452-203,368,'Shopping','Vila Olimpia','São Paulo','sp');
insert into clientes(nome,email,telefone,cpf,endereco,cep,numero,complemento,bairro,cidade,uf)
values ('Humberto','humberto@gmail.com',11999999986,51435433233,'Rua do gordo',57485-203,988,'Metro','Tatuapé','São Paulo','sp');
insert into clientes(nome,email,telefone,cpf,endereco,cep,numero,complemento,bairro,cidade,uf)
values ('Gustavo','gustavo@gmail.com',11999999742,51435433654,'Rua do magro',57485-301,748,'Metro','Belem','São Paulo','sp');
insert into clientes(nome,email,telefone,cpf,endereco,cep,numero,complemento,bairro,cidade,uf)
values ('Gentili','gentili@gmail.com',11999997474,5555555544,'Rua do azul',57147-333,988,'Mercado','Santana','São Paulo','sp');


select * from clientes;

select * from servico inner join clientes
on servico.os = clientes.idcli;

select sum(valor) as Faturamento from servico where statusos = 'Retirado';
select sum(valor) as Espera from servico where statusos = 'Em andamento';

select 
servico.modecar, servico.servico,servico.statusos,servico.valor,
clientes.nome,clientes.email,clientes.telefone
from servico inner join clientes 
on servico.os = clientes.idcli
where statusos = 'Aguardando aprovação';

select
servico.modecar, servico.servico,servico.statusos,servico.valor,
clientes.nome,clientes.email,clientes.telefone
from servico inner join clientes 
on servico.os = clientes.idcli
where statusos = 'Retirado';

select
servico.modecar, servico.servico,servico.statusos,servico.valor,
clientes.nome,clientes.email,clientes.telefone
from servico inner join clientes 
on servico.os = clientes.idcli
where statusos = 'Em andamento';
-------------------------------------
-- CRUD 
update servico set statusos = 'Retirado' where os = 1;
update servico set valor = 400 where os = 4;
update servico set valor = 150 where os = 6;
update servico set statusos = 'Em andamento' where os = 10;
update servico set servico = 'Aguardando aprovação' where os = 3;
update servico set os = 10 where os = 12;

update clientes set endereco = 'Rua Pera' where idcli = 5;
update clientes set telefone = 11999999988 where idcli = 10;
update clientes set telefone = 11999689794 where idcli = 5;
update clientes set endereco = 'Rua nova' where idcli = 8;
update clientes set email = 'moraes@gmail.com' where idcli = 2;

select os,modecar,valor from servico;
select os,modecar,servico,statusos from servico;

delete from servico where os = 10;

select * from servico;

select idcli as ID, nome as clientes, telefone as Telefone from clientes where nome like "G%";