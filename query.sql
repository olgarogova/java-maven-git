-- Поиск products по title
select * from products where title = 'ACADEMY ATLANTIS' ;

-- Поиск products по price = 9.99 и category = 8 и отсортировать по category и price
select * from products where price = 9.99 and category = 8 order by category, price;

-- Поиск products у которых category = 8 или 15
select * from products where category = 8 or category = 15;

-- Поиск products у которых price между 10 и 20 (используйте BETWEEN)
select * from products where price between 10 and 20;

-- Поиск orders у которых orderdate между 2004-01-05 и 2004-02-05
select * from orders where orderdate between '2004-01-05' and '2004-02-05';

-- Сгруппировать данные в orders по полю customerid и посчитать количество относительно каждого customerid
select customerid, count(customerid) from orders group by customerid; 

-- Сгруппировать данные в orders по полям customerid и orderdate и просуммировать totalamount, при этом сумма totalamount должна быть больше 100
select customerid, orderdate, sum(totalamount) from orders group by customerid, orderdate having sum(totalamount) > 100;

-- Написать запрос к таблицам customer, orders, orderlines и products с использованием JOIN и вывести firstname, lastname, title, orderdate
select c.firstname, c.lastname, p.title, o.orderdate 
from customers c
inner join orders o on c.customerid = o.customerid
inner join orderlines o2 on o.orderid = o2.orderid
inner join products p on o2.prod_id = p.prod_id;
