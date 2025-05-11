CREATE SCHEMA `sq-user` DEFAULT CHARACTER SET utf8mb4 collate utf8mb4_general_ci;
use `sq-user`;

CREATE TABLE users (
  user_id BIGINT NOT NULL,
  project_id BIGINT NOT NULL COMMENT '소속 프로젝트 식별값',
  state VARCHAR(32) NOT NULL COMMENT '계정 상태',
  login_id VARCHAR(255) NOT NULL COMMENT '로그인 아이디',
  pwd VARCHAR(255) NOT NULL COMMENT '비밀번호',
  nickname VARCHAR(128) NOT NULL COMMENT '닉네임' CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
  profile_json JSON NULL COMMENT '프로필 정보 JSON',
  deleted_at DATETIME(3) NULL DEFAULT NULL COMMENT '삭제 일시',
  created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '생성 일시',
  updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '수정 일시',
  PRIMARY KEY (user_id),
  INDEX idx_project_id_state_login_id (project_id, state, login_id)
) COMMENT '사용자';

CREATE TABLE outbox (
  outbox_id BIGINT NOT NULL COMMENT '아웃박스 식별값',
  event_type VARCHAR(64) NOT NULL COMMENT '이벤트 유형',
  shard_key BIGINT NOT NULL COMMENT '샤딩 키값, 이 값을 이용하여 인스턴스 마다 배정하여 처리함',
  created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '생성일시',
  payload TEXT NOT NULL COMMENT '이벤트 송신 데이터 페이로드',
  PRIMARY KEY (outbox_id),
  INDEX idx_shard_key_created_at (shard_key, created_at))
COMMENT = '이벤트 아웃박스';
