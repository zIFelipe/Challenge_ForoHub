
<h1 align="center"> Challenge_Forohub🗨️ </h1>

<h1 style="text-align: left;">Descripcion📝</h1>
Este proyecto es una aplicacion que crea una API Rest la cual está centrada en un Foro, donde los usuarios pueden publicar consultas referentes a un curso, y pueden recibir respuestas de parte de "administradores" para la duda presentada.🚀
<h1 style="text-align: left;">Estado del proyecto: Finalizado📎</h1>
<h1 style="text-align: left;">Tecnologías empleadas📡 </h1>

* Java 17 ✅

* **Spring Boot** para el REST API y JPA ✅

* **Hibernate** gestion de base de datos ✅

* **MySQL** , **H2 Database** configuracion base de datos ✅
  
* **Spring Security 6** para proteger la información ✅
  
* **Token JWT** para generar el token de inicio de sesión ✅
  
* **Spring doc** genera una documentación clara y legible para los usuarios ✅

* **Maven (version 4+** Gestiona dependencias y construcción del proyecto. ✅
  
* **Spring web** Permite crear APIs RESTful y manejar solicitudes HTTP. ✅
  
* **Spring Boot DevTools** Facilita el desarrollo con reinicio automático y LiveReload. ✅
  
* **Flyway Migration** Gestiona versiones y migraciones de la base de datos. ✅
  
* - **Insomnia** Entorno de pruebas para la API creada. ✅
  
  
<h1 style="text-align: left;">🛠️ Requisitos Previos 💬</h1>

1. Java Development Kit (JDK) 17 o superior.
2. Un entorno de desarrollo como preferiblemenete IntelliJ IDEA.
3. Base de datos configurada (MySQL, H2 o similar) preferiblemente MySQL Workbench.
4. Maven instalado para la gestión de dependencias.
5. Insomnia para hacer las pruebas de la API.

<h1 style="text-align: left;">Tutorial de Uso e instalación 🖥️</h1>
1. Clona este repositorio e instalalo en tu computadora:

    ```bash
     [ git clone [https://github.com/tu-usuario/literalura.git](https://github.com/zIFelipe/Challenge_LiterAlura.git)
      cd LiterAlura](https://github.com/zIFelipe/Challenge_ForoHub.git)
    ```
    
2. Utiliza una IDE compatible con Java, preferiblemente IntelliJ IDEA.
   
3.  Configura tu base de datos en el archivo`application.properties`:

                  spring.application.name=LiterAlura_Callenge
                  spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
                  spring.datasource.username=${DB_USER}
                  spring.datasource.password=${DB_PASSWORD}
                   spring.jpa.show-sql=true
                  spring.jpa.properties.hibernate.format_sql=true
                  
                  server.error.include-stacktrace=never
                  spring.jpa.open-in-view=true
                  api.security.secret=${JWT_SECRET:123456}
4.  Compila el programa mediante "ChallengeForoHubApplication"
   

    

<h1 style="text-align: left;">Funciones 💡</h1>

### Clases y estructura principal:

---

### Paquete: `model` 🏷️
Este paquete contiene los paquetes y clases principales para la ejecucion del proyecto, como usuarios, cursos, respuestas y topicos(consultas).

#### Paquetes en `model`:

* **Curso**: cuenta con la entidad curso, además de las DTO actualizar curso, registrar curso y listar curos, tambien contamos con un repositorio para la conexión con la base de datos y un enum para las categorias.
  
* **Usuario**: Cuenta con la entidad usuario, además de las DTO actualizar usuario, registrar usuario, listar usuario y autenticar usuario (necesario para obtener el token), tambien tenemos el repositorio usuario para la conexión con la base de datos y un paquete de validaciones para evitar errores en la ejecucion del programa.

* **Topico**: Cuenta con la entidad topico, además de las DTO actualizar topico, registrar topico, listar topico, tambien tenemos el repositorio topico para la conexión con la base de datos y un paquete de validaciones para evitar errores en la ejecucion del programa.

* **Respuesta**: Cuenta con la entidad respuesta, además de las DTO actualizar respuesta, registrar respuesta, listar respuesta, tambien tenemos el repositorio respuesta para la conexión con la base de datos y un paquete de validaciones para evitar errores en la ejecucion del programa.

---

### Paquete: `infra` 📦
Este paquete contiene todo lo que refiere a seguridad del programa y la generación de los tokens, posee 3 paquetes, seguridad, errores y springdoc.

#### Clases en `seguriad`:

* **Authentication service**: Carga los detalles de un usuario desde la base de datos usando su nombre de usuario (login).
  
* **DatosJWTtoken**: contenedor simple para encapsular un token.
  
* **SecurityConfigurations**: configura las reglas de seguridad, cómo manejar la autenticación y el encriptado de contraseñas, asegurando que solo usuarios autorizados accedan a ciertas partes de la aplicación.
  
* **SecurityFilter**: actúa como un filtro de seguridad que verifica el token JWT de cada solicitud, valida al usuario y establece su autenticación en el contexto de seguridad de Spring, permitiendo acceder a recursos protegidos.
  
* **TokenService**: Clase encargada de la generación del token para ingresar a la aplicación.

---

### Paquete: `errores` 🔧
Este paquete contiene el tratado de errores como los 400.

#### Clases en `errores`:

* **TratadorErrores**: gestiona las excepciones en la aplicación, devolviendo respuestas adecuadas (como 404 Not Found o 400 Bad Request) cuando ocurren errores.

* **ValidacionException**: Es una excepción personalizada que se puede usar para indicar errores relacionados con validaciones en la aplicación.

### Paquete: `controller` 🔧
Este paquete contiene el controlador que compila las funciones principales CRUD de cada  entidad.

#### Clases en `controller`:

* **AuthenticationController**: Controlador enfocado en la autenticación del usuario para la obtención del token y acceder a otras funciones.

* **UsuarioController**: Controlador encargado de ejecutar las funciones CRUD de usuario como lo son registrar, actualziar, listar y eliminar.
  
* **CursoController**: Controlador encargado de ejecutar las funciones CRUD de curso como lo son registrar, actualziar, listar y eliminar.
  
* **TopicoController**: Controlador encargado de ejecutar las funciones CRUD de Topico como lo son registrar, actualziar, listar y eliminar.
  
* **RespuestaController**: Controlador encargado de ejecutar las funciones CRUD de Respuesta como lo son registrar, actualziar, listar y eliminar.

---
## Anotaciones estereotipadas 📊

En este proyecto se utilizaron diversas anotaciones para las clases como lo son:

### 1. **`@Entity` 📦**  
La anotación `@Entity` marca una clase como una **entidad JPA**, lo que significa que esa clase será mapeada a una tabla en la base de datos. Cada entidad debe tener al menos un campo que se identifique como la **clave primaria**

---

### 2. **`@Table` 🏷️**  
La anotación `@Table` se utiliza para **especificar la tabla de la base de datos** con la que se va a mapear la entidad.

---

### 3. **`@Id` 🔑**  
Con `@Id` se indica el campo que es la **clave primaria** de la entidad. Esto permite que JPA sepa cómo identificar de manera única cada instancia de la entidad en la base de datos.

---

### 4. **`@GeneratedValue` ⚙️**  
La anotación `@GeneratedValue` define la **estrategia de generación de valores** para la clave primaria. 

---

### 5. **`@Column` 📝**  
`@Column` especifica el **mapeo de una columna en la tabla de la base de datos**.

---

### 6. **`@OneToMany` 🔗**  
La anotación `@OneToMany` define una relación **uno a muchos** entre dos entidades. Indica que una entidad está asociada a muchas instancias de otra entidad, como un autor que tiene muchos libros.

---

### 7. **`@ManyToOne` 🔄**  
La anotación `@ManyToOne` establece una relación **muchos a uno** entre dos entidades. Es el opuesto de `@OneToMany`. Se utiliza cuando varias instancias de una entidad están asociadas a una sola instancia de otra entidad. Por ejemplo, muchos libros pueden estar asociados a un solo autor.

---

### 8. **`@Component` 🛠️**  
Marca una clase como un componente gestionado por Spring, que puede ser utilizado en cualquier parte de la aplicación para representar un objeto que Spring administrará.

---

### 9. **`@Repository` 🗂️**  
Indica que una clase es un repositorio de acceso a datos, es decir, que interactúa con una base de datos, y ayuda a manejar excepciones relacionadas con la persistencia.
---
### 10. **`@EnableWebSecurity` 🔐**  
Habilita la configuración de seguridad web en una aplicación Spring, permitiendo que se configure la protección de las rutas y la autenticación.

---

### 11. **`@Configuration` ⚙️**  
Indica que una clase contiene configuración para la aplicación, como la definición de beans que Spring debe gestionar, esencial para el contexto de la aplicación.
---
### 12. **`@Autowired` 🤖**  
Permite que Spring inyecte automáticamente una dependencia en una clase, ya sea un servicio, repositorio, entre otros, para simplificar la gestión de componentes.

---

### 13. **`@Service` 💼**  
Marca una clase como un servicio que contiene la lógica de negocio de la aplicación, generalmente usada para interactuar con los repositorios o realizar operaciones principales.

---

### 14. **`@PostMapping` 📤**  
Define una ruta HTTP POST en el controlador, utilizada para recibir solicitudes que envíen datos al servidor, como para crear un nuevo recurso.

---

### 15. **`@GetMapping` 📤**  
Define una ruta HTTP GET en el controlador, utilizada para manejar solicitudes que recuperan datos del servidor, comúnmente usada para leer o mostrar recursos.

---

### 16. **`@DeleteMapping` 🗑️**  
Define una ruta HTTP DELETE en el controlador, usada para eliminar un recurso en el servidor, generalmente identificando el recurso a través de su ID.

---

### 17. **`@PutMapping` 🔄**  
Define una ruta HTTP PUT en el controlador, usada para actualizar un recurso en el servidor, reemplazando completamente el recurso con los nuevos datos proporcionados.

---

### 18. **`@Transactional` 💡**  
Indica que los métodos dentro de una clase deben ser ejecutados dentro de una transacción, garantizando que todas las operaciones en el método sean atómicas y consistentes. 

---

<h1 style="text-align: left;">Desarrollado por 🎓</h1>
**Luis Felipe Méndez González 📠**  

---

<h1 style="text-align: left;">Validaciones ✅</h1>
El programa cuenta con validaciones para el correcto funcionamiento del mismo, no se podrán ingresar usuarios los cuales ya esten registrados con el mismo nombre (login) o correo electronico, esto aplica tambien cuando se quiere actualizar un usuario, además, no se podrán listar
usuarios que no existan mediante su id o nombre de usuario. Tampoco se podrán ingresar topicos con cursos o usuarios que no existen, además de que no se podrá ingresar el mismo titulo y mensaje para evitar spam (aplica para su actualización también). No se podrán listar tópicos 
que no se encuentren en la base de datos. Por último, no se podrán registrar respuestas las cuales no tengan un topico existente o un usuario al que vincular la respuesta, ademas de que tampoco se listarán respuestas inexistentes por su id.


<h1 style="text-align: left;">Capturas del funcionamientos 🎓</h1>
Aqui se verá como funciona el programa y que hace cada opción

<h1 style="text-align: left;">Usuarios🎓</h1>

**`Login y obtención de token` ✅**  
![loginUsuario](https://github.com/user-attachments/assets/14ab5975-ec28-4c21-8e0b-68b87c14de33)

**`Registrar usuario` ✅**  
![RegistrarUsuario](https://github.com/user-attachments/assets/38667df4-8088-44df-8a0d-3884de6c21de)

**`Listar usuario` ✅**  
![listarUsuarios](https://github.com/user-attachments/assets/c4748bd4-df34-44b6-be05-06a897841ca2)

**`Actualizar usuario` ✅**  
![actualizarUsuario](https://github.com/user-attachments/assets/3cbe4dbd-4ebb-4481-b9e8-96c8c9af047d)

**`Eliminar usuario` ✅**  
![eliminarUsuario](https://github.com/user-attachments/assets/18c71564-8f13-4765-a2e8-49b809403cd0)

**`Cambios de eliminar y actualizar en listado usuario` ✅**  
![listado con actualizar y eliminarUsuario](https://github.com/user-attachments/assets/4cf738f1-438b-457f-b771-8c5005a1dd81)

---

<h1 style="text-align: left;">Cursos 📊</h1>

**`Registrar curso` ✅**
![registrarCurso](https://github.com/user-attachments/assets/7d880083-3c2d-48ec-84d3-278c3c80c84d)

**`Listar curso` ✅** 
![listarCurso](https://github.com/user-attachments/assets/88be983c-c93d-4507-8709-bed725951d30)

**`Actualizar curso` ✅** 
![actualizarCurso](https://github.com/user-attachments/assets/f0e7c699-55db-49da-a0b3-7227167b477f)

**`Eliminar curso` ✅**  
![eliminarCurso](https://github.com/user-attachments/assets/2967a184-4e0a-443c-ad1c-e6755ded4d66)

**`Cambios de eliminar y actualizar en listado curso` ✅**  
![listadoCUrso con eliminar y actualziar](https://github.com/user-attachments/assets/e30119d2-b781-4959-a98f-58c0803d1191)

---

<h1 style="text-align: left;">Topico 🏷️</h1>

**`Registrar topico` ✅**
![registroTopico](https://github.com/user-attachments/assets/1f975ae5-7d71-406f-91e3-7d7db5f00900)

**`Listar topico` ✅** 
![listadoTopico](https://github.com/user-attachments/assets/0beb6a63-90b0-40ce-a226-bf5dcca839a7)

**`Actualizar topico titulo` ✅** 
![actualizarTitulo](https://github.com/user-attachments/assets/354f3695-2b43-4453-8293-503182288a5e)

**`Actualizar topico mensaje` ✅**   
![actualizarMensaje](https://github.com/user-attachments/assets/c9ed58fb-44ee-4cdd-909c-ebf3991e51da)

**`Eliminar topico` ✅**  
![eliminarTopico](https://github.com/user-attachments/assets/5a3d572d-3a72-4a03-9f77-1a871bde14c2)

**`Cambios de eliminar y actualizar en listado topico` ✅**  
![listado con eliminar y actualizar](https://github.com/user-attachments/assets/f6bfc81e-70de-44d5-a2a2-3caae8e83dba)

---

<h1 style="text-align: left;">Respuesta ✅</h1>

**`Registrar respuesta` ✅**
![registrarRespuesta](https://github.com/user-attachments/assets/614481eb-365f-43d5-bb67-3ac4dab7db65)

**`Listar respuesta` ✅** 
![listarRespuesta](https://github.com/user-attachments/assets/9b946672-460e-4b7f-a9b9-ee663cdab573)

**`Eliminar respuesta` ✅**  
![eliminarRespuesta](https://github.com/user-attachments/assets/bde15cc9-0594-4d2f-ad64-b64bb8f9ac89)

**`Cambios de eliminar en listado respuesta` ✅**
![listar con eliminar](https://github.com/user-attachments/assets/826d241c-e0e3-459d-9a2d-b263cbbb9123)

---
