-- 회원
INSERT INTO `sns_member` (`nick`, `name`, `phone_number`, `email`, `filepath`, `password`)
VALUES
    ('팡파레', '방현석', '01011111111', NULL, NULL, sha1('1111')),
    ('지나가율', '양소율', '01022222222', NULL, NULL, sha1('1111')),
    ('연궁이', '정연수', '01033333333', NULL, NULL, sha1('1111')),
    ('호빵맨', '김호일', '01044444444', NULL, NULL, sha1('1111')),
    ('산준', '김선준', '01055555555', NULL, NULL, sha1('1111')),
    ('래시포드', '전준호', '01066666666', NULL, NULL, sha1('1111'));

-- 마이페이지
INSERT INTO `sns_mypage` (`mno`, `state_message`, `birthday`, `gender`, `likes`, `visit_count`, `created_date`)
VALUES
  (1, NULL, NULL, 0, 0, 0, NOW()),
  (2, '살려줘,,,,', '1998-08-27', 0, 0, 0, NOW()),
  (3, NULL, NULL, 0, 0, 0, NOW()),
  (4, NULL, NULL, 0, 0, 0, NOW()),
  (5, NULL, NULL, 0, 0, 0, NOW()),
  (6, NULL, NULL, 0, 0, 0, NOW());

-- 게시글 카테고리
  INSERT INTO sns_board_category (name)
  VALUES
      ('게시글'),
      ('방명록');

-- 게시글
INSERT INTO `sns_board` (`mno`, `title`, `content`, `view_count`, `likes`, `category`, `created_at`, `updated_at`)
VALUES
  (1, '울집 고앵쓰 귀엽조?', '귀여운 넘...', 0, 0, 1, NOW(), NULL),
  (2, '랜만에 야구장 힐링', '우리가 이겼지렁ㅋ', 0, 0, 1, NOW(), NULL),
  (3, '오운완', '정상 찍고 옴', 0, 0, 1, NOW(), NULL),
  (4, '맛도리 뇸뇸', 'Content of the fourth post.', 0, 0, 1, NOW(), NULL),
  (5, '힐링할 거 추천 받아요', 'ㅈㄱㄴ', 0, 0, 1, NOW(), NULL),
  (6, '푸바옹 보고 힐링하세요^-^', '1바오랑 2바오 이름 뭐로 지으려나', 0, 0, 1, NOW(), NULL);

-- 댓글
INSERT INTO `sns_board_comment` (`bno`, `mno`, `content`, `created_at`, `updated_at`)
VALUES
  (1, 2, '고양이 넘 귀엽자너ㅠ', NOW(), NULL),
  (2, 4, '에헤이.. 담엔 이긴다', NOW(), NULL),
  (1, 3, '나만 없어 고양이,,,', NOW(), NULL),
  (2, 6, 'ㅋㅋㅋㅋㅋㅋㅋㅅㄱㅇ', NOW(), NULL),
  (3, 5, '잘 보고 가요^-^', NOW(), NULL),
  (3, 6, '대단쓰ㄷㄷ', NOW(), NULL);

-- 게시글 좋아요
# INSERT INTO `sns_board_like` (`mno`, `bno`)
# VALUES
#   (1, 1),
#   (2, 2),
#   (3, 3),
#   (4, 4),
#   (5, 5),
#   (6, 6);

-- 팔로우
INSERT INTO `sns_follow` (`follower`, `following`)
VALUES
  (1, 2),
  (2, 3),
  (3, 4),
  (4, 5),
  (5, 6),
  (6, 1);

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
  (6, 1, 1, 1, 1);

INSERT INTO sns_guestbook (title, content, likes, created_at, mno, mpno)
VALUES
  ('와따감', 'ㅎㅇ', 0, NOW(), 1, 2),
  ('ㅎㅇㅎㅇ', 'ㅎㅇㅎㅇ', 0, NOW(), 2, 1),
  ('ㅎr이', '세 번째 게시물 내용입니다.', 0, NOW(), 3, 4),
  ('ㅎㅇㅎㅇ', '세 번째 게시물 내용입니다.', 0, NOW(), 4, 3),
  ('할로', '세 번째 게시물 내용입니다.', 0, NOW(), 5, 6),
  ('왔다감^-^', CONCAT('Ooooo\n',
          ' (      )\n',
          '   )   /\n',
          '  (_/'), 0, NOW(), 1, 2);