INSERT INTO food (name, expiration_date, status, image_url, memo, created_at, registered_by_user_id, refrigerator_id)
VALUES ('우유', '2026-05-15', 'FRESH', NULL, '저지방 우유', NOW(), 1, 1);

INSERT INTO food (name, expiration_date, status, image_url, memo, created_at, registered_by_user_id, refrigerator_id)
VALUES ('계란', '2026-05-20', 'FRESH', NULL, '10개 남음', NOW(), 1, 1);

INSERT INTO food (name, expiration_date, status, image_url, memo, created_at, registered_by_user_id, refrigerator_id)
VALUES ('두부', '2026-05-10', 'EXPIRED', NULL, NULL, NOW(), 1, 1);
