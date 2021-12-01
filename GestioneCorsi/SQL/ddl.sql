create table amministratore(
cod_admin int,
nome_admin varchar2(30) not null,
cognome_admin varchar2(30) not null,
username varchar2(30) not null,
password varchar2(1024) not null,
constraint p_cod_admin primary key(cod_admin),
constraint u_username unique(username)
);

create table docente(
cod_docente int,
nome_docente varchar2(30) not null,
cognome_docente varchar2(30) not null,
cv_docente varchar2(1024) not null,
constraint p_cod_docente primary key(cod_docente)
);

create table corsista(
cod_corsista int,
nome_corsista varchar2(30) not null,
cognome_corsista varchar2(30) not null,
precedenti_formativi number(1,0) not null,
constraint p_cod_corsista primary key(cod_corsista)
);

create table corso(
cod_corso int,
cod_docente int,
nome_corso varchar2(30) not null,
data_inizio_corso date not null,
data_fine_corso date not null,
costo_corso number(7,2) not null,
commenti_corso varchar2(200),
aula_corso varchar2(30) not null,
constraint p_cod_corso primary key(cod_corso),
constraint f_cod_docente foreign key(cod_docente) references docente(cod_docente) on delete cascade
);

create table iscrizione(
cod_corsista int,
cod_corso int,
constraint f_cod_corsista foreign key(cod_corsista) references corsista(cod_corsista) on delete cascade,
constraint f_cod_corso foreign key(cod_corso) references corso(cod_corso) on delete cascade,
constraint p_iscrizione primary key(cod_corsista, cod_corso)
);


-- sequenze
create sequence corso_seq;
create sequence corsista_seq;

