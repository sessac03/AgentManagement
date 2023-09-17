package com.agent.manage.database

import java.io.*

class CompanyDB {
    companion object {
        val companyDB = ArrayList<HashMap<String, String>>()

        val file = File("./managementfile/CompanyFile.dat")
        fun getCompanyFileDB(){
            BufferedReader(FileReader(file)).use { br ->
                br.lines().forEach {
                    val str = it.split(",")
                    val hashMap=HashMap<String, String>()
                    hashMap.put("name",str[0])
                    hashMap.put("address",str[1])
                    hashMap.put("callNum",str[2])
                    companyDB.add(hashMap)
                }
            }
        }

        fun updateCompanyFileDB() {
            println("CompanyDB updateCompanyFileDB $companyDB")
            var fileOut = BufferedWriter(FileWriter("./managementfile/CompanyFile.dat"))
            with(fileOut) {
                for (index in companyDB.indices) {
                    write("${companyDB[index].get("name")},${companyDB[index].get("address")},${companyDB[index].get("callNum")}\n")
                    flush()
                }
                close()
            }
        }
    }
}