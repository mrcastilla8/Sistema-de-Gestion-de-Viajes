/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import Modelo.VentaBoletoModelo;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Renato
 */
public class ObtenerOrigenes_Prueba {
    
    public ObtenerOrigenes_Prueba() {
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
    public void testObtenerOrigenes() {
        VentaBoletoModelo ventaBoletoModelo = new VentaBoletoModelo();

        List<String> origenes = ventaBoletoModelo.obtenerOrigenes();

        assertNotNull(origenes, "La lista de orígenes no debería ser nula");
    }
}
