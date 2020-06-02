 

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
 