#  AppRutinas - Aplicación de Entrenamiento Personalizado

**AppRutinas** es una aplicación móvil nativa para Android desarrollada en Java. Está diseñada para facilitar la organización, personalización y el seguimiento de ejercicios diarios, funcionando de manera autónoma y sin requerir conexión a internet.

---

##  Características Principales

* **Control de Acceso:** Validación de credenciales locales en RAM con soporte para cuentas **Gratuitas** y **Premium**.
* **Dashboard Principal:** Visualización de la fecha actual y acceso rápido a rutinas del día.
* **Personalización:** Configuración de métricas corporales (peso, altura) y objetivos de entrenamiento mediante componentes interactivos (`Spinner` y `SeekBar`).
* **Seguimiento Semanal:** Consulta de ejercicios por días de la semana y control de avance con casillas de verificación (`CheckBox`).
* **Reglas de Negocio:** Restricción o liberación de niveles de intensidad según el tipo de cuenta activa.

---

##  Tecnologías y Herramientas Utilizadas

* **Lenguaje:** Java (JDK 17+)
* **Entorno de Desarrollo:** Android Studio
* **Plataforma:** Android SDK Platform
* **Estructura de Datos:** Colecciones Java (`ArrayList`) para simulación de datos en memoria RAM (`UserDatabase`)
* **Diseño UI:** Interfaces nativas XML

---

##  Estructura del Proyecto

app/src/main/
├── java/com/sise/apprutinas/
│   ├── activity/          # Controladores de pantalla (Home, Login, Register, etc.)
│   ├── model/             # Modelo de datos (Ejercicio)
│   ├── network/           # Consumo de API
│   ├── utils/             # Clases auxiliares y listas precargadas (Rutinas)
└── res/                   # Diseños XML y recursos gráficos

---

###  Credenciales de Prueba

Para probar las funcionalidades de la aplicación sin registrarte, puedes usar las siguientes cuentas precargadas:

| Tipo de Cuenta | Correo Electrónico | Contraseña |
| :--- | :--- | :--- |
| **Premium** | `premium@test.com` | `123456` |
| **Gratuito** | `gratuito@test.com` | `123456` |

---

##  Documentación del Proyecto

Toda la documentación técnica del sistema, el informe académico final y las diapositivas de presentación se encuentran alojados en la carpeta [`/docs`](./docs/):

 Archivos en formato `.pdf` para visualización directa desde la interfaz web de GitHub.

---

##  Equipo de Trabajo

* **Grupo:** N° 6
* **Curso:** Desarrollo de Aplicaciones Móviles

###  Integrantes
* **Campos Arias Camila Nicole** 
* **Anyoza Joel Andre** 
* **Yata Pinaud Belinda Adela** 
