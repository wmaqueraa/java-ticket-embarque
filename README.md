# ✈️ Sistema de Ticket de Embarque Electrónico

## Descripción General

El sistema de **Ticket de Embarque Electrónico** es una solución para aerolíneas que permite gestionar el proceso de check-in y emisión de tarjetas de embarque a través de tres canales: **online**, **en aeropuerto** y **en kiosco**. El sistema garantiza la asignación de asientos, generación de códigos QR y entrega digital o física del ticket.

---

## 📦 Modelo de Dominio — Clases Principales

### 🏢 Aerolínea (`Aerolinea`)
Representa a la empresa operadora del vuelo.

| Campo | Tipo | Descripción |
|---|---|---|
| `id` | UUID | Identificador único |
| `nombre` | String | Nombre comercial (ej. LATAM, Avianca) |
| `codigoIata` | String | Código IATA de 2 letras (ej. LA, AV) |
| `pais` | String | País de origen |
| `logoUrl` | String | URL del logo oficial |

---

### 🛫 Aeropuerto (`Aeropuerto`)
Representa el punto de origen o destino del vuelo.

| Campo | Tipo | Descripción |
|---|---|---|
| `id` | UUID | Identificador único |
| `nombre` | String | Nombre del aeropuerto |
| `codigoIata` | String | Código IATA de 3 letras (ej. LIM, BOG) |
| `ciudad` | String | Ciudad donde se ubica |
| `pais` | String | País donde se ubica |
| `terminal` | String | Terminal de operación |

---

### ✈️ Avión (`Avion`)
Representa la aeronave asignada a un vuelo.

| Campo | Tipo | Descripción |
|---|---|---|
| `id` | UUID | Identificador único |
| `modelo` | String | Modelo de la aeronave (ej. Boeing 737) |
| `matricula` | String | Matrícula de la aeronave |
| `capacidadTotal` | int | Total de asientos disponibles |
| `aerolineaId` | UUID | Referencia a la aerolínea propietaria |

---

### 🪑 Asiento (`Asiento`)
Representa cada asiento físico dentro de un avión.

| Campo | Tipo | Descripción |
|---|---|---|
| `id` | UUID | Identificador único |
| `avionId` | UUID | Referencia al avión al que pertenece |
| `numeroAsiento` | NumeroAsiento | Código del asiento (ej. 12A, 14C) |
| `clase` | ClaseAsiento | ECONOMICA, EJECUTIVA, PRIMERA |
| `tipoAsiento` | TipoAsiento | VENTANA, PASILLO, CENTRO |
| `isOcupado` | boolean | `true` si ya fue asignado en el vuelo actual |

---

### 🎟️ Boleto (`Boleto`)
Representa el derecho de un pasajero a abordar un vuelo específico.

| Campo | Tipo | Descripción |
|---|---|---|
| `id` | UUID | Identificador único |
| `codigoReserva` | String | Referencia a la reserva madre |
| `idPasajero` | UUID | Pasajero titular del boleto |
| `idVuelo` | UUID | Vuelo al que corresponde |
| `tipEquipaje` | TipoEquipaje | BOLSO, MANO, MALETA |
| `estado` | EstadoBoleto | EMITIDO, CONFIRMADO, ANULADO |
| `precio` | BigDecimal | Precio pagado por el boleto |

---

### 👤 Pasajero (`Pasajero`)
Representa a la persona que viaja en el vuelo.

| Campo | Tipo | Descripción |
|---|---|---|
| `id` | UUID | Identificador único |
| `nombre` | String | Nombre(s) del pasajero |
| `apellido` | String | Apellido(s) del pasajero |
| `dni` | String | Documento Nacional de Identidad |
| `pasaporte` | String | Número de pasaporte (viajes internacionales) |
| `email` | String | Correo electrónico de contacto |
| `telefono` | String | Teléfono de contacto |
| `fechaNacimiento` | LocalDate | Fecha de nacimiento |
| `nacionalidad` | String | País de nacionalidad |

---

### 🛩️ Vuelo (`Vuelo`)
Representa un vuelo programado entre dos aeropuertos.

| Campo | Tipo | Descripción |
|---|---|---|
| `id` | UUID | Identificador único |
| `codigoVuelo` | String | Código oficial del vuelo (ej. LA2045) |
| `aerolineaId` | UUID | Aerolínea que opera el vuelo |
| `avionId` | UUID | Avión asignado al vuelo |
| `aeropuertoOrigenId` | UUID | Aeropuerto de salida |
| `aeropuertoDestinoId` | UUID | Aeropuerto de llegada |
| `fechaSalida` | LocalDateTime | Fecha y hora de salida programada |
| `fechaLlegada` | LocalDateTime | Fecha y hora de llegada estimada |
| `puertaEmbarque` | String | Puerta asignada (ej. B12) |
| `esInternacional` | Boolean | `true` si cruza fronteras internacionales |
| `estado` | EstadoVuelo | PROGRAMADO, EN_VUELO, ATERRIZADO, CANCELADO |

---

### 📋 Reserva (`Reserva`)
Representa la reserva de vuelo realizada por el pasajero.

| Campo | Tipo | Descripción |
|---|---|---|
| `id` | UUID | Identificador único |
| `codigoReserva` | String | Código alfanumérico único (ej. RES-2024-001) |
| `fechaReserva` | LocalDateTime | Fecha y hora de creación |
| `estadoReserva` | EstadoReserva | PENDIENTE, CONFIRMADA, CANCELADA |
| `pasajeroId` | UUID | Referencia al pasajero titular |
| `vueloId` | UUID | Referencia al vuelo reservado |

---

### 📄 Detalle de Reserva (`DetalleReserva`)
Representa los datos específicos del check-in por pasajero.

| Campo | Tipo | Descripción |
|---|---|---|
| `id` | UUID | Identificador único |
| `reservaId` | UUID | Referencia a la reserva |
| `asientoId` | UUID | Asiento asignado |
| `estadoCheckin` | EstadoCheckIn | PENDIENTE, REALIZADO, OMITIDO |
| `fechaCheckin` | LocalDateTime | Fecha y hora del check-in |

---

### 🎫 Ticket de Embarque (`TicketEmbarque`)
Representa la tarjeta de embarque generada tras el check-in.

| Campo | Tipo | Descripción |
|---|---|---|
| `id` | UUID | Identificador único |
| `numeroTicket` | String | Número secuencial del ticket (ej. TK-A1B2C3-001) |
| `vueloId` | UUID | Referencia al vuelo |
| `puertaEmbarque` | String | Puerta de embarque asignada (ej. B12) |
| `horaEmbarque` | LocalDateTime | Hora de inicio de embarque |
| `grupoEmbarque` | String | Grupo de embarque (ej. Grupo 1, Grupo 2) |
| `numeroAsiento` | NumeroAsiento | Asiento asignado (ej. 12A) |
| `estado` | EstadoTicket | ACTIVO, USADO, ANULADO |
| `codigoQR` | String | QR en Base64 para validación en puerta |

---

## 🧩 Casos de Uso del Sistema

### 📌 CU-01: Registrar Aerolínea (`RegistrarAerolineaUseCase`)
**Actor:** Administrador del sistema  
**Descripción:** Permite registrar una nueva aerolínea en el sistema con sus datos oficiales.

**Flujo principal:**
1. El administrador proporciona nombre, código IATA, país y logo
2. El sistema valida que el código IATA no esté registrado (unicidad)
3. Se persiste la aerolínea con estado ACTIVO
4. El sistema retorna la aerolínea creada con su ID generado

**Validaciones:**
- Código IATA debe ser exactamente 2 letras mayúsculas
- Nombre no puede estar vacío
- No se permiten códigos IATA duplicados

**Endpoint:** `POST /api/v1/aerolineas`

```bash
curl --location 'http://localhost:8000/api/v1/aerolineas' \
--header 'Content-Type: application/json' \
--data '{
    "nombre": "LATAM Airlines",
    "codigoIata": "LA",
    "pais": "Peru",
    "logoUrl": "https://latam.com/logo.png"
}'
```

---

### 📌 CU-02: Registrar Aeropuerto (`RegistrarAeropuertoUseCase`)
**Actor:** Administrador del sistema  
**Descripción:** Permite registrar un aeropuerto con su código IATA, ciudad y terminal.

**Flujo principal:**
1. El administrador proporciona nombre, código IATA, ciudad, país y terminal
2. El sistema valida unicidad del código IATA
3. Se persiste el aeropuerto en la base de datos
4. El sistema retorna el aeropuerto creado

**Validaciones:**
- Código IATA debe ser exactamente 3 letras mayúsculas (ej. LIM, BOG, MIA)
- Ciudad y país son obligatorios
- No se permiten códigos IATA duplicados

**Endpoint:** `POST /api/v1/aeropuertos`

```bash
curl --location 'http://localhost:8000/api/v1/aeropuertos' \
--header 'Content-Type: application/json' \
--data '{
    "nombre": "Aeropuerto Internacional Jorge Chavez",
    "codigoIata": "LIM",
    "ciudad": "Lima",
    "pais": "Peru",
    "terminal": "Terminal Internacional"
}'
```

---

### 📌 CU-03: Registrar Pasajero (`RegistrarPasajeroUseCase`)
**Actor:** Agente de aerolínea / Pasajero (autoregistro)  
**Descripción:** Permite registrar a un pasajero con sus datos personales y de identidad.

**Flujo principal:**
1. Se reciben los datos del pasajero (nombre, apellido, DNI, email, etc.)
2. El sistema valida que el DNI o pasaporte no esté ya registrado
3. Se calcula la categoría del pasajero (adulto, menor, infante) según fecha de nacimiento
4. Se persiste el pasajero
5. El sistema retorna el pasajero creado con su ID

**Validaciones:**
- DNI o pasaporte es obligatorio (al menos uno)
- Email debe tener formato válido
- Fecha de nacimiento no puede ser futura
- Nombre y apellido no pueden estar vacíos

**Endpoint:** `POST /api/v1/pasajeros`

```bash
curl --location 'http://localhost:8000/api/v1/pasajeros' \
--header 'Content-Type: application/json' \
--data '{
    "nombre": "Carlos",
    "apellido": "Mendoza",
    "dni": "12345678",
    "email": "carlos.mendoza@email.com",
    "telefono": "987654321",
    "fechaNacimiento": "1990-05-15",
    "nacionalidad": "Peruana"
}'
```

---

### 📌 CU-04: Registrar Vuelo (`RegistrarVueloUseCase`)
**Actor:** Operador de aerolínea  
**Descripción:** Permite programar un nuevo vuelo asignando aerolínea, avión, aeropuertos y horarios.

**Flujo principal:**
1. El operador proporciona código de vuelo, aerolínea, avión, origen, destino y horarios
2. El sistema valida que el avión no tenga otro vuelo en el mismo horario (conflicto)
3. El sistema determina si es vuelo internacional según los aeropuertos
4. Se crean automáticamente los asientos del avión para este vuelo
5. Se persiste el vuelo con estado PROGRAMADO
6. El sistema retorna el vuelo creado

**Validaciones:**
- La fecha de salida debe ser futura
- La fecha de llegada debe ser posterior a la de salida
- El avión no puede estar asignado a otro vuelo en el mismo rango horario
- Aeropuerto origen y destino no pueden ser iguales

**Endpoint:** `POST /api/v1/vuelos`

```bash
curl --location 'http://localhost:8000/api/v1/vuelos' \
--header 'Content-Type: application/json' \
--data '{
    "codigoVuelo": "LA2045",
    "aerolineaId": "550e8400-e29b-41d4-a716-446655440000",
    "avionId": "660e9500-f30c-52e5-b827-557766551111",
    "aeropuertoOrigenId": "770fa600-g41d-63f6-c938-668877662222",
    "aeropuertoDestinoId": "880gb700-h52e-74g7-d049-779988773333",
    "fechaSalida": "2024-12-15T08:00:00",
    "fechaLlegada": "2024-12-15T10:30:00",
    "puertaEmbarque": "B12",
    "esInternacional": false
}'
```

---

### 📌 CU-05: Registrar Reserva (`RegistrarReservaUseCase`)
**Actor:** Pasajero / Agente de aerolínea  
**Descripción:** Permite crear una reserva de vuelo para uno o más pasajeros, generando los boletos correspondientes.

**Flujo principal:**
1. El pasajero selecciona vuelo, tipo de equipaje y proporciona su ID
2. El sistema verifica disponibilidad de asientos en el vuelo
3. Se genera un código de reserva único alfanumérico
4. Se crean los boletos por cada pasajero incluido en la reserva
5. Se persiste la reserva con estado PENDIENTE
6. El sistema retorna la reserva con el código generado y los boletos

**Validaciones:**
- El vuelo debe existir y estar en estado PROGRAMADO
- Debe haber asientos disponibles para la cantidad de pasajeros
- El pasajero no puede tener ya una reserva activa en el mismo vuelo
- El tipo de equipaje es obligatorio por boleto

**Endpoint:** `POST /api/v1/reservas`

```bash
curl --location 'http://localhost:8000/api/v1/reservas' \
--header 'Content-Type: application/json' \
--data '{
    "vueloId": "550e8400-e29b-41d4-a716-446655440000",
    "pasajeroId": "660e9500-f30c-52e5-b827-557766551111",
    "tipEquipaje": "MANO",
    "precio": 250.00
}'
```

---

### 📌 CU-06: Realizar Check-In Online (`RealizarCheckInOnlineUseCase`)
**Actor:** Pasajero  
**Descripción:** Permite al pasajero confirmar su asistencia al vuelo y obtener su ticket de embarque digital.

**Flujo principal:**
1. Pasajero ingresa código de reserva y datos del avión
2. Sistema valida la reserva y obtiene boletos asociados
3. Por cada boleto: asigna asiento, actualiza estado y genera QR
4. Crea los tickets de embarque con puerta, hora y grupo
5. Persiste todo en lote y retorna la lista de tickets

**Endpoint:** `POST /api/v1/ticketembarque`

---

## 📊 Diagrama de Casos de Uso

```
┌─────────────────────────────────────────────────────────────────────┐
│               SISTEMA DE TICKET DE EMBARQUE ELECTRÓNICO             │
│                                                                     │
│  ┌─────────────────────────────────────────────────────────────┐    │
│  │                   ADMINISTRADOR                             │    │
│  │                        │                                   │    │
│  │          ┌─────────────┼──────────────┐                    │    │
│  │          │             │              │                    │    │
│  │   ┌──────▼──────┐ ┌────▼────┐ ┌──────▼──────┐            │    │
│  │   │ Registrar   │ │Registrar│ │  Registrar  │            │    │
│  │   │ Aerolínea   │ │Aeropuerto│ │   Vuelo    │            │    │
│  │   │  (CU-01)    │ │ (CU-02) │ │  (CU-04)   │            │    │
│  │   └─────────────┘ └─────────┘ └─────────────┘            │    │
│  └─────────────────────────────────────────────────────────────┘    │
│                                                                     │
│  ┌─────────────────────────────────────────────────────────────┐    │
│  │                     PASAJERO                                │    │
│  │                        │                                   │    │
│  │     ┌──────────────────┼──────────────────┐               │    │
│  │     │                  │                  │               │    │
│  │ ┌───▼────┐      ┌──────▼──────┐   ┌───────▼──────┐       │    │
│  │ │Registrar│     │  Registrar  │   │  Check-In    │       │    │
│  │ │Pasajero │     │   Reserva   │   │  Online      │       │    │
│  │ │ (CU-03) │     │   (CU-05)   │   │  (CU-06)     │       │    │
│  │ └─────────┘     └─────────────┘   └──────────────┘       │    │
│  └─────────────────────────────────────────────────────────────┘    │
│                                                                     │
│  ┌─────────────────────────────────────────────────────────────┐    │
│  │                  AGENTE / KIOSCO                            │    │
│  │                        │                                   │    │
│  │          ┌─────────────┴──────────────┐                    │    │
│  │          │                            │                    │    │
│  │  ┌───────▼────────┐         ┌─────────▼──────┐            │    │
│  │  │  Check-In en   │         │  Check-In en   │            │    │
│  │  │  Aeropuerto    │         │    Kiosco      │            │    │
│  │  └────────────────┘         └────────────────┘            │    │
│  └─────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 🔄 Canales de Check-In

### 1. 🌐 Check-In Online (`checkinWeb`)
- **Disponibilidad:** 48 a 2 horas antes del vuelo
- **Endpoint:** `POST /api/v1/ticketembarque`
- **Flujo:**
    1. Pasajero ingresa código de reserva en la web o app
    2. El sistema valida la reserva y los boletos asociados
    3. Se asigna asiento aleatorio o preferido
    4. Se genera el ticket con código QR
    5. El ticket se entrega digitalmente (PDF, email, app)

### 2. 🏢 Check-In en Aeropuerto (`checkinPresencial`)
- **Disponibilidad:** 3 horas a 45 minutos antes del vuelo
- **Flujo:**
    1. Pasajero se acerca al mostrador de la aerolínea
    2. El agente busca la reserva por código o DNI
    3. Se registra equipaje de bodega si corresponde
    4. Se imprime el ticket físico en papel térmico
    5. Se entrega ticket físico al pasajero

### 3. 🖥️ Check-In en Kiosco (`checkinKiosco`)
- **Disponibilidad:** 3 horas a 45 minutos antes del vuelo
- **Flujo:**
    1. Pasajero escanea pasaporte o ingresa código de reserva
    2. El kiosco valida la identidad del pasajero
    3. El pasajero selecciona o acepta el asiento asignado
    4. El kiosco imprime el ticket físico al instante
    5. Opcionalmente envía el ticket digital al email

---

## ⏱️ Tiempos de Embarque por Tipo de Vuelo

| Tipo de Vuelo | Inicio Embarque | Cierre Embarque |
|---|---|---|
| **Vuelo Nacional** | 35 min antes de salida | 5 min antes de salida |
| **Vuelo Internacional** | 55 min antes de salida | 10 min antes de salida |

---

## 📦 Tipos de Equipaje Permitidos

| Tipo | Descripción | Dimensiones | Peso máx. |
|---|---|---|---|
| `BOLSO` | Bolso o mochila personal | Sin restricción fija | 5 kg |
| `MANO` | Maleta de mano | 36x19x53 cm | 10 kg |
| `MALETA` | Maleta de bodega | 41x27x65 cm | 23 kg |

---

## 🔁 Diagrama de Flujo — Generación de Ticket de Embarque

```
┌─────────────────────────────────────────────────────────────────┐
│                    INICIO DEL CHECK-IN                          │
└──────────────────────────┬──────────────────────────────────────┘
                           │
              ┌────────────▼────────────┐
              │   ¿Canal de Check-In?   │
              └────────────┬────────────┘
         ┌─────────────────┼─────────────────┐
         │                 │                 │
    ┌────▼────┐       ┌────▼────┐      ┌────▼────┐
    │ ONLINE  │       │AEROPUERTO│     │ KIOSCO  │
    │  Web/App│       │Mostrador │     │Terminal │
    └────┬────┘       └────┬────┘      └────┬────┘
         │                 │                 │
         └─────────────────▼─────────────────┘
                           │
              ┌────────────▼────────────┐
              │  Ingresar Código de     │
              │       Reserva           │
              └────────────┬────────────┘
                           │
              ┌────────────▼────────────┐
              │  ¿Reserva válida y      │
              │    confirmada?          │
              └────────────┬────────────┘
                    │             │
                   SÍ            NO
                    │             │
                    │    ┌────────▼────────┐
                    │    │ ERROR: Reserva  │
                    │    │  no encontrada  │
                    │    └─────────────────┘
                    │
       ┌────────────▼────────────┐
       │  Obtener boletos        │
       │  asociados a reserva    │
       └────────────┬────────────┘
                    │
       ┌────────────▼────────────┐
       │  ¿Existen boletos?      │
       └────────────┬────────────┘
              │           │
             SÍ           NO
              │           │
              │   ┌───────▼───────┐
              │   │ ERROR: Sin    │
              │   │  boletos      │
              │   └───────────────┘
              │
       ┌──────▼──────────────────┐
       │  Obtener asientos       │
       │  disponibles del avión  │
       └────────────┬────────────┘
                    │
       ┌────────────▼────────────┐
       │  Por cada boleto:       │
       │  ┌─────────────────┐    │
       │  │ Cambiar estado  │    │
       │  │ boleto →        │    │
       │  │ CONFIRMADO      │    │
       │  └────────┬────────┘    │
       │           │             │
       │  ┌────────▼────────┐    │
       │  │ Asignar asiento │    │
       │  │ aleatorio o     │    │
       │  │ preferido       │    │
       │  └────────┬────────┘    │
       │           │             │
       │  ┌────────▼────────┐    │
       │  │ Crear DetalleRe-│    │
       │  │ serva con estado│    │
       │  │ REALIZADO       │    │
       │  └────────┬────────┘    │
       │           │             │
       │  ┌────────▼────────┐    │
       │  │ Generar código  │    │
       │  │ QR con datos:   │    │
       │  │ RESERVA|VUELO   │    │
       │  │ |ASIENTO        │    │
       │  └────────┬────────┘    │
       │           │             │
       │  ┌────────▼────────┐    │
       │  │ Crear Ticket de │    │
       │  │ Embarque con:   │    │
       │  │ - NumeroTicket  │    │
       │  │ - Puerta        │    │
       │  │ - Hora embarque │    │
       │  │ - Grupo         │    │
       │  └────────┬────────┘    │
       └────────────┬────────────┘
                    │
       ┌────────────▼────────────┐
       │  Persistir en lote:     │
       │  - saveAll(boletos)     │
       │  - saveAll(tickets)     │
       └────────────┬────────────┘
                    │
       ┌────────────▼────────────┐
       │  ¿Canal de entrega?     │
       └────────────┬────────────┘
         ┌──────────┼──────────┐
         │          │          │
    ┌────▼────┐ ┌───▼────┐ ┌──▼──────┐
    │DIGITAL  │ │IMPRESO │ │ AMBOS   │
    │PDF/Email│ │Papel   │ │         │
    │App móvil│ │Térmico │ │         │
    └────┬────┘ └───┬────┘ └──┬──────┘
         └──────────┴──────────┘
                    │
       ┌────────────▼────────────┐
       │  TICKET ENTREGADO ✅    │
       │  Pasajero listo para    │
       │  abordar                │
       └─────────────────────────┘
```

---

## 🏗️ Arquitectura del Sistema — Clean Architecture

```
java-ticket-embarque/
├── domain/                          # Núcleo de negocio
│   ├── model/
│   │   ├── entity/                  # Entidades del dominio
│   │   │   ├── Aerolinea.java
│   │   │   ├── Aeropuerto.java
│   │   │   ├── Avion.java
│   │   │   ├── Asiento.java
│   │   │   ├── Boleto.java
│   │   │   ├── DetalleReserva.java
│   │   │   ├── Pasajero.java
│   │   │   ├── Reserva.java
│   │   │   ├── TicketEmbarque.java
│   │   │   └── Vuelo.java
│   │   └── valueobject/             # Value Objects
│   │       ├── NumeroAsiento.java
│   │       ├── EstadoReserva.java
│   │       ├── EstadoCheckIn.java
│   │       ├── EstadoTicket.java
│   │       ├── EstadoBoleto.java
│   │       ├── TipoEquipaje.java
│   │       ├── ClaseAsiento.java
│   │       └── TipoAsiento.java
│   ├── repository/                  # Puertos (interfaces)
│   │   ├── BoletoRepository.java
│   │   ├── ReservaRepository.java
│   │   ├── AsientoRepository.java
│   │   ├── PasajeroRepository.java
│   │   ├── VueloRepository.java
│   │   ├── DetalleReservaRepository.java
│   │   └── TicketEmbarqueRepository.java
│   └── service/                     # Servicios de dominio
│       ├── AsientoService.java
│       └── VueloService.java
│
├── application/                     # Casos de uso
│   ├── dto/
│   │   ├── command/
│   │   │   └── ConfirmarReservaCommand.java
│   │   └── response/
│   │       └── TicketEmbarqueResponse.java
│   ├── port/in/
│   │   ├── RegistrarAerolineaUseCase.java       # CU-01
│   │   ├── RegistrarAeropuertoUseCase.java      # CU-02
│   │   ├── RegistrarPasajeroUseCase.java        # CU-03
│   │   ├── RegistrarVueloUseCase.java           # CU-04
│   │   ├── RegistrarReservaUseCase.java         # CU-05
│   │   └── RealizarCheckInOnlineUseCase.java    # CU-06
│   └── service/
│       ├── AerolineaApplicationService.java
│       ├── AeropuertoApplicationService.java
│       ├── PasajeroApplicationService.java
│       ├── VueloApplicationService.java
│       ├── ReservaApplicationService.java
│       └── TicketEmbarqueApplicationService.java
│
└── infrastructure/                  # Adaptadores
    ├── adapter/in/web/
    │   └── TicketEmbarqueController.java
    └── persistence/
        ├── JpaBoletoRepository.java
        ├── BoletoRepositoryImpl.java
        ├── JpaReservaRepository.java
        ├── ReservaRepositoryImpl.java
        └── ...
```

---

## 🚀 Ejecución del Proyecto

### Requisitos previos
- Java 17+
- Maven 3.8+
- PostgreSQL 14+

### Configuración `application-dev.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ticket_electronico
spring.datasource.username=postgres
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8000
```

### Comandos
```bash
# Compilar
mvn clean compile

# Ejecutar
mvn spring-boot:run

# Generar JAR
mvn clean package
java -jar target/java-ticket-embarque-1.0-SNAPSHOT.jar
```

### Probar la API
```bash
curl --location 'http://localhost:8000/api/v1/ticketembarque' \
--header 'Content-Type: application/json' \
--data '{
    "codigoReserva": "RES-2024-001",
    "fechaConfirmar": "2024-12-15T10:30:00",
    "avionId": "550e8400-e29b-41d4-a716-446655440000",
    "asientoAleatorio": true
}'
```

### Documentación Swagger
```
http://localhost:8000/swagger-ui/index.html
```

---

## 📌 Estados del Sistema

```
RESERVA:    PENDIENTE ──► CONFIRMADA ──► CANCELADA
BOLETO:     EMITIDO   ──► CONFIRMADO ──► ANULADO
CHECKIN:    PENDIENTE ──► REALIZADO  ──► OMITIDO
TICKET:     ACTIVO    ──► USADO      ──► ANULADO
```

---

## 👨‍💻 Autor

**WILMER JOSE MAQUERA AQUISE** — TECSUP  
Proyecto: Arquitectura Limpia con Spring Boot  
Semana 03 — Clean Architecture

---

*Generado con Java 17 · Spring Boot 3.2.0 · PostgreSQL · Hibernate 6.3*