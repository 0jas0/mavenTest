package com.jas.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test2 {
    private   List<String> calcLongSickPayroll = Arrays.asList("cTempAttendanceData");

    private  List<String> calcSalaryDaysAndWorkdaysAndStandardDays = Arrays.asList("eTempTransaction", "cTempAttendanceData");

    private   List<String> calcOverTimeFee = Arrays.asList("cTempCommonCalendar", "cTempEmployeeCalendar", "cTempAttendanceOverTimeData", "cTempCustomBasePay", "cTempAdjustmentMap", "cTempLatestAdjustmentMapForCalculateBasePay");

    private   List<String> calcAllow;

    private   List<String> calcBasePayDO = Arrays.asList("cTempCommonCalendar", "cTempEmployeeCalendar", "cTempCustomBasePay", "cTempPayNoAttendanceList", "cTempAttendancePlanSettingMap", "cTempAdjustmentMap", "cTempLatestAdjustmentMapForCalculateBasePay");

    private   List<String> calcBonus = Arrays.asList("eTempTransaction", "cTempCompanyBonusList");

    private   List<String> calcCompesationAndPaymentNotice = Arrays.asList("eTempTransaction");

    private   List<String> calcSocialAndAccumulation = Arrays.asList("cTempInsuranceData");

    private   List<String> calcAttendanceDeucTotal = Arrays.asList("cTempAttendanceDeductData");

    private   List<String> calcCostOptionItem = Arrays.asList("eTempTransaction");

    private   List<String> calcAdjustOption = Arrays.asList("eTempTransaction", "cTempAdjustOptionList");

    private   List<String> addCustomFieldValues = Arrays.asList("cTempCustomFieldValuesMap");

    private   List<String> calcaAtualSalaryReamrkAndInformalSalary = Arrays.asList("cTempActualSalaryRemarkMap");

    private   List<String> calcTotalAmountBeforeTax;

    private   List<String> calcTotalAmountBeforeTaxMinusCompensation;

    private   List<String> calcTotalAmountWithoutTax;

    private   List<String> calcTotalSalary;

    private   List<String> calcTaxableRealAmount;

    private   List<String> calcChargedTax;

    private   List<String> calcTotalAmountAfterTax = new ArrayList<>();

    private   List<String> calcTotalPayAmount;

    private   List<String> calcInsuranceCompanyAndDisabledSecurityFundAndAgencyFee = Arrays.asList("cTempInsuranceData");

    private   List<String> calcPayrollCost;

    private   List<String> calcTotalLaborCost;

    private   List<String> getAdjustmentTransaction = Arrays.asList("cTempAdjustmentMap");

    private   List<String> calcRemarkTax;

    private static  int EMPLOYEE_NUM_GET_BY_IN_RANGE_OR_COMPANYID = 1000;

    {
        calcAllow = new ArrayList<>();
        calcAllow.addAll(calcSalaryDaysAndWorkdaysAndStandardDays);

        calcTotalAmountBeforeTax = new ArrayList<>();
        calcTotalAmountBeforeTax.addAll(calcAllow);
        calcTotalAmountBeforeTax.addAll(calcBasePayDO);
        calcTotalAmountBeforeTax.addAll(calcBonus);
        calcTotalAmountBeforeTax.addAll(calcAttendanceDeucTotal);
        calcTotalAmountBeforeTax.addAll(calcCompesationAndPaymentNotice);
        calcTotalAmountBeforeTax.addAll(calcAdjustOption);
        calcTotalAmountBeforeTax.addAll(calcLongSickPayroll);
        calcTotalAmountBeforeTax.addAll(calcaAtualSalaryReamrkAndInformalSalary);

        calcTotalAmountBeforeTaxMinusCompensation = new ArrayList<>();
        calcTotalAmountBeforeTaxMinusCompensation.addAll(calcCompesationAndPaymentNotice);
        calcTotalAmountBeforeTaxMinusCompensation.addAll(calcTotalAmountBeforeTax);

        calcTotalAmountWithoutTax = new ArrayList<>();
        calcTotalAmountWithoutTax.addAll(calcAllow);
        calcTotalAmountWithoutTax.addAll(calcAdjustOption);
        calcTotalAmountWithoutTax.addAll(calcBonus);
        calcTotalAmountWithoutTax.addAll(calcBasePayDO);
        calcTotalAmountWithoutTax.addAll(calcCompesationAndPaymentNotice);

        calcTotalSalary = new ArrayList<>();
        calcTotalSalary.addAll(calcCompesationAndPaymentNotice);
        calcTotalSalary.addAll(calcTotalAmountBeforeTax);
        calcTotalSalary.addAll(calcTotalAmountWithoutTax);

        calcTaxableRealAmount = new ArrayList<>();
        calcTaxableRealAmount.addAll(calcTotalAmountBeforeTax);
        calcTaxableRealAmount.addAll(calcSocialAndAccumulation);

        calcChargedTax = Arrays.asList("eTempTransaction");
        calcChargedTax.addAll(calcSocialAndAccumulation);
        calcChargedTax.addAll(calcTotalAmountBeforeTax);
        calcChargedTax.addAll(calcCompesationAndPaymentNotice);

        calcTotalPayAmount = new ArrayList<>();
        calcTotalPayAmount.addAll(calcTotalAmountAfterTax);
        calcTotalPayAmount.addAll(calcAllow);
        calcTotalPayAmount.addAll(calcAdjustOption);
        calcTotalPayAmount.addAll(calcBonus);
        calcTotalPayAmount.addAll(calcBasePayDO);
        calcTotalPayAmount.addAll(calcaAtualSalaryReamrkAndInformalSalary);

        calcPayrollCost = new ArrayList<>();
        calcPayrollCost.addAll(calcTotalPayAmount);
        calcPayrollCost.addAll(calcChargedTax);
        calcPayrollCost.addAll(calcSocialAndAccumulation);
        calcPayrollCost.addAll(calcInsuranceCompanyAndDisabledSecurityFundAndAgencyFee);

        calcTotalLaborCost = new ArrayList<>();
        calcTotalLaborCost.addAll(calcTotalPayAmount);
        calcTotalLaborCost.addAll(calcChargedTax);
        calcTotalLaborCost.addAll(calcSocialAndAccumulation);
        calcTotalLaborCost.addAll(calcInsuranceCompanyAndDisabledSecurityFundAndAgencyFee);
        calcTotalLaborCost.addAll(calcCostOptionItem);

        calcRemarkTax = Arrays.asList("eTempTransaction");
        calcRemarkTax.addAll(calcCompesationAndPaymentNotice);
    }

    public static void main(String[] args) {
        new test2();
    }
}
