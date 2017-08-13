package DTO;

/*
 * This class represents the OrganDonorDTO
 * The properties of OrganDonorDTO are
 * 		1. organDonationId
 * 		2. organName
 * 		3. organDonater
 * 		4. mobile
 * 		5. email
 * 		6. address
 */
public class OrganDonorDTO {
	public int organDonationId;
	public String organName;
	public String organDonater;
	public String mobile;
	public String email;
	public String address;
	public int getOrganDonationId() {
		return organDonationId;
	}
	public void setOrganDonationId(int organDonationId) {
		this.organDonationId = organDonationId;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public String getOrganDonater() {
		return organDonater;
	}
	public void setOrganDonater(String organDonater) {
		this.organDonater = organDonater;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
