package com.example.crapsgame.models;

public class Game {

    private int lastRoll;    // Última suma de los dados
    private int punto;       // Valor del "punto" (4, 5, 6, 8, 9 o 10)
    private boolean firstRoll; // Indica si es el primer tiro de la ronda
    private boolean gameOver;  // Indica si la ronda terminó (ganar o perder)

    private int ganado;      // Conteo de partidas ganadas
    private int perdido;     // Conteo de partidas perdidas

    public Game() {
        resetGame();
    }

    /**
     * Reinicia la ronda actual, pero conserva los valores de
     * partidas ganadas/perdidas acumuladas.
     */
    public void resetGame() {
        lastRoll = 0;
        punto = 0;
        firstRoll = true;
        gameOver = false;
        // Se mantienen 'ganado' y 'perdido' para llevar la cuenta total.
    }

    /**
     * Recibe la suma de los dos dados y actualiza la lógica de Craps
     * (ganar, perder, establecer punto, etc.).
     */
    public void playRound(int sum) {
        lastRoll = sum;

        if (firstRoll) {
            // PRIMER TIRO DE LA RONDA
            switch (sum) {
                case 7:
                case 11:
                    // Gana automáticamente
                    ganado++;
                    gameOver = true;
                    break;
                case 2:
                case 3:
                case 12:
                    // Pierde automáticamente
                    perdido++;
                    gameOver = true;
                    break;
                default:
                    // Si no es 2,3,7,11,12 => 4,5,6,8,9,10 (punto)
                    punto = sum;
                    // Continúa el juego
                    break;
            }
            firstRoll = false;
        } else {
            // TIROS POSTERIORES
            if (sum == 7) {
                // Pierde la ronda
                perdido++;
                gameOver = true;
            } else if (sum == punto) {
                // Gana la ronda
                ganado++;
                gameOver = true;
            }
            // Si no salió ni 7 ni el punto, el juego sigue.
        }
    }

    // GETTERS

    public int getLastRoll() {
        return lastRoll;
    }

    public int getPunto() {
        return punto;
    }

    public int getGanado() {
        return ganado;
    }

    public int getPer
