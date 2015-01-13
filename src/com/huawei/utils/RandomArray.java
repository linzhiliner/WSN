package com.huawei.utils;
/**
 * @�������
 * ����һ���ɴ���������������ɵ����飬��������û���ظ���ֵ��
 * 
 * @author 
 * @email 
 */
import java.util.Arrays;
import java.util.Random;

public class RandomArray {

 /*
  * ���Դ���
  */
 public static void main(String[] args) {
  
  System.out.println(Arrays.toString(RandomArray.getRandomArray(2,10)));
 }

 /**
  * ���»�õ���������Ѳ����������������Ƚϣ������ظ����ݣ�������������һ�飻
  * ���򣬽������������顣
  * 
  * @param i ����Ĵ�С
  * @return �������
  */
 public static int[] getRandomArray(int i,int size) {
  int[] a = new int[i]; // a ���������
  for (int m = 0; m < i; m++) { // m �Ѳ��������������
   int temp = random(size);
   if (m == 0)
    a[0] = temp;
   else {
    for (int n = 0; n < m; n++) { // n �����Ѳ����������
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
  * �����������
  * 0 <= Math.random() < 1
  * 
  * @return 1��10֮����������
  */
 public static int random(int size) {
	 Random random = new Random();
	 return random.nextInt(size)+1;
 }

}