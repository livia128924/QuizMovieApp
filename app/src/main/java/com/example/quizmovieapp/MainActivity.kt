package com.example.quizmovieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.quizmovieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private  var rightAnswer: String? = null
    private var rightAnswerCount = 0
    private var quizCount = 1
    private val QUIZ_COUNT = 5
   private val questions = mutableListOf(
        mutableListOf(
            "question_01",
            " Qual ator interpretou o personagem principal em \"Coringa\" (2019)?",
            "single_choice",
            listOf("a"),
            mapOf(
                "a" to "Joaquin Phoenix",
                "b" to "Heath Ledger",
                "c" to "Robert Pattinson",
                "d" to "Christian Bale"
            )
        ),
       mutableListOf(
            "question_02",
            "Qual filme ganhou o Oscar de Melhor Filme em 2020?",
            "single_choice",
            listOf("a"),
            mapOf(
                "a" to "Parasita",
                "b" to "1917",
                "c" to "O Irlandês",
                "d" to "Coringa",

            )
        ),
       mutableListOf(
            "question_03",
            "Qual atriz interpretou a personagem Katniss Everdeen em \"Jogos Vorazes\" (2012)?",
            "single_choice",
            listOf("b"),
            mapOf(
                "a" to "Emma Stone",
                "b" to "Jennifer Lawrence",
                "c" to "Kristen Stewart",
                "d" to "Shailene Woodley"
            )
        ),
       mutableListOf(
            "question_04",
            "Qual é o nome do filme em que um cientista constrói uma máquina do tempo para tentar salvar a vida de sua namorada?",
            "single_choice",
            listOf("d"),
            mapOf(
                "a" to "O Efeito Borboleta",
                "b" to "Donnie Darko",
                "c" to "Feitiço do Tempo",
                "d" to "Questão de Tempo"
            )
        ),
       mutableListOf(
            "question_05",
            "Qual é o nome do primeiro filme da saga \"O Senhor dos Anéis\" lançado em 2001?",
            "single_choice",
            listOf("a"),
            mapOf(
                "a" to "A Sociedade do Anel",
                "b" to "As Duas Torres",
                "c" to "O Retorno do Rei",
                "d" to "A Batalha dos Cinco Exércitos"
            )
        ),
       mutableListOf(
            "question_06",
            "Qual filme conta a história de um jovem que entra para uma gangue de skinheads neonazistas?",
            "single_choice",
            listOf("a"),
            mapOf(
                "a" to "American History X",
                "b" to "Clube da Luta",
                "c" to "Filhos da Esperança",
                "d" to "O Poderoso Chefão"
            )
        ),
       mutableListOf(
            "question_07",
            "Qual é o nome da personagem principal em \"La La Land\" (2016)?",
            "single_choice",
            listOf("c"),
            mapOf(
                "a" to "Sebastian Wilder",
                "b" to "Keith Reynolds",
                "c" to "Mia Dolan",
                "d" to "Dave Johnson\n"
            )
        ),
       mutableListOf(
            "question_08",
            "Qual é o nome do filme em que Johnny Depp interpreta um pirata?",
            "multi_choice",
            listOf("b", "d"),
            mapOf(
                "a" to "O Senhor dos Anéis",
                "b" to "Piratas do Caribe: A Maldição do Pérola Negra",
                "c" to "O Código Da Vinci",
                "d" to "Harry Potter e o Prisioneiro de Azkaban"
            )
        ),
       mutableListOf(
            "question_09",
            " Qual é o nome do filme em que Matt Damon interpreta um agente secreto?",
            "multi_choice",
            listOf("a", "b"),
            mapOf(
                "a" to "Bourne: Identidade Desconhecida",
                "b" to "Missão Impossível - Efeito Fallout",
                "c" to "John Wick: De Volta ao Jogo",
                "d" to "Capitão América: O Primeiro Vingador"
            )
        ),       mutableListOf(
            "question_10",
            "Qual é o nome do filme em que Sandra Bullock interpreta uma astronauta?",
            "multi_choice",
            listOf("a", "c"),
            mapOf(
                "a" to "Gravidade",
                "b" to "Interestelar",
                "c" to "A Chegada",
                "d" to "Marte"
            )
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        showNextQuiz()
        //QuestionAdapter(questions)

    }

    fun showNextQuiz() {
        binding.countLabel.text = getString(R.string.count_label, quizCount)
        if (questions.isNotEmpty()) {
            val quiz = questions[0]
            val ops = quiz[4] as Map<String, String>

            binding.questionLabel.text = quiz[1].toString()
            rightAnswer = quiz[3].toString()

            ops.values.toMutableList().shuffle()

            binding.answerBtn1.text = ops["a"]
            binding.answerBtn2.text = ops["b"]
            binding.answerBtn3.text = ops["c"]
            binding.answerBtn4.text = ops["d"]

            questions.removeAt(0)
        }
    }
    fun checkAnswer(view:View){
        val answerbtn: Button  = findViewById(view.id)
        val btnText = answerbtn.text.toString()
        val alertTitle: String

        if(btnText == rightAnswer){
            alertTitle = "Correto!"
            rightAnswerCount++
        }else{
            alertTitle = "Errado!"
        }

        AlertDialog.Builder(this)
            .setTitle(alertTitle)
            .setMessage("Resposta: $rightAnswer")
            .setPositiveButton("OK"){dialogInterface, i ->
                checkQuizCount()
            }
            .setCancelable(false)
            .show()
    }
    fun checkQuizCount(){
        if(quizCount == QUIZ_COUNT){

        }else{
            quizCount++
            showNextQuiz()
        }
    }


    data class Question(
        val id: String,
        val title: String,
        val type: String,
        val answers: List<String>,
        val options: Map<String, String>
    )

//    class QuestionAdapter(val questions: MutableList<MutableList<Any>>) :
//        RecyclerView.Adapter<QuestionViewHolder>() {
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
//            val binding = ActivityMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//            return QuestionViewHolder(binding)
//        }
//
//        override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
//            val question = questions[position]
//            holder.bind(question)
//        }
//
//        override fun getItemCount(): Int {
//            return questions.size
//        }
//
//    }
//
//    class QuestionViewHolder(private val binding: ActivityMainBinding) : RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(question: Question) {
//        binding.questionLabel.text = question.title
//
//
//        }
//    }

}