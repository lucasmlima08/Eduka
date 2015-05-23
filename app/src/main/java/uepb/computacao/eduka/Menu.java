package uepb.computacao.eduka;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }

    public void eventoMenu(View view){
        Metodos.chamarSomAnimal("beep", this);
        if (view.getId() == R.id.jogar)
            startActivity(new Intent(this, Menu_Fases.class));
        else if (view.getId() == R.id.creditos)
            startActivity(new Intent(this, Creditos.class));
    }
}
