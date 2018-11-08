
public class Main {
	private static int tile=0;
	static int [][] board=new int[8][8];
	 public  static void chessBoard(int x_root,int y_root,int x_cover,int y_cover,int size) {
		if(size==1)return;
		int t=tile++;
		int s=size/2;
		if(x_cover<=x_root+s-1&&y_cover<=y_root+s-1)
			chessBoard(x_root, y_root, x_cover, y_cover, s);
		else {
			board[x_root+s-1][y_root+s-1]=t;
			chessBoard(x_root, y_root, x_root+s-1,y_root+s-1, s);
		}
		
		if(x_cover<=x_root+s-1&&y_cover>=y_root+s)
			chessBoard(x_root, y_root+s, x_cover, y_cover, s);
		else {
			board[x_root+s-1][y_root+s]=t;
			chessBoard(x_root, y_root+s, x_root+s-1,y_root+s, s);
		
		}
		
		if(x_cover>=x_root+s&&y_cover>=y_root+s)
			chessBoard(x_root+s, y_root+s, x_cover, y_cover, s);
		else {
			board[x_root+s][y_root+s]=t;
			chessBoard(x_root+s, y_root+s, x_root+s,y_root+s, s);
	}
		if(x_cover>=x_root+s&&y_cover<=y_root+s-1)
			chessBoard(x_root+s, y_root, x_cover, y_cover, s);
		else {
			board[x_root+s][y_root+s-1]=t;
			chessBoard(x_root+s, y_root, x_root+s,y_root+s-1, s);
	}
		for(int i=size-1;i>=0;i++) {
			System.out.println("-----------------------");
			for(int j=0;j<size;j++)
				System.out.println(board[i][j]+"|");
			
		}
}
	public static void main(String args[]) {
		chessBoard(0, 0, 3, 4, 8);
	}
}
