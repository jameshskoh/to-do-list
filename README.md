# to-do-list

A REST-based to do list server that uses OAuth2 for authentication.



### Requirements

#### Functional Requirements

* [x] Support user sign-in via OAuth2, possible third party: Gmail, Facebook, GitHub
* [x] Support adding a to-do
* [x] Support deleting a to-do
* [x] Support listing all to-do
* [x] Support marking a to-do as done/undone
* [x] Containerize the system in Docker
* [x] Support starting the image via Docker Compose: `docker-compose up`
* [x] Support access to to-do list via `http://host:port/api_path/params`
* [ ] Support authorization via JWT `curl -H "Authorization: Bearer {JWT token}" http://host:port/api_path/params`



#### Other Requirements

* Clean, readable, maintainable, testable, performant code
* Easily extendable code
* Database or datastore are separate from this system



#### To Do

* [x] Enable OAuth2 sign-in
* [x] Set up a database container
* [x] Set up a volume for database files
* [x] Write and test database instance and table creation script
* [x] Make sure secrets are in external files
* [x] Implement repositories
* [x] Implement Hibernate entities
* [x] Implement REST endpoints
* [ ] Add JWT support
* [x] Testing
* [x] Write and test DOCKERFILE
* [x] Write and test Docker Compose



### Containers

#### todolist-app

* Based on `eclipse-temurin:17-jdk-alpine`
* Requires a bind mount
  * Mount point: `/container/config`
  * Used to store secrets/configurations that contain secret



#### todolist-mysql

* Based on `mysql:latest`
* Requires a volume
  * Mount point: `/var/lib/mysql`
  * Table creation SQL script needs to be run on first startup



## Usage

* Tested on Postman
* If you are not logged in
  * You will be redirected to the 3rd party authority automatically
* All requests require a cookie with a valid `JSESSIONID`



### Home

* Endpoint `GET /`
* Shows your account details from the authority

```json
{
    "login": "jameshskoh",
    "id": 29790797,
    ...
}
```





### List All Your To-do

* Endpoint `GET /todo`
* Returns `200 OK` and a list of your to-dos

```json
[
    {
        "id": "0510b8e2-434b-45e4-a867-114f635c7645",
        "userId": "29790797",
        "label": "Buy groceries",
        "done": false
    },
    {
        "id": "2c8df670-2639-4fc4-895f-029b6e1c621b",
        "userId": "29790797",
        "label": "Pump petrol",
        "done": false
    }
]
```



### Create New To-do

* Endpoint `POST /todo`
* Body: your to-do label

```json
{
    "label": "Buy vege"
}
```

* Returns `201 CREATED` and your created to-do

```json
{
    "id": "211145b4-d4fb-406e-8e1d-4ce594f7e9b8",
    "userId": "29790797",
    "label": "Buy vege",
    "done": false
}
```

> Note: each `id` is a randomly generated UUID, verified to be unique.



### Set a To-do as Done/Undone

* Endpoint `PUT /todo/{id}`
* Body

````json
{
    "done": true
}
````

* Returns `200 OK` and your updated to-do

```json
{
    "id": "211145b4-d4fb-406e-8e1d-4ce594f7e9b8",
    "userId": "29790797",
    "label": "Buy vege",
    "done": true
}
```



### Delete a To-do

* Endpoint `DELETE /todo/{id}`
* Returns `204 NO CONTENT`



## Configuration

### Secret Store Directory, `secret-store`

* Set up a directory to store secrets
  * Make sure Docker has access to the directory
  * E.g. `C:\dockermount`



### OAuth2 Third Party Authority

* Register for an OAuth app on a 3rd party authority, e.g. GitHub, Google, etc.
* Documentations
  * GitHub: https://docs.github.com/en/developers/apps/building-oauth-apps/creating-an-oauth-app
  * Google: https://support.google.com/cloud/answer/6158849?hl=en
* Required fields
  * Application name: a name of your liking
  * Homepage URL: `http://{host}:{port}/`
  * Authorization callback URL
    * GitHub: `http://{host}:{port}/login/oauth2/code/github`
    * Google: `http://{host}:{port}/login/oauth2/code/google`
  * Replace `{host}` and `{port}` accordingly, e.g. `localhost`, `8080`



### `docker-compose.yml`

* Can be found at the root of the source code

```yml
version: '3'
services:
  todolist-mysql:
    image: mysql:latest
    ports:
      - "3306:3306"
    volumes:
      - todolist-mysql-vol:/var/lib/mysql
    environment:
      MYSQL_DATABASE: todo_db
      MYSQL_USER: todolist_admin
      MYSQL_PASSWORD: todolist_admin
      MYSQL_ROOT_PASSWORD: todolist_pw
    restart: unless-stopped
  todolist-app:
    build: ToDoList/
    ports:
      - "8080:8080"
    volumes:
      - type: bind
        source: {secret-store}
        target: /container/config
    restart: unless-stopped
volumes:
  todolist-mysql-vol:
```

* Replace `services.todolist-app.volumes.source` with `secret-store` full path
* Optional
  * Replace `services.todolist-mysql.environment.MYSQL_PASSWORD`
  * Replace `services.todolist-mysql.environment.MYSQL_ROOT_PASSWORD`



### `todolist-application.yml`

* Should be stored in `secret-store`

```yml
spring:
  datasource:
    url: jdbc:mysql://todolist-mysql:3306/todo_db?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: todolist_admin
    password: todolist_admin
  jpa:
    database-platform: org.hibernate.dialect.MySQLDialect
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        current_session_context_class: thread
  security:
    oauth2:
      client:
        registration:
          github:
            client-id: {client-id}
            client-secret: {client-secret}
security.enable-csrf: false
logging.level.org.springframework.web: DEBUG
```

* Retrieve `client-id` and `client-secret` from the 3rd party authority
* Based on your chosen authority, change the content under `spring.security.oauth2.client.registration`, then replace `client-id` and `client-secret`
  * E.g. GitHub
    ```yml
    ...
    registration:
      github:
        client-id: {client-id}
        client-secret: {client-secret}
    ...
    ```

  * E.g. Google
    ```yml
    ...
    registration:
      google:
        client-id: {client-id}
        client-secret: {client-secret}
    ...
    ```

* Optional
  * Replace `spring.datasource.password`



## Container Startup

### First Run

1. Run `mvn package` on `ToDoList`
   * Verify that `ToDoList/target` contains `ToDoList-0.0.1-SNAPSHOT.jar`
2. `cd` to project root
3. Run `docker-compose build`
4. Run `docker-compose up`
   * todolist-app will throw exception on first run, ignore it
5. Execute `MySQL/01-createtable.sql` using `todolist_admin` user
6. Run `docker-compose stop` or press `Ctrl+C` to stop the containers
7. Restart by running `docker-compose start`
   * todolist-app should work properly after this



### Subsequent Runs

1. `cd` to project root
2. Run `docker-compose start`
   * Optional: run `docker-compose start -d` for detached mode

