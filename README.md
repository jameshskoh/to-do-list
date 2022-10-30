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
* [ ] Support access to to-do list via `http://host:port/api_path/params`
* [ ] Support authorization via JWT `curl -H "Authorization: Bearer {JWT token} http://host:port/api_path/params"`



### Other Requirements

* Clean, readable, maintainable, testable, performant code
* Easily extendable code
* Database or datastore are separate from this system



### To Do

* [x] Enable OAuth2 sign-in
* [ ] Set up a database container
* [ ] Set up a volume for database files
* [ ] Write and test database instance and table creation script
* [ ] Make sure secrets are in external files
* [ ] Implement repositories
* [ ] Implement Hibernate entities
* [ ] Implement REST endpoints
* [ ] Final testing

