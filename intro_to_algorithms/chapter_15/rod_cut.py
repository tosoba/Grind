from typing import List


def rod_cut_no_dp(prices: List[int], length: int) -> int:
    assert prices and 1 <= length <= len(prices)
    if length == 1:
        return prices[0]
    revenues = [prices[length - 1]]
    for i in range(length - 2, -1, -1):
        revenues.append(rod_cut_no_dp(prices, i + 1) + prices[length - 2 - i])
    return max(revenues)


def rod_cut_with_cut_price_no_dp(prices: List[int], length: int, cut_price: int) -> int:
    assert prices and 1 <= length <= len(prices)
    if length == 1:
        return prices[0]
    revenues = [prices[length - 1]]
    for i in range(length - 2, -1, -1):
        revenues.append(rod_cut_no_dp(prices, i + 1) + prices[length - 2 - i] - cut_price)
    return max(revenues)


def rod_cut_memo(prices: List[int], length: int) -> int:
    assert prices and 1 <= length <= len(prices)
    if length == 1:
        return prices[0]

    memo: List[int] = [-1] * len(prices)
    memo[0] = prices[0]

    def rod_cut(current_length: int) -> int:
        if memo[current_length - 1] != -1:
            return memo[current_length - 1]
        revenues = [prices[length - 1]]
        for i in range(length - 2, -1, -1):
            revenues.append(rod_cut_no_dp(prices, i + 1) + prices[length - 2 - i])
        max_revenue = max(revenues)
        memo[current_length - 1] = max_revenue
        return max_revenue

    return rod_cut(length)


def rod_cut_bottom_up(prices: List[int], length: int) -> int:
    assert prices and 1 <= length <= len(prices)
    if length == 1:
        return prices[0]

    memo: List[int] = [-1] * length
    memo[0] = prices[0]

    def rod_cut(current_length: int):
        if memo[current_length - 1] != -1:
            return memo[current_length - 1]
        revenues = [prices[length - 1]]
        for i in range(length - 2, -1, -1):
            revenues.append(rod_cut_no_dp(prices, i + 1) + prices[length - 2 - i])
        max_revenue = max(revenues)
        memo[current_length - 1] = max_revenue

    for j in range(1, length + 1):
        rod_cut(j)
    return memo[-1]


def rod_cut_with_cut_price(prices: List[int], length: int, cut_price: int) -> int:
    assert prices and 1 <= length <= len(prices)
    if length == 1:
        return prices[0]

    memo: List[int] = [-1] * length
    memo[0] = prices[0]

    def rod_cut(current_length: int) -> int:
        if memo[current_length - 1] != -1:
            return memo[current_length - 1]
        revenues = [prices[length - 1]]
        for i in range(length - 2, -1, -1):
            revenues.append(rod_cut_no_dp(prices, i + 1) + prices[length - 2 - i] - cut_price)
        max_revenue = max(revenues)
        memo[current_length - 1] = max_revenue
        return max_revenue

    return rod_cut(length)


def main():
    prices = [1, 5, 8, 9, 10, 17, 17, 20, 24, 30]
    for length in range(1, len(prices) + 1):
        # print(rod_cut_no_dp(prices, length))
        # print(rod_cut_memo(prices, length))
        # print(rod_cut_bottom_up(prices, length))
        # print(rod_cut_with_cut_price(prices, length, 1))
        print(f'{rod_cut_memo(prices, length)} - {rod_cut_with_cut_price(prices, length, cut_price=1)}')

    pass


if __name__ == '__main__':
    main()
