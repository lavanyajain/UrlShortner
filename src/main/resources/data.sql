create table UrlMap (
  encoded varchar(400) not null,
  decoded varchar (1000) not null,
  count int,
  primary key(encoded)
);