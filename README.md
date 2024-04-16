# Java RESTful API Exercise: IOU Service

## Description

Build a RESTful API service that allows users to `create`, `read`, `update`, and `delete` IOUs (I Owe You) using Java and Spring Boot.

## Getting Started

### Clone Repository
- Fork this repository in your GitHub account
- Clone your fork locally or open in CodeSpaces.

```sh
git clone [REPO_URL]
cd [REPO_NAME]
```

> :bulb: **Note:** Replace [REPO_URL] with the link to your GitHub repository and [REPO_NAME] with the repository's name.

### Create Database
- Login to MySQL

```sh
mysql -u root -p
```
> :bulb: **Note:** If your root user doesn't have a password set, omit the `-p` flag.

- Create a new database and exit MySQL

```sh
CREATE DATABASE IF NOT EXISTS restapiexercise;
exit;
```

### Initialise Project
- Open this [pre-configured Initializr project](https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.2.4&packaging=jar&jvmVersion=17&groupId=com.cbfacademy&artifactId=restapiexercise&name=REST%20API%20Exercise&description=RESTful%20API%20exercise%20using%20Spring%20Boot&packageName=com.cbfacademy.restapiexercise&dependencies=web,data-jpa,mysql,devtools) and click "Generate" to download a zipped project.
- Extract the downloaded zip file and copy the *contents* of the folder (**not** the folder itself!) to your local repository
- Open your repository in VS Code
- Add the following connection details to application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/restapiexercise
spring.datasource.username=[db user]
spring.datasource.password=[db user password, blank if not set]
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=create
spring.jpa.open-in-view=true
```

### Install Dependencies

Open a terminal at the root of the repo directory and run the following command to install the dependencies:

```sh
./mvnw clean dependency:resolve
```

If you are on a Windows machine, that will be:
```cmd
mvnw clean dependency:resolve
```

### Run Application

To start the API from the terminal, run the following command:

```sh
./mvnw spring-boot:run
```

Or on Windows:

```cmd
mvnw spring-boot:run
```

If successful, you should see output that ends similarly to the below

```
2024-04-12T11:49:59.055-04:00  INFO 39975 --- [REST API Exercise] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2024-04-12T11:49:59.059-04:00  INFO 39975 --- [REST API Exercise] [           main] c.c.r.RestApiExerciseApplication         : Started RestApiExerciseApplication in 1.493 seconds (process running for 1.638)
```

### Stop Application
Stop the application by pressing `Ctrl + C`

### Save Repository Snapshot
Commit your work and push to GitHub

```sh
git add .
git commit -m "[Your commit message]"
git push origin main
```

## Completing the Exercise

Follow the instructions provided in the session slides.

## Testing the API

You can test your endpoints using [Postman](https://www.postman.com) or your preferred REST client.

The JSON representation of an IOU that you'll get in responses or provide in the request body for `POST` and `PUT` requests will resemble the following:

```json
{
  "id": "d1415cfc-dbd9-4474-94fc-52e194e384fa",
  "borrower": "John Doe",
  "lender": "Alice Smith",
  "amount": 100.0,
  "dateTime": "2023-11-02T14:30:00Z"
}
```

> :bulb: **Note:** Remember that the `id` property may not be needed for all request types.

## Top Tips

- :camera_flash: Commit frequently and use meaningful commit messages. A granular, well-labelled history becomes an increasingly valuable asset over time.
- :cactus: Use feature branches. Build the habit of isolating your changes for specific tasks and merging them into your default branch when complete.
- :vertical_traffic_light: Use consistent naming conventions. Choose easily understandable names and naming patterns for your classes, functions and variables.
- :triangular_ruler: Keep your code tidy. Using the built-in formatting of VS Code or other IDEs makes your code easier to read and mistakes easier to spot.
- :books: Read the docs. Whether via Intellisense in your IDE, or browsing online documentation, build a clear understanding of the libraries your code leverages.
- :calendar: Don't wait until the last minute. Plan your work early and make the most of the time available to complete the assessment and avoid pre-deadline palpitations.
- :sos: Ask. :clap: For. :clap: Help! :clap: Your mentors, instructors and assistants are literally here to support you, so *make use of them* - don't sit and struggle in silence.

Best of luck! Remember, it's not just about the destination; it's the journey. Happy coding! 🚀
