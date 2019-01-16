public class NQueens
{
	//////////////////////////////////////////////////////////////////////

	boolean board[][];
	int size;
	short result = 0;

	//////////////////////////////////////////////////////////////////////

	public NQueens(int size)
	{
		if (size < 4)
			size = 4;

		this.size = size;
		this.board = new boolean[this.size][this.size];
		for (int cc=0; cc<this.size; cc++) {
			for (int rr=0; rr<this.size; rr++) {
				this.board[cc][rr] = false;
			}
		}
	}

	//////////////////////////////////////////////////////////////////////


	public NQueens(int size, int col, int row)
	{
		this(size);
		this.board[col-1][row-1] = true;
	}

	//////////////////////////////////////////////////////////////////////

	public void solve()
	{
		if (tryColumn(0)) {
			this.result = 1;
		}
		else {
			this.result = 2;
		}
	}

	//////////////////////////////////////////////////////////////////////

	public void printResult()
	{
		if (this.result == 0) {
			solve();
		}

		if (this.result == 1) {
			printBoard();
		}
		else {
			System.out.println("No Result");
		}
	}

	//////////////////////////////////////////////////////////////////////

	public void printBoard()
	{
		printDashLine();

		System.out.print("|   |");
		for (int cc=0; cc<this.size; cc++) {
			System.out.printf("%2d |", (cc+1));
		}
		System.out.println();

		printDashLine();

		for (int rr=0; rr<this.size; rr++) {
			System.out.printf("|%2d |", (rr+1));
			for (int cc=0; cc<this.size; cc++) {
				if (this.board[cc][rr]) {
					System.out.print(" X |");
				}
				else {
					System.out.print("   |");
				}
			}
			System.out.println();

			printDashLine();
		}
	}

	//////////////////////////////////////////////////////////////////////

	public void printDashLine()
	{
		System.out.print("+---+");
		for (int cc=0; cc<this.size; cc++) {
			System.out.print("---+");
		}
		System.out.println();
	}

	//////////////////////////////////////////////////////////////////////

	private boolean tryColumn(int col)
	{
		if (col == this.size)
			return true;

		if (columnTaken(col)) {
			return tryColumn(col+1);
		}

		int rr;
		for (rr=0; rr<this.size; rr++) {
			if (isValidSquare(col, rr)) {
				this.board[col][rr] = true;
				if (tryColumn(col+1)) {
					return true;
				}
				this.board[col][rr] = false;
			}
		}

		return false;
	}

	//////////////////////////////////////////////////////////////////////

	private boolean columnTaken(int col)
	{
		for (int rr=0; rr<this.size; rr++) {
			if (this.board[col][rr])
				return true;
		}

		return false;
	}

	//////////////////////////////////////////////////////////////////////

	private boolean isValidSquare(int col, int row)
	{
		for (int cc=0; cc<this.size; cc++) {
			for (int rr=0; rr<this.size; rr++) {
				if (cc == col && rr == row) {
					continue;
				}

				if (! this.board[cc][rr]) {
					continue;
				}

				if (cc == col) {
					return false;
				}

				if (rr == row) {
					return false;
				}

				if (Math.abs(cc-col) == Math.abs(rr-row)) {
					return false;
				}

			}
		}

		return true;
	}

	//////////////////////////////////////////////////////////////////////

	public static void main(String[] args)
	{
		NQueens nq = new NQueens(8, 5, 2);
	
		nq.printResult();
	}

	//////////////////////////////////////////////////////////////////////
}