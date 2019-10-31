package TSP;

import java.util.ArrayList;
import java.util.Scanner;

public class TSP {
    static ArrayList<int[]> arrangement;//存放全排列结果
    static int cityNum;//点的个数


    public static void main(String[] args) {
        arrangement = new ArrayList<int[]>();
        Scanner s = new Scanner(System.in);
        System.out.println("蛮力法解决TSP问题");
        System.out.println("请输入点的个数");
        cityNum = s.nextInt();
        int a[] = new int[cityNum];
        for (int i = 0; i < cityNum; i++)
            a[i] = i;
        permutations(a, 0, cityNum - 1);//产生全排列
///////////////////////////////////////////////////////////////////////////
        int[][] array = new int[cityNum][cityNum];//数据矩阵
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                System.out.println("请输入第" + (i + 1) + "个点与第" + (j + 1) + "个点的距离");
                array[j][i] = array[i][j] = s.nextInt();
            }
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j]);
                System.out.print("\t");
            }
            System.out.print("\n");
        }
////////////////////////////////////////////////////////////////////
        for (int i = 0; i < cityNum; i++)
            calculateAndPrint(array, i);
    }

    public static void calculateAndPrint(int[][] array, int startPoint) {//startPoint为开始点
        int minLenth = 10000;
        int[] minLenthPath = new int[10];
        for (int i = arrangement.size() / cityNum * startPoint; i < arrangement.size() / cityNum * (startPoint + 1); i++) {//开始计算距离
            int nowLenth = 0;
            for (int j = 0; j < cityNum - 1; j++) {//除了回去的最后一步
                nowLenth += array[arrangement.get(i)[j]][arrangement.get(i)[j + 1]];
            }
            nowLenth += array[arrangement.get(i)[0]][arrangement.get(i)[cityNum - 1]];//回到原点的最后一步
            if (nowLenth < minLenth) {//更新最短
                minLenth = nowLenth;
                minLenthPath = arrangement.get(i);
            }
        }
        System.out.print("从" + (startPoint + 1) + "点开始的最短路径为");
        for (int i = 0; i < cityNum; i++)
            System.out.print((minLenthPath[i] + 1) + ",");
        System.out.print(startPoint + 1);
        System.out.println("长度为" + minLenth);
    }

    public static void permutations(int[] a, int m, int n) {//全排列
        if (m == n) {
            arrangement.add(new int[cityNum]);
            for (int i = 0; i <= n; i++) {
                arrangement.get(arrangement.size() - 1)[i] = a[i];
            }
        } else {
            for (int i = m; i <= n; i++) {
                int temp = a[m];
                a[m] = a[i];
                a[i] = temp;
                permutations(a, m + 1, n);
                temp = a[m];
                a[m] = a[i];
                a[i] = temp;
            }
        }
    }
}
