DELETE FROM CLIENT_TARGET_DEVICE;
DELETE FROM TARGET_DEVICE_HOSTS;
DELETE FROM CLIENT;
DELETE FROM HOST;
DELETE FROM TARGET_DEVICE;
INSERT INTO CLIENT (ID, ACCOUNT_CODE) VALUES ('1', 'ClienteA'),(2, 'ClienteB');
INSERT INTO TARGET_DEVICE (ID,NAME,PLUGIN_VERSION,PING_TIME) VALUES (1, 'XBox','3.3.1',10),(2, 'Panasonic','3.2.2',5),(3, 'osmf','3.3.1',5);
INSERT INTO HOST (ID,HOST_DNS,PERCENTAGE) VALUES (1,'clusterA.com',70),(2, 'clusterB.com',30),(3, 'clusterA.com',50),(4, 'clusterB.com',100),(5, 'clusterB.com',50);
INSERT INTO CLIENT_TARGET_DEVICE (CLIENT_ID,TARGET_DEVICE_ID) VALUES (1,1),(1,2),(2,3);
INSERT INTO TARGET_DEVICE_HOSTS (TARGET_DEVICE_ID,HOSTS_ID) VALUES (1,1),(1,2),(2,4),(3,3),(3,5);