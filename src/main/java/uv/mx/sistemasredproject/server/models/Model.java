package uv.mx.sistemasredproject.server.models;

public class Model {
    private static Model model;
    private final DatabaseDriver databaseDriver;


    public Model() {
        databaseDriver = new DatabaseDriver();
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }

}
