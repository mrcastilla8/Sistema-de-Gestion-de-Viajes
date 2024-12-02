/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import Modelo.Viaje;
import Modelo.VentaBoletoModelo;

/**
 *
 * @author Renato
 */
public class Boletos_Prueba {
    
    public Boletos_Prueba() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testImprimirBoleto() {
        VentaBoletoModelo ventaBoletoModelo = new VentaBoletoModelo();

        int idViaje = 1; 
        String numAsiento = "12";
        String nombres = "Juan";
        String apellidos = "Perez";
        String dni = "12345671212121218910";

        try {
            ventaBoletoModelo.imprimirBoleto(idViaje, numAsiento, nombres, apellidos, dni);
        } catch (Exception e) {
            assertNotNull(null, "Hubo un error al imprimir el boleto: " + e.getMessage());
        }
    }

    
}
