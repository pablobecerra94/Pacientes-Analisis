package ar.edu.unlam.analisis.pacientes;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class controlpac {

	public static void ps(String x) {
		System.out.print(x);

	}

	public static int LeerEntero() {
		String línea = new String("");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			línea = br.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int ne = 0;
		try {
			ne = Integer.parseInt(línea);
		} catch (Exception e) {
		}
		;
		return (ne);
	}

	public static String LeerCadena() {
		String línea = new String("");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			línea = br.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		double ne = 0;
		return (línea);
	}

	public static void main(String args[]) throws Exception {
		String op = "";
		int sw = 0, sw1 = 0;
		int op1, op2, op3; // variables de selección usadas en los diferentes
							// menús
		String codpac, cp, nompac, codmed, cm, enfpac, nommed, espmed; // variables
																		// usadas
																		// en el
																		// registro
																		// de
																		// datos
		String codp = "", codpa = "", nomp = "", nompa = "", codm = "", codme = "", enfp = "", nomm = "", espm = ""; // variables
																														// usadas
																														// en
																														// la
																														// lectura
																														// de
																														// datos
		String codtem; // variables auxiliares temporales

		do {
			op1 = 0;

			verMenuPrincipal();
			// ps("\n");
			op1 = LeerEntero();
			verificarOpcionValidaMenuPrincipal(op1);

			if (op1 == 1) // seleción ingreso de pacientes
			{

				do {

					verMenuIngresoDePacientes();

					op2 = LeerEntero();

					verificarOpcionValidaIngresoDePacientes(op2);

					switch (op2) {
					case 1: // ingreso de datos, datos del paciente
						ingresarDatosDelPaciente();

						break;
					// ingreso de datos, situacion del paciente
					case 2:
						codm = ingresarSituacionPaciente(codm);
						break;

					case 3:
						ingresarDatosDelMedico();
					}
				} while (op2 != 4);
			} else {
				if (op1 == 2) // seleción informes
				{

					do {
						verMenuInformes();
						op2 = LeerEntero();
						verificarOpcionInforme(op2);

						switch (op2) {
						case 1:
							try {

								ps("Digite el codigo del medico que desea consultar: ");
								codtem = LeerCadena();

								DataInputStream datomed = null;
								datomed = new DataInputStream(new FileInputStream("C:\\datomed.txt"));

								sw = 1;
								while (sw != 0) {
									try {
										codm = datomed.readUTF();
										nomm = datomed.readUTF();
										espm = datomed.readUTF();

									} catch (EOFException e) {
										sw = 0;
									}

									if (codm.equals(codtem)) // compara el
																// codigo
																// extraido de
																// la
																// tabla
																// "datomed" con
																// el codigo
																// digitado
									{
										ps("::: El medico " + nomm + " atiende a los siguientes pacientes: " + "\n");
										DataInputStream situpac = null;
										situpac = new DataInputStream(new FileInputStream("C:\\situpac.txt"));

										sw = 1;
										while (sw != 0) {
											try {
												codp = situpac.readUTF();
												codme = situpac.readUTF();
												enfp = situpac.readUTF();

												if (codme.equals(codtem)) // compara
																			// el
																			// codigo
																			// medico
																			// de
																			// la
																			// tabla
																			// "datomed"
																			// con
																			// el
																			// de
																			// la
																			// tabla
																			// "situpac"
												{
													DataInputStream datopac = null;
													datopac = new DataInputStream(
															new FileInputStream("C:\\datopac.txt"));

													sw1 = 1;
													while (sw1 != 0) {
														try {
															codpa = datopac.readUTF();
															nompa = datopac.readUTF();

															if (codpa.equals(codp)) // compara
																					// el
																					// codigo
																					// del
																					// paciente
																					// de
																					// la
																					// tabla
																					// "situpac"
																					// con
																					// el
																					// codigo
																					// del
																					// paciente
																					// de
																					// la
																					// tabla
																					// "datopac"
															{
																ps("::: Paciente: " + nompa + "\n");
															}
														} catch (EOFException e) {
															sw1 = 0;
														}
													}
												}
											} catch (EOFException e) {
												sw = 0;
											}
										}
									}
								}

							} catch (IOException ioe) {
							}
							;
							break;

						case 2:

							ps("Digite el codigo del medico que desea consultar: ");
							codtem = LeerCadena();

							DataInputStream datomed = null;
							datomed = new DataInputStream(new FileInputStream("C:\\datomed.txt"));

							sw1 = 1;
							while (sw1 != 0) {
								try {
									codm = datomed.readUTF();
									nomm = datomed.readUTF();
									espm = datomed.readUTF();

									if (codm.equals(codtem)) // compara el
																// codigo
																// digitado
																// con el codigo
																// del medico de
																// la
																// tabla
																// "datomed"
									{
										ps("El medico " + nomm + " trata las siguientes enfermedades:" + "\n");

										DataInputStream situpac = null;
										situpac = new DataInputStream(new FileInputStream("C:\\situpac.txt"));

										sw = 1;
										while (sw != 0) {
											try {
												codp = situpac.readUTF();
												codme = situpac.readUTF();
												enfp = situpac.readUTF();

												if (codtem.equals(codme)) // compara
																			// el
																			// codigo
																			// del
																			// medico
																			// de
																			// la
																			// tabla
																			// "datomed"
																			// con
																			// el
																			// codigo
																			// del
																			// medico
																			// en
																			// la
																			// tabla
																			// "situpac"

												{
													ps(">>>> " + enfp + "\n");
												}
											} catch (EOFException e) {
												sw = 0;
											}
										}
									}
								} catch (EOFException e) {
									sw1 = 0;
								}
							}
							break;
						}

					} while (op2 != 3);

				}
			}
		} while (op1 != 3);

	}

	private static void verificarOpcionInforme(int op2) {
		if (op2 < 1 || op2 > 3) {
			ps("Seleccione una de las opciones del menu" + "\n");
		}
	}

	private static void verMenuInformes() {
		ps("   ..............................................." + "\n");
		ps("   :-:  C O N T R O L  D E  P A C I E N T E S  :-:" + "\n");
		ps("   ..............................................." + "\n");
		ps("   :-:           - I N F O R M E S -           :-:" + "\n");
		ps("   :-:.........................................:-:" + "\n");
		ps("   :-: 1. Listado de pacientes por medico      :-:" + "\n");
		ps("   :-: 2. Enfermedades que atiende cada medico :-:" + "\n");
		ps("   :-: 3. Anterior                             :-:" + "\n");
		ps("   ..............................................." + "\n");
		ps("   ....Elija la opcion deseada: ");
	}

	private static void ingresarDatosDelMedico() throws FileNotFoundException, IOException {
		String op;
		String codmed;
		String nommed;
		String espmed;
		DataOutputStream datomed = null;
		datomed = new DataOutputStream(new FileOutputStream("C:\\datomed.txt"));
		try {
			do {

				ps("   ....................................................." + "\n");
				ps("   :-:      - D A T O S   D E L   M E D I C O -      :-:" + "\n");
				ps("   :-:...............................................:-:" + "\n");

				ps("Digite el codigo del medico: ");
				codmed = LeerCadena();
				datomed.writeUTF(codmed);

				ps("Digite el nombre del medico: ");
				nommed = LeerCadena();
				datomed.writeUTF(nommed);

				ps("Digite la especializacion del medico: ");
				espmed = LeerCadena();
				datomed.writeUTF(espmed);

				ps("Desea ingresar otro medico? S/N");
				ps("\n");

				op = LeerCadena();

			} while (op.equals("S") || op.equals("s"));
		} catch (IOException ioe) {
		}
		;
		datomed.close();
	}

	private static String ingresarSituacionPaciente(String codm) throws FileNotFoundException {
		String op;
		String enfpac;
		String codp;
		DataOutputStream situpac = null;
		situpac = new DataOutputStream(new FileOutputStream("C:\\situpac.txt"));

		try {
			do {

				ps("   ....................................................." + "\n");
				ps("   :-: - S I T U A C I O N  D E L  P A C I E N T E - :-:" + "\n");
				ps("   :-:...............................................:-:" + "\n");

				ps("Digite el codigo del paciente: ");
				codp = LeerCadena();
				situpac.writeUTF(codp);
				ps("Digite el codigo del medico: ");
				codm = LeerCadena();
				situpac.writeUTF(codm);
				ps("Digite el diagnostico del medico: ");
				enfpac = LeerCadena();
				situpac.writeUTF(enfpac);

				ps("Desea ingresar otro registro al historial? S/N");
				ps("\n");
				op = LeerCadena();

			} while (op.equals("S") || op.equals("s"));
			situpac.close();
		} catch (IOException ioe) {
		}
		;
		return codm;
	}

	private static void ingresarDatosDelPaciente() throws FileNotFoundException {
		String op;
		String codpac;
		String nompac;
		DataOutputStream datopac = null;
		datopac = new DataOutputStream(new FileOutputStream("C:\\datopac.txt"));
		try {

			do {

				ps("   ..............................................." + "\n");
				ps("   :-:  - D A T O S  D E L  P A C I E N T E -  :-:" + "\n");
				ps("   :-:.........................................:-:" + "\n");

				ps("Digite el codigo del paciente: ");
				codpac = LeerCadena();
				datopac.writeUTF(codpac);
				ps("Digite el nombre del paciente: ");
				nompac = LeerCadena();

				datopac.writeUTF(nompac);

				ps("Desea ingresar otro paciente? S/N" + "\n");

				op = LeerCadena();

			} while (op.equals("S") || op.equals("s"));
			datopac.close();

		} catch (IOException ioe) {
		}
		;
	}

	private static void verificarOpcionValidaIngresoDePacientes(int op2) {
		if (op2 < 1 || op2 > 4) {
			ps("Debe digitar una opcion del menu" + "\n");
		}
	}

	private static void verMenuIngresoDePacientes() {
		ps("   ..............................................." + "\n");
		ps("   :-: -I N G R E S O  D E  P A C I E N T E S- :-:" + "\n");
		ps("   :-:.........................................:-:" + "\n");
		ps("   :-: 1.  Datos del paciente                  :-:" + "\n");
		ps("   :-: 2.  Situacion del paciente              :-:" + "\n");
		ps("   :-: 3.  Datos del medico                    :-:" + "\n");
		ps("   :-: 4.  Anterior                            :-:" + "\n");
		ps("   ..............................................." + "\n");
		ps("   ....Elija la opcion deseada: ");
	}

	private static void verificarOpcionValidaMenuPrincipal(int op1) {
		if (op1 < 1 || op1 > 3) {
			ps("Debe digitar una opcion del menu" + "\n");
		}
	}

	private static void verMenuPrincipal() {
		ps("   .............................................." + "\n");
		ps("   :-:        C E N T R O  M E D I C O        :-:" + "\n");
		ps("   :-:   >>>> L O S  L A U R E L E S <<<<     :-:" + "\n");
		ps("   :-:  C O N T R O L  D E  P A C I E N T E S :-:" + "\n");
		ps("   :-:........................................:-:" + "\n");
		ps("   :-: 1.  Ingreso de datos                   :-:" + "\n");
		ps("   :-: 2.  Informes                           :-:" + "\n");
		ps("   :-: 3.  Salir                              :-:" + "\n");
		ps("   .............................................." + "\n");
		ps("   ....Elija la opcion deseada: ");
	}

}
