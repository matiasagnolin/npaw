DROP TABLE IF EXISTS Data;

CREATE TABLE billionaires (
  message_id VARCHAR(250)  PRIMARY KEY,
  account_code VARCHAR(250) NOT NULL,
  target_device VARCHAR(250) NOT NULL,
  plugin_version VARCHAR(250) NOT NULL,
  ping_time INT NOT NULL,
  host_cluster VARCHAR(250) NOT NULL
);

/*INSERT INTO billionaires (message_id, account_code, target_device) VALUES
  ('Aliko', 'Dangote', 'Billionaire Industrialist'),
  ('Bill', 'Gates', 'Billionaire Tech Entrepreneur'),
  ('Folrunsho', 'Alakija', 'Billionaire Oil Magnate');*/