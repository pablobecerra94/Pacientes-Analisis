package ar.edu.unlam.pacientes;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import ar.edu.unlam.analisis.enums.ETipoInforme;
import ar.edu.unlam.analisis.pacientes.controlpac;

public class ControlpacTest {

	@Test
	public void devuelveElInformeDeEspecialidad() {
		try {
			Collection<String> resultado = controlpac.getInformeMedico("01", ETipoInforme.ESPECIALIDADES);
				assertTrue(resultado.contains("diagnostico"));
		
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void devuelveErrorAlPasarNullComoTipoInforme() {
		try {
			Collection<String> resultado = controlpac.getInformeMedico("01", null);
			assertEquals(0,resultado.size());
			
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void devuelveVacioAlPasarUnMedicoInexistente() { 
		try {
			Collection<String> resultado = controlpac.getInformeMedico("011", ETipoInforme.ESPECIALIDADES);
			assertEquals(0,resultado.size());
			
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void devuelveVacioAlSiElArchivoEstaVacio() {
			//TODO buscar mock para simular resultado.
		assertTrue(true);
	}
	
	@Test
	public void devuelveElInformeDePacienteAtendido() {
		try {
			Collection<String> resultado = controlpac.getInformeMedico("01", ETipoInforme.PACIENTES);
			assertTrue(resultado.contains("facu"));
			
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	

	@Test
	public void noDevuelveUnPacienteQueNoAtiende() {
		try {
			Collection<String> resultado = controlpac.getInformeMedico("01", ETipoInforme.PACIENTES);
			assertFalse(resultado.contains("hola"));
			
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void noExistenRegistrosDePacientes() {
			//TODO buscar mock para simular resultado.
		assertTrue(true);
	}
	

	

}
