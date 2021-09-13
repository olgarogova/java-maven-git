-- ����� products �� title
select * from products where title = 'ACADEMY ATLANTIS' ;

-- ����� products �� price = 9.99 � category = 8�� ������������� �� category � price
select * from products where price = 9.99 and category = 8 order by category, price;

-- ����� products � ������� category = 8 ��� 15
select * from products where category = 8 or category = 15;

-- ����� products � ������� price ����� 10 � 20 (����������� BETWEEN)
select * from products where price between 10 and 20;

-- ����� orders � ������� orderdate ����� 2004-01-05 � 2004-02-05
select * from orders where orderdate between '2004-01-05' and '2004-02-05';

-- ������������� ������ � orders �� ���� customerid � ��������� ���������� ������������ ������� customerid
select customerid, count(customerid) from orders group by customerid; 

-- ������������� ������ � orders �� ����� customerid � orderdate � �������������� totalamount, ��� ���� ����� totalamount ������ ���� ������ 100
select customerid, orderdate, sum(totalamount) from orders group by customerid, orderdate having sum(totalamount) > 100;

-- �������� ������ � �������� customer, orders, orderlines � products � �������������� JOIN � ������� firstname, lastname, title, orderdate
select c.firstname, c.lastname, p.title, o.orderdate 
from customers c
inner join orders o on c.customerid = o.customerid
inner join orderlines o2 on o.orderid = o2.orderid
inner join products p on o2.prod_id = p.prod_id;
