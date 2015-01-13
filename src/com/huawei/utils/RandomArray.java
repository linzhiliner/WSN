package com.huawei.utils;
/**
 * @随机数组
 * 生成一个由大于零的随机整数组成的数组，且数组中没有重复的值。
 * 
 * @author 
 * @email 
 */
import java.util.Arrays;
import java.util.Random;

public class RandomArray {

 /*
  * 测试代码
  */
 public static void main(String[] args) {
  
  System.out.println(Arrays.toString(RandomArray.getRandomArray(2,10)));
 }

 /**
  * 将新获得的随机数与已产生的其它随机数相比较，若有重复数据，则丢弃，并重来一遍；
  * 否则，将新数存入数组。
  * 
  * @param i 数组的大小
  * @return 随机数组
  */
 public static int[] getRandomArray(int i,int size) {
  int[] a = new int[i]; // a 随机数数组
  for (int m = 0; m < i; m++) { // m 已产生的随机数个数
   int temp = random(size);
   if (m == 0)
    a[0] = temp;
   else {
    for (int n = 0; n < m; n++) { // n 遍历已产生的随机数
     if (temp == a[n]) {
      temp = random(size);
      n = -1;
     }
    }
    a[m] = temp;
   }
  }
  return a;
 }

 /**
  * 随机数发生器
  * 0 <= Math.random() < 1
  * 
  * @return 1至10之间的随机整数
  */
 public static int random(int size) {
	 Random random = new Random();
	 return random.nextInt(size)+1;
 }

}