package uepb.computacao.eduka;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Fase_Som_Imagem extends Activity {

    private ImageView imagem_op1, imagem_op2, imagem_op3, imagem_op4; // Define as variáveis de imagens que herdarão os objetos do layout do jogo.
    private int posicaoCorreta = 0; // Guarda a posição correta do ANIMAL PRINCIPAL no array abaixo. (Sorteado pelo método "construirFaseAleatoria()").
    private String[] animaisSelecionados = {"","","",""}; // Array que guarda a posição dos animais da fase (Definidos aleatoriamente pelo método "construirFaseAleatoria()")
    private String animalDaFase; // Guarda o nome do ANIMAL PRINCIPAL (Sorteado pelo método "construirFaseAleatoria()").
    private String ultimoAnimalRepetido = "";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fase_som_imagem);

        chamarObjetos();
        construirFaseAleatoria();
    }

    // As variáveis herdam os objetos do layout (Para modificá-los).
    private void chamarObjetos(){
        imagem_op1 = (ImageView) findViewById(R.id.imagem_op1);
        imagem_op2 = (ImageView) findViewById(R.id.imagem_op2);
        imagem_op3 = (ImageView) findViewById(R.id.imagem_op3);
        imagem_op4 = (ImageView) findViewById(R.id.imagem_op4);
    }

    // Metodo de construção da fase: Sorteia o animal e a posição dele,
    // em seguida preenche as outras posições com os outros aleatorios.
    private void construirFaseAleatoria(){
        String[] objetosAux = Metodos.objetos; // Lista auxiliar copia a lista original.

        objetosAux = Metodos.removerDaLista(objetosAux, ultimoAnimalRepetido); // Remove o último animal sorteado para não repetir.

        int valorAleatorio = Metodos.sortearNumero(objetosAux.length); // Sorteia ANIMAL PRINCIPAL (posição da lista).
        posicaoCorreta = Metodos.sortearNumero(4); // Sorteia ANIMAL PRINCIPAL (posição da fase).

        animalDaFase = objetosAux[valorAleatorio]; // ANIMAL PRINCIPAL recebe o nome sorteado.
        ultimoAnimalRepetido = animalDaFase;
        animaisSelecionados[posicaoCorreta] = animalDaFase; // ANIMAL PRINCIPAL vai para a posicao sorteada da fase (Array).
        objetosAux = Metodos.removerDaLista(objetosAux, animalDaFase); // ANIMAL PRINCIPAL é removido da lista auxiliar para sortear os outros.

        // Percorre as outras posições da fase (Array) para completar com os OUTROS ANIMAIS aleatórios (Mesmo procedimento do anterior).
        for (int i=0; i<4; i++){
            if (animaisSelecionados[i] == ""){
                valorAleatorio = Metodos.sortearNumero(objetosAux.length);
                animaisSelecionados[i] = objetosAux[valorAleatorio];
                objetosAux = Metodos.removerDaLista(objetosAux, objetosAux[valorAleatorio]);
            }
        }
        colocarImagensNasOpcoes();
    }

    private void reconstruirFaseAleatoria(){
        for (int i = 0; i < 4; i++) // Se tiver acertado a fase será reconstruida, então o array com os animais deve ser esvaziado.
            animaisSelecionados[i] = ""; // Esvazia a posição do array.
        construirFaseAleatoria(); // Reconstroi a fase.
    }

    // Após as variáveis (de imagem) das opções da fase receberem os objetos do layout.
    // Método: Suas imagens serão alteradas de acordo com o array "animaisSelecionados" que guarda os 4 animais sorteados.
    private void colocarImagensNasOpcoes(){
        imagem_op1.setImageResource(Metodos.getImagemMoldura(animaisSelecionados[0], this));
        imagem_op2.setImageResource(Metodos.getImagemMoldura(animaisSelecionados[1], this));
        imagem_op3.setImageResource(Metodos.getImagemMoldura(animaisSelecionados[2], this));
        imagem_op4.setImageResource(Metodos.getImagemMoldura(animaisSelecionados[3], this));
    }

    // Método de evento de todos os objetos da tela.
    public void eventosSomImagem(View v){
        if (v.getId() == R.id.ouvir_audio){ // Clicou para ouvir o áudio do animal sorteado.
            Metodos.chamarSomAnimal(animalDaFase, this); // Chama o som do animal usando nome dele.
        } else {
            int escolha = 0; // Variavel de auxilio de escolha, pra saber se clicou na imagem correta (Posição correta do array).
            if (v.getId() == R.id.imagem_op1) // Clicou na primeira imagem
                escolha = 0;
            else if (v.getId() == R.id.imagem_op2) // Clicou na segunda imagem
                escolha = 1;
            else if (v.getId() == R.id.imagem_op3) // Clicou na terceira imagem
                escolha = 2;
            else if (v.getId() == R.id.imagem_op4) // Clicou na quarta imagem
                escolha = 3;
            if (escolha == posicaoCorreta){ // Faz a comparação pra saber se acertou.
                Intent intent = new Intent(this, Acertou.class);
                intent.putExtra("Activity", "Fase_Som_Imagem");
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, Errou.class);
                intent.putExtra("Activity", "Fase_Som_Imagem");
                startActivity(intent);
            }
            Metodos.chamarSomAnimal("beep", this); // Chama o áudio beep.
        }
    }
}
