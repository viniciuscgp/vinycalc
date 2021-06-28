package grupouninter.com.vinycalc;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Calcula calc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colocaIcones();

        // Classe que faz o trabalho.
        // Desacoplada da GUI.
        calc = new Calcula();

        atualizaDisplay();
    }
    public void atualizaDisplay() {
        TextView display = findViewById(R.id.etDisplay);
        TextView tv = findViewById(R.id.tvOp);

        display.setText(calc.getNumeroDigitado());
        tv.setText(calc.getOperadorAtual());
    }

    public void botaoClick(View view)
    {
        Button btn = (Button) view;
        calc.processaEntrada((String) btn.getTag());
        atualizaDisplay();
    }

    private void colocaIcones() {
        Button btnBackSpace = findViewById(R.id.btnBackSpace);
        Button btnLimpar = findViewById(R.id.btnLimpar);

        Typeface type = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/fontawesome-webfont.ttf");

        btnBackSpace.setTypeface(type);
        btnBackSpace.setText(Html.fromHtml("&#xf0a8;"));

        btnLimpar.setTypeface(type);
        btnLimpar.setText("C");


    }


}