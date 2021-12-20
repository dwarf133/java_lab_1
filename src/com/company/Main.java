package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {

    public static boolean isSaved = false; //проверка сохранена ли первая расстановка
    public static int [][] savedField; // сохраненная растановка
    public  static int SIZE; // входной параметр N
    public static int [][] field; // поле на котором осуществляется перебор

    public static boolean tryQueen(int a, int b) // проверка возможности установить ферзя
    {
        for(int i = 0; i < a; ++i)
        {
            if(field[i][b] == 1)
            {
                return false;
            }
        }

        for(int i = 1; i <= a && b-i >= 0; ++i)
        {
            if(field[a-i][b-i] == 1)
            {
                return false;
            }
        }

        for(int i = 1; i <= a && b+i < SIZE; i++)
        {
            if(field[a-i][b+i] == 1)
            {
                return false;
            }
        }

        return true;
    }

    public static int Count(int  x){  //рекурсивный перебор
        int sum = 0;
        if(x == SIZE) {
            if(!isSaved){ // сохранения первой расстановки
                savedField = field.clone();
                for(int i=0;i<SIZE;i++)
                    savedField[i] = field[i].clone();
                isSaved = true;
            }
            return 1;
        }
        for(int i=0;i<SIZE;i++){
            if(tryQueen(x, i)){
                field[x][i]=1;
                sum += Count(x+1);
                field[x][i]=0;
            }

        }
        return sum;

    }
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            SIZE = Integer.parseInt(br.readLine());
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return;
        }
        field = new int[SIZE][];
        for(int i=0;i<SIZE;i++){
            field[i] = new int[SIZE];
            Arrays.fill(field[i], 0);
        }
        int ans = Count(0);
        System.out.println(ans);
        System.out.println("--------------------------------------------------------");
        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++)System.out.printf("%d ", savedField[i][j]);
            System.out.println();
        }
        System.out.println("--------------------------------------------------------");
    }
}
