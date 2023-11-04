public class player extends BoardElement implements StatsPrinter{
    //kintamasis nurodantis žaidimo žaidėjo suringtus taškus.
    private Double scoreoftheplayer = 0.0;

    public player(char symbol) {
        super(symbol);
    }

    public Double getScoreoftheplayer(boolean bool) {
        if(bool){
            return scoreoftheplayer;
        }
        return -1.0;
    }

    public void setScoreoftheplayer(Double scoreoftheplayer) {
        this.scoreoftheplayer = scoreoftheplayer;
    }

    @Override
    void printSymbol() {
        //todo - sukurti kokį nors body
    }



        @Override
      public void printStats() {
            System.out.println("Player's \"" + getSymbol() + "\" score is: " + scoreoftheplayer);
      }
}
