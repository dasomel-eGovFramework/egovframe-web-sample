# 샘플 DB 스크립트

본 디렉토리는 `egovframe-web-sample` 실행을 위한 HSQLDB 샘플 데이터베이스 스크립트를 포함합니다.

## 스크립트 파일

- `sampledb.script` — HSQLDB 기본 샘플 스키마/데이터
- `sampledb_mysql.script`, `sampledb_data_mysql.script` — MySQL 마이그레이션 참고용
- `sampledb_oracle.script`, `sampledb_data_oracle.script` — Oracle 마이그레이션 참고용

## HSQLDB 서버 구동

애플리케이션이 `jdbc:hsqldb:hsql://localhost/sampledb`에 접속하기 위해서는 HSQLDB 서버가 실행되어 있어야 합니다.

### 1. Maven Central에서 최신 HSQLDB jar 다운로드

```bash
mvn dependency:get -Dartifact=org.hsqldb:hsqldb:2.7.3
# 다운로드 경로: ~/.m2/repository/org/hsqldb/hsqldb/2.7.3/hsqldb-2.7.3.jar
```

또는 공식 사이트(<https://hsqldb.org/>)에서 직접 다운로드합니다.

### 2. 서버 실행

본 디렉토리에서 다음 명령으로 서버를 구동합니다.

```bash
java -cp /path/to/hsqldb-2.7.3.jar org.hsqldb.Server \
     -database.0 file:sampledb \
     -dbname.0 sampledb
```

기본 접속 URL: `jdbc:hsqldb:hsql://localhost/sampledb`

## 참고

- 애플리케이션 런타임의 HSQLDB 의존성은 부모 POM(`egovframe-web-config-parent:5.0.0`)이 관리하는 버전을 사용합니다.
- 이전에 본 디렉토리에 동봉되어 있던 `hsqldb-1.8.0.10.jar`(2010년 릴리스)는 알려진 보안 취약점(CVE-2022-41853 등)이 있어 제거되었습니다. 최신 안정 버전(2.7.x 이상)을 사용하세요.
