package com.agent.manage.management.event

import com.agent.manage.ConsoleReader

fun showEventMenu() {
    val eventManage = EventManageFun()

    do {
        println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
        println("이벤트 관리 메뉴:    1. 이벤트 조회    2. 이벤트 등록    3. 이벤트 검색    4. 이벤트 수정    5. 이벤트 삭제    6. 뒤로가기")
        println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
        var line: String?
        line = ConsoleReader.consoleScanner()
        if (line == "") {
            println("값을 입력해주세요.")
        } else {
            try {
                val menu = line.trim().toInt()
                when (menu) {
                    // 이벤트 조회
                    1 -> {
                        println("[조회 결과]")
                        println("-----------------------------------")
                        eventManage.getEvents()
                        break
                    }
                    // 이벤트 등록
                    2 -> {
                        println("행사명,행사 날짜(0000-00-00),캐스팅된 아이돌 형식으로 입력해주세요.")
                        eventManage.addEvent()
                        break
                    }
                    // 이벤트 검색
                    3 -> {
                        print("검색할 이벤트명을 입력해주세요: ")
                        eventManage.searchEvent()
                        break
                    }
                    // 이벤트 수정
                    4 -> {
                        print("수정할 이벤트명을 입력해주세요: ")
                        eventManage.updateEvent()
                        break
                    }
                    // 이벤트 삭제
                    5 -> {
                        print("삭제할 이벤트명을 입력해주세요: ")
                        eventManage.deleteEvent()
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