drop table if exists book_natural;

create table book_natural
(
    isbn      varchar(255) not null,
    publisher varchar(255),
    title     varchar(255),
    primary key (title)
) engine = InnoDB;