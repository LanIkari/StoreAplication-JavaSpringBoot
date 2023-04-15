package com.poyostore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

@SpringBootApplication
public class PoyoStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoyoStoreApplication.class, args);

        // Establece la conexión con la base de datos de MongoDB
        String connectionString = "mongodb+srv://Brandon:1202945@store.2pkxjwk.mongodb.net/test?retryWrites=true&w=majority";
        ConnectionString connString = new ConnectionString(connectionString);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);

        // Obtiene la base de datos de MongoDB
        MongoDatabase database = mongoClient.getDatabase("DBPoyoStore");

        // Obtiene la colección de MongoDB
        MongoCollection<Document> collection = database.getCollection("producto");

        // Crea un nuevo documento con los datos que deseas insertar
        Document document = new Document("product", "2kg de carnitas del Brandon").append("cost", 999).append("category","food");

        // Inserta el documento en la colección
        collection.insertOne(document);

        // Cierra la conexión con la base de datos
        mongoClient.close();
    }
}