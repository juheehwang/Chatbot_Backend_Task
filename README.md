# 1. 프로젝트 루트에서 실행
docker-compose up -d --build

# 2. 컨테이너 확인
docker ps

# 3. 애플리케이션 로그 확인
docker logs -f spring-app

# 4. 종료
docker-compose down

# 5. postman에서 회원가입부터 순서대로 진행
