package com.agent.manage.management.company

import com.agent.manage.ConsoleReader
import com.agent.manage.database.CompanyDB.Companion.companyDB
import com.agent.manage.database.CompanyDB.Companion.updateCompanyFileDB
import com.agent.manage.database.IdolDB.Companion.idolDB
import kotlin.math.abs
import kotlin.random.Random

class CompanyManageFun {
    fun getCompanies() {
        if (companyDB.size == 0) {
            println("등록된 회사가 없습니다.")
        } else {
            for (index in companyDB.indices) {
                println("이름\t\t\t: ${companyDB[index].get("name")}")
                println("주소\t\t\t: ${companyDB[index].get("address")}")
                println("연락처\t\t: ${companyDB[index].get("callNum")}")
                val idolList = idolDB.filter {
                    it.value.company == companyDB[index].get("name")
                }.toList()
                print("아이돌 리스트\t: ")
                idolList.forEach {
                    print("${it.second.name} ")
                }
                println()
                println("-----------------------------------")
            }
        }
    }

    fun addCompany() {
        var line: String?
        line = ConsoleReader.consoleScanner()
        if (!line.isNullOrEmpty()) {
            val companyInfo = line.split(',')
            val companyHash = HashMap<String, String>()
            companyHash.put("id", "${abs(Random.nextInt())}")
            companyHash.put("name", companyInfo[0])
            companyHash.put("address", companyInfo[1])
            companyHash.put("callNum", companyInfo[2])
            var flag=false
            for (index in companyDB.indices) {
                if (companyDB[index].get("name")==companyHash.get("name")){
                    flag=true
                    break
                }
            }
            if (flag){
                println("이미 존재하는 회사명입니다.")
            }else{
                companyDB.add(companyHash)
//            println("AddCompany 결과: $companyDB")
                updateCompanyFileDB()
            }
        }
    }

    fun searchCompany() {
        var companyName: String?
        companyName = ConsoleReader.consoleScanner()
        if (!companyName.isNullOrEmpty()) {
            var flag = false
            for (index in companyDB.indices) {
                if (companyName == companyDB[index].get("name")) {
                    println("[$companyName] 검색결과")
                    println("이름\t\t\t: ${companyDB[index].get("name")}")
                    println("주소\t\t\t: ${companyDB[index].get("address")}")
                    println("연락처\t\t: ${companyDB[index].get("callNum")}")
                    val idolList = idolDB.filter {
                        it.value.company == companyDB[index].get("name")
                    }.toList()
                    print("아이돌 리스트\t: ")
                    idolList.forEach {
                        print("${it.second.name} ")
                    }
                    println()
                    flag = true
                }
            }
            if (flag == false) {
                println("존재하지 않는 회사입니다.")
            }
        }
    }

    fun updateCompany() {
        var companyName: String?
        companyName = ConsoleReader.consoleScanner()
        if (!companyName.isNullOrEmpty()) {
            var flag = false
            var idx = 0
            for (index in companyDB.indices) {
                if (companyName == companyDB[index].get("name")) {
                    idx = index
                    flag = true
                }
            }
            if (flag == false) {
                println("존재하지 않는 회사입니다.")
                return
            }
            println("회사명,주소,전화번호 형식으로 입력해주세요.")
            val newData = ConsoleReader.consoleScanner()
            if (!newData.isNullOrEmpty()) {
                val str = newData.split(",")
                companyDB[idx].replace("name", str[0])
                companyDB[idx].replace("address", str[1])
                companyDB[idx].replace("callNum", str[2])
                println("수정이 완료되었습니다.")
            }
        }
        updateCompanyFileDB()
    }

    fun deleteCompany() {
        var line: String?
        line = ConsoleReader.consoleScanner()
        println("companyManageFun deleteCompany $companyDB")
        if (!line.isNullOrEmpty()) {
            var indexDelete = -1
            for (index in companyDB.indices) {
                println(line)
                if (line.equals(companyDB[index].get("name"))) {
                    indexDelete = index
                    break
                }
            }
            if (indexDelete != -1) {
                companyDB.removeAt(indexDelete)
                println("삭제 완료!")
            } else {
                println("존재하지 않는 회사입니다.")
            }
        }
        updateCompanyFileDB()
    }
}