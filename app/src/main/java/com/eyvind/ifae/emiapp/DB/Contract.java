
package com.eyvind.ifae.emiapp.DB;

import android.provider.BaseColumns;

public class Contract {
    public static final class EstudiantesEntry implements BaseColumns {
        public static final String TABLE_NAME = "estudiantes";
        public static final String COLUMN_ID_CARNET = "idCarnet";
        public static final String COLUMN_FULL_NAME = "Nombres";
        public static final String COLUMN_SEMESTRE = "Semestre";
        public static final String COLUMN_CARRERA = "Carrera";
        public static final String COLUMN_DIRECCION = "Direccion";
        public static final String COLUMN_CORREO = "Correo";
    }

    public static final class CuentaEntry implements BaseColumns {
        public static final String TABLE_NAME = "cuenta";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_FULL_NAME = "Nombres";
        public static final String COLUMN_SEMESTRE = "Semestre";
        public static final String COLUMN_CARRERA = "Carrera";
        public static final String COLUMN_DIRECCION = "Direccion";
        public static final String COLUMN_CORREO = "Correo";
    }
}
