# Write your MySQL query statement
update Salary 
set sex= case
when sex='m' then 'f'
else 'm'
end;



