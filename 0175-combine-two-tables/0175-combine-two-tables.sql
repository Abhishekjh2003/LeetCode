# Write your MySQL query statement below
Select e.firstName,e.lastName,d.city,d.state
from Person e left Join Address d
on e.personId = d.personId ;