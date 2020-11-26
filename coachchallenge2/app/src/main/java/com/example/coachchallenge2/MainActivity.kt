package com.example.coachchallenge2

import Player
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //private val P_BATU = findViewById<LinearLayout>(R.id.P_BATU)
    //private val P_KERTAS = findViewById<LinearLayout>(R.id.P_KERTAS)
    //private val P_GUNTING = findViewById<LinearLayout>(R.id.P_GUNTING)
    //private val COM_BATU = findViewById<LinearLayout>(R.id.COM_BATU)
    //private val COM_GUNTING = findViewById<LinearLayout>(R.id.COM_GUNTING)
    //private val COM_KERTAS = findViewById<LinearLayout>(R.id.COM_KERTAS)
    //private val TEXT_VS = findViewById<TextView>(R.id.text_vs)
    private val random = listOf("batu", "gunting", "kertas").shuffled()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val controller = Controller(object : Callback {
            override fun tampilkanHasil(hasil: String) {
                text_vs.text = hasil
            }

        })

        reset.setOnClickListener {
            enableOrdisableSemuaTombol(true)
            text_vs.text = "VS"
        }

        P_BATU.setOnClickListener {
            memilihDanProses(controller, pilihan = "batu")
        }

        P_KERTAS.setOnClickListener {
            memilihDanProses(controller, pilihan = "kertas")
        }

        P_GUNTING.setOnClickListener {
            memilihDanProses(controller, pilihan = "gunting")
        }
    }

    //function untuk memproses pilihan player
    private fun memilihDanProses(
        controller: Controller,
        pilihan: String
    ) {

        val player = Player()
        val comp = Player()

        player.pilihan = pilihan
        comp.pilihan = listOf("batu", "gunting", "kertas").shuffled().first()

        Log.e("pilihan", "${player.pilihan}")
        Log.e("pilihan", "${comp.pilihan}")

        enableOrdisableSemuaTombol(false)

        when (pilihan) {
            "batu" -> P_BATU.setBackgroundSelected()
            "gunting" -> P_GUNTING.setBackgroundSelected()
            "kertas" -> P_KERTAS.setBackgroundSelected()
        }

        when (comp.pilihan) {
            "batu" -> COM_BATU.setBackgroundSelected()
            "gunting" -> COM_GUNTING.setBackgroundSelected()
            "kertas" -> COM_KERTAS.setBackgroundSelected()
        }

        controller.setPlayer1(player)
        controller.setPlayer2(comp)
        controller.proses()
    }

    //function untuk disable atau enable tombolnya
    private fun enableOrdisableSemuaTombol(isEnable: Boolean) {
        P_BATU.isEnabled = isEnable
        P_GUNTING.isEnabled = isEnable
        P_KERTAS.isEnabled = isEnable

        COM_BATU.isEnabled = isEnable
        COM_GUNTING.isEnabled = isEnable
        COM_KERTAS.isEnabled = isEnable

        P_BATU.setBackgroundUnselected()
        P_GUNTING.setBackgroundUnselected()
        P_KERTAS.setBackgroundUnselected()

        COM_BATU.setBackgroundUnselected()
        COM_GUNTING.setBackgroundUnselected()
        COM_KERTAS.setBackgroundUnselected()
    }

    //membuat setiap background komponen dengan warna yg sama
    private fun View.setBackgroundSelected() {
        this.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.colorAccent))
    }

    private fun View.setBackgroundUnselected() {
        this.setBackgroundColor(
            ContextCompat.getColor(
                this@MainActivity,
                android.R.color.transparent
            )
        )
    }
}