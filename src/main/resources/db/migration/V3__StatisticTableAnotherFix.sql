DROP TABLE IF EXISTS statistics_table;

CREATE TABLE statistics_table (
  id BIGINT NOT NULL PRIMARY KEY,
  user_id BIGINT DEFAULT -1,
  num_of_completed BIGINT DEFAULT -1,
  num_of_in_progress BIGINT DEFAULT -1,
  num_of_to_do BIGINT DEFAULT -1,
  timestamp_created TIMESTAMP
);