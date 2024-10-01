package org.example.singleTableSpring;

import org.example.singleTableSpring.repositorios.AlumnoRepository;
import org.example.singleTableSpring.repositorios.PersonaRepository;
import org.example.singleTableSpring.repositorios.ProfesorRepository;
import org.example.singleTableSpring.entidades.Profesor;
import org.example.singleTableSpring.entidades.Alumno;
import org.example.singleTableSpring.enumeraciones.Especialidades;
import org.example.singleTableSpring.enumeraciones.Titulos;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication

public class SingleTableSpringApplication {
	private static final Logger logger = LoggerFactory.getLogger(SingleTableSpringApplication.class);

	@Autowired
	private PersonaRepository personaRepository;
	@Autowired
	private ProfesorRepository profesorRepository;

	@Autowired
	private AlumnoRepository alumnoRepository;
	public static void main(String[] args) {
		SpringApplication.run(SingleTableSpringApplication.class, args);

		System.out.println("funcionando");
	}

	@Bean
	@Transactional
	CommandLineRunner init(PersonaRepository personaRepository,
						   AlumnoRepository alumnoRepository,
						   ProfesorRepository profesorRepository) {
		return args -> {

			LocalDate fechaIngreso = LocalDate.of(2023, 4, 10);
			BigDecimal sueldo = new BigDecimal("745.85");

			Profesor p1 = Profesor.builder()
					.nombre("Dario")
					.apellido("Reinoso")
					.cantHijos(1)
					.fechaIngreso(fechaIngreso)
					.sueldo(sueldo)
					.titulo(Titulos.INGENIERO)
					.build();

			profesorRepository.save(p1);

			fechaIngreso = LocalDate.of(2021, 5, 12);
			sueldo = new BigDecimal("174.84");

			Profesor p2 = Profesor.builder()
					.nombre("Graciela")
					.apellido("Sevilla")
					.cantHijos(3)
					.fechaIngreso(fechaIngreso)
					.sueldo(sueldo)
					.titulo(Titulos.MASTER)
					.build();

			profesorRepository.save(p2);

			Alumno a1 = Alumno.builder()
					.nombre("Valentina")
					.apellido("Artola")
					.legajo(62123)
					.especialidad(Especialidades.BACHILLER)
					.build();

			alumnoRepository.save(a1);

			Alumno a2 = Alumno.builder()
					.nombre("Tadeo")
					.apellido("Drube")
					.legajo(62100)
					.especialidad(Especialidades.BACHILLER)
					.build();

			alumnoRepository.save(a2);
		};
	};
}
