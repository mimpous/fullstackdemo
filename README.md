# fullstackdemo
1. Clone project into your system

<h2>Backend startup</h2>

1. navigate to <b>backend</b> folder
2. Run the folowing command :

mvn clean install

then 

docker-compose -f docker-compose.yml up -d --build

You can check some data using following url :
http://localhost:8080/api/tutorials/

To stop the container use as following :
docker-compose stop


<h2>Frontend startup</h2>
1. navigate to <b>react-frontend</b> folder
2. Run the folowing command  

docker-compose -f docker-compose.prod.yml up -d --build

Available url :
http://localhost:1337/

To stop the container use as following
docker-compose stop


To start the application in development mode run the following command :
npm start