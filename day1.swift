@main
struct Day1 {
    static var firstColumn: [Int] = []
    static var secondColumn: [Int] = []

    static func main() {
        while let line = readLine() {
            let numbers = line.split(separator: " ").compactMap { Int($0) }
            firstColumn.append(numbers[0])
            secondColumn.append(numbers[1])
        }

        let lines = firstColumn.count
        var total2 = 0
        for num in firstColumn {
            total2 += num * secondColumn.count { $0 == num }
        }

        var total1 = 0
        for _ in 0..<lines {
            let firstColumnMin = firstColumn.min()!
            let firstColumnMinIndex =
                firstColumn.firstIndex(of: firstColumnMin)!
            let secondColumnMin = secondColumn.min()!
            let secondColumnMinIndex =
                secondColumn.firstIndex(of: secondColumnMin)!

            total1 += abs(firstColumnMin - secondColumnMin)

            firstColumn[firstColumnMinIndex] = Int.max
            secondColumn[secondColumnMinIndex] = Int.max
        }

        print(total1)
        print(total2)
    }
}
