CREATE TABLE Ticket (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    ticket_type ticket_type NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES "User"(id)
);
