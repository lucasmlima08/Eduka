package uepb.computacao.eduka;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Errou extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.errou);

        // Chamar Banner.
        AdView adView = (AdView)this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adRequest.isTestDevice(this);
        adView.loadAd(adRequest);
    }

    public void eventoMenu(View view){
        Metodos.chamarSomAnimal("beep", this);
        Bundle extras = getIntent().getExtras();
        String ultimaActivity = extras.getString("Activity");

        if (ultimaActivity.equals("Fase_Imagem_Som")){
            Intent intent = new Intent(this, Fase_Imagem_Som.class);
            intent.putExtra("Info", "Reconstruir");
            startActivity(intent);
            this.finish();
        } else if (ultimaActivity.equals("Fase_Som_Imagem")){
            Intent intent = new Intent(this, Fase_Som_Imagem.class);
            intent.putExtra("Info", "Reconstruir");
            startActivity(intent);
            this.finish();
        }
    }
}
