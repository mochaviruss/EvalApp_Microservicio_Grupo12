# EvalApp - Microservicio

Microservicio del grupo 12 para el ramo Desarrollo de Aplicaciones Móviles. Guarda los alumnos, las rúbricas y las evaluaciones de la app EvalApp.

Integrantes:
- Ian Villalobos
- George Castillo

App: https://github.com/mochaviruss/EvalApp_Grupo12

Está hecho en Kotlin con Spring Boot y la base de datos es H2 (se guarda en la carpeta `datos`). La primera vez crea 8 alumnos, 3 rúbricas y 4 evaluaciones de prueba. Todos los datos son de prueba.

Endpoints (en el computador es http://localhost:8080 y desde el emulador http://10.0.2.2:8080):
- /api/alumnos
- /api/rubricas
- /api/evaluaciones

Cada uno tiene GET para listar, GET /{id}, POST para crear, PUT /{id} para editar y DELETE /{id} para eliminar.

Para probarlo hay que abrir la carpeta en Android Studio, esperar que cargue y en la terminal escribir `.\gradlew bootRun`. Si avisa que la versión de Java no es compatible, aceptar la opción que propone para usar JDK 21. Cuando salga "Tomcat started on port 8080" se puede abrir http://localhost:8080/api/alumnos en el navegador.

La base de datos se puede ver en http://localhost:8080/h2-console (JDBC URL `jdbc:h2:file:./datos/evalapp`, usuario `sa`, sin clave).

Pruebas unitarias: clic derecho en `src/test/kotlin` > Run 'Tests in ...'. Prueban los Service y el Repository se simula con MockK.
