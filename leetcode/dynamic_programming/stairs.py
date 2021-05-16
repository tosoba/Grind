class ClimbStairsSolution:
    def __init__(self) -> None:
        super().__init__()
        self.memo = []

    def climb_stairs(self, n: int) -> int:
        self.memo = [-1] * ((n + 1) if n == 1 else n)
        self.memo[0] = 1
        self.memo[1] = 2
        return self.__climb_stairs_helper(n)

    def __climb_stairs_helper(self, n: int) -> int:
        assert 45 >= n >= 1
        if self.memo[n - 1] != -1:
            return self.memo[n - 1]
        ways_minus_1 = self.memo[n - 2] if self.memo[n - 2] != -1 else self.__climb_stairs_helper(n - 1)
        ways_minus_2 = self.memo[n - 3] if self.memo[n - 3] != -1 else self.__climb_stairs_helper(n - 2)
        self.memo[n - 1] = ways_minus_1 + ways_minus_2
        return self.memo[n - 1]


if __name__ == '__main__':
    print(ClimbStairsSolution().climb_stairs(45))
