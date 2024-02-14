package com.qa.test

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable


public class custom_keyword {

	@Keyword
	def validateAppointmentDate(String date, String month, String year) {

		def invalid = false
		def lastDate
		def monthNum
		switch(month) {
			case 'Jan': lastDate = 31
				monthNum = "01"
				break
			case 'Feb': lastDate = 29
				monthNum = "02"
				break
			case 'Mar':lastDate = 31
				monthNum = "03"
				break
			case 'Apr':lastDate = 30
				monthNum = "04"
				break
			case 'May':lastDate = 31
				monthNum = "05"
				break
			case 'Jun':lastDate = 30
				monthNum = "06"
				break
			case 'Jul':lastDate = 31
				monthNum = "07"
				break
			case 'Aug':lastDate = 31
				monthNum = "08"
				break
			case 'Sep':lastDate = 30
				monthNum = "09"
				break
			case 'Oct':lastDate = 31
				monthNum = "10"
				break
			case 'Nov': lastDate = 30
				monthNum = "11"
				break
			default:lastDate = 31
				monthNum = "12"
		}
		int dt = Integer.parseInt(date)
		if(dt == 0 || dt > lastDate)
			invalid = true
		if(invalid) {
			//			WebUI.verifyElementNotPresent(findTestObject('CURA_OR/Page_CURA Healthcare Service/yearObject', [('year_value') : year]), 0)
			//			WebUI.verifyElementNotPresent(findTestObject('CURA_OR/Page_CURA Healthcare Service/monthObject', [('month_value') : month]), 0)
			WebUI.click(findTestObject('CURA_OR/Page_CURA Healthcare Service/yearObject', [('year_value') : year]))
			WebUI.click(findTestObject('CURA_OR/Page_CURA Healthcare Service/monthObject', [('month_value') : month]))
			WebUI.verifyElementNotPresent(findTestObject('CURA_OR/Page_CURA Healthcare Service/dateObject', [('date_value') : date]), 0)
		}
		else {
			WebUI.verifyElementPresent(findTestObject('CURA_OR/Page_CURA Healthcare Service/yearObject', [('year_value') : year]), 0)
			WebUI.click(findTestObject('CURA_OR/Page_CURA Healthcare Service/yearObject', [('year_value') : year]))
			WebUI.verifyElementPresent(findTestObject('CURA_OR/Page_CURA Healthcare Service/monthObject', [('month_value') : month]), 0)
			WebUI.click(findTestObject('CURA_OR/Page_CURA Healthcare Service/monthObject', [('month_value') : month]))
			WebUI.verifyElementPresent(findTestObject('CURA_OR/Page_CURA Healthcare Service/dateObject', [('date_value') : date]), 0)
			WebUI.click(findTestObject('CURA_OR/Page_CURA Healthcare Service/dateObject', [('date_value') : date]))
			WebUI.click(findTestObject('Object Repository/CURA_OR/Page_CURA Healthcare Service/button_Book Appointment'))
			WebUI.verifyElementPresent(findTestObject('Object Repository/CURA_OR/Page_CURA Healthcare Service/h2_Appointment Confirmation'), 0)
			String appointmentDate = String.format("%02d/%s/%s", dt, monthNum, year)
			WebUI.verifyElementText(findTestObject('Object Repository/CURA_OR/Page_CURA Healthcare Service/p_20022024'), appointmentDate)
		}
	}
}
