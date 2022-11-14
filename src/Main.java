import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String input = "";
        BoardMap game = new BoardMap();
        Scanner scanner = new Scanner(System.in);

        while(!input.equals("q")){
            game.printMap();
            game.printInfo();
            input = scanner.nextLine();

            if(input.equals("a")){
                game.setPosX(game.getPosX()-1);
            }
            else if(input.equals("d")){
                game.setPosX(game.getPosX()+1);
            }
            else if(input.equals("w")){
                game.setPosY(game.getPosY()-1);
            }
            else if(input.equals("s")){
                game.setPosY(game.getPosY()+1);
            } else if (input.equals(" ")) {
                if(game.markAndChangeTurns()&&game.getMove()>3){
                    if(game.isPossibleToMakeMove()){
                        System.out.println("ALL GOOOOOOD!!!");
                    }else{
                        game.changeTurn();
                        if(game.isPossibleToMakeMove()) {
                            System.out.println("There was no move possible. Player's turn passed");
                        }else{
                            input="q";
                            System.out.println("Game OVER. No moves were possible!");
                            game.printMap();
                            game.printInfo();
                        }
                    }
                };
            } else if (input.equals("r")) {
                game.changeTurn();
            }
        }





        }
    }