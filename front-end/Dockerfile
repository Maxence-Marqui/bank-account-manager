FROM node:18-alpine

WORKDIR /angular-app

COPY . .

RUN npm install -g @angular/cli

RUN npm install

CMD [ "ng" , "serve" , "--host", "0.0.0.0" ]