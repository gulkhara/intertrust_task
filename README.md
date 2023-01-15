# intertrust_task

Automated API and UI tests

## Running locally

Make sure you have DOCKER installed and running on your machine

```sh
git clone https://github.com/gulkhara/intertrust_task.git # or download zip from github and unzip
cd intertrust_task
```

### Environmental variables must be added before running tests

Open "docker-compose.yml" and replace changeme into valid auth token:

```yml
- AUTH_TOKEN=changeme
```


### Run containers

```sh
docker-compose up --build
```
After tests are finished, Allure report is generated. Report can be found by following link:
http://localhost:5252/allure-docker-service-ui/projects/default

PS: The docker image for chrome browser is not compatible with MAC M1 chip
