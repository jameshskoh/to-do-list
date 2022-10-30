# to-do-list

A REST-based to do list server that uses OAuth2 for authentication.



## Requirements

### Functional Requirements

* [x] Support user sign-in via OAuth2, possible third party: Gmail, Facebook, GitHub
* [ ] Support adding a to-do
* [ ] Support deleting a to-do
* [ ] Support listing all to-do
* [ ] Support marking a to-do as done/undone
* [ ] Containerize the system in Docker
* [ ] Support starting the image via Docker Compose: `docker compose up`
* [x] Support access to to-do list via `http://host:port/api_path/params`
* [ ] Support authorization via JWT `curl -H "Authorization: Bearer {JWT token} http://host:port/api_path/params"`



### Other Requirements

* Clean, readable, maintainable, testable, performant code
* Easily extendable code
* Database or datastore are separate from this system



### To Do

* [x] Enable OAuth2 sign-in
* [x] Set up a database container
* [x] Set up a volume for database files
* [x] Write and test database instance and table creation script
* [x] Make sure secrets are in external files
* [x] Implement repositories
* [x] Implement Hibernate entities
* [x] Implement REST endpoints
* [ ] Add JWT support
* [ ] Testing
* [ ] Write and test DOCKERFILE
* [ ] Write and test Docker Compose

