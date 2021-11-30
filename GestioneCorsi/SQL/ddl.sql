create table amministratore(
nome_admin varchar2(30) not null,
cognome_admin varchar2(30) not null,
cod_admin int,
username varchar2(30) not null,
password varchar2(1024) not null,
constraint p_cod_admin primary key(cod_admin),
constraint u_username unique(username)
);