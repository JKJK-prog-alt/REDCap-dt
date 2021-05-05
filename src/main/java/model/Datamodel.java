package model;

public class Datamodel {


	
	private String record_id;
	private String gender;
	private String hepatitis_b;
	private String bilirubin_concentration;
	private String asa_stage;
	private String child_pugh;
	private String cm_stage;
	private String form_1_complete;
	
	
		public Datamodel(String record_id,String gender,String hepatitis_b,String bilirubin_concentration, String asa_stage, String child_pugh,String cm_stage, String form_1_complete) {
			
			this.setRecord_id(record_id);
			this.setGender(gender);
			this.setHepatitis_b(hepatitis_b);
			this.setBilirubin_concentration(bilirubin_concentration);
			this.setAsa_stage(asa_stage);
			this.setChild_pugh(child_pugh);
			this.setCm_stage(cm_stage);
			this.setForm_1_complete(form_1_complete);
			
				
		}
		
		public  Datamodel() {
			
		}

		public String getRecord_id() {
			return record_id;
		}

		public void setRecord_id(String record_id) {
			this.record_id = record_id;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getHepatitis_b() {
			return hepatitis_b;
		}

		public void setHepatitis_b(String hepatitis_b) {
			this.hepatitis_b = hepatitis_b;
		}

		public String getBilirubin_concentration() {
			return bilirubin_concentration;
		}

		public void setBilirubin_concentration(String bilirubin_concentration) {
			this.bilirubin_concentration = bilirubin_concentration;
		}

		public String getAsa_stage() {
			return asa_stage;
		}

		public void setAsa_stage(String asa_stage) {
			this.asa_stage = asa_stage;
		}

		public String getChild_pugh() {
			return child_pugh;
		}

		public void setChild_pugh(String child_pugh) {
			this.child_pugh = child_pugh;
		}

		public String getCm_stage() {
			return cm_stage;
		}

		public void setCm_stage(String cm_stage) {
			this.cm_stage = cm_stage;
		}

		public String getForm_1_complete() {
			return form_1_complete;
		}

		public void setForm_1_complete(String form_1_complete) {
			this.form_1_complete = form_1_complete;
		}
	
		


}
