

public class CalculadoraGUITest {
    public static void main(String[] args) {
        CalculadoraGUI calc = new CalculadoraGUI();
        int exitosos = 0;

        try {
            assert calc.evaluarExpresion("2+3").equals("5.0") : "Error en 2+3";
            exitosos++;
            assert calc.evaluarExpresion("7-4").equals("3.0") : "Error en 7-4";
            exitosos++;
            assert calc.evaluarExpresion("5*6").equals("30.0") : "Error en 5*6";
            exitosos++;
            assert calc.evaluarExpresion("8/2").equals("4.0") : "Error en 8/2";
            exitosos++;
            assert calc.evaluarExpresion("2+3*4").equals("14.0") : "Error en 2+3*4";
            exitosos++;
            assert calc.evaluarExpresion("10/2+3").equals("8.0") : "Error en 10/2+3";
            exitosos++;
            assert calc.evaluarExpresion("4*2+6").equals("14.0") : "Error en 4*2+6";
            exitosos++;
            assert calc.evaluarExpresion("9-3*2").equals("3.0") : "Error en 9-3*2";
            exitosos++;
            assert calc.evaluarExpresion("5.5+1.5").equals("7.0") : "Error en 5.5+1.5";
            exitosos++;
            assert calc.evaluarExpresion("6.6-2.2").equals("4.4") : "Error en 6.6-2.2";
            exitosos++;
            assert calc.evaluarExpresion("2.5*4").equals("10.0") : "Error en 2.5*4";
            exitosos++;
            assert calc.evaluarExpresion("10.0/4").equals("2.5") : "Error en 10.0/4";
            assert calc.evaluarExpresion("10/0").equals("Error") : "Error en división por cero";
            exitosos++;
            assert calc.evaluarExpresion("1+2*3/2-1").equals("3.0") : "Error en operación compuesta";
            exitosos++;
            assert calc.evaluarExpresion("100/10+5*2-3").equals("19.0") : "Error en 100/10+5*2-3";
            exitosos++;

            System.out.println("✅ Pasaron " + exitosos + " pruebas de aceptación.");
        } catch (AssertionError e) {
            System.out.println("❌ Falló una prueba: " + e.getMessage());
        }
    }
}
