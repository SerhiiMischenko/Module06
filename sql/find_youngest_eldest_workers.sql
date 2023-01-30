(select 'YOUNGEST' TYPE, name 'NAME', birthday BIRTHDAY from worker where birthday = (select max(birthday) from worker))
union all
(select 'ELDEST' TYPE, name NAME, birthday BIRTHDAY from worker where birthday = (select min(birthday) from worker));