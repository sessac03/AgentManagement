package com.agent.manage.management.idol

import com.agent.manage.ConsoleReader
import com.agent.manage.data.IdolGroup
import com.agent.manage.database.CompanyDB.Companion.companyDB
import com.agent.manage.database.CompanyDB.Companion.updateCompanyFileDB
import com.agent.manage.database.IdolDB.Companion.idolDB
import com.agent.manage.database.IdolDB.Companion.updateIdolFileDB
import kotlin.math.abs
import kotlin.random.Random

class IdolManageFun {
    fun getIdol() {
        if (idolDB.size == 0) {
            println("등록된 아이돌이 없습니다.")
        } else {
            for (idol in idolDB) {
                println("소속사: ${idol.value.company}")
                println("그룹명: ${idol.value.name}")
                println("멤버수: ${idol.value.count}")
                print("아이돌 리스트: ")
                idol.value.members?.forEach {
                    print("${it} ")
                }
                //TODO 행사리스트
                println()
                println("-----------------------------------")
            }
        }
    }

    fun addIdol() {
        var line: String?
        line = ConsoleReader.consoleScanner()
        if (!line.isNullOrEmpty()) {
            val str = line.split(',')
            val id = 122 //TODO id 값 자동으로 생성해주는 함수 만들기
            val members = str.subList(3, str.size)
            val data = IdolGroup(str[0], str[1], str[2].toInt(), members)
            val dupIdol = idolDB.filter {
                it.value.name == data.name
            }
            if (dupIdol.isEmpty()) {
                idolDB.put(id, data)
                println("등록이 완료되었습니다.")
//            println("AddIdol 결과: $idolDB")
                updateIdolFileDB()
            } else {
                println("이미 존재하는 아이돌입니다.")
            }
        }
    }

    fun searchIdol() {
        var idolName: String?
        idolName = ConsoleReader.consoleScanner()
        if (!idolName.isNullOrEmpty()) {
            var flag = false
            for (idol in idolDB) {
                if (idolName.equals(idol.value.name)) {
                    flag = true
                    println("[$idolName] 검색결과")
                    println("소속사: ${idol.value.company}")
                    println("그룹명: ${idol.value.name}")
                    println("멤버수: ${idol.value.count}")
                    print("아이돌 리스트: ")
                    idol.value.members?.forEach {
                        print("${it} ")
                    }
                    //TODO 행사리스트
                    break
                }
            }
            println()

            if (flag == false) {
                println("존재하지 않는 회사입니다.")
            }
        }
    }

    fun updateIdol() {
        var idolName: String?
        idolName = ConsoleReader.consoleScanner()
        if (!idolName.isNullOrEmpty()) {
            var flag = false
            var idolKey = 0
            for (idol in idolDB) {
                if (idolName.equals(idol.value.name)) {
                    flag = true
                    idolKey = idol.key
                    break
                }
            }
            if (flag == false) {
                println("존재하지 않는 그룹명입니다.")
                return
            }

            println("회사명,그룹명,멤버수,멤버 형식으로 입력해주세요.")
            val newData = ConsoleReader.consoleScanner()
            if (!newData.isNullOrEmpty()) {
                val str = newData.split(',')
                val members = str.subList(3, str.size)
                val data = IdolGroup(str[0], str[1], str[2].toInt(), members)
                idolDB.put(idolKey, data)
                println("수정이 완료되었습니다.")
//                println("AddIdol 결과: $idolDB")
            }
        }
        updateIdolFileDB()
    }

    fun deleteIdol() {
        var IdolName: String?
        IdolName = ConsoleReader.consoleScanner()
        if (!IdolName.isNullOrEmpty()) {
            var flag = false
            for (idol in idolDB) {
                if (IdolName.equals(idol.value.name)) {
                    flag = true
                    idolDB.remove(idol.key)
                    break
                }
            }
            if (flag) {
                println("삭제 완료!")
            } else {
                println("존재하지 않는 회사입니다.")
            }
        }
        updateIdolFileDB()
    }
}