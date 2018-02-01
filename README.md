Postino - OSLID mailer
======================

Watches a specified folder for files matching `*.txt`, sends any matching file to the specified `recipients` and deletes the original file.
Backups are stored in `sourceDirectory/.camel` and `${app.base}/data/outbox`.

### Dependencies ###

- Java JRE 8 (JDK 8 to build)
- Maven


### Ubuntu Setup ###

- `apt-get install python-software-properties`
- `add-apt-repository ppa:webupd8team/java`
- `apt-get update`
- `apt-get install oracle-java8-installer`


### Build ###

- `mvn clean compile`


### Start service ###


1. **Configure Environment**

    ```
    export SMTP_USER="a2zlangmgr@gmail.com"
    export SMTP_PASS="password"
    export SMTP_HOST="smtp.gmail.com:465"
    export SMTP_RCPTS="ronniedz@gmail.com, ronniedz1s@yahoo.com"
    ```

2. **Run**

    + Option 1 - Set the source directory by passing the path as an argument:
    
        - `mvn exec:java -Dexec.args="../server/http_server/data"`
    
    + Option 2 - The default source directory is: `${app.base}/data/inbox`.
    
        - `ln -s ../server/http_server/data ./inbox # Make this path a softlink to the source directory`
    
        - `mvn exec:java`

    ***Requires write permission on `${app.base}/data/inbox`***

3. **Basic deployment**

```

sudo -u www-data bash -c 'export SMTP_RCPTS="ronniedz1s@yahoo.com, garrickajo@gmail.com"; export SMTP_PASS="password"; mvn exec:java &'

```


