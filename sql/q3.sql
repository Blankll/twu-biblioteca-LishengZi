SELECT title FROM book WHERE id NOT IN (SELECT book_id FROM checkout_item)
UNION
SELECT title FROM movie WHERE id NOT IN (SELECT movie_id FROM checkout_item);
