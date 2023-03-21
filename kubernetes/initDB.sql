create
user "authorization";
alter
user "authorization" with PASSWORD 'authorization';
create schema "authorization";
alter
schema "authorization" owner to "authorization";
