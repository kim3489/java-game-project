#include <iostream>
#include <vector>

// 삽입 정렬
void insertionSort(std::vector<int>& arr) {
    // TODO: 삽입 정렬 알고리즘을 구현하세요.
    int n = arr.size();
    for (int i = 1; i < n; i++) {
        int x = arr[i];
        int j = i - 1;
        while (j >= 0 && arr[j] > x) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = x;
    }
}

// 선택 정렬
void selectionSort(std::vector<int>& arr) {
    // TODO: 선택 정렬 알고리즘을 구현하세요.
    int n = arr.size(); //배열 크기 측정
    int temp;
    for (int i = 0; i < n - 1; i++) {
        int min = i;    //최소값 지정
        for (int j = i + 1; j < n; j++) {
            if (arr[j] < arr[min]) {
                min = j;    //다른 값이 min값 보다 작을 경우 min값 변경
            }
        }
        if (min != i) { //min과 i의 배열 값 교환
            temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }
}