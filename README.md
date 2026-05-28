# Habit Tracker API 🚀

API REST para la aplicación Habit Tracker. Permite gestionar hábitos, usuarios y seguimiento diario mediante endpoints seguros y organizados.

---

## 🚀 Características

- 🔐 Autenticación de usuarios
- ✅ CRUD de hábitos
- 📅 Seguimiento diario
- 🌐 API REST
- 🔄 Comunicación con frontend React
- 🗄️ Persistencia de datos

---

## 🛠️ Tecnologías utilizadas

- Node.js
- Express.js
- MongoDB / PostgreSQL
- JWT
- Mongoose *(si lo usaste)*
- dotenv

---

## 📂 Estructura del proyecto

```bash
habittracker-api/
│
├── controllers/
├── routes/
├── models/
├── middleware/
├── config/
├── package.json
└── server.js
```

---

## ⚙️ Instalación

Clonar repositorio:

```bash
git clone https://github.com/JoseRamirezkqa/habittracker-api.git
```

Entrar al proyecto:

```bash
cd habittracker-api
```

Instalar dependencias:

```bash
npm install
```

---

## 🔑 Variables de entorno

Crear archivo `.env`

```env
PORT=3000
MONGO_URI=tu_uri
JWT_SECRET=tu_secret
```

---

## ▶️ Ejecución

Iniciar servidor:

```bash
npm run dev
```

Servidor disponible en:

```txt
http://localhost:3000
```

---

## 📡 Endpoints principales

| Método | Endpoint | Descripción |
|---|---|---|
| GET | /habits | Obtener hábitos |
| POST | /habits | Crear hábito |
| PUT | /habits/:id | Actualizar hábito |
| DELETE | /habits/:id | Eliminar hábito |

---

## 🔗 Frontend relacionado

El frontend de este proyecto se encuentra en:

https://github.com/JoseRamirezkqa/habittracker-frontend

---

## 🏗️ Arquitectura

```txt
Frontend React → API REST Express → Base de Datos
```

---

## 🎯 Objetivo del proyecto

Desarrollar una API robusta para la gestión de hábitos y seguimiento de progreso utilizando tecnologías backend modernas.

---

## 👨‍💻 Autor

- Jose Ramirez Cacua
- Stiven Rey
- María José Hernández


---

## 📄 Licencia

Proyecto de uso académico y educativo.
