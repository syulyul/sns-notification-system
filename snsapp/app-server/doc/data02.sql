  -- 회원
INSERT INTO `sns_member` (`nick`, `name`, `phone_number`, `password`)
VALUES
  ('user1', 'User One', '010-1111-1111', sha1('1111')),
  ('user2', 'User Two', '010-2222-2222', sha1('1111')),
  ('user3', 'User Three', '010-3333-3333', sha1('1111')),
  ('user4', 'User Four', '010-4444-4444', sha1('1111')),
  ('user5', 'User Five', '010-5555-5555', sha1('1111')),
  ('user6', 'User Six', '010-6666-6666', sha1('1111')),
  ('user7', 'User Seven', '010-7777-7777', sha1('1111')),
  ('user8', 'User Eight', '010-8888-8888', sha1('1111')),
  ('user9', 'User Nine', '010-9999-9999', sha1('1111')),
  ('user10', 'User Ten', '010-1010-1010', sha1('1111'));

-- 마이페이지
INSERT INTO `sns_mypage` (`mpno`, `state_message`, `filepath`, `birthday`, `gender`, `email`, `likes`, `today_visit_count`, `visit_count`, `created_date`)
VALUES
  (1, '첫 번째 회원의 상태 메시지입니다.', '/profile/user1.jpg', '1990-01-01', 1, 'user1@example.com', 50, 10, 100, NOW()),
  (2, 'This is the second user''s status message.', '/profile/user2.jpg', '1985-03-15', 2, 'user2@example.com', 40, 8, 90, NOW()),
  (3, '세 번째 회원의 상태 메시지입니다.', '/profile/user3.jpg', '1992-07-20', 1, 'use3@example.com', 60, 12, 110, NOW()),
  (4, 'Status message of the fourth user.', '/profile/user4.jpg', '1988-05-10', 2, 'user4@example.com', 70, 14, 120, NOW()),
  (5, '다섯 번째 회원의 상태 메시지입니다.', '/profile/user5.jpg', '1995-12-25', 1, 'user5@example.com', 45, 9, 95, NOW()),
  (6, 'This is the sixth user''s status message.', '/profile/user6.jpg', '1987-09-30', 2, 'user6@example.com', 55, 11, 105, NOW()),
  (7, '일곱 번째 회원의 상태 메시지입니다.', '/profile/user7.jpg', '1993-04-12', 1, 'user7@example.com', 65, 13, 115, NOW()),
  (8, 'Status message of the eighth user.', '/profile/user8.jpg', '1986-11-08', 2, 'user8@example.com', 75, 15, 125, NOW()),
  (9, '아홉 번째 회원의 상태 메시지입니다.', '/profile/user9.jpg', '1991-08-03', 1, 'user9@example.com', 80, 16, 130, NOW()),
  (10, 'This is the tenth user''s status message.', '/profile/user10.jpg', '1989-02-18', 2, 'user10@example.com', 70, 14, 120, NOW());

-- 게시글
INSERT INTO `sns_board` (`mno`, `title`, `content`, `view_count`, `likes`, `category`, `created_at`, `updated_at`)
VALUES
  (1, '게시글 1', '이것은 첫 번째 게시글입니다.', 100, 20, 1, NOW(), NULL),
  (2, '게시글 2', 'This is the second post.', 50, 15, 2, NOW(), NULL),
  (3, '게시글 3', '세 번째 게시글 내용입니다.', 80, 10, 1, NOW(), NULL),
  (4, '게시글 4', 'Content of the fourth post.', 120, 30, 2, NOW(), NULL),
  (5, '게시글 5', '다섯 번째 게시글입니다.', 70, 25, 1, NOW(), NULL),
  (6, '게시글 6', 'This is post number six.', 90, 18, 2, NOW(), NULL),
  (7, '게시글 7', '일곱 번째 게시글 내용입니다.', 110, 22, 1, NOW(), NULL),
  (8, '게시글 8', 'Eighth post content.', 60, 14, 2, NOW(), NULL),
  (9, '게시글 9', '아홉 번째 게시글입니다.', 130, 35, 1, NOW(), NULL),
  (10, '게시글 10', 'Tenth post content.', 75, 28, 2, NOW(), NULL);

-- 댓글
INSERT INTO `sns_board_comment` (`bno2`, `mno`, `content`, `created_at`, `updated_at`)
VALUES
  (1, 1, '첫 번째 게시글에 대한 댓글입니다.', NOW(), NULL),
  (2, 2, 'Comment on the second post.', NOW(), NULL),
  (1, 3, '댓글 내용입니다.', NOW(), NULL),
  (2, 4, 'Another comment on post number two.', NOW(), NULL),
  (3, 5, '다섯 번째 게시글에 대한 댓글입니다.', NOW(), NULL),
  (3, 6, 'A comment on post number six.', NOW(), NULL),
  (4, 7, '일곱 번째 게시글에 대한 댓글입니다.', NOW(), NULL),
  (5, 8, 'Eighth post comment.', NOW(), NULL),
  (4, 9, '아홉 번째 게시글에 대한 댓글입니다.', NOW(), NULL),
  (5, 10, 'Comment on the tenth post.', NOW(), NULL);

-- 게시글 좋아요
INSERT INTO `sns_board_like` (`mno`, `bno`)
VALUES
  (1, 1),
  (2, 2),
  (3, 3),
  (4, 4),
  (5, 5),
  (6, 6),
  (7, 7),
  (8, 8),
  (9, 9),
  (10, 10);

-- 팔로우
INSERT INTO `sns_follow` (`follower`, `following`)
VALUES
  (1, 2),
  (2, 3),
  (3, 4),
  (4, 5),
  (5, 6),
  (6, 7),
  (7, 8),
  (8, 9),
  (9, 10),
  (10, 1);

-- 알림 유형
INSERT INTO `sns_noti_type` (`name`)
VALUES
  ('좋아요 알림'),
  ('팔로우 알림'),
  ('댓글 알림'),
  ('태그 알림');

-- 개인 설정
INSERT INTO `sns_personal_option` (`mno`, `like_noti_state`, `follow_noti_state`, `coment_noti_state`, `tag_noti_state`)
VALUES
  (1, 1, 1, 1, 1),
  (2, 1, 1, 1, 1),
  (3, 1, 1, 1, 1),
  (4, 1, 1, 1, 1),
  (5, 1, 1, 1, 1),
  (6, 1, 1, 1, 1),
  (7, 1, 1, 1, 1),
  (8, 1, 1, 1, 1),
  (9, 1, 1, 1, 1),
  (10, 1, 1, 1, 1);
