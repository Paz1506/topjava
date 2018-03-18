DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (datetime, description, calories, user_id) VALUES
  ('2016-03-18 12:08:22', 'First meal', 800, 100000),
  ('2016-03-20 17:15:22', 'Second meal', 300, 100000),
  ('2012-06-20 17:30:22', 'Thrid meal', 700, 100000),
  ('2017-12-22 17:30:22', 'Fourth meal', 1500, 100001),
  ('2017-06-20 09:30:22', 'Fifth meal', 500, 100001),
  ('2018-08-20 17:30:22', 'Sixth meal', 100, 100001);