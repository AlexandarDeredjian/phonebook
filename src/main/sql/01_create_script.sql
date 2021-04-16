
    create table contact (
       contact_id  serial not null,
        firstname varchar(255),
        lastname varchar(255),
        primary key (contact_id)
    )

    
    create table phonenumber (
       phonenumber_id  serial not null,
        contact_id int4,
        number_type int4,
        phonenumber varchar(255),
        primary key (phonenumber_id)
    )

    
    alter table phonenumber 
       add constraint FKmhlfyfug8dwdqsqau6xn3s809 
       foreign key (contact_id) 
       references contact