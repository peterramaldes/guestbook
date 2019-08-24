# Estudando Clojure com Framework Luminus

## Gerando um pacote para produção

The application can be packaged for standalone deployment by running the following command:

```clojure
lein uberjar
``` 
This will create a runnable jar that can be run as seen below:

```console
export DATABASE_URL="jdbc:h2:./guestbook_dev.db"
java -jar target/uberjar/guestbook.jar
```

Note that we have to supply the DATABASE_URL environment variable when running as a jar, as it's not packaged with the application.