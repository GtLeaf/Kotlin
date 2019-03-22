package interviewer;

public class DynamicProgramming {
    public static void main(String[] args){
        int[] v = {0, 2, 4, 3, 7};
        int[] w = {0, 2, 3, 5, 5};
        int[] p = new int[]{0,0,0,0,0,0,0,0,0,0};
        Backpack01 backpack01 = new Backpack01();
        System.out.println(backpack01.knapsack1(w, v, w.length-1, 10));

    }
}

class Backpack01{

    public int solutionBackPack01(int[] w, int[] v, int pack[], int ni, int index){
        //检索完所有物品 或 背包没有空间
        if (ni>=w.length || (pack.length-1-index) < w[ni] ){
            return pack[index];
        }

        if ((pack.length-1-index) >= w[ni]){
            for (int i=1; i<w[ni]-1; i++){
                pack[index+i] = pack[index+i-1];
            }
            pack[index+w[ni]] = pack[index]+v[ni];
            return Math.max(solutionBackPack01(w, v, pack, ni+1, index+w[ni]),solutionBackPack01(w, v, pack, ni+1, index));
        }else {
            return solutionBackPack01(w, v, pack, ni+1, index);
        }

    }

    public int knapsack1(int[] w, int[] v, int ni, int pack){
        //边界处理
        return ks(w, v,ni,pack);
    }

    private int ks(int[] w, int[] v, int ni, int pack){
        if (ni<0){
            return 0;
        }
        if (pack >= w[ni]){
            return Math.max(ks(w, v, ni-1, pack), ks(w, v, ni-1, pack-w[ni])+v[ni]);
        }else {
            return ks(w, v, ni-1, pack);
        }
    }
}