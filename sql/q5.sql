SELECT name FROM checkout_item c LEFT JOIN member m on c.member_id = m.id GROUP BY name HAVING COUNT(*) > 1;
