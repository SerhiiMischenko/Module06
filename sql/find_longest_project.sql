select id, round(datediff(finish_date, start_date) / 30) as month_count from project
 group by id having month_count = (select max(round(datediff(finish_date, start_date) / 30)) from project)
 order by month_count desc;
