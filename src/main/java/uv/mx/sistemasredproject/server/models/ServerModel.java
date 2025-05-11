package uv.mx.sistemasredproject.server.models;

public class ServerModel {
    private static ServerModel model;
    private final DatabaseDriver databaseDriver;


    public ServerModel() {
        databaseDriver = new DatabaseDriver();
    }

    public static synchronized ServerModel getInstance() {
        if (model == null) {
            model = new ServerModel();
        }
        return model;
    }

    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }

}
