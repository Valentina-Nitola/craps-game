package com.example.crapsgame.models;

public class Game {

    private int lastRoll;        // Última suma de los dados
    private int point;           // Valor del "punto" (4, 5, 6, 8, 9 o 10)
    private boolean firstRoll;   // Indica si es el primer tiro de la ronda
    private boolean gameOver;    // Indica si la ronda terminó (ganar o perder)

    private int wins;            // Conteo de partidas ganadas
    private int losses;          // Conteo de partidas perdidas

    public Game() {
        resetGame();
    }

    /**
     * Reinicia la ronda actual, pero conserva las victorias/derrotas totales.
     */
    public void resetGame() {
        lastRoll = 0;
        point = 0;
        firstRoll = true;
        gameOver = false;
    }

    /**
     * Este método recibe la suma de los dos dados
     * y actualiza la lógica del juego (ganar, perder, establecer punto).
     */
    public void evaluateRoll(int sum) {
        lastRoll = sum;

        if (firstRoll) {
            // PRIMER TIRO DE LA RONDA
            switch (sum) {
                case 7:
                case 11:
                    // Gana automáticamente
                    wins++;
                    gameOver = true;
                    break;
                case 2:
                case 3:
                case 12:
                    // Pierde automáticamente
                    losses++;
                    gameOver = true;
                    break;
                default:
                    // Si no es 2,3,7,11,12 => 4,5,6,8,9,10 (punto)
                    point = sum;
                    // El juego continúa, no se marca gameOver
                    break;
            }
            firstRoll = false;
        } else {
            // TIROS POSTERIORES
            if (sum == 7) {
                // Pierde la ronda
                losses++;
                gameOver = true;
            } else if (sum == point) {
                // Gana la ronda
                wins++;
                gameOver = true;
            }
            // Si no salió ni 7 ni el punto, el juego sigue en marcha
        }
    }

    // GETTERS para que tu controlador muestre la información

    public int getLastRoll() {
        return lastRoll;
    }

    public int getPoint() {
        return point;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }
}

