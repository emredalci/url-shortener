create table public.url
(
    id        bigserial primary key,
    shortened varchar(255) not null constraint uk_1qlbcl6gyfdrpot9mdn2wcnu2 unique,
    url       varchar(255) not null,
    user_id   bigint       not null
);

insert into public.url (id, shortened, url, user_id)
values (1,'WN6jeG','https://www.emlakjet.com/satilik-konut/istanbul-adalar/?fiyat_trendi=dusen&icerik_turu=video&fiyat_analizi=firsat&sanal_tur=var', 1);