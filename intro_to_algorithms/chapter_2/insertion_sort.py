from typing import List


def insertion_sort_inc(arr: List[int]):
    for i in range(1, len(arr)):
        j = i
        while j > 0 and arr[j - 1] > arr[j]:
            arr[j], arr[j - 1] = arr[j - 1], arr[j]
            j -= 1


def insertion_sort_dec(arr: List[int]):
    for i in range(1, len(arr)):
        j = i
        while j > 0 and arr[j - 1] < arr[j]:
            arr[j], arr[j - 1] = arr[j - 1], arr[j]
            j -= 1


def insertion_sort_bin_search(arr: List[int]):
    result = []
    if not arr:
        return result
    else:
        result.append(arr[0])

    for i in range(1, len(arr)):
        if arr[i] <= result[0]:
            result.insert(0, arr[i])
        elif arr[i] >= result[-1]:
            result.append(arr[i])
        else:
            end = i
            start = 0
            mid = int((end + start) / 2)
            while mid - 1 >= 0 and mid < end and not (result[mid - 1] <= arr[i] <= result[mid]):
                if arr[i] > result[mid]:
                    start = mid
                else:
                    end = mid
                mid = int((end + start) / 2)
            result.insert(mid, arr[i])
    return result


def insertion_sort_rec_1(arr: List[int], end: int):
    if end - 1 == 0:
        return
    j = len(arr) - end + 1
    while j > 0 and arr[j - 1] > arr[j]:
        arr[j], arr[j - 1] = arr[j - 1], arr[j]
        j -= 1
    insertion_sort_rec_1(arr, end - 1)


def insertion_sort_rec_2(arr: List[int], element_index: int, sorted_arr: List[int]):
    if element_index == 0:
        sorted_arr.append(arr[element_index])
        return
    insertion_sort_rec_2(arr, element_index - 1, sorted_arr)
    i = 0
    while i < len(sorted_arr) and sorted_arr[i] < arr[element_index]:
        i += 1
    sorted_arr.insert(i, arr[element_index])


if __name__ == '__main__':
    test_arr = [11, 1, 9, 5, 2, 4, 6, 1, 3]
    print(insertion_sort_bin_search(test_arr))
