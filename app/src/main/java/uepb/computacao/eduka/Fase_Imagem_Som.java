package uepb.computacao.eduka;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Fase_Imagem_Som extends Activity implements View.OnClickListener {

    private ImageView animalDoJogo, nomeDoAnimal, op1, op2, op3, op4; // Define as variáveis de imagens que herdarão os objetos do layout do jogo.
    private int imagemAleatoria = 0;
    private int posicaoCorreta = 0; // Guarda a posição correta do ANIMAL PRINCIPAL no array abaixo. (Sorteado pelo método "construirFaseAleatoria()").
    private String[] animaisSelecionados = {"","","",""}; // Array que guarda a posição dos animais da fase (Definidos aleatoriamente pelo método "construirFaseAleatoria()")
    private String ultimoAnimalRepetido = "";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fase_imagem_som);

        try {
            Bundle extras = getIntent().getExtras();
            String ultimaActivity = extras.getString("Activity");

            if (ultimaActivity.equals("Acertou")){
                chamarObjetos();
                construirFaseAleatoria();
            } else if (ultimaActivity.equals("Errou")){
                // Reconstroi a mesma fase.
                chamarObjetos();
                animalDoJogo.setImageResource(Metodos.getImagem(Metodos.objetos[imagemAleatoria], this)); // ANIMAL PRINCIPAL recebe a sua imagem que foi sorteada.
                nomeDoAnimal.setImageResource(Metodos.getNome(Metodos.objetos[imagemAleatoria], this)); // NOME DO ANIMAL PRINCIPAL recebe a sua imagem também.
            }
        } catch (Exception e){
            chamarObjetos();
            construirFaseAleatoria();
        }
    }

    // As variáveis herdam os objetos do layout (Para modificá-los).
    private void chamarObjetos(){
        animalDoJogo = (ImageView) findViewById(R.id.animalDoJogo);
        nomeDoAnimal = (ImageView) findViewById(R.id.nomeDoAnimal);
        op1 = (ImageView) findViewById(R.id.som_op1);
        op2 = (ImageView) findViewById(R.id.som_op2);
        op3 = (ImageView) findViewById(R.id.som_op3);
        op4 = (ImageView) findViewById(R.id.som_op4);
    }

    // Metodo de construção da fase: Sorteia o animal e a posição dele,
    // em seguida preenche as outras posições com os outros aleatorios.
    private void construirFaseAleatoria(){
        String[] objetosAux = Metodos.objetos; // Lista auxiliar copia a lista original.

        objetosAux = Metodos.removerDaLista(objetosAux, ultimoAnimalRepetido); // Remove o último animal sorteado para não repetir.

        imagemAleatoria = Metodos.sortearNumero(objetosAux.length); // Sorteia ANIMAL PRINCIPAL (posição da lista).
        posicaoCorreta = Metodos.sortearNumero(4); // Sorteia ANIMAL PRINCIPAL (posição da fase).

        animalDoJogo.setImageResource(Metodos.getImagem(objetosAux[imagemAleatoria], this)); // ANIMAL PRINCIPAL recebe a sua imagem que foi sorteada.
        nomeDoAnimal.setImageResource(Metodos.getNome(objetosAux[imagemAleatoria], this)); // NOME DO ANIMAL PRINCIPAL recebe a sua imagem também.
        animaisSelecionados[posicaoCorreta] = objetosAux[imagemAleatoria]; // ANIMAL PRINCIPAL vai para a posicao sorteada da fase (Array).
        ultimoAnimalRepetido = animaisSelecionados[posicaoCorreta]; // Guarda o ANIMAL PRINCIPAL para não repetir na próxima fase.
        objetosAux = Metodos.removerDaLista(objetosAux, objetosAux[imagemAleatoria]); // ANIMAL PRINCIPAL é removido da lista auxiliar para sortear os outros.

        // Percorre as outras posições da fase (Array) para completar com os OUTROS ANIMAIS aleatórios (Mesmo procedimento do anterior).
        for (int i=0; i<4; i++){
            if (animaisSelecionados[i] == ""){
                int valorAleatorio = Metodos.sortearNumero(objetosAux.length);
                animaisSelecionados[i] = objetosAux[valorAleatorio];
                objetosAux = Metodos.removerDaLista(objetosAux, objetosAux[valorAleatorio]);
            }
        }
    }

    // Transforma todos os icones dos sons como o padrão.
    private void resetIcone(){
        op1.setImageResource(R.drawable.audio_1);
        op2.setImageResource(R.drawable.audio_1);
        op3.setImageResource(R.drawable.audio_1);
        op4.setImageResource(R.drawable.audio_1);
    }

    // Método de evento de todos os objetos da tela.
    private boolean escolheu = false;
    private int escolha = 0;
    public void eventosImagemSom(View v){
        resetIcone();
        if (v.getId() == R.id.confirmar_som) { // Icone de áudio confirmação.
            if (escolheu == true) { // Só vai executar se estiver escolhido uma opção.
                if (escolha == posicaoCorreta) { // Faz a comparação pra saber se acertou.
                    Intent intent = new Intent(this, Acertou.class);
                    intent.putExtra("Activity", "Fase_Imagem_Som");
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(this, Errou.class);
                    intent.putExtra("Activity", "Fase_Imagem_Som");
                    startActivity(intent);
                }
            } else {
                Toast.makeText(this, "Escolha uma Opção!", Toast.LENGTH_SHORT).show();
            }
            Metodos.chamarSomAnimal("beep", this);
        } else {
            if (v.getId() == R.id.som_op1) { // Primeiro icone de áudio.
                op1.setImageResource(R.drawable.ok_1); // Recebe a imagem de escolha.
                escolha = 0; // Recebe o valor de escolha que será comparado com a posição do ANIMAL PRINCIPAL da variável "posicaoCorreta".
            } else if (v.getId() == R.id.som_op2){ // Segundo icone de áudio.
                op2.setImageResource(R.drawable.ok_1); // Recebe a imagem de escolha.
                escolha = 1; // Recebe o valor de escolha que será comparado com a posição do ANIMAL PRINCIPAL da variável "posicaoCorreta".
            } else if (v.getId() == R.id.som_op3){ // Terceiro icone de áudio.
                op3.setImageResource(R.drawable.ok_1); // Recebe a imagem de escolha.
                escolha = 2; // Recebe o valor de escolha que será comparado com a posição do ANIMAL PRINCIPAL da variável "posicaoCorreta".
            } else if (v.getId() == R.id.som_op4){ // Quarto icone de áudio.
                op4.setImageResource(R.drawable.ok_1); // Recebe a imagem de escolha.
                escolha = 3; // Recebe o valor de escolha que será comparado com a posição do ANIMAL PRINCIPAL da variável "posicaoCorreta".
            }
            escolheu = true;
            Metodos.chamarSomAnimal(animaisSelecionados[escolha], this); // Executa o som do animal, usando o nome que está na posição dele no array da fase.
        }
    }

    @Override
    public void onClick(View v){
        if (v.getId() == R.id.acertou){
            Metodos.chamarSomAnimal("beep", this);
            startActivity(new Intent(this, Fase_Imagem_Som.class));
            finish();
        } else if (v.getId() == R.id.errou){
            Metodos.chamarSomAnimal("beep", this);
            this.setContentView(R.layout.fase_imagem_som);
        }
    }
}
