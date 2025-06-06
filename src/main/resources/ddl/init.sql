CREATE SCHEMA `sq-user` DEFAULT CHARACTER SET utf8mb4 collate utf8mb4_general_ci;
use `sq-user`;

CREATE TABLE users (
  user_id BIGINT NOT NULL,
  project_id BIGINT NOT NULL COMMENT '소속 프로젝트 식별값',
  state VARCHAR(32) NOT NULL COMMENT '계정 상태',
  login_id VARCHAR(255) NOT NULL COMMENT '로그인 아이디',
  pwd VARCHAR(255) NOT NULL COMMENT '비밀번호',
  nickname VARCHAR(128) NOT NULL COMMENT '닉네임',
  profile_json JSON NULL COMMENT '프로필 정보 JSON',
  deleted_at DATETIME(3) NULL DEFAULT NULL COMMENT '삭제 일시',
  created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '생성 일시',
  updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '수정 일시',
  PRIMARY KEY (user_id),
  INDEX idx_project_id_state_login_id (project_id, state, login_id)
) COMMENT '사용자';
