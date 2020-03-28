FROM openjdk:11-jdk as build
WORKDIR /workspace/app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
RUN ./gradlew dependencies

COPY src src
RUN ./gradlew build -x test
RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)

FROM openjdk:11

ENV SPRING_DATASOURCE_URL "DATABASE_URL"
ENV SPRING_DATASOURCE_USERNAME "root"
ENV SPRING_DATASOURCE_PASSWORD "password"
ENV SPRING_DATASOURCE_DIALECT "org.hibernate.dialect.PostgreSQL94Dialect"
ENV FIREBASE_KEY_LOCATION "firebase"


VOLUME /tmp
ARG DEPENDENCY=/workspace/app/build/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

EXPOSE 8080
ENTRYPOINT ["java", "-Dorg.codevscovid19.threedprintingservice.security.firebase.key.location=${FIREBASE_KEY_LOCATION}",\
            "-Dspring.jpa.database-platform=${SPRING_DATASOURCE_DIALECT}",\
            "-cp","app:app/lib/*","org.codevscovid19.threedprintingservice.Application"]