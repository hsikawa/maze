package maze;

import java.awt.Point;
import java.util.Stack;

public class maze {

	public static void main(String[] args) {
		String [][] map  = {{" "," "," "," "},
							{"■"," ","■","■"},
							{"■"," "," "," "}};

		Stack<Point> stack = new Stack<>();

		Point nowPoint = new Point(0,0);
		stack.push(nowPoint);
		Point nextPoint = null;

		do {
			//現在位置と次の位置を比較し一致しなければ進める(stackに格納)
			nowPoint = stack.peek();

			//現在位置をマーク
			map[nowPoint.y][nowPoint.x] = "*";
			printMap(map);

			//次の位置を取得
			nextPoint = searchNext(nowPoint, map);

			if(nowPoint.x != nextPoint.x || nowPoint.y != nextPoint.y) {
				stack.push(nextPoint);
			}else{//一致しなければ、戻る(stackからpop)
				map[nowPoint.y][nowPoint.x] = "×";
				printMap(map);
				stack.pop();
			}
		}while(nextPoint.x != map[0].length - 1  || nextPoint.y != map.length - 1);
		map[nextPoint.y][nextPoint.x] = "*";
		printMap(map);
	}

	/**
	 * 次のマスを検索する
	 * @return
	 */
	private static Point searchNext(Point nowPoint, String [][] map){
		Point nextPoint = new Point();


		nextPoint.setLocation(nowPoint.x - 1, nowPoint.y);
		if(checkPoint(nextPoint, map)) {
			return nextPoint;
		}

		nextPoint.setLocation(nowPoint.x, nowPoint.y - 1);
		if(checkPoint(nextPoint, map)) {
			return nextPoint;
		}

		nextPoint.setLocation(nowPoint.x + 1, nowPoint.y);
		if(checkPoint(nextPoint, map)) {
			return nextPoint;
		}

		nextPoint.setLocation(nowPoint.x, nowPoint.y + 1);
		if(checkPoint(nextPoint, map)) {
			return nextPoint;
		}

		return nowPoint;
	}

	/**
	 * mapの範囲外または次のポイントに移動可能か判断する
	 */
	private static boolean checkPoint(Point point, String [][] map) {
		Point maxPoint = new Point(map[0].length, map.length);

		if(0 <= point.x && point.x < maxPoint.x && 0 <= point.y && point.y < maxPoint.y && " ".equals(map[point.y][point.x])) {
			return true;
		}
		return false;
	}

	private static void printMap(String [][] map) {
		System.out.println("--------------------");
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println("");
		}

		System.out.println("--------------------");
	}



}
