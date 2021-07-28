CREATE TABLE users
(
    id                      BIGSERIAL PRIMARY KEY,
    username                TEXT        NOT NULL UNIQUE,
    password                TEXT        NOT NULL,
    name                    TEXT        NOT NULL,
    avatar                  TEXT        NOT NULL DEFAULT 'noavatar.png',
    authorities             TEXT[]      NOT NULL DEFAULT '{}',
    account_non_expired     BOOLEAN     NOT NULL DEFAULT FALSE,
    account_non_locked      BOOLEAN     NOT NULL DEFAULT FALSE,
    credentials_non_expired BOOLEAN     NOT NULL DEFAULT FALSE,
    enabled                 BOOLEAN     NOT NULL DEFAULT FALSE,
    created                 timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tokens
(
    token   TEXT PRIMARY KEY,
    user_id BIGINT      NOT NULL REFERENCES users,
    created timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE posts
(
    id         BIGSERIAL PRIMARY KEY,
    author_id  BIGINT      NOT NULL REFERENCES users,
    content    TEXT        NOT NULL,
    created    timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated    timestamptz,
    deleted    timestamptz,
    deleted_by BIGINT REFERENCES users
);
