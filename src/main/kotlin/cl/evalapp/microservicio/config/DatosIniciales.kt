package cl.evalapp.microservicio.config

import cl.evalapp.microservicio.model.Alumno
import cl.evalapp.microservicio.model.Criterio
import cl.evalapp.microservicio.model.CriterioEvaluado
import cl.evalapp.microservicio.model.Evaluacion
import cl.evalapp.microservicio.model.Nivel
import cl.evalapp.microservicio.model.Rubrica
import cl.evalapp.microservicio.repository.AlumnoRepository
import cl.evalapp.microservicio.repository.EvaluacionRepository
import cl.evalapp.microservicio.repository.RubricaRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DatosIniciales(
    private val alumnoRepository: AlumnoRepository,
    private val rubricaRepository: RubricaRepository,
    private val evaluacionRepository: EvaluacionRepository
) : CommandLineRunner {

    private fun cuatroNiveles() = mutableListOf(
        Nivel("Muy buen desempeño", 100),
        Nivel("Logrado", 80),
        Nivel("Aceptable", 60),
        Nivel("No logrado", 0)
    )

    private fun tresNiveles() = mutableListOf(
        Nivel("Logrado completamente", 100),
        Nivel("Logrado parcialmente", 60),
        Nivel("No logrado", 0)
    )

    override fun run(vararg args: String) {
        if (alumnoRepository.count() > 0) {
            return
        }

        val camila = alumnoRepository.save(Alumno(nombre = "Camila Rojas Soto", curso = "Sección A"))
        val diego = alumnoRepository.save(Alumno(nombre = "Diego Muñoz Vera", curso = "Sección A"))
        val ana = alumnoRepository.save(Alumno(nombre = "Ana Torres Díaz", curso = "Sección A"))
        alumnoRepository.save(Alumno(nombre = "Benjamín Silva Pino", curso = "Sección A"))
        val matias = alumnoRepository.save(Alumno(nombre = "Matías Pérez Lagos", curso = "Sección B"))
        alumnoRepository.save(Alumno(nombre = "Valentina Castro Ruiz", curso = "Sección B"))
        alumnoRepository.save(Alumno(nombre = "Tomás Herrera Gómez", curso = "Sección B"))
        alumnoRepository.save(Alumno(nombre = "Fernanda Soto Leiva", curso = "Sección B"))

        val oral = rubricaRepository.save(
            Rubrica(
                nombre = "Presentación oral",
                asignatura = "Comunicación oral",
                criterios = mutableListOf(
                    Criterio("Dominio del tema", "Explica el contenido con seguridad y sin leer.", 5),
                    Criterio("Voz y postura", "Habla fuerte y claro, y mira al público.", 5),
                    Criterio("Uso del tiempo", "Respeta el tiempo asignado.", 5),
                    Criterio("Responde preguntas", "Contesta las preguntas con argumentos.", 5)
                ),
                niveles = cuatroNiveles()
            )
        )

        val ensayo = rubricaRepository.save(
            Rubrica(
                nombre = "Ensayo argumentativo",
                asignatura = "Comunicación escrita",
                criterios = mutableListOf(
                    Criterio("Presenta una tesis clara", "La postura se entiende desde el primer párrafo.", 5),
                    Criterio("Usa argumentos con evidencia", "Cada argumento tiene datos o ejemplos.", 5),
                    Criterio("Redacción y ortografía", "El texto es ordenado y sin faltas.", 5),
                    Criterio("Conclusión coherente", "Retoma la tesis y cierra las ideas.", 5)
                ),
                niveles = cuatroNiveles()
            )
        )

        val auxilios = rubricaRepository.save(
            Rubrica(
                nombre = "Procedimiento de primeros auxilios",
                asignatura = "Primeros auxilios",
                criterios = mutableListOf(
                    Criterio("Evalúa la seguridad de la escena", "Revisa que no haya peligro antes de acercarse.", 5),
                    Criterio("Pide ayuda y comunica la emergencia", "Llama o pide a alguien que llame a emergencias.", 5),
                    Criterio("Aplica la técnica correctamente", "Sigue los pasos en el orden correcto.", 5),
                    Criterio("Usa el equipo de protección", "Se pone guantes antes de atender.", 5)
                ),
                niveles = tresNiveles()
            )
        )

        evaluacionRepository.save(
            Evaluacion(
                alumnoId = matias.id!!,
                alumno = matias.nombre,
                curso = matias.curso,
                rubricaId = oral.id!!,
                rubrica = oral.nombre,
                fecha = "2026-11-15 09:00",
                puntaje = 15,
                puntajeMaximo = 20,
                nota = 5.1,
                retroalimentacion = "Domina el tema. Debe mejorar el volumen de voz y ajustarse al tiempo.",
                criterios = mutableListOf(
                    CriterioEvaluado("Dominio del tema", "Explica el contenido con seguridad y sin leer.", 5, "Muy buen desempeño", 5, ""),
                    CriterioEvaluado("Voz y postura", "Habla fuerte y claro, y mira al público.", 5, "Aceptable", 3, "Hablar más fuerte."),
                    CriterioEvaluado("Uso del tiempo", "Respeta el tiempo asignado.", 5, "Aceptable", 3, "Se pasó dos minutos."),
                    CriterioEvaluado("Responde preguntas", "Contesta las preguntas con argumentos.", 5, "Logrado", 4, "")
                ),
                niveles = cuatroNiveles(),
                evaluador = "Instructor Demo"
            )
        )

        evaluacionRepository.save(
            Evaluacion(
                alumnoId = camila.id!!,
                alumno = camila.nombre,
                curso = camila.curso,
                rubricaId = ensayo.id!!,
                rubrica = ensayo.nombre,
                fecha = "2026-11-16 11:30",
                puntaje = 17,
                puntajeMaximo = 20,
                nota = 5.9,
                retroalimentacion = "Muy buen ensayo. La conclusión puede ser más precisa.",
                criterios = mutableListOf(
                    CriterioEvaluado("Presenta una tesis clara", "La postura se entiende desde el primer párrafo.", 5, "Muy buen desempeño", 5, ""),
                    CriterioEvaluado("Usa argumentos con evidencia", "Cada argumento tiene datos o ejemplos.", 5, "Muy buen desempeño", 5, ""),
                    CriterioEvaluado("Redacción y ortografía", "El texto es ordenado y sin faltas.", 5, "Logrado", 4, "Revisar tildes."),
                    CriterioEvaluado("Conclusión coherente", "Retoma la tesis y cierra las ideas.", 5, "Aceptable", 3, "Retomar la tesis al cerrar.")
                ),
                niveles = cuatroNiveles(),
                evaluador = "Docente Demo"
            )
        )

        for (alumno in listOf(diego, ana)) {
            evaluacionRepository.save(
                Evaluacion(
                    alumnoId = alumno.id!!,
                    alumno = alumno.nombre,
                    curso = alumno.curso,
                    rubricaId = auxilios.id!!,
                    rubrica = auxilios.nombre,
                    fecha = "2026-11-17 15:00",
                    puntaje = 11,
                    puntajeMaximo = 20,
                    nota = 3.8,
                    retroalimentacion = "Deben repasar la técnica antes de la próxima práctica.",
                    criterios = mutableListOf(
                        CriterioEvaluado("Evalúa la seguridad de la escena", "Revisa que no haya peligro antes de acercarse.", 5, "Logrado parcialmente", 3, ""),
                        CriterioEvaluado("Pide ayuda y comunica la emergencia", "Llama o pide a alguien que llame a emergencias.", 5, "Logrado completamente", 5, ""),
                        CriterioEvaluado("Aplica la técnica correctamente", "Sigue los pasos en el orden correcto.", 5, "No logrado", 0, "Repasar la secuencia de pasos."),
                        CriterioEvaluado("Usa el equipo de protección", "Se pone guantes antes de atender.", 5, "Logrado parcialmente", 3, "")
                    ),
                    niveles = tresNiveles(),
                    grupo = "Diego Muñoz Vera, Ana Torres Díaz",
                    evaluador = "Instructor Demo"
                )
            )
        }
    }
}
