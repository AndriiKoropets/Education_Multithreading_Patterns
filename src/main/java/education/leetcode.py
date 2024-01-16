from typing import List, Optional


class Solution:
    def findDisappearedNumbers(self, nums: List[int]) -> List[int]:
        result = list()
        zeros = [0]*len(nums)
        nums.sort()

        for i in range(len(nums)):
            if nums[i] != -1:
                zeros[nums[i] - 1] = -1
        for i in range(len(zeros)):
            if zeros[i] != -1:
                result.append(i+1)

        return result

    def fizzBuzz(self, n: int) -> List[str]:
        result = [str(x) for x in range(1, n + 1)]
        for i in range(len(result)):
            num = i + 1
            if num % 3 == 0 and num % 5 == 0:
                result[i] = 'FizzBuzz'
            elif num % 3 == 0 and num % 5 != 0:
                result[i] = 'Fizz'
            elif num % 3 != 0 and num % 5 == 0:
                result[i] = 'Buzz'
        return result

    def getConcatenation(self, nums: List[int]) -> List[int]:
        length = len(nums)*2
        result = list()

        for i in range(length):
            result.append(nums[i%len(nums)])

        return result

    def buildArray(self, nums: List[int]) -> List[int]:
        temp = -1
        # for i in range(len(nums)):
        #     loop = nums[i]
        #     while true:
        #         if nums[i] != nums[nums[i]]:
        #             temp = nums[i]
        #             nums[i] = nums[nums[i]]
        #
        #     if nums[i] != nums[nums[i]]:
        #         temp = nums[i]
        #         nums[i] = nums[nums[i]]
        #
        # while true:
        #
        #     i++
        return nums

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right



if __name__ == '__main__':
    solution = Solution()
    print(solution.getConcatenation([5,4,6,7,9,3,10,9,5,6]))

    print([5,4,6,7,9,3,10,9,5,6]*2)
    # print(solution.fizzBuzz(15))