from typing import List


def rod_cut_no_dp(prices: List[int], length: int):
    assert prices and 1 <= length <= len(prices)
    if length == 1:
        return prices[0]
    revenues = [prices[length - 1]]
    for i in range(length - 2, 0, -1):
        revenues.append(rod_cut_no_dp(prices, i) + prices[length - 2 - i])
    return max(revenues)


def main():
    prs = [1, 5, 8, 9, 10, 17, 17, 20, 24, 30]
    for length in range(1, len(prs) + 1):
        print(rod_cut_no_dp(prs, length))
    pass


if __name__ == '__main__':
    main()
