FROM node:16
ARG SECRET_KEY
ARG TOKEN_PREFIX
ARG REFRESH_PREFIX
ARG REDIS_HOST
ARG REDIS_PORT
ARG REDIS_USERNAME
ARG REDIS_PASSWORD
ARG MONGO_URL
ARG MONGO_USER
ARG MONGO_PASS
WORKDIR /app
COPY . /app
RUN npm install
RUN mkdir -p env

RUN echo "SECRET_KEY=${SECRET_KEY}" >> /app/env/.env
RUN echo "TOKEN_PREFIX=${TOKEN_PREFIX}" >> /app/env/.env
RUN echo "REFRESH_PREFIX=${REFRESH_PREFIX}" >> /app/env/.env
RUN echo "REDIS_HOST=${REDIS_HOST}" >> /app/env/.env
RUN echo "REDIS_PORT=${REDIS_PORT}" >> /app/env/.env
RUN echo "REDIS_USERNAME=${REDIS_USERNAME}" >> /app/env/.env
RUN echo "REDIS_PASSWORD=${REDIS_PASSWORD}" >> /app/env/.env
RUN echo "MONGO_URL=${MONGO_URL}" >> /app/env/.env
RUN echo "MONGO_USER=${MONGO_USER}" >> /app/env/.env
RUN echo "MONGO_PASS=${MONGO_PASS}" >> /app/env/.env
CMD ["npm", "run", "dev"]

