create table if not exists "invoices"
(
    id  uuid    default random_uuid() primary key,
    user_id varchar (255),
    amount int,
    pdf_url varchar (255)
);