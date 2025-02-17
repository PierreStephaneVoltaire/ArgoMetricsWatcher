FROM public.ecr.aws/amazoncorretto/amazoncorretto:21-al2023-jdk
WORKDIR /home/app
EXPOSE 8080
COPY  build/libs/ArgoMetricsWatcher-0.1-all.jar /home/app/application.jar
ENTRYPOINT ["java", "-jar", "/home/app/application.jar"]
