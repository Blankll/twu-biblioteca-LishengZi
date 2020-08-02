INSERT INTO book(title) VALUES ('The Pragmatic Programmer');
INSERT INTO member(name) VALUES ('Lisheng Zi');
INSERT INTO checkout_item(member_id, book_id) VALUES((SELECT id FROM member WHERE name = 'Lisheng Zi'), (SELECT id FROM book WHERE title = 'The Pragmatic Programmer'));

SELECT m.name FROM checkout_item AS c, member AS m, book AS b WHERE b.title = 'The Pragmatic Programmer' AND b.id = c.book_id AND c.member_id = m.id;

