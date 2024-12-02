@main
struct Day1 {
    static var reports: [[Int]] = []

    static func isValid1(report: [Int]) -> Bool {
        let sortedReport = report.sorted()
        if sortedReport != report && sortedReport.reversed() != report {
            return false
        }
        for i in 1..<report.count {
            let dist = abs(report[i] - report[i - 1])
            if dist == 0 || dist > 3 {
                return false
            }
        }
        return true
    }

    static func isValid2(report: [Int]) -> Bool {
        var isValid = isValid1(report: report)

        for i in 0..<report.count {
            isValid = isValid || isValid1(
                report:
                    Array(report[0..<i] + report[i + 1..<report.count]))
        }

        return isValid
    }

    static func main() {
        while let line = readLine() {
            let numbers = line.split(separator: " ").compactMap { Int($0) }
            reports.append(numbers)
        }

        print(reports.count { isValid1(report: $0) })
        print(reports.count { isValid2(report: $0) })
    }
}
