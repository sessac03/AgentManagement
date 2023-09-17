package com.agent.manage.main

import com.agent.manage.ConsoleReader
import com.agent.manage.database.CompanyDB.Companion.getCompanyFileDB
import com.agent.manage.database.EventDB.Companion.getEventFileDB
import com.agent.manage.database.IdolDB.Companion.getIdolFileDB
import com.agent.manage.management.company.showCompanyMenu
import com.agent.manage.management.idol.showIdolMenu
import com.agent.manage.management.event.showEventMenu

fun startManage() {
    getCompanyFileDB()
    getIdolFileDB()
    getEventFileDB()
    do {
        println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
        println("전체 메뉴:    1. 회사 관리    2. 아이돌 관리   3. 행사관리    4. 종료 또는 엔터를 치세요.")
        println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
        var line: String?
        line = ConsoleReader.consoleScanner()
        if (line == "") {
            println("프로그램이 종료되었습니다.")
            break
        } else {
            try {
                val menu = line.trim().toInt()
                when (menu) {
                    // 회사 관리
                    1 -> {
                        showCompanyMenu()
                    }
                    // 아이돌 관리
                    2 -> {
                        showIdolMenu()
                    }
                    // 행사 관리
                    3 -> {
                        showEventMenu()
                    }
                    // 프로그램 종료
                    4 -> {
                        println("프로그램이 종료되었습니다.")
                        break
                    }
                    else -> {
                        println("유효하지 않은 메뉴입니다.")
                    }
                }
            }catch (nfe: NumberFormatException){
                println("숫자로 입력해주세요.")
            }
        }
    } while (true)
}

fun main() {
    startManage()
}