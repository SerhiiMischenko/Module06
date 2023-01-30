SELECT c.name, COUNT(p.id) AS project_count 
FROM client c 
INNER JOIN project p ON c.id = p.client_id 
GROUP BY c.name 
HAVING COUNT(p.id) = (SELECT COUNT(proj.id) FROM project proj GROUP BY proj.client_id LIMIT 1);