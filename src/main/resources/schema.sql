CREATE TABLE IF NOT EXISTS medico (
    medico_id INTEGER PRIMARY KEY AUTOINCREMENT
    , nombre TEXT NOT NULL
    , especialidad TEXT NOT NULL
    , cedula TEXT NOT NULL UNIQUE
    , correo_electronico TEXT NOT NULL
    , eliminado INTEGER
);

CREATE TABLE IF NOT EXISTS paciente (
    paciente_id INTEGER PRIMARY KEY AUTOINCREMENT
    , nombre TEXT NOT NULL
    , curp TEXT NOT NULL UNIQUE
    , telefono TEXT NOT NULL
    , correo_electronico TEXT NOT NULL
    , eliminado INTEGER
);

CREATE TABLE IF NOT EXISTS cita (
    cita_id INTEGER PRIMARY KEY AUTOINCREMENT
    , fecha_hora TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
    , motivo TEXT NOT NULL
    , medico_id INTEGER NOT NULL
    , paciente_id INTEGER NOT NULL
    , eliminado INTEGER
    , FOREIGN KEY (medico_id) REFERENCES doctor(medico_id)
    , FOREIGN KEY (paciente_id) REFERENCES patient(paciente_id)
);