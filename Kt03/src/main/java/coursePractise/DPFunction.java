package coursePractise;

import org.apache.commons.lang.text.StrBuilder;

public class DPFunction {
    public static void main(String[] args){
        int[][] matrix = new int[][]{{1,8,5,8,},{3,1,0,8},{5,3,6,4,},{9,4,1,0}};
        DPSolve solve = new DPSolve();
        long startTime = System.currentTimeMillis();
//        System.out.println(solve.solveSteps(43));
//        System.out.println(solve.solveMatrixMin(matrix));//
//        System.out.println(solve.solveIncreaseSub(new int[]{2,1,5,3,6,4,8,9,7}));
//        System.out.println(solve.solveMaxSameSub("1A2C3D4B56", "B1D23CA45B6A"));
//        System.out.println(solve.solvePackage(new int[]{0,3,6,8,1,7}, new int[]{0,1,2,5,4,6}, 8));
//        System.out.println(solve.solveMinEditCost("ab12cd3","abcdf",5, 3, 2));
        System.out.println(solve.solveMinEditCost("ab12cd3","abcdf",5, 3, 2));
        System.out.println(System.currentTimeMillis() - startTime);
    }

}

class DPSolve{
    //-------------------将str1编辑成str2,最小编辑代价-----------------
    //开始：10:04
    //结束：11：12
    //耗时：68分钟
    public int solveMinEditCost(String str1, String str2, int ic, int dc, int rc){
        int[][] dp = new int[str1.length()+1][str2.length()+1];

        return codeMinEditCost(str1.toCharArray(), str2.toCharArray(), str1.length(), str2.length(), ic, dc, rc);
    }

    //返回dp[x][y]的最小代价
    public static int codeMinEditCost(char[] str1, char[] str2, int x, int y, int ic, int dc, int rc){
        //basecode
        if (0 == x && 0 == y){
            return 0;
        }
        if (x<0){
            return Integer.MAX_VALUE - dc;
        }
        if (y<0){
            return Integer.MAX_VALUE - ic;
        }

        //x-1
        int costLeft = codeMinEditCost(str1, str2, x-1, y, ic, dc, rc) + dc;
        //y-1
        int costUp = codeMinEditCost(str1, str2, x, y-1, ic, dc, rc) + ic;
        //x-1 && y-1
        int costPre = Integer.MAX_VALUE;
        if (x-1 >= 0 && y-1 >= 0){
            if (str1[x-1] == str2[y-1]){
                costPre = codeMinEditCost(str1, str2, x-1, y-1, ic, dc, rc);
            }else {//str1[x] != str2[y]
                costPre = codeMinEditCost(str1, str2, x-1, y-1, ic, dc, rc) + rc;
            }
        }

        return Math.min(Math.min(costLeft, costUp), costPre);
    }

    //---------------------背包问题-------------------
    //开始12:26，13:00 14:35
    //暂停12:41，13:13 15:08
    //耗时：15+13+33=64
    public int solvePackage(int[] weight, int[] value, int capacity){
        /*int[] dp = new int[capacity+1];
        for (int i=0; i<weight.length; i++){
            for (int j=weight[i]; j<=capacity; j++){
                dp[j] = dp[j] > dp[j-weight[i]]+value[i] ? dp[j] : dp[j-weight[i]]+value[i];
            }
        }
        return dp[capacity];*/

        int[][] dp2 = new int[weight.length][value.length];
        return codePackage(weight, value, weight.length-1, dp2, capacity);

        /*solvePackage2(weight, value, weight.length-1, capacity);
        return 0;*/
    }
    //返回到index为止的最大value
    public int codePackage(int[] weight, int[] value, int index, int[][] dp, int capacity){
        //basecase
        if (capacity < 0){
            return -value[index+1];
        }
        if(index < 0){
            return 0;
        }

        return Math.max(codePackage(weight, value, index-1, dp, capacity)
        , codePackage(weight, value, index-1, dp, capacity-weight[index]) + value[index]);
    }
    //标准解法
    public static void solvePackage2(int[] cost,int[] value,int N,int V){
        int[][] dp=new int[N+1][V+1];
        for(int i=1;i<=N;i++){
            for(int j=1;j<=V;j++){
                if(cost[i]<=j){
                    dp[i][j]=Math.max(dp[i-1][j], dp[i-1][j-cost[i]]+value[i]);
                }
                else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        System.out.println(dp[N][V]);
    }

    //----------------------最长公共子序列--------------
    //开始9:45
    //结束10:45
    //耗时60分钟
    //拓展：返回最长子序列,未完成
    public int solveMaxSameSub(String str1, String str2){
        if (null == str1 || null == str2){
            return 0;
        }
        int[][] dp = new int[str1.length()][str2.length()];

        StringBuilder builder = new StringBuilder();
        int result = codeMaxSameSub(str1.toCharArray(), str2.toCharArray(),
                str1.length()-1, str2.length()-1, dp, builder);
        System.out.println(builder.toString());
        return result;
    }

    //测试用例：S1:ABCD S2:ABCD
    //S1:A1B2 3A4B
    //s1:1A2C3D4B56 s2:B1D23CA45B6A
    public int codeMaxSameSub(char[] str1, char[] str2, int indexStr1, int indexStr2, int[][] dp, StringBuilder builder){
        //basecase
        if (indexStr1 < 0 || indexStr2 < 0){
            return 0;
        }

        //左边indexStr1-1位置
        int lenLeft = 0;
        //上边indesStr2-1位置
        int lenRight = 0;
        //左上角indexStr1-1, indexStr2-1位置
        int lenPre = 0;

        StringBuilder builderLeft = new StringBuilder();
        StringBuilder builderRight = new StringBuilder();
        StringBuilder builderPre = new StringBuilder();

        if (indexStr1-1 >= 0){
            lenLeft = dp[indexStr1-1][indexStr2] != 0 ? dp[indexStr1-1][indexStr2]
                    : codeMaxSameSub(str1, str2, indexStr1-1, indexStr2, dp, builderLeft);
        }
        if (indexStr2-1 >= 0){
            lenRight = dp[indexStr1][indexStr2-1] != 0 ? dp[indexStr1][indexStr2-1]
                    : codeMaxSameSub(str1, str2, indexStr1, indexStr2-1, dp, builderRight);
        }
        if (str1[indexStr1] == str2[indexStr2] ){
            lenPre = 1 + codeMaxSameSub(str1, str2, indexStr1-1, indexStr2-1, dp, builderPre);
            builderPre.append(str1[indexStr1]);
        }

        return dp[indexStr1][indexStr2] = Math.max(Math.max(lenLeft, lenRight), lenPre);
    }



    //-----------------------走台阶问题-----------------
    public long solveSteps(int n){
        long[] stepCount = new long[n+1];
        return codeSteps2(n, stepCount);
//        return codeSteps3(n);
    }

    //暴力搜索
    public long codeSteps(int n ){
        if (1 == n){
            return 1;
        }
        if (2 == n){
            return 2;
        }
        /*if (stepCount[n] == 0){
            stepCount[n] = codeSteps(n-1, stepCount) + codeSteps(n-2, stepCount);
        }*/
//        return stepCount[n];
        return codeSteps(n-1) + codeSteps(n-2);
    }

    //记忆搜索
    public long codeSteps2(int n, long[] stepCount){
        if (1 == n){
            return 1;
        }
        if (2 == n){
            return 2;
        }
        if (stepCount[n] == 0){
            stepCount[n] = codeSteps2(n-1, stepCount) + codeSteps2(n-2, stepCount);
        }
        return stepCount[n];
    }

    //此方法不对，切硬币方法是组合问题，走台阶是全排列问题
    public long codeSteps3(int n){
        int[] step = new int[]{1, 2};
        long[] stepCount = new long[n+1];
        stepCount[0] = 1;
        for (int i=0; i<step.length; i++){
            for (int j=step[i]; j<n+1; j++){
                stepCount[j] += j - step[i];
            }
        }
        return stepCount[n];
    }

    //-----------------------矩阵路径最小值问题----------------
    //开始10:07
    //结束11:05
    //耗时1小时,扣边界50分钟
    public int solveMatrixMin(int[][] matrix){
        int[][] dp = new int[matrix.length][matrix[0].length];
        if (null == matrix){
            return 0;
        }
//        return codeMatrixMin(matrix, matrix.length-1, matrix[0].length-1);
        return codeMatrixMin(matrix, matrix.length-1, matrix[0].length-1, dp);
    }
    //测试用例{{1,2}, {3,4}}
    public int codeMatrixMin(int[][] matrix, int x, int y){
        //边界处理
        if (x == 0 && y == 0){
            return matrix[0][0];
        }
        //有一边触碰到边界时，<0的那边应返回不影响判断的值
        int upValue = y-1<0 ? Integer.MAX_VALUE : codeMatrixMin(matrix, x, y-1);
        int leftValue = x-1<0 ? Integer.MAX_VALUE : codeMatrixMin(matrix, x-1, y);

        return Math.min( upValue, leftValue) + matrix[x][y];
    }

    public int codeMatrixMin(int[][] matrix, int x, int y, int[][] dp){
        //边界处理
        if (0 == x && 0 == y){
            return matrix[0][0];
        }
        if (x < 0 || y < 0){
            return Integer.MAX_VALUE;
        }
        if (0 == dp[x][y]){
            dp[x][y] = matrix[x][y] + Math.min(codeMatrixMin(matrix, x-1, y, dp), codeMatrixMin(matrix, x, y-1, dp));
        }

        return dp[x][y];
    }

    //---------------------找最长递增子序列---------------
    //开始12:08 14:44
    //暂停13:01 14:56
    //耗时：53+12=65
    public int solveIncreaseSub(int[] arr){
        int[] dp = new int[arr.length];
        dp[0] = 1;
        return codeIncreaseSub(arr, arr.length-1, dp, 0);
    }

    //找以index为结尾的最大递增子序列
    public int codeIncreaseSub(int[] arr, int index, int[] dp, int max){
        //basecase
        if (index < 0){
            return 0;
        }
        //等于也算递增
        int tempIndex = index-1;
        //找第一个小于等于arr[index]的
        while(tempIndex >= 0 && arr[tempIndex] > arr[index]){
            tempIndex--;
        }
        //找不到
        if (tempIndex < 0){
            return dp[index] = 1;
        }
        //找到了
        dp[index] = (dp[tempIndex] != 0 ? dp[tempIndex] : codeIncreaseSub(arr, tempIndex, dp, max)) + 1;
        //取最大值
        max = Math.max(dp[index], dp[index-1] != 0 ? dp[index-1] : codeIncreaseSub(arr, index-1, dp, max));
        return max;
    }
}
