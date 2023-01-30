select id, round(datediff(finish_date, start_date) / 30) * (select sum(salary) from project_worker, worker where  project_worker.worker_id) as PRICE from project 
group by id;