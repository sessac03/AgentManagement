package com.agent.manage.management.idol

import com.agent.manage.ConsoleReader
import com.agent.manage.management.company.CompanyManageFun

fun showIdolMenu() {
    val idolManage = IdolManageFun()

    do {
        println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
        println("아이돌 관리 메뉴:    1. 아이돌 조회    2. 아이돌 등록    3. 아이돌 검색    4. 아이돌 수정    5. 아이돌 삭제    6. 뒤로가기")
        println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
        var line: String?
        line = ConsoleReader.consoleScanner()
        if (line == "") {
            println("값을 입력해주세요.")
        } else {
            try {
                val menu = line.trim().toInt()
                when (menu) {
                    // 아이돌 조회
                    1 -> {
                        println("[조회 결과]")
                        println("-----------------------------------")
                        idolManage.getIdol()
                        break
                    }
                    // 아이돌 등록
                    2 -> {
                        println("회사명,그룹명,멤버수,멤버 형식으로 입력해주세요.")
                        idolManage.addIdol()
                        break
                    }
                    // 아이돌 검색
                    3 -> {
                        print("그룹명을 입력해주세요: ")
                        idolManage.searchIdol()
                        break
                    }
                    // 아이돌 수정
                    4 -> {
                        print("수정할 그룹명을 입력해주세요: ")
                        idolManage.updateIdol()
                        break
                    }
                    // 아이돌 삭제
                    5 -> {
                        print("삭제할 그룹명을 입력해주세요: ")
                        idolManage.deleteIdol()
                        break
                    }
                    // 뒤로가기
                    6 -> break
                    // 그 외
                    else -> {
                        println("유효하지 않은 메뉴입니다.")
                    }
                }
            } catch (nfe: NumberFormatException) {
                println("숫자로 입력해주세요.")
            }
        }
    } while (true)
}