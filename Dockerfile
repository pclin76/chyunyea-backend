FROM public.ecr.aws/amazoncorretto/amazoncorretto:17-al2023
COPY target/application.jar application.jar
ENV JAVA_OPTS="${JAVA_OPTS} -Xms3G -Xmx6G"
EXPOSE 8080
#RUN dnf update
#RUN dnf install -y 'dnf-command(config-manager)' gzip util-linux-user vim wget
#RUN wget https://download.opensuse.org/repositories/shells:fish:release:3/CentOS-9_Stream/shells:fish:release:3.repo -O /etc/yum.repos.d/shells:fish:release:3.repo
#RUN dnf install -y fish
#RUN chsh -s /usr/bin/fish && usermod -s /usr/bin/fish root
ENTRYPOINT ["java","-jar","/application.jar"]
