# Project setup

## Prepare environment

### Creating your SSH key and adding to Gitlab account

You can access to your repository either with your gitlab user and password or using an SSH private key.

The last option is the preferred one because is like a two-factor authentication since you need the private key file to connect to the repository (and a password if you set on creation time).
Then you provide the public key generated at the same time to all those places where you want to access using your private key.

Think the private key is like a physical key and the public key the keyhole.

:warning: **Skip the following steps if you already know how private/public key works and you already are using one for Gitlab (or just add to you Gitlab account).**

Please fulfill the steps below to create an SSH private key and add the public key to your Gitlab account:

1. https://docs.gitlab.com/ee/ssh/README.html#requirements
2. https://docs.gitlab.com/ee/ssh/README.html#ed25519-ssh-keys **(continue in the next step)**
3. https://docs.gitlab.com/ee/ssh/README.html#common-steps-for-generating-an-ssh-key-pair **(use the default file path)**
4. https://docs.gitlab.com/ee/ssh/README.html#adding-an-ssh-key-to-your-gitlab-account
5. https://docs.gitlab.com/ee/ssh/README.html#testing-that-everything-is-set-up-correctly

### Installing Docker

Docker is the standard way to package and deploy complex applications and microservices because encapsulate them and make easier their installation and more secure because isolate them from the rest of the system.

We are not going to learn how to create a Docker image but we are going to use it to install and use a MySQL database.

:warning: **Skip the following steps if you already have docker daemon installed in your system.**

Find below a link from the official documentation to install latest docker daemon version (if you are using linux, installing using repository is always the best option):

* https://docs.docker.com/engine/install/

#### Pulling MySQL image

Now we need to download the docker image for MySQL which would be ready to use.

Those images are stored into DockerHub repository, so you would need to create an account there at the following link:

* https://hub.docker.com/signup

Run the following command in your terminal to download MySQL 8.0.19 docker image.

`docker run --name mysql8-0-19 -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:8.0.19`

* **docker run** is the command which will download an image and prepare it with the other parameters.
* **--name** is to give our local container a name because we can have several containers but with different configurations.
* **-p** think in docker like a virtual machine, with this parameter we are mapping port 3306 from docker container to your local 3306 port so when you would connect to that port your request is redirected or mapped to the same port on the docker container when is started (*3306 is the default port for MySQL*).
* **-e MYSQL_ROOT_PASSWORD** is specific configuration for MySQL docker image to set the root password of the database.
* **-d** let you specify the docker image you will download from DockerHub.

If you are curious, you will find more information at the following documentation page https://hub.docker.com/_/mysql

Command above will download docker image, configure and store a container in our local machine ready to use.

:information_source: **A docker image is the base with all the files required for an application but the container is what we run locally because have all the configurations we would need. A container is created using an image.**

#### Creating database

To start MySQL just run the command below in your terminal:

`docker start mysql8-0-19`

Note we start our conainer using the name we set in the step above.

You can check if the container is running with the following command:

`docker ps`

You should see an output like the following:

```
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                               NAMES
some_container_id   mysql:8.0.19        "docker-entrypoint.s…"   At some time        Running time        0.0.0.0:3306->3306/tcp, 33060/tcp   mysql8-0-19
```

Also you can check logs from the application running on your container with the following command:

`docker logs mysql8-0-19`

:information_source: **You need to start your container when you need to use it and you can stop it running the same command but changing `start` with `stop`**

To create the databases for our application you can use any MySQL client, I recommend the following one:

* https://dbeaver.io/

You can also use the bundled database client from your IDE.

The password for root user is **root** (the one we set when configured our container).

Create the following databases:
* **app** (this is the main database for the running application)

:warning: **Using root user to connect your application with the database is a bad practice and not recommended for production environments, but for our bootcamp we don't need to use another one.**

### Installing SDKMAN!

To develop our application we would need a Java development kit (JDK) and Gradle for dependency management.

Instead install them manually we would use SDKMAN! which let us do it much easier and let us have several versions installed in the machine and change between them as we would need.

You will find the installation instructions here https://sdkman.io/install

But basically is just running the following command:

`curl -s "https://get.sdkman.io" | bash`

#### Installing JDK

Four our application we are going to use Java 11 the latest LTS (long term support) version.

To install it run the command below:

`sdk install java 11.0.6-sapmchn`

This will install Java 11 provided by SAP, but you can install the same version provided by another maintainer. You can check all the available versions to install running the following command:

`sdk list java`

To check everything is installed find run the commands below:

* `java -version`

With output:

```
openjdk version "11.0.6" 2020-01-15 LTS
OpenJDK Runtime Environment (build 11.0.6+10-LTS-sapmachine)
OpenJDK 64-Bit Server VM (build 11.0.6+10-LTS-sapmachine, mixed mode)
```

* `javac -version`

With output:

```
javac 11.0.6
```

#### Installing Gradle

Our web application will use several frameworks, libraries and tools which also depend or delegate in other libraries.

Is a pain manage all this by hand so we would use Gradle for this.

Gradle alse let us configure our project in a standard way so can be work on in other OS or environments if they use the same version of Gradle. Also let us automatize some tasks like run tests or package our application.

:tada: **Congratulations! You don't need to do anything since our base project is already configured to use Gradle and will be downloaded on demand. The following steps are only if you want to use it in your system, but are not required.**

Run the following command to install Gradle using SDKMAN!

`sdk install gradle 6.3`

You can also list all available versions to download.

To check if was installed fine:

`gradle -v`

With output:

```
------------------------------------------------------------
Gradle 6.3
------------------------------------------------------------

Build time:   2020-03-24 19:52:07 UTC
Revision:     bacd40b727b0130eeac8855ae3f9fd9a0b207c60

JVM:          11.0.6 (SAP SE 11.0.6+10-LTS-sapmachine)
OS:           Info about your OS
```

##  Cloning the repository

To work on your project you need to clone the remote repository in your local machine.

To do so just run the following command:

`git clone git@gitlab.com:aspanetconomy/path/to/your/project.git`

You can get the path to your project if you access to the **Repository** section of your project on Gitlab page and click the top-right button **Clone**.

This will clone your remote repository in a local folder with name of the last part of the url before `.git`

E.g If your path is `git@gitlab.com:aspanetconomy/path/to/your/project.git` this will clone to `project` folder. 

If you want to clone to another folder, append to the command above the name of your the folder `git clone git@gitlab.com:aspanetconomy/path/to/your/project.git folder_name`

To check everything is fine, access to the folder and run the command below:

`git status`

The output should be:

```
On branch master
Your branch is up to date with 'origin/master'.

nothing to commit, working tree clean
```

## Running our project

Now we can start working on your project.

To run it (and check everything is fine) go to your cloned repository folder and run the command:

`./gradlew bootRun`

* **./gradlew** is wrapper executable for Gradle which will check if it is installed in your system otherwise it will download it to `.gradle` folder into your project folder. Also will download all the libraries your project needs to work. For windows you need to run replace the command with `gradlew.bat bootRun`.
* **bootRun** is the task we want to execute. This task is specific from our project since we will build it using Spring Boot and that library provides that task which basically will start an embedded server with our application.

If everything goes well after some lines the terminal should stop with the following message.

```
INFO 68825 --- [  restartedMain] n.a.b.springmvc.SpringmvcApplication     : Started SpringmvcApplication in 2.885 seconds (JVM running for 3.342)
```

Then you can access your application at the following link http://localhost:8080

:warning: **At the beginning our project is empty and if you access to the url you will get an error page. That is expected.**

### Launch tests

To launch all the automatic test from our application, run the following command in the project folder.

`./gradlew test`

All the test results should be printed below, if you run the command just after clone your project, the output should be:

```
> Task :test

net.aspanc.bootcamp.springmvc.SpringmvcApplicationTests

  Test contextLoads PASSED
```

## Importing to IntelliJ

We will use IntelliJ IDEA Ultimate which provide a professional environment for our work and toons of helpful tools.

You can import the project in two different ways.

* If you have not cloned your project yet, you can just open the IDE and click **Get from version control** where you would add the path to your repository as you would running `git clone` but the IDE would do for you.

* If you cloned your repository from terminal open the IDE and click **Open or import** and select the folder where you cloned the repository.

In both cases the IDE will recognize is a project modeled with Gradle and will import it and would use the wrapper to run all Gradle tasks for you from the IDE, will download all the dependencies and you can start your project and tests.

:information_source: **If IntelliJ doesn't detect is a Gradle project it will ask you and you only need to select Gradle from the list.**

Also this IDE provides you some visual tools for manage database (just need to configure the connection) and source code repository.

## Summary

### Start/stop database

* `docker start mysql8-0-19`
* `docker stop mysql8-0-19`

### Run/test application (from terminal)

* `./gradlew bootRun`
* `./gradlew test`

### Url

* http://localhost:8080