package cleancode.minesweeper.asis.cell;

public class NumberCell extends Cell {

	private final int nearbyLandMineCount;

	public NumberCell(final int nearbyLandMineCount) {
		this.nearbyLandMineCount = nearbyLandMineCount;
	}

	@Override
	public boolean isLandMine() {
		return false;
	}

	@Override
	public boolean hasLandMineCount() {
		return true;
	}

	@Override
	public String getSign() {
		if (isOpened) {
			return String.valueOf(nearbyLandMineCount);
		}
		if (isFlagged) {
			return FLAG_SIGN;
		}
		return UNCHECKED_SIGN;
	}
}
