package com.example.coachchallenge2

import Player

class Controller(val callback: Callback) {
    private var player1: Player? = null
    private var player2: Player? = null

    private val IMBANG = "imbang"
    private val MENANG_1 = "Player 1 Menang"
    private val MENANG_2 = "Computer Menang"

    private val GUNTING = "gunting"
    private val BATU = "batu"
    private val KERTAS = "kertas"

    fun setPlayer1(player1: Player) {
        this.player1 = player1
    }

    fun setPlayer2(player2: Player) {
        this.player2 = player2
    }

    fun proses() {
        val valuePlayer1 = this.player1?.pilihan
        val valuePlayer2 = this.player2?.pilihan

        val hasilAkhir = when (valuePlayer1) {
            BATU -> {
                when (valuePlayer2) {
                    valuePlayer1 -> IMBANG
                    KERTAS -> MENANG_2
                    GUNTING -> MENANG_1
                    else -> "Salah Input"
                }
            }
            GUNTING -> {
                when (valuePlayer2) {
                    valuePlayer1 -> IMBANG
                    BATU -> MENANG_2
                    KERTAS -> MENANG_1
                    else -> "Salah Input"
                }
            }
            KERTAS -> {
                when (valuePlayer2) {
                    valuePlayer1 -> IMBANG
                    GUNTING -> MENANG_2
                    BATU -> MENANG_1
                    else -> "Salah Input"
                }
            }
            valuePlayer2 -> IMBANG
            else -> "Salah Input"
        }

        callback.tampilkanHasil(hasilAkhir)
    }
}