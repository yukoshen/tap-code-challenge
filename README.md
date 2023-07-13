# tap-code-challenge

Springboot Version: 2.7.13 </br>
Java Version: Java 11 </br>

### Add a new client ip </br>
Endpoint: http://localhost:8080/v1/addClient </br>
Method: POST </br>
Content-Type: application/json</br>
```json
{
    "clientName": "client1",
    "environment": "DEV",
    "application": "app1",
    "ipAddresses": [
        {
            "ipAddress": "192.168.0.1"
        },
        {
            "ipAddress": "192.168.0.2"
        },
        {
            "ipAddress": "192.168.0.3"
        }
    ]
}
```
Content-Type: application/xml</br>
Accept: application/xml</br>
```xml
<client>
    <clientName>client1</clientName>
    <environment>DEV</environment>
    <application>app1</application>
    <ipAddresses>
        <ipAddress>192.168.0.1</ipAddress>
    </ipAddresses>
        <ipAddresses>
        <ipAddress>192.168.0.2</ipAddress>
    </ipAddresses>
        <ipAddresses>
        <ipAddress>192.168.0.3</ipAddress>
    </ipAddresses>
</client>
```

### Get client by environment, application and clientName </br>
Endpoint: http://localhost:8080/v1/getClient </br>
Method: GET </br>
Content-Type: application/json</br>
```json
{
    "clientName": "client1",
    "environment": "DEV",
    "application": "app1"
}
```
Content-Type: application/xml</br>
Accept: application/xml</br>
```xml
<client>
    <clientName>client1</clientName>
    <environment>DEV</environment>
    <application>app1</application>
</client>
```

### Delete a client ip </br>
Endpoint: http://localhost:8080/v1/deleteClient </br>
Method: DELETE </br>
Content-Type: application/json</br>
```json
{
    "clientId": 1,
    "ipId": 1
}
```
Content-Type: application/xml</br>
Accept: application/xml</br>
```xml
<client>
    <clientId>1</clientId>
    <ipId>1</ipId>
</client>
```

### Running the application via Docker
Visit this link in dockerhub - https://hub.docker.com/repository/docker/kjsiador/tap-code-challenge/general <br>
1. Pull the image from docker hub by running this command
```Poweshell
docker image pull kjsiador/tap-code-challenge
```
2. Check if you successfully pulled the docker image by running this command
```Poweshell
docker images
```
3. Run the image in your local powershell by running this command
```Poweshell
docker container run -p 8080:8080 kjsiador/tap-code-challenge
```



