DROP TABLE IF EXISTS statistics_table;

CREATE TABLE statistics_table (
  id BIGINT NOT NULL PRIMARY KEY,
  userId BIGINT DEFAULT -1,
  numOfCompleted BIGINT DEFAULT -1,
  numOfInProgress BIGINT DEFAULT -1,
  numOfToDo BIGINT DEFAULT -1,
  timestampCreated TIMESTAMP
);