SELECT m.name FROM checkout_item AS c, member AS m, book AS b WHERE b.title = 'The Hobbit' AND b.id = c.book_id AND c.member_id = m.id;

SELECT m.name FROM book b left join  checkout_item c on b.id = c.book_id left join member m on m.id = c.member_id WHERE b.title = 'The Hobbit';