public class player {
    private String name1;
    private String name2;
    private display v;
    private int playerId;
    private int movesCount;
    private char[][] board;
    private String message;

    // default constructor
    public player() {
        this.board = new char[3][3];
        this.movesCount = 9;
        this.playerId = 1;
    }

    // initializing the reference of view class
    public void registerView(display v) {
        this.v = v;
    }

    //setters and getters
    public String getName1()
    {
        return name1;
    }
    public String getName2()
    {
        return name2;
    }
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getMovesCount() {
        return movesCount;
    }

    public void setMovesCount(int movesCount) {
        this.movesCount = movesCount;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }
    public void setName1(String name){
        this.name1=name;
    }
    public void setName2(String name){
        this.name2=name;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // function to update the board model
    public void PlayMove(int x, int y) {

        if(getMovesCount() > 0){
            // mark the board with x or o depending on playerId
            if(playerId%2 != 0)
                board[x][y] = 'X';
            else
                board[x][y] = 'O';

            // reduce the count of moves left
            setMovesCount(--movesCount);

            // check if playerId has won or if game is tied,
            // and send message accordingly to view, also update the view
            if(isWinner(x, y)) {
                String pl;
                if(playerId%2!=0)
                {
                    pl=getName1();
                }
                else
                {
                    pl=getName2();
                }

                setMessage("Player " + pl + " is Winner!");
                v.isWinner(x, y, board[x][y], getMessage());
            }
            else if(getMovesCount()==0) {
                setMessage("No Winner! Game ended in a Tie");
                v.isWinner(x, y, board[x][y], getMessage());
            }
            else {
                if(playerId%2 != 0) {
                    // toggle the playerId
                    setPlayerId(2);
                    setMessage("'O':  Player  " +getName2());
                }
                else {
                    setPlayerId(1);
                    setMessage("'X':  Player  " +getName1());

                }
                // update the board with message for next player
                v.update(x, y, board[x][y], getMessage());
            }

        }

    }

    // function to check if there is a winner
    public boolean isWinner(int x, int y) {
        int countRow = 0;
        int countCol = 0;
        int countLDiag = 0;
        int countRDiag = 0;
        char symbol;
        if(getPlayerId() %2 !=0)
            symbol = 'X';
        else
            symbol = 'O';

        for(int i=0; i<board.length;i++) {
            if(board[x][i] == symbol)
                countRow++;
            if(board[i][y] == symbol)
                countCol++;
            if(board[i][i] == symbol)
                countRDiag++;
            if(board[board.length-1-i][i] == symbol)
                countLDiag++;
        }

        if(countCol==board.length || countRow==board.length
                || countLDiag == board.length || countRDiag == board.length)
            return true;

        return false;
    }

    // function to clear the model and reset it to initial state
    public void ResetModel() {
        movesCount = 9;
        setPlayerId(1);
        setMessage("");
        for(int i=0; i<board.length;i++) {
            for(int j=0; j<board.length;j++) {
                board[i][j] = '\0';
            }
        }
        v.resetGame();

    }

}