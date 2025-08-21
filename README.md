# Laboratorio 02: Auditoría Móvil con Herramientas SAST

Repositorio para el desarrollo del Laboratorio 02 del curso de **Auditoría de Sistemas** de la Universidad Privada de Tacna. El objetivo de este laboratorio es realizar un análisis estático de seguridad (SAST) a aplicaciones Android (APK) utilizando una herramienta contenerizada con Docker.

![UPT](https://img.shields.io/badge/Universidad-Privada%20de%20Tacna-blue)
![Curso](https://img.shields.io/badge/Curso-Auditoría%20de%20Sistemas-red)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Django](https://img.shields.io/badge/Django-092E20?style=for-the-badge&logo=django&logoColor=white)

---

## Tabla de Contenidos
1. [Descripción del Proyecto](#descripción-del-proyecto)
2. [Objetivos del Laboratorio](#objetivos-del-laboratorio)
3. [Tecnologías Utilizadas](#tecnologías-utilizadas)
4. [Puesta en Marcha del Entorno](#puesta-en-marcha-del-entorno)
5. [Resultados del Análisis](#resultados-del-análisis)
6. [Informe Completo](#informe-completo)
7. [Conclusiones Clave](#conclusiones-clave)
8. [Autor y Docente](#autor-y-docente)

---

## Descripción del Proyecto
Este proyecto implementa y utiliza la herramienta "Auditoría Móvil", una aplicación web basada en Django que automatiza el análisis de seguridad de archivos APK. La herramienta se despliega en un entorno local utilizando Docker y Docker Compose, lo que garantiza una configuración rápida y reproducible. Se analizaron dos aplicaciones para identificar vulnerabilidades, malas prácticas de seguridad y posibles riesgos asociados.

---

## Objetivos del Laboratorio
- **Identificar** los elementos que participan en la Auditoría de seguridad móvil.
- **Analizar** los posibles riesgos asociados a las vulnerabilidades encontradas en las aplicaciones.
- **Utilizar** una herramienta automatizada (SAST) para la recolección de evidencias.

---

## Tecnologías Utilizadas
- **Docker & Docker Compose:** Para la contenerización y orquestación del entorno de la aplicación de auditoría.
- **Git:** Para el control de versiones y la gestión del código fuente.
- **Herramienta "Auditoría Móvil":** Aplicación basada en Django y Python para el análisis SAST.
- **Archivos APK de Prueba:**
  - `Magis TV`: Aplicación de streaming.
  - `PGSharp`: APK modificado del juego Pokémon GO.

---

## Puesta en Marcha del Entorno
Siga estos pasos para replicar el entorno de laboratorio y ejecutar los análisis.

### Prerrequisitos
- Tener instalado [Git](https://git-scm.com/downloads).
- Tener instalado [Docker Desktop](https://www.docker.com/products/docker-desktop/).

### Pasos de Instalación
1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/J0rgZ/Auditoria-Movil.git
   ```

2. **Navegar al directorio del proyecto:**
   ```bash
   cd Auditoria-Movil
   ```

3. **Construir las imágenes de Docker:**
   Este comando leerá el archivo `docker-compose.yml` y construirá todos los servicios necesarios (web, base de datos, worker, etc.).
   ```bash
   docker-compose build
   ```

4. **Iniciar los contenedores:**
   Este comando iniciará todos los servicios en segundo plano (`-d` significa modo "detached").
   ```bash
   docker-compose up -d
   ```

5. **Verificar que los servicios están en ejecución:**
   ```bash
   docker ps
   ```
   Deberías ver varios contenedores (`mobile_audit_web`, `mobile_audit_db`, etc.) con el estado `Up`.

6. **Acceder a la aplicación:**
   Abre tu navegador web y dirígete a `http://localhost:8888`. Deberás crear una cuenta para empezar a usar la herramienta.

---

## Resultados del Análisis
Se realizaron dos análisis para comparar una aplicación comercial con una aplicación modificada de alto riesgo.

### Caso 1: Análisis de Magis TV
Aplicación de streaming analizada para establecer una línea base de seguridad. La herramienta completó el escaneo y generó un informe detallado que sirvió como punto de comparación.

### Caso 2: Análisis de PGSharp (APK Modificado)
PGSharp es una versión modificada del juego Pokémon GO que introduce funcionalidades no autorizadas. Este tipo de aplicaciones suelen ser un vector de riesgo significativo.

Los resultados fueron alarmantes y confirmaron las sospechas:
- **Hallazgos Críticos: 88**
- **Hallazgos Altos: 2058**
- **Hallazgos Medios: 3876**

Esta cantidad masiva de hallazgos sugiere la desactivación intencionada de mecanismos de seguridad del juego original, el uso de código ofuscado y una alta probabilidad de contener rastreadores o funcionalidades maliciosas para el usuario.

---

## Informe Completo
El informe detallado del laboratorio, con la metodología, análisis de hallazgos y conclusiones completas, se encuentra disponible en formato PDF en este repositorio.

📄 **[Ver Informe Completo de Laboratorio 02.pdf](Informe/Lab02_Jorge_Briceño.pdf)**

---

## Conclusiones Clave
1.  **Eficacia de SAST:** Las herramientas SAST son cruciales para una auditoría inicial, permitiendo identificar rápidamente vulnerabilidades de bajo nivel y malas configuraciones de seguridad.
2.  **Riesgo de APKs Modificados:** Se demostró empíricamente que las aplicaciones modificadas (MODs) presentan un riesgo de seguridad extremadamente alto para los usuarios, exponiéndolos a robo de datos y malware.
3.  **Importancia de la Contenerización:** Docker simplificó radicalmente el despliegue del entorno de auditoría, demostrando su valor para crear ambientes de prueba consistentes y reproducibles en el campo de la ciberseguridad.

---

## Autor y Docente

*   **Autor:**
    *   Briceño Diaz, Jorge Luis - `2017059611`
*   **Docente:**
    *   Dr. Oscar Juan Jimenez Flores
