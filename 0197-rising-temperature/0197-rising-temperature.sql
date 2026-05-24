# Write your MySQL query statement below
select w.id
from Weather w
join Weather e
ON DATEDIFF(w.recordDate, e.recordDate) = 1
and w.temperature>e.temperature;
