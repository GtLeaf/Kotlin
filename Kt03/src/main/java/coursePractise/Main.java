package coursePractise;

public class Main {
    public static void main(String[] args){
        Solve solve = new Solve();
        long startTime = System.currentTimeMillis();
        solve.solveCutCoin5(new int[]{3,5,7,8,9,10,11}, 500);
//        solve.solveCutCoin4(new int[]{1,2,5}, 10);
        System.out.println(System.currentTimeMillis() - startTime);
    }
}

class Solve{
    //---------切硬币5,10,25,1---------------------
    //开始16:25
    //结束16:57
    //耗时32分钟

    //暴力搜索
    public void solveCutCoin(int[] coins, int amount){
        System.out.println(codeCutCoin(coins, 0, amount));
    }
    //返回coinArr[index...N-1]种硬币的分割方法
    public int codeCutCoin(int[] coins, int index, int amount){
        //baseCase
        int res = 0;
        //如果是最后一种硬币，并且amount=0
        if (index >= coins.length){
            res = 0 == amount ? 1 : 0;
            return res;
        }

        //用amount-硬币面值 代替累加。
        for(int i = 0; i* coins[index]<= amount; i++){
            res += codeCutCoin(coins, index+1, amount -i* coins[index]);
        }
        return res;
    }

    //记忆搜索方法
    public void solveCutCoin2(int[] coins, int aim){
        int[][] count = new int[coins.length+1][aim+1];
        System.out.println(codeCutCoin2(coins, 0, aim, count));
        System.out.println("结束");
    }
    //返回coinArr[index...N-1]种硬币的分割方法
    public int codeCutCoin2(int[] coins, int index, int amount, int[][] counts){
        //baseCase
        int res = 0;
        if (index >= coins.length){
            res = 0 == amount ? 1 : 0;
            return res;
        }

        //当前位置值不为0，表示计算过，直接使用
        if (0 != counts[index][amount]){
            return (-1 == counts[index][amount]) ? 0 : counts[index][amount];
        }
        //计算当前位置的值
        int count;
        for(int i = 0; i* coins[index]<= amount; i++){
            //先判断是否计算过
            count = counts[index+1][amount -i* coins[index]];
            if (0 != count){
                res += (-1 == count) ? 0 : count;
            }else {
                //没计算过，重新计算
                res += codeCutCoin2(coins, index+1, amount -i* coins[index], counts);
            }
        }
        //将每次计算过的值保存，用-1标记组合数为0的位置
        counts[index][amount] = (0 == res) ? -1 : res;
        return res;
    }

    //动态规划
    public void solveCutCoin3(int[] coinArr, int aim){
        int[][] counts = new int[coinArr.length][aim+1];
        System.out.println(codeCutCoin3(coinArr, coinArr.length-1, aim, counts));
    }
    public int codeCutCoin3(int[] coins, int index, int amount, int[][] counts){
        //baseCase
        if (0 == amount){
            return 1;
        }
        if (index < 0 || amount < 0){
            return 0;
        }
        if (0 == counts[index][amount]){
            counts[index][amount] = codeCutCoin3(coins, index-1, amount, counts)
                    + codeCutCoin3(coins, index, amount - coins[index], counts);
        }
        return counts[index][amount];
    }

    //动态规划2 空间复杂度优化
    public void solveCutCoin4(int[] coinArr, int aim){
        int[] counts = new int[aim+1];
        int[] isUpdate = new int[aim+1];
//        counts[0] = 1;
        System.out.println(codeCutCoin42(coinArr, coinArr.length-1, aim, counts, isUpdate));
//        System.out.println(codeCutCoin4(coinArr, coinArr.length-1, aim, counts));
        System.out.println("结束");
    }
    public int codeCutCoin4(int[] coins, int index, int amount, int[] counts){
        //basecase
        if (index < 0 || amount < 0){
            return 0;
        }
//        System.out.println(index + ","+ amount + "," + counts[amount] + "[---");
        if (0 == amount){
            return 1;
        }
        //避免重复计算
        /*counts[amount] = codeCutCoin4(coins, index - 1, amount, counts);
        if (amount-coins[index] >=0){
            if (0 == counts[amount-coins[index]]){
                int res = codeCutCoin4(coins, index, amount-coins[index], counts);
                counts[amount-coins[index]] = (0 == res) ? -1 :res;
            }
            counts[amount] += -1 == counts[amount-coins[index]] ? 0 : counts[amount-coins[index]];
        }*/
        /*counts[amount] = codeCutCoin4(coins, index - 1, amount, counts);
        if (amount-coins[index] >=0){
            if (0 == counts[amount-coins[index]]){
                int res = codeCutCoin4(coins, index, amount-coins[index], counts);
                counts[amount-coins[index]] = (0 == res) ? -1 :res;
            }
            counts[amount] += -1 == counts[amount-coins[index]] ? 0 : counts[amount-coins[index]];
        }*/
        counts[amount] = codeCutCoin4(coins, index - 1, amount, counts)
                + (0 == counts[amount-coins[index]] ? codeCutCoin4(coins, index, amount-coins[index], counts) : counts[amount-coins[index]]) ;
        System.out.println(index + ","+ amount + "," + counts[amount]);
        return counts[amount];
    }
    public int codeCutCoin42(int[] coins, int index, int amount, int[] counts, int[] isUpdate){
        //basecase
        if (index < 0 || amount < 0){
            return 0;
        }
        if (0 == amount){
            return 1;
        }
        //避免重复计算
        if (index >= isUpdate[amount]){
            counts[amount] =  codeCutCoin42(coins, index - 1, amount, counts, isUpdate)
                    + codeCutCoin42(coins, index, amount-coins[index], counts, isUpdate);
            isUpdate[amount] = index+1;
        }
//        System.out.println(index + ","+ amount + "," + counts[amount]);
        return counts[amount];
    }

    //动态规划
    public void solveCutCoin5(int[] coinArr, int aim){
        int[][] counts = new int[coinArr.length][aim+1];
        System.out.println(codeCutCoin5(coinArr, coinArr.length-1, aim, counts));
    }
    public int codeCutCoin5(int[] coins, int index, int amount, int[][] counts){
        //baseCase
        if (0 == amount){
            return 1;
        }
        if (index < 0 || amount < 0){
            return 0;
        }
        if (0 == counts[index][amount]){
            int res = codeCutCoin5(coins, index-1, amount, counts)
                    + codeCutCoin5(coins, index, amount - coins[index], counts);
            counts[index][amount] = (0 == res) ? -1 : res;
        }
//        System.out.println(index + ","+ amount + "," + counts[index][amount]);
        return -1 == counts[index][amount] ? 0 : counts[index][amount];
    }
}


