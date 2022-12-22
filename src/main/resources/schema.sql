CREATE TABLE IF NOT EXISTS users (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(512) NOT NULL,
  CONSTRAINT pk_user PRIMARY KEY (id),
  CONSTRAINT UQ_USER_EMAIL UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS requests (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  description VARCHAR(512) NOT NULL,
  created TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  requestor_id int NOT NULL,
  CONSTRAINT pk_request PRIMARY KEY (id),
  FOREIGN KEY(requestor_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS items (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(512) NOT NULL,
  available boolean NOT NULL,
  owner_id int NOT NULL,
  request_id int,
  CONSTRAINT pk_item PRIMARY KEY (id),
  FOREIGN KEY(owner_id) REFERENCES users(id),
  FOREIGN KEY(request_id) REFERENCES requests(id)
);

CREATE TABLE IF NOT EXISTS bookings (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  start_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  end_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  item_id int NOT NULL,
  booker_id int NOT NULL,
  status VARCHAR(16) NOT NULL,
  CONSTRAINT pk_booking PRIMARY KEY (id),
  FOREIGN KEY(item_id) REFERENCES items(id),
  FOREIGN KEY(booker_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS comments (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  text VARCHAR(255) NOT NULL,
  item_id int NOT NULL,
  author_id int NOT NULL,
  created TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  CONSTRAINT pk_comment PRIMARY KEY (id),
  FOREIGN KEY(item_id) REFERENCES items(id),
  FOREIGN KEY(author_id) REFERENCES users(id)
);