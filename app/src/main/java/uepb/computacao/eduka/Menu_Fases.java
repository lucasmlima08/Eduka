package uepb.computacao.eduka;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Menu_Fases extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_fases);

        // Chamar Banner.
        AdView adView = (AdView)this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adRequest.isTestDevice(this);
        adView.loadAd(adRequest);
    }

    public void eventosFases(View view){
        Metodos.chamarSomAnimal("beep", this);
        if (view.getId() == R.id.fase_1)
            startActivity(new Intent(this, Fase_Imagem_Som.class));
        else if (view.getId() == R.id.fase_2)
            startActivity(new Intent(this, Fase_Som_Imagem.class));
    }
}
