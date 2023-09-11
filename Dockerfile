# Build
FROM gradle:jdk17-alpine AS builder
WORKDIR /project

# 그래들 파일이 변경되었을 때만 새롭게 의존패키지 다운로드 받게함 (파일 올릴 때 마다 gradle 다운로드 안하게 캐시 작업)
# COPY build.gradle.kts settings.gradle.kts /build/
# RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true

# 빌더 이미지에서 애플리케이션 빌드
COPY . /project
RUN gradle build -x test --parallel

FROM openjdk:17-jdk
WORKDIR /target
COPY --from=builder /project/build/libs/blog-backend-0.0.1-SNAPSHOT.jar blog-backend.jar

EXPOSE 8080
ENV	USE_PROFILE prod

USER nobody
ENTRYPOINT [\
   "java", "-jar",\
   "-Dspring.profiles.active=${USE_PROFILE}",\
   "/target/blog-backend.jar"\
]