package uepb.computacao.eduka;

import android.content.Context;
import android.media.MediaPlayer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Metodos {

    // Define a variável responsável pela execução do áudio do animal.
    public static MediaPlayer toque;

    // OBS: Lista de objetos, para imagens e sons.
    public static String[] objetos = {"cachorro", "cavalo", "elefante", "galo", "grilo", "leao", "macaco",
                                      "ovelha", "passarinho", "pato", "pintinho", "porco", "sapo", "vaca"};

    // Retorna a ImageView da respectiva posição da lista (Imagem do animal).
    public static int getImagem(String animal, Context context){
        int imagem = context.getResources().getIdentifier("img_"+animal, "drawable", context.getPackageName());
        return imagem;
    }

    // Retorna a ImageView da respectiva posição da lista (Imagem do animal).
    public static int getImagemMoldura(String animal, Context context){
        int imagem = context.getResources().getIdentifier("moldura_"+animal, "drawable", context.getPackageName());
        return imagem;
    }

    // Retorna o DRAWABLE (imagem) pelo nome do animal.
    public static int getNome(String animal, Context context){
        int imagem = context.getResources().getIdentifier("nome_"+animal, "drawable", context.getPackageName());
        return imagem;
    }

    // Retorna o RAW (áudio) pelo nome do animal.
    public static int getSom(String animal, Context context){
        int audio = context.getResources().getIdentifier(animal, "raw", context.getPackageName());
        return audio;
    }

    // Retorna um valor aleatório dentro do limite.
    public static int sortearNumero(int limite){
        Random valor = new Random();
        int aleatorio = valor.nextInt(limite);
        return aleatorio;
    }

    // Método usado para executar áudio dos animais e do toque dos botões.
    public static void chamarSomAnimal(String animal, Context context)
    {
        pararSomAnimal();
        int audio = Metodos.getSom(animal, context);
        toque = MediaPlayer.create(context, audio);
        toque.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion(MediaPlayer mp) {
                toque.stop();
                toque.release();
                toque = null;
            }
        });
        toque.start();
    }

    // Método: Parar o som caso esteja em execução.
    public static void pararSomAnimal()
    {
        if (toque != null && toque.isPlaying())
            toque.stop();
    }

    // Remove um elemento do array e retorna o próprio array.
    public static String[] removerDaLista(String[] objetosAux, String animal)
    {
        List<String> list = new ArrayList<String>(Arrays.asList(objetosAux));
        list.remove(animal);
        objetosAux = list.toArray(new String[0]);
        return objetosAux;
    }
}
