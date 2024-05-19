CREATE TABLE User (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    username VARCHAR(255) NOT NULL,
    tip ENUM('editor', 'admin') NOT NULL,
    status ENUM('active', 'blocked') NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE Destination (
    destination_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL
);

CREATE TABLE Article (
    article_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    text TEXT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    visit_count INT DEFAULT 0 NOT NULL,
    autor_id INT NOT NULL,
    destination_id INT NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES User(user_id),
    FOREIGN KEY (destination_id) REFERENCES Destination(destination_id)
);

CREATE TABLE Activity (
    activity_id INT AUTO_INCREMENT PRIMARY KEY,
    tag VARCHAR(255) NOT NULL
);

CREATE TABLE Comment (
    comment_id INT AUTO_INCREMENT PRIMARY KEY,
    author_name VARCHAR(100) NOT NULL,
    text TEXT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    article_id INT NOT NULL,
    FOREIGN KEY (article_id) REFERENCES Article(article_id)
);

CREATE TABLE Article_Activity (
    article_id INT NOT NULL,
    activity_id INT NOT NULL,
    PRIMARY KEY (article_id, activity_id),
    FOREIGN KEY (article_id) REFERENCES Article(article_id),
    FOREIGN KEY (activity_id) REFERENCES Activity(activity_id)
);










