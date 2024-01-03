
package com.whydigit.efit.entity;

import java.sql.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.whydigit.efit.dto.CreatedUpdatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String corporate_code;
	private String corporate_name;
    private String client_code;
    private String client_name;
    private String branch;
    private String employee_id;
    private String salutation;
    private String first_name;
    private String father_name;
    private Date date_of_birth;
    private String gender;
    private String marital_status;
    private String note;
    private String blood_group;
    private String work_permit_status;
    private String residential_status;
    private String currency;
    private String pay_frequency;
    private String work_unit;
    private String work_units_in_pay_period;
    private String other_field1;
    private String other_field2;
    private String active_status;
    private String profile_image_path;
    private int date_of_joining;
    private String pay_category;
    private String pf_category;
    private String tax_calculation_method;
    private String department;
    private String min_wage_category;
    private String cost_centre;
    private String designation;
    private String pt_state;
    private String location;
    private String birthplace;
    private String religion;
    private String homestate;
    private String nationality;
    private int no_of_child;
    private String country_of_origin;
    private String passport_no;
    private Date expiry_date;
    private String place_of_issue;
    private String mode_of_payment;
    private String bank_code;
    private String bank_branch_name;
    private String reimbursement_bank_name;
    private String salary_bank_account_no;
    private String bank_name;
    private String reimbursement_ifsc_code;
    private String reimbursement_account_no;
    private String address;
    private String contact_no;
    private String country;
    private String email;
    private String city;
    private String state;
    private String  esi_no;
    private String pf_no;
    private String uan_no;
    private int aadhar_no;
    private String family_pension_no;
    private String  fin_no;
    private String nric_no;
    private String pf_yn;
    private String esi_yn;
    private String pt_yn;

    @Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
