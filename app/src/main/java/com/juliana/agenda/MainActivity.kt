package com.juliana.agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    lateinit var nomeDigitado: EditText
    lateinit var celularDigitado: EditText
    lateinit var referenciaDigitada: EditText
    lateinit var pesquisarDigitada: EditText

    lateinit var salvar: Button
    lateinit var lupa: Button
    lateinit var exibir: Button

    lateinit var lista: TextView

    private lateinit var rdgSetor: RadioGroup

    private var setorSelecionado: Setor? = null

    var nomesCadastrados : MutableList<Contato> = mutableListOf()

    var exibirContatos = ""
    lateinit var X : Contato

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nomeDigitado = findViewById(R.id.edtNome)
        var nome = nomeDigitado.text.toString()
        celularDigitado = findViewById(R.id.edtCelular)
        var celular = celularDigitado.text.toString()
        referenciaDigitada = findViewById(R.id.edtReferencia)
        var referencia = referenciaDigitada.text.toString()
        pesquisarDigitada = findViewById(R.id.edtPesquisar)
        var pesquisar = pesquisarDigitada.text.toString()


        salvar = findViewById(R.id.btnSalvar)
        lupa = findViewById(R.id.btnPesquisar)
        exibir = findViewById(R.id.btnExibir)

        lista = findViewById(R.id.txtResultado)

        rdgSetor = findViewById(R.id.rdgSetor)


        salvar.setOnClickListener{
            nome?.let{
                celular?.let{
                    referencia?.let{
                        registrarPessoa(nome, celular, referencia)
                    }

                }

            }

        }

       lupa.setOnClickListener{
            val resultado = nomesCadastrados.find{X ->
                X.getNome() == pesquisar}

            if (resultado != null) {
                lista.text = resultado.toString()
            } else {
                Toast.makeText(this,
                    "Não foi possível encontrar o cadastro",
                    Toast.LENGTH_SHORT).show()
            }
            exibir.visibility = View.VISIBLE
        }


        exibir.setOnClickListener{

            lista.text = exibirContatos

        }





        //lista.text = nomesCadastrados.sortedBy { it.nomePessoa, }
    }

    public fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {

            val foiChecado = view.isChecked

            when (view.id) {
                R.id.rdPessoal ->
                    if (foiChecado) {
                        setorSelecionado = Setor.PESSOAL
                        referenciaDigitada.setHint("Referência")

                    }
                R.id.rdTrabalho ->
                    if (foiChecado) {
                        setorSelecionado = Setor.TRABALHO
                        referenciaDigitada.setHint("E-mail")
                    }
            }

        }
    }

    fun registrarPessoa(nome: String, celular: String, item: String) {


        nomesCadastrados.add(Contato(nome, celular,item))

        for(N in nomesCadastrados){
            exibirContatos+= "$N \n"
        }

        lista.text = exibirContatos
    }


}