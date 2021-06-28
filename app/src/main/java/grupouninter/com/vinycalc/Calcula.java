package grupouninter.com.vinycalc;

import android.util.Log;

import java.util.ArrayList;

/**************************************************************
 * Autor : Vinicius César
 * Data : 19/06/2021
 * Esta classe faz todo o processamento
 * Assim fica facil implementar em outras linguagens/frameworks
 * ************************************************************
*/


public class Calcula {
    private ArrayList expressao;
    private static String TAG="CALC";
    private String numeroDigitado;
    private boolean limparNumeroDigitado;
    private String operadorAtual;

    // Construtor
    public Calcula() {
        inicializa();
    }

    // Estado inicial
    private void inicializa(){
        numeroDigitado = "";
        operadorAtual = "";
        limparNumeroDigitado = false;
        expressao = new ArrayList();
    }

    // Seleciona o tipo de entrada
    public void processaEntrada(String str) {
        Log.d("TAG", str);
        // Se for um operador----------------
        if (isOperador(str)) {
            expressao.add(numeroDigitado);
            expressao.add(str);
            numeroDigitado = calculaExpressao();
            limparNumeroDigitado = true;
        } else if (isOutros(str)) {
        // Processa outros-------------------
            if (str.equals("BS")) {
                if (numeroDigitado.length() > 0)
                    numeroDigitado = numeroDigitado.substring(0, numeroDigitado.length() - 1);
            }
            if (str.equals("C")) {
                inicializa();
            }
        } else {
        // Se for Numero---------------------
            if (limparNumeroDigitado) {
                numeroDigitado = "";
                limparNumeroDigitado = false;
            }
            numeroDigitado += str;
        }
    }

    private String calculaExpressao() {
        float total = 0;
        String str;
        String op = "@";
        for (int i = 0; i < expressao.size(); i++) {
            str = (String) expressao.get(i);
            if (isNumero(str)) {
                float v = Float.parseFloat(str);
                if (isOperador(op)) {
                    if (op.equals("+")) total += v;
                    if (op.equals("-")) total -= v;
                    if (op.equals("/")) total /= v;
                    if (op.equals("*")) total *= v;
                } else {
                    total = v;
                }
            } else {
                if (isOperador(str)) {
                    op = str;
                }
            }

        }
        return String.valueOf(total);
    }


    private boolean isOperador(String str) {
        return "+-*/=".contains(str);
    }

    private boolean isOutros(String str) {
        return "BS;C".contains(str);
    }

    private boolean isNumero(String str) {
        return (!isOperador(str) && !isOutros(str));
    }

    public String getNumeroDigitado() {
        return numeroDigitado;
    }

    public String getOperadorAtual() {
        return expressao.toString();
    }
}
