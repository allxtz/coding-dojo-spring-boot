FROM mysql:latest

MAINTAINER dbteam

RUN chown -R mysql:root /var/lib/mysql/

ENV MYSQL_DATABASE=wapp
ENV MYSQL_USER=appuser
ENV MYSQL_PASSWORD=appuser
ENV MYSQL_ROOT_PASSWORD=admin

ADD src/main/resources/data/schema.sql /etc/mysql/data.sql

RUN sed -i 's/MYSQL_DATABASE/'$MYSQL_DATABASE'/g' /etc/mysql/data.sql
RUN cp /etc/mysql/data.sql /docker-entrypoint-initdb.d

EXPOSE 3306