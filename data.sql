create table livro
(
    isbn               integer      not null,
    titulo             varchar(100) not null,
    autor              varchar(100) not null,
    ano_lancamento     integer      not null,
    quantidade_paginas integer      not null,
    genero             varchar(100) not null,
    constraint pk_livro primary key (isbn),
    constraint uq_livro unique (titulo)
);

create table livraria
(
    id         serial       not null,
    nome       varchar(100) not null,
    isbn_livro integer      not null,
    constraint pk_livraria primary key (id),
    constraint fk_livraria_livro foreign key (isbn_livro) references livro (isbn),
    constraint uq_livraria unique (nome)
);